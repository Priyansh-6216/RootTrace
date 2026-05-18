import { Share2 } from 'lucide-react';

const TracesPage = () => {
  return (
    <div className="p-8 space-y-6">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-3xl font-bold text-white tracking-tight flex items-center gap-3">
            <Share2 className="text-indigo-400" />
            Distributed Trace Graph
          </h2>
          <p className="text-slate-400 mt-1">Visualize request flows across microservices powered by Neo4j.</p>
        </div>
      </div>
      
      <div className="glass-card h-[600px] flex items-center justify-center border-dashed border-2 border-slate-700/50">
        <div className="text-center">
          <Share2 className="mx-auto text-slate-500 mb-4" size={48} />
          <h3 className="text-xl font-medium text-slate-300">Neo4j Trace Visualization</h3>
          <p className="text-slate-500 mt-2 max-w-md mx-auto">
            The Neo4j backend integration is active. Trace flows between services will appear here as nodes and edges.
          </p>
        </div>
      </div>
    </div>
  );
};

export default TracesPage;
