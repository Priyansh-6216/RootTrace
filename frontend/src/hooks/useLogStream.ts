import { useState, useEffect, useCallback } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import type { LogEntry } from '../types/Log';

const WEBSOCKET_URL = 'http://localhost:8080/ws-roottrace';

export const useLogStream = () => {
  const [liveLogs, setLiveLogs] = useState<LogEntry[]>([]);
  const [isConnected, setIsConnected] = useState(false);
  const [stompClient, setStompClient] = useState<Client | null>(null);

  const connect = useCallback(() => {
    const client = new Client({
      webSocketFactory: () => new SockJS(WEBSOCKET_URL),
      debug: (str) => {
        console.debug('STOMP: ', str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    client.onConnect = (frame) => {
      setIsConnected(true);
      console.log('Connected to WebSocket: ' + frame);
      
      client.subscribe('/topic/logs', (message) => {
        if (message.body) {
          const logEntry: LogEntry = JSON.parse(message.body);
          setLiveLogs((prevLogs) => [logEntry, ...prevLogs].slice(0, 1000)); // Keep last 1000 logs
        }
      });
    };

    client.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message']);
      console.error('Additional details: ' + frame.body);
    };

    client.onWebSocketClose = () => {
      setIsConnected(false);
      console.log('WebSocket connection closed');
    };

    client.activate();
    setStompClient(client);

    return () => {
      client.deactivate();
    };
  }, []);

  const disconnect = useCallback(() => {
    if (stompClient) {
      stompClient.deactivate();
      setIsConnected(false);
    }
  }, [stompClient]);

  useEffect(() => {
    return () => {
      disconnect();
    };
  }, [disconnect]);

  const clearLiveLogs = () => {
    setLiveLogs([]);
  };

  return { liveLogs, isConnected, connect, disconnect, clearLiveLogs };
};
