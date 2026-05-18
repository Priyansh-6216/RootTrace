import { Brain, ExternalLink } from 'lucide-react';

const AIPage = () => {
  return (
    <div className="p-8 space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-3xl font-bold text-white tracking-tight flex items-center gap-3">
            <Brain className="text-purple-400" />
            AI Root Cause Analysis
          </h2>
          <p className="text-slate-400 mt-1">LangChain-powered automatic incident resolution and GitHub integration.</p>
        </div>
      </div>
      
      <div className="glass-card p-6 border-l-4 border-l-purple-500">
        <div className="flex items-start justify-between">
          <div>
            <h3 className="text-lg font-medium text-purple-300 uppercase tracking-wider text-sm mb-2">AI Analysis Report</h3>
            <h4 className="text-2xl font-bold text-white mb-4">Database connection timeout in 'user-service'</h4>
            
            <div className="space-y-4">
              <div>
                <h5 className="text-slate-300 font-medium">Evidence Found:</h5>
                <ul className="list-disc list-inside text-slate-400 ml-2 mt-1">
                  <li>Log: TimeoutException getting connection</li>
                  <li>Trace: user-service {"->"} database took {">"} 5000ms</li>
                </ul>
              </div>
              
              <div>
                <h5 className="text-slate-300 font-medium">Recommended Actions:</h5>
                <ul className="list-disc list-inside text-slate-400 ml-2 mt-1">
                  <li>Increase HikariCP max pool size</li>
                  <li>Investigate slow queries in user profile fetching</li>
                </ul>
              </div>
            </div>
          </div>
          
          <div className="flex flex-col items-end gap-3">
            <div className="bg-emerald-500/10 border border-emerald-500/20 text-emerald-400 px-3 py-1 rounded-full text-sm font-medium">
              95% Confidence
            </div>
            <button className="glass-button flex items-center gap-2 bg-purple-600/20 text-purple-400 border-purple-500/30">
              <ExternalLink size={16} />
              View Auto-Generated PR
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AIPage;
