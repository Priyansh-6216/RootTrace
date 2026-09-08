import { useState, useEffect } from 'react';
import { AlertCircle, AlertTriangle, Info } from 'lucide-react';
import { motion, AnimatePresence } from 'framer-motion';

export interface Incident {
  id: string;
  title: string;
  severity: 'SEV1' | 'SEV2' | 'SEV3' | 'SEV4';
  service: string;
  time: string;
}

export function IncidentFeed() {
  const [incidents, setIncidents] = useState<Incident[]>([
    { id: '1', title: 'Database Connection Timeout', severity: 'SEV1', service: 'checkout-service', time: 'Just now' },
    { id: '2', title: 'High Memory Usage', severity: 'SEV3', service: 'user-profile', time: '2m ago' },
  ]);

  const getIcon = (severity: string) => {
    switch (severity) {
      case 'SEV1': return <AlertCircle className="w-5 h-5 text-rose-500" />;
      case 'SEV2': return <AlertTriangle className="w-5 h-5 text-orange-500" />;
      default: return <Info className="w-5 h-5 text-blue-500" />;
    }
  };

  return (
    <div className="glass-panel p-4 h-full overflow-hidden flex flex-col">
      <h2 className="text-xl font-bold mb-4 flex items-center gap-2">
        <AlertCircle className="w-5 h-5 text-indigo-400" />
        Live Incident Feed
      </h2>
      <div className="flex-1 overflow-y-auto pr-2 space-y-3">
        <AnimatePresence>
          {incidents.map((inc) => (
            <motion.div
              key={inc.id}
              initial={{ opacity: 0, y: -20, scale: 0.95 }}
              animate={{ opacity: 1, y: 0, scale: 1 }}
              exit={{ opacity: 0, scale: 0.9 }}
              transition={{ duration: 0.3 }}
              className="p-3 rounded-lg bg-slate-800/50 border border-slate-700/50 hover:bg-slate-700/50 transition-colors cursor-pointer group"
            >
              <div className="flex items-start gap-3">
                <div className="mt-1">{getIcon(inc.severity)}</div>
                <div className="flex-1 min-w-0">
                  <div className="flex items-center justify-between">
                    <span className="font-semibold truncate text-slate-200">{inc.title}</span>
                    <span className="text-xs text-slate-400 ml-2 whitespace-nowrap">{inc.time}</span>
                  </div>
                  <div className="flex items-center gap-2 mt-1">
                    <span className="text-xs px-2 py-0.5 rounded-full bg-slate-900 border border-slate-700">
                      {inc.service}
                    </span>
                    <span className={`text-[10px] font-bold px-1.5 py-0.5 rounded ${
                      inc.severity === 'SEV1' ? 'bg-rose-500/20 text-rose-400' :
                      inc.severity === 'SEV2' ? 'bg-orange-500/20 text-orange-400' :
                      'bg-slate-700 text-slate-300'
                    }`}>
                      {inc.severity}
                    </span>
                  </div>
                </div>
              </div>
            </motion.div>
          ))}
        </AnimatePresence>
      </div>
    </div>
  );
}
