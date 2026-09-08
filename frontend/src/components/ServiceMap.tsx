import { useCallback } from 'react';
import {
  ReactFlow,
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  MarkerType,
  BackgroundVariant
} from '@xyflow/react';
import '@xyflow/react/dist/style.css';

const initialNodes = [
  { id: '1', position: { x: 250, y: 50 }, data: { label: 'API Gateway' }, className: 'bg-slate-800 text-white border-slate-600 rounded-lg p-3 shadow-lg w-40 text-center font-semibold' },
  { id: '2', position: { x: 100, y: 150 }, data: { label: 'Auth Service' }, className: 'bg-slate-800 text-white border-slate-600 rounded-lg p-3 shadow-lg w-40 text-center' },
  { id: '3', position: { x: 400, y: 150 }, data: { label: 'Checkout Service' }, className: 'bg-rose-900/50 text-rose-100 border-rose-500 border-2 rounded-lg p-3 shadow-[0_0_15px_rgba(225,29,72,0.4)] w-40 text-center animate-glow' },
  { id: '4', position: { x: 400, y: 250 }, data: { label: 'PostgreSQL' }, className: 'bg-slate-800 text-white border-slate-600 rounded-lg p-3 shadow-lg w-40 text-center rounded-full' },
];

const initialEdges = [
  { id: 'e1-2', source: '1', target: '2', animated: true, style: { stroke: '#94a3b8' } },
  { id: 'e1-3', source: '1', target: '3', animated: true, style: { stroke: '#f43f5e', strokeWidth: 2 }, markerEnd: { type: MarkerType.ArrowClosed, color: '#f43f5e' } },
  { id: 'e3-4', source: '3', target: '4', animated: true, style: { stroke: '#f43f5e', strokeWidth: 2 }, markerEnd: { type: MarkerType.ArrowClosed, color: '#f43f5e' } },
];

export function ServiceMap() {
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  return (
    <div className="w-full h-full glass-panel overflow-hidden relative">
      <div className="absolute top-4 left-4 z-10 bg-slate-900/80 px-3 py-1.5 rounded text-sm border border-slate-700 backdrop-blur-sm shadow-xl font-medium">
        Dependency Graph (Neo4j)
      </div>
      <ReactFlow
        nodes={nodes}
        edges={edges}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        fitView
        className="bg-transparent"
        proOptions={{ hideAttribution: true }}
      >
        <Background variant={BackgroundVariant.Dots} gap={24} size={1} color="#334155" />
        <Controls className="bg-slate-800 border-slate-700 fill-white" />
        <MiniMap nodeColor="#475569" maskColor="rgba(15, 23, 42, 0.7)" className="bg-slate-900 border border-slate-700" />
      </ReactFlow>
    </div>
  );
}
