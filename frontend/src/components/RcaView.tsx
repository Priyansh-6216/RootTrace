import { ShieldAlert, Cpu, Activity, ArrowRight, Zap, Coins } from 'lucide-react';
import { motion } from 'framer-motion';

export function RcaView() {
  return (
    <div className="glass-panel p-5 h-full flex flex-col gap-4">
      <div className="flex items-center justify-between border-b border-slate-700/50 pb-3">
        <h2 className="text-xl font-bold flex items-center gap-2">
          <ShieldAlert className="w-6 h-6 text-indigo-400" />
          AI Root Cause Analysis
        </h2>
        <div className="flex items-center gap-4 text-xs font-medium bg-slate-900/50 px-3 py-1.5 rounded-full border border-slate-700">
          <span className="flex items-center gap-1 text-slate-300">
            <Cpu className="w-3.5 h-3.5 text-emerald-400" /> Model: GPT-4-Turbo
          </span>
          <span className="w-px h-3 bg-slate-700"></span>
          <span className="flex items-center gap-1 text-slate-300">
            <Coins className="w-3.5 h-3.5 text-amber-400" /> Cost: $0.014
          </span>
        </div>
      </div>

      <div className="flex-1 overflow-y-auto space-y-5 pr-2">
        <motion.div 
          initial={{ opacity: 0, y: 10 }}
          animate={{ opacity: 1, y: 0 }}
          className="bg-indigo-900/10 border border-indigo-500/20 rounded-xl p-4"
        >
          <h3 className="text-sm uppercase tracking-wider font-bold text-indigo-300 mb-2 flex items-center gap-2">
            <Zap className="w-4 h-4" /> Top Hypothesis (95% Confidence)
          </h3>
          <p className="text-slate-300 leading-relaxed">
            The checkout-service is experiencing a severe degradation due to a connection pool exhaustion connecting to PostgreSQL. The <code>HikariCP</code> pool is fully saturated, corroborated by a 412% spike in p95 latency and recent <code>TimeoutException</code> logs.
          </p>
        </motion.div>

        <div>
          <h3 className="text-sm uppercase tracking-wider font-bold text-slate-400 mb-3">Supporting Evidence</h3>
          <div className="space-y-2">
            <div className="flex items-center justify-between bg-slate-800/40 p-3 rounded-lg border border-slate-700/50">
              <div className="flex items-center gap-3">
                <span className="px-2 py-0.5 rounded text-[10px] font-bold bg-purple-500/20 text-purple-400 uppercase tracking-wide">Metric</span>
                <span className="text-sm text-slate-200">P95 latency spike of +412%</span>
              </div>
              <span className="text-xs text-emerald-400 font-mono font-medium">Strength: 0.94</span>
            </div>
            <div className="flex items-center justify-between bg-slate-800/40 p-3 rounded-lg border border-slate-700/50">
              <div className="flex items-center gap-3">
                <span className="px-2 py-0.5 rounded text-[10px] font-bold bg-blue-500/20 text-blue-400 uppercase tracking-wide">Log</span>
                <span className="text-sm text-slate-200">TimeoutException getting connection from pool</span>
              </div>
              <span className="text-xs text-emerald-400 font-mono font-medium">Strength: 0.88</span>
            </div>
          </div>
        </div>

        <div>
          <h3 className="text-sm uppercase tracking-wider font-bold text-slate-400 mb-3">Proposed Remediation Steps</h3>
          <ul className="space-y-2">
            <li className="flex gap-3 text-sm text-slate-300 bg-slate-800/30 p-3 rounded-lg">
              <ArrowRight className="w-5 h-5 text-indigo-400 shrink-0" />
              <span>Increase <code>maximum-pool-size</code> in checkout-service <code>application.yml</code> from 10 to 30.</span>
            </li>
            <li className="flex gap-3 text-sm text-slate-300 bg-slate-800/30 p-3 rounded-lg">
              <ArrowRight className="w-5 h-5 text-indigo-400 shrink-0" />
              <span>Restart checkout-service pods to apply the new connection pool settings.</span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}
