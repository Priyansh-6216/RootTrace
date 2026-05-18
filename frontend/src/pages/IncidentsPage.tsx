import { AlertTriangle } from 'lucide-react';

const IncidentsPage = () => {
  return (
    <div className="p-8 space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-3xl font-bold text-white tracking-tight flex items-center gap-3">
            <AlertTriangle className="text-amber-400" />
            Active Incidents
          </h2>
          <p className="text-slate-400 mt-1">Monitor and resolve ongoing system issues.</p>
        </div>
      </div>
      
      <div className="glass-card p-6">
        <h3 className="text-xl font-semibold text-white mb-4">Recent Incident: Database Timeout</h3>
        <p className="text-slate-400">
          Detected a high rate of TimeoutExceptions in the user-service when attempting to connect to the primary database.
        </p>
        <div className="mt-4 flex gap-4">
          <button className="glass-button bg-amber-500/20 text-amber-400 border-amber-500/30">
            Acknowledge
          </button>
          <button className="glass-button">
            View Logs
          </button>
        </div>
      </div>
    </div>
  );
};

export default IncidentsPage;
