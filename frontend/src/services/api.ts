import axios from 'axios';
import { LogEntry } from '../types/Log';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
});

export const logService = {
  getAllLogs: async (): Promise<LogEntry[]> => {
    const response = await api.get('/logs');
    return response.data;
  },

  searchLogs: async (params: {
    serviceName?: string;
    logLevel?: string;
    message?: string;
    startTime?: string;
    endTime?: string;
  }): Promise<LogEntry[]> => {
    const response = await api.get('/logs/search', { params });
    return response.data;
  },

  uploadLogs: async (logs: LogEntry[]): Promise<LogEntry[]> => {
    const response = await api.post('/logs/upload', logs);
    return response.data;
  },
};

export default api;
