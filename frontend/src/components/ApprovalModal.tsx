import { motion, AnimatePresence } from 'framer-motion';
import { ShieldCheck, X, CheckCircle, XCircle } from 'lucide-react';
import { useState } from 'react';

interface ApprovalModalProps {
  isOpen: boolean;
  onClose: () => void;
  onApprove: () => void;
  onReject: (reason: string) => void;
}

export function ApprovalModal({ isOpen, onClose, onApprove, onReject }: ApprovalModalProps) {
  const [reason, setReason] = useState('');
  const [mode, setMode] = useState<'pending' | 'rejecting'>('pending');

  return (
    <AnimatePresence>
      {isOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            className="absolute inset-0 bg-slate-950/80 backdrop-blur-sm"
            onClick={onClose}
          />
          
          <motion.div
            initial={{ opacity: 0, scale: 0.95, y: 20 }}
            animate={{ opacity: 1, scale: 1, y: 0 }}
            exit={{ opacity: 0, scale: 0.95, y: 20 }}
            className="glass-panel w-full max-w-lg overflow-hidden relative z-10 border-indigo-500/30 shadow-[0_0_50px_-12px_rgba(99,102,241,0.25)]"
          >
            <div className="p-6">
              <div className="flex justify-between items-start mb-5">
                <div className="flex items-center gap-3">
                  <div className="p-2 bg-indigo-500/20 rounded-lg">
                    <ShieldCheck className="w-6 h-6 text-indigo-400" />
                  </div>
                  <div>
                    <h2 className="text-xl font-bold text-white">Human Approval Required</h2>
                    <p className="text-sm text-slate-400">Review AI-proposed remediation plan</p>
                  </div>
                </div>
                <button onClick={onClose} className="text-slate-400 hover:text-white transition-colors">
                  <X className="w-5 h-5" />
                </button>
              </div>

              <div className="bg-slate-900/50 border border-slate-700/50 rounded-lg p-4 mb-6">
                <p className="text-sm text-slate-300 leading-relaxed">
                  RootTrace AI is requesting permission to open a Pull Request against <strong>checkout-service</strong> to increase the database connection pool size from 10 to 30.
                </p>
                <div className="mt-3 text-xs text-amber-400/80 flex items-center gap-2">
                  <span className="w-1.5 h-1.5 rounded-full bg-amber-400 animate-pulse"></span>
                  This will generate code changes via GitHub API.
                </div>
              </div>

              {mode === 'pending' ? (
                <div className="flex gap-3">
                  <button 
                    onClick={() => setMode('rejecting')}
                    className="flex-1 glass-button-danger flex justify-center items-center gap-2"
                  >
                    <XCircle className="w-4 h-4" /> Reject Plan
                  </button>
                  <button 
                    onClick={onApprove}
                    className="flex-1 glass-button flex justify-center items-center gap-2"
                  >
                    <CheckCircle className="w-4 h-4" /> Approve & Execute
                  </button>
                </div>
              ) : (
                <motion.div initial={{ opacity: 0, height: 0 }} animate={{ opacity: 1, height: 'auto' }}>
                  <label className="block text-sm font-medium text-slate-300 mb-2">Reason for rejection:</label>
                  <textarea 
                    value={reason}
                    onChange={(e) => setReason(e.target.value)}
                    className="w-full bg-slate-900/50 border border-slate-700 rounded-lg p-3 text-sm text-white focus:outline-none focus:border-indigo-500 focus:ring-1 focus:ring-indigo-500 mb-4 h-24 resize-none"
                    placeholder="e.g., The proposed connection pool size is too high for the current DB instance."
                  />
                  <div className="flex gap-3">
                    <button 
                      onClick={() => setMode('pending')}
                      className="flex-1 px-4 py-2 rounded-lg bg-slate-800 hover:bg-slate-700 text-white font-medium transition-colors border border-slate-700"
                    >
                      Cancel
                    </button>
                    <button 
                      onClick={() => onReject(reason)}
                      disabled={!reason.trim()}
                      className="flex-1 glass-button-danger flex justify-center items-center gap-2 disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                      Confirm Rejection
                    </button>
                  </div>
                </motion.div>
              )}
            </div>
          </motion.div>
        </div>
      )}
    </AnimatePresence>
  );
}
