import { Activity, Server, ShieldAlert, Cpu, Plus } from 'lucide-react';
import { AreaChart, Area, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer } from 'recharts';

const data = [
  { name: '10:00', errors: 4, latency: 120 },
  { name: '11:00', errors: 3, latency: 132 },
  { name: '12:00', errors: 10, latency: 400 },
  { name: '13:00', errors: 2, latency: 110 },
  { name: '14:00', errors: 5, latency: 150 },
  { name: '15:00', errors: 8, latency: 180 },
];

const StatCard = ({ icon: Icon, label, value, trend, color }: any) => (
  <div className="glass-card p-6 flex flex-col gap-4">
    <div className="flex items-center justify-between">
      <div className={`p-3 rounded-xl bg-${color}-500/20 text-${color}-400`}>
        <Icon size={24} />
      </div>
      <span className={`text-xs font-bold px-2 py-1 rounded-full bg-${trend > 0 ? 'red' : 'emerald'}-500/10 text-${trend > 0 ? 'red' : 'emerald'}-400`}>
        {trend > 0 ? '+' : ''}{trend}%
      </span>
    </div>
    <div>
      <p className="text-slate-400 text-sm font-medium">{label}</p>
      <h3 className="text-2xl font-bold text-white mt-1">{value}</h3>
    </div>
  </div>
);

const Dashboard = () => {
  return (
    <div className="p-8 space-y-8">
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-3xl font-bold text-white">System Overview</h1>
          <p className="text-slate-400 mt-1">Real-time health monitoring and incident detection.</p>
        </div>
        <button className="flex items-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2.5 rounded-xl font-medium transition-all shadow-lg shadow-indigo-500/20">
          <Plus size={20} />
          <span>Upload Logs</span>
        </button>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
        <StatCard icon={Activity} label="Total Requests" value="1.2M" trend={-2} color="indigo" />
        <StatCard icon={ShieldAlert} label="Active Incidents" value="12" trend={15} color="red" />
        <StatCard icon={Server} label="Healthy Services" value="42/45" trend={-1} color="emerald" />
        <StatCard icon={Cpu} label="Avg. Latency" value="145ms" trend={5} color="amber" />
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2 glass-card p-6">
          <div className="flex items-center justify-between mb-8">
            <h3 className="text-lg font-bold text-white">Error Rate vs Latency</h3>
            <select className="bg-white/5 border border-white/10 rounded-lg px-3 py-1.5 text-sm text-slate-300 focus:outline-none">
              <option>Last 24 hours</option>
              <option>Last 7 days</option>
            </select>
          </div>
          <div className="h-[300px] w-full">
            <ResponsiveContainer width="100%" height="100%">
              <AreaChart data={data}>
                <defs>
                  <linearGradient id="colorErrors" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#f43f5e" stopOpacity={0.3}/>
                    <stop offset="95%" stopColor="#f43f5e" stopOpacity={0}/>
                  </linearGradient>
                </defs>
                <CartesianGrid strokeDasharray="3 3" stroke="#ffffff10" vertical={false} />
                <XAxis dataKey="name" stroke="#64748b" fontSize={12} tickLine={false} axisLine={false} />
                <YAxis stroke="#64748b" fontSize={12} tickLine={false} axisLine={false} />
                <Tooltip 
                  contentStyle={{ backgroundColor: '#0f172a', border: '1px solid #ffffff10', borderRadius: '12px' }}
                  itemStyle={{ color: '#f1f5f9' }}
                />
                <Area type="monotone" dataKey="errors" stroke="#f43f5e" fillOpacity={1} fill="url(#colorErrors)" strokeWidth={3} />
              </AreaChart>
            </ResponsiveContainer>
          </div>
        </div>

        <div className="glass-card p-6">
          <h3 className="text-lg font-bold text-white mb-6">Recent Incidents</h3>
          <div className="space-y-4">
            {[1, 2, 3, 4].map((i) => (
              <div key={i} className="p-4 rounded-xl bg-white/5 border border-white/5 hover:border-white/10 transition-all cursor-pointer group">
                <div className="flex items-start justify-between mb-2">
                  <span className="text-xs font-bold uppercase tracking-wider text-red-400">Critical</span>
                  <span className="text-xs text-slate-500">2m ago</span>
                </div>
                <h4 className="text-sm font-bold text-slate-200 group-hover:text-indigo-400 transition-colors">Payment Service Timeout</h4>
                <p className="text-xs text-slate-500 mt-1 line-clamp-1">Cascading failure in fraud-detection-service...</p>
              </div>
            ))}
          </div>
          <button className="w-full mt-6 py-2 text-sm font-medium text-slate-400 hover:text-white transition-colors">
            View All Incidents
          </button>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
