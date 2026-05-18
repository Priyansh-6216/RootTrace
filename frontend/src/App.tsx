import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Header from './components/Header';
import Dashboard from './pages/Dashboard';
import LogsPage from './pages/LogsPage';
import TracesPage from './pages/TracesPage';
import IncidentsPage from './pages/IncidentsPage';
import AIPage from './pages/AIPage';

function App() {
  return (
    <Router>
      <div className="flex h-screen bg-slate-950 overflow-hidden">
        <Sidebar />
        <div className="flex-1 flex flex-col overflow-hidden">
          <Header />
          <main className="flex-1 overflow-y-auto">
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/logs" element={<LogsPage />} />
              <Route path="/traces" element={<TracesPage />} />
              <Route path="/incidents" element={<IncidentsPage />} />
              <Route path="/ai" element={<AIPage />} />
            </Routes>
          </main>
        </div>
      </div>
    </Router>
  );
}

export default App;
