import { useState } from 'react';
import { IncidentFeed } from '../components/IncidentFeed';
import { ServiceMap } from '../components/ServiceMap';
import { RcaView } from '../components/RcaView';
import { ApprovalModal } from '../components/ApprovalModal';
import { ShieldAlert, PlayCircle } from 'lucide-react';

export default function Dashboard() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [status, setStatus] = useState<'PENDING' | 'APPROVED' | 'REJECTED'>('PENDING');

  const handleApprove = () => {
    setStatus('APPROVED');
    setIsModalOpen(false);
    // In real implementation, this triggers the POST /api/v1/remediations/{id}/approve
  };

  const handleReject = (reason: string) => {
    console.log("Rejected:", reason);
    setStatus('REJECTED');
    setIsModalOpen(false);
    // In real implementation, this triggers the POST /api/v1/remediations/{id}/reject
  };

  return (
    <div className="h-screen w-full bg-[#0f172a] text-slate-200 p-4 lg:p-6 overflow-hidden flex flex-col gap-6">
      <header className="flex justify-between items-center bg-slate-900/50 p-4 rounded-xl border border-slate-700/50 backdrop-blur-md shrink-0">
        <div className="flex items-center gap-3">
          <div className="bg-indigo-600 p-2 rounded-lg shadow-[0_0_15px_rgba(79,70,229,0.5)]">
            <ShieldAlert className="w-6 h-6 text-white" />
          </div>
          <div>
            <h1 className="text-xl font-bold tracking-tight text-white leading-tight">RootTrace</h1>
            <p className="text-xs text-indigo-300 font-medium tracking-wide uppercase">Production V2</p>
          </div>
        </div>
        
        <div className="flex items-center gap-4">
          <div className="flex items-center gap-2 px-4 py-2 bg-slate-800 rounded-lg border border-slate-700">
            <span className="relative flex h-3 w-3">
              <span className="animate-ping absolute inline-flex h-full w-full rounded-full bg-emerald-400 opacity-75"></span>
              <span className="relative inline-flex rounded-full h-3 w-3 bg-emerald-500"></span>
            </span>
            <span className="text-sm font-medium text-slate-300">System Healthy</span>
          </div>
          
          <button 
            onClick={() => setIsModalOpen(true)}
            className="glass-button flex items-center gap-2"
          >
            <PlayCircle className="w-5 h-5" />
            Review Remediation
          </button>
        </div>
      </header>

      <div className="flex-1 grid grid-cols-1 lg:grid-cols-12 gap-6 min-h-0">
        <div className="lg:col-span-3 h-full min-h-0">
          <IncidentFeed />
        </div>
        
        <div className="lg:col-span-6 h-full min-h-0 flex flex-col gap-6">
          <div className="flex-1 min-h-0">
            <ServiceMap />
          </div>
          <div className="h-24 glass-panel flex items-center justify-between px-6 shrink-0 bg-slate-800/80">
            <div>
              <h3 className="text-slate-400 text-xs font-bold uppercase tracking-wider mb-1">Status</h3>
              <div className="flex items-center gap-2">
                <span className={`w-2.5 h-2.5 rounded-full ${status === 'PENDING' ? 'bg-amber-500' : status === 'APPROVED' ? 'bg-emerald-500' : 'bg-rose-500'}`}></span>
                <span className="text-lg font-bold text-white">
                  {status === 'PENDING' ? 'Awaiting Human Approval' : status === 'APPROVED' ? 'Remediation Applying...' : 'Remediation Rejected'}
                </span>
              </div>
            </div>
          </div>
        </div>

        <div className="lg:col-span-3 h-full min-h-0">
          <RcaView />
        </div>
      </div>

      <ApprovalModal 
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        onApprove={handleApprove}
        onReject={handleReject}
      />
    </div>
  );
}
