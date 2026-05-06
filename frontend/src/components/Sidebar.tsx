import { LayoutDashboard, FileText, Share2, Brain, AlertTriangle, Settings, Terminal } from 'lucide-react';

const Sidebar = () => {
  const menuItems = [
    { icon: LayoutDashboard, label: 'Dashboard', active: true },
    { icon: FileText, label: 'Log Explorer' },
    { icon: Share2, label: 'Trace Graph' },
    { icon: AlertTriangle, label: 'Incidents' },
    { icon: Brain, label: 'AI Analysis' },
  ];

  return (
    <div className="w-64 h-screen glass border-r border-white/10 flex flex-col p-4">
      <div className="flex items-center gap-3 px-2 mb-10">
        <div className="w-10 h-10 bg-indigo-600 rounded-xl flex items-center justify-center shadow-lg shadow-indigo-500/30">
          <Share2 className="text-white" size={24} />
        </div>
        <h1 className="text-xl font-bold tracking-tight text-white">RootTrace</h1>
      </div>

      <nav className="flex-1 space-y-2">
        {menuItems.map((item) => (
          <button
            key={item.label}
            className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all ${
              item.active 
                ? 'bg-indigo-600/20 text-indigo-400 border border-indigo-500/30' 
                : 'text-slate-400 hover:bg-white/5 hover:text-slate-200'
            }`}
          >
            <item.icon size={20} />
            <span className="font-medium">{item.label}</span>
          </button>
        ))}
      </nav>

      <div className="pt-4 border-t border-white/10 space-y-2">
        <button className="w-full flex items-center gap-3 px-4 py-3 rounded-xl text-slate-400 hover:bg-white/5 hover:text-slate-200 transition-all">
          <Terminal size={20} />
          <span className="font-medium">GitHub Repo</span>
        </button>
        <button className="w-full flex items-center gap-3 px-4 py-3 rounded-xl text-slate-400 hover:bg-white/5 hover:text-slate-200 transition-all">
          <Settings size={20} />
          <span className="font-medium">Settings</span>
        </button>
      </div>
    </div>
  );
};

export default Sidebar;
