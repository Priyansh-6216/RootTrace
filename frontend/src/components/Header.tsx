import { Bell, Search, User } from 'lucide-react';

const Header = () => {
  return (
    <header className="h-16 border-b border-white/10 px-8 flex items-center justify-between bg-slate-950/50 backdrop-blur-md sticky top-0 z-10">
      <div className="flex-1 max-w-xl">
        <div className="relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 text-slate-500" size={18} />
          <input 
            type="text" 
            placeholder="Search logs, traces, or incidents..." 
            className="w-full bg-white/5 border border-white/10 rounded-full py-2 pl-10 pr-4 text-sm text-slate-200 focus:outline-none focus:ring-2 focus:ring-indigo-500/50 transition-all"
          />
        </div>
      </div>

      <div className="flex items-center gap-4">
        <button className="p-2 text-slate-400 hover:text-white transition-colors relative">
          <Bell size={20} />
          <span className="absolute top-2 right-2 w-2 h-2 bg-red-500 rounded-full border-2 border-slate-950"></span>
        </button>
        <div className="h-8 w-[1px] bg-white/10 mx-2"></div>
        <div className="flex items-center gap-3 pl-2 cursor-pointer hover:opacity-80 transition-opacity">
          <div className="text-right">
            <p className="text-sm font-medium text-slate-200">SRE Admin</p>
            <p className="text-xs text-slate-500">System Administrator</p>
          </div>
          <div className="w-9 h-9 rounded-full bg-gradient-to-tr from-indigo-600 to-purple-600 flex items-center justify-center shadow-lg shadow-indigo-500/20">
            <User className="text-white" size={18} />
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;
