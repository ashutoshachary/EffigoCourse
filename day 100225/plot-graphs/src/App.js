import React from 'react';
import './index.css';
import RDboard from './components/RDboard';
import RestaurantDashboard from './components/ReactDashboard';

function App() {
  return (
    <div>
      <nav className="navbar">
        <div className="navbar-container">
          <div className="navbar-content">
            <a href="/" className="navbar-brand">
              Restaurant Dashboard
            </a>
          </div>
        </div>
      </nav>
      <RestaurantDashboard />
    </div>
  );
}

export default App;