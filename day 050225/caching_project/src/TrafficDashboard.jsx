import React, { useState, useEffect } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';

const TrafficDataDashboard = () => {
  const [accidents, setAccidents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [selectedCountry, setSelectedCountry] = useState('USA');

  useEffect(() => {
    fetchAccidentsByCountry(selectedCountry);
  }, [selectedCountry]);

  const fetchAccidentsByCountry = async (country) => {
    setLoading(true);
    setError(null);
    
    try {
      console.log('Fetching data for country:', country);
      const response = await fetch(`http://localhost:8083/api/accidents/country/${country}`);
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      console.log('Received data:', data);
      
      if (!Array.isArray(data)) {
        throw new Error('Received data is not an array');
      }
      
      setAccidents(data);
    } catch (error) {
      console.error('Error fetching data:', error);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  };

  // Calculate statistics only if we have data
  const severityStats = accidents.length > 0 
    ? accidents.reduce((acc, curr) => {
        acc[curr.accidentSeverity] = (acc[curr.accidentSeverity] || 0) + 1;
        return acc;
      }, {})
    : {};

  const chartData = Object.entries(severityStats).map(([severity, count]) => ({
    severity,
    count
  }));

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-6">Traffic Accident Analysis Dashboard</h1>
      
      <div className="mb-6">
        <select 
          className="p-2 border rounded"
          value={selectedCountry}
          onChange={(e) => setSelectedCountry(e.target.value)}
        >
          <option value="USA">USA</option>
          <option value="UK">UK</option>
        </select>
      </div>

      {loading && (
        <div className="text-center py-4">
          <p>Loading data...</p>
        </div>
      )}

      {error && (
        <div className="text-center py-4 text-red-600">
          <p>Error: {error}</p>
          <button 
            className="mt-2 p-2 bg-blue-500 text-white rounded"
            onClick={() => fetchAccidentsByCountry(selectedCountry)}
          >
            Retry
          </button>
        </div>
      )}

      {!loading && !error && accidents.length === 0 && (
        <div className="text-center py-4">
          <p>No data found for {selectedCountry}</p>
        </div>
      )}

      {!loading && !error && accidents.length > 0 && (
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          {/* Severity Chart */}
          <div className="p-4 border rounded shadow">
            <h2 className="text-xl font-semibold mb-4">Accidents by Severity</h2>
            {chartData.length > 0 ? (
              <LineChart width={500} height={300} data={chartData}>
                <CartesianGrid strokeDasharray="3 3" />
                <XAxis dataKey="severity" />
                <YAxis />
                <Tooltip />
                <Legend />
                <Line type="monotone" dataKey="count" stroke="#8884d8" />
              </LineChart>
            ) : (
              <p>No severity data available</p>
            )}
          </div>

          {/* Stats Summary */}
          <div className="p-4 border rounded shadow">
            <h2 className="text-xl font-semibold mb-4">Summary Statistics</h2>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <h3 className="font-medium">Total Accidents</h3>
                <p className="text-2xl">{accidents.length}</p>
              </div>
              <div>
                <h3 className="font-medium">Total Fatalities</h3>
                <p className="text-2xl">
                  {accidents.reduce((sum, acc) => sum + acc.numberOfFatalities, 0)}
                </p>
              </div>
              <div>
                <h3 className="font-medium">Total Injuries</h3>
                <p className="text-2xl">
                  {accidents.reduce((sum, acc) => sum + acc.numberOfInjuries, 0)}
                </p>
              </div>
              <div>
                <h3 className="font-medium">Avg Response Time</h3>
                <p className="text-2xl">
                  {accidents.length > 0 
                    ? (accidents.reduce((sum, acc) => sum + acc.emergencyResponseTime, 0) / accidents.length).toFixed(2)
                    : 0} min
                </p>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default TrafficDataDashboard;