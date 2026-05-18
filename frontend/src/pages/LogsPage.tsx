import { useState, useEffect } from 'react';
import { Search, Filter, Download, RefreshCcw, AlertCircle, Info, AlertTriangle, Play, Pause } from 'lucide-react';
import { logService } from '../services/api';
import type { LogEntry } from '../types/Log';
import { useLogStream } from '../hooks/useLogStream';

const LogsPage = () => {
  const [logs, setLogs] = useState<LogEntry[]>([]);
  const [loading, setLoading] = useState(true);
  const [isLive, setIsLive] = useState(false);
  const { liveLogs, connect, disconnect, clearLiveLogs } = useLogStream();
  const [searchParams, setSearchParams] = useState({
    serviceName: '',
    logLevel: '',
    message: '',
  });

  const fetchLogs = async () => {
    setLoading(true);
    try {
      const data = await logService.searchLogs(searchParams);
      setLogs(data);
    } catch (error) {
      console.error('Error fetching logs:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (!isLive) {
      fetchLogs();
    }
  }, [isLive]);

  const toggleLiveStream = () => {
    if (isLive) {
      disconnect();
      setIsLive(false);
    } else {
      clearLiveLogs();
      connect();
      setIsLive(true);
    }
  };

  const getLevelColor = (level: string) => {
    switch (level.toUpperCase()) {
      case 'ERROR': return 'text-red-400 bg-red-400/10 border-red-400/20';
      case 'WARN': return 'text-amber-400 bg-amber-400/10 border-amber-400/20';
      case 'INFO': return 'text-emerald-400 bg-emerald-400/10 border-emerald-400/20';
      default: return 'text-slate-400 bg-slate-400/10 border-slate-400/20';
    }
  };

  const getLevelIcon = (level: string) => {
    switch (level.toUpperCase()) {
      case 'ERROR': return <AlertCircle size={14} />;
      case 'WARN': return <AlertTriangle size={14} />;
      case 'INFO': return <Info size={14} />;
      default: return <Info size={14} />;
    }
  };

  const formatTimestamp = (timestamp: string) => {
    try {
      const date = new Date(timestamp);
      return date.toLocaleTimeString() + '.' + date.getMilliseconds().toString().padStart(3, '0');
    } catch (e) {
      return timestamp;
    }
  };

  const displayedLogs = isLive ? liveLogs : logs;

  return (
    <div className="p-8 space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-3xl font-bold text-white tracking-tight flex items-center gap-3">
            Log Explorer
            {isLive && (
              <span className="flex items-center gap-1.5 px-2.5 py-1 rounded-full bg-emerald-500/10 text-emerald-400 text-xs font-medium border border-emerald-500/20">
                <span className="relative flex h-2 w-2">
                  <span className="animate-ping absolute inline-flex h-full w-full rounded-full bg-emerald-400 opacity-75"></span>
                  <span className="relative inline-flex rounded-full h-2 w-2 bg-emerald-500"></span>
                </span>
                Live
              </span>
            )}
          </h2>
          <p className="text-slate-400 mt-1">Search and analyze logs across all your microservices.</p>
        </div>
        <div className="flex gap-3">
          <button 
            onClick={toggleLiveStream}
            className={`glass-button flex items-center gap-2 ${isLive ? 'bg-emerald-600/20 text-emerald-400 border-emerald-500/30' : ''}`}
          >
            {isLive ? <Pause size={18} /> : <Play size={18} />}
            {isLive ? 'Pause Stream' : 'Live Stream'}
          </button>
          {!isLive && (
            <button 
              onClick={fetchLogs}
              className="glass-button flex items-center gap-2"
            >
              <RefreshCcw size={18} className={loading ? 'animate-spin' : ''} />
              Refresh
            </button>
          )}
          <button className="glass-button flex items-center gap-2 bg-indigo-600/20 text-indigo-400 border-indigo-500/30">
            <Download size={18} />
            Export
          </button>
        </div>
      </div>

      {!isLive && (
        <div className="grid grid-cols-1 lg:grid-cols-4 gap-4">
          <div className="lg:col-span-3 glass-card p-4 flex items-center gap-3">
            <Search className="text-slate-500" size={20} />
            <input 
              type="text" 
              placeholder="Search logs message..."
              className="bg-transparent border-none outline-none text-slate-200 w-full"
              value={searchParams.message}
              onChange={(e) => setSearchParams({ ...searchParams, message: e.target.value })}
              onKeyDown={(e) => e.key === 'Enter' && fetchLogs()}
            />
          </div>
          <div className="glass-card p-4 flex items-center gap-3">
            <Filter className="text-slate-500" size={20} />
            <select 
              className="bg-transparent border-none outline-none text-slate-200 w-full appearance-none"
              value={searchParams.logLevel}
              onChange={(e) => setSearchParams({ ...searchParams, logLevel: e.target.value })}
            >
              <option value="" className="bg-slate-900">All Levels</option>
              <option value="INFO" className="bg-slate-900">Info</option>
              <option value="WARN" className="bg-slate-900">Warning</option>
              <option value="ERROR" className="bg-slate-900">Error</option>
            </select>
          </div>
        </div>
      )}

      <div className="glass-card overflow-hidden">
        <div className="overflow-x-auto">
          <table className="w-full text-left border-collapse">
            <thead>
              <tr className="border-b border-white/5 bg-white/5">
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Timestamp</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Service</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Level</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Message</th>
                <th className="px-6 py-4 text-xs font-semibold text-slate-400 uppercase tracking-wider">Trace ID</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-white/5">
              {!isLive && loading ? (
                <tr>
                  <td colSpan={5} className="px-6 py-20 text-center text-slate-500">
                    <RefreshCcw className="animate-spin inline-block mr-2" />
                    Loading logs...
                  </td>
                </tr>
              ) : isLive && liveLogs.length === 0 ? (
                <tr>
                  <td colSpan={5} className="px-6 py-20 text-center text-slate-500">
                    <RefreshCcw className="animate-spin inline-block mr-2" />
                    Waiting for live logs...
                  </td>
                </tr>
              ) : displayedLogs.length === 0 ? (
                <tr>
                  <td colSpan={5} className="px-6 py-20 text-center text-slate-500">
                    No logs found matching your criteria.
                  </td>
                </tr>
              ) : (
                displayedLogs.map((log, index) => (
                  <tr key={log.id || index} className="hover:bg-white/5 transition-colors group">
                    <td className="px-6 py-4 text-sm text-slate-400 whitespace-nowrap font-mono">
                      {formatTimestamp(log.timestamp)}
                    </td>
                    <td className="px-6 py-4">
                      <span className="px-2 py-1 rounded-md bg-indigo-500/10 text-indigo-400 border border-indigo-500/20 text-xs font-medium">
                        {log.serviceName}
                      </span>
                    </td>
                    <td className="px-6 py-4">
                      <span className={`flex items-center gap-1.5 px-2 py-1 rounded-md border text-xs font-medium ${getLevelColor(log.logLevel)}`}>
                        {getLevelIcon(log.logLevel)}
                        {log.logLevel}
                      </span>
                    </td>
                    <td className="px-6 py-4 text-sm text-slate-300 max-w-md truncate group-hover:whitespace-normal group-hover:overflow-visible group-hover:max-w-none">
                      {log.message}
                    </td>
                    <td className="px-6 py-4 text-sm text-slate-500 font-mono">
                      {log.traceId || '-'}
                    </td>
                  </tr>
                ))
              )}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default LogsPage;
