import React, { useState, useEffect, useRef, useCallback } from 'react';
import { LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend } from 'recharts';
import { TrendingUp, Users, AlertTriangle, Clock, MapPin } from 'lucide-react';
      // or
import './index.css';
const GlassCard = ({ title, icon, children, className = "" }) => (
  <div className={`backdrop-blur-lg bg-white/80 rounded-2xl shadow-xl border border-white/20 ${className}`}>
    <div className="border-b border-gray-100/20 p-6 flex items-center gap-3">
      {icon}
      <h2 className="text-xl font-semibold text-gray-800">{title}</h2>
    </div>
    <div className="p-6">
      {children}
    </div>
  </div>
);

const StatBox = ({ title, value, icon, color }) => (
  <div className={`p-6 rounded-xl ${color} transform transition-all duration-200 hover:scale-105`}>
    <div className="flex items-center gap-4">
      <div className="p-3 bg-white/20 rounded-lg">
        {icon}
      </div>
      <div>
        <p className="text-sm font-medium text-white/80">{title}</p>
        <p className="text-2xl font-bold text-white">{value}</p>
      </div>
    </div>
  </div>
);

const PAGE_SIZE = 20;

const TrafficDataDashboard = () => {
  const [accidents, setAccidents] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const [selectedCountry, setSelectedCountry] = useState('USA');
  const [page, setPage] = useState(1);
  const [hasMore, setHasMore] = useState(true);
  const observer = useRef();
  const cache = useRef(new Map());

  // Rest of the state management and data fetching logic remains the same
  const lastElementRef = useCallback(node => {
    if (loading) return;
    if (observer.current) observer.current.disconnect();
    observer.current = new IntersectionObserver(entries => {
      if (entries[0].isIntersecting && hasMore) {
        setPage(prevPage => prevPage + 1);
      }
    });
    if (node) observer.current.observe(node);
  }, [loading, hasMore]);

  const getCacheKey = (country, page) => `${country}-${page}`;

  const fetchAccidentsByCountry = async (country, pageNum) => {
    const cacheKey = getCacheKey(country, pageNum);
    
    if (cache.current.has(cacheKey)) {
      return cache.current.get(cacheKey);
    }

    setLoading(true);
    setError(null);

    try {
      const response = await fetch(
        `http://localhost:8083/api/accidents/country/${country}?page=${pageNum}&size=${PAGE_SIZE}`
      );

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      cache.current.set(cacheKey, data);
      return data;
    } catch (error) {
      console.error('Error fetching data:', error);
      setError(error.message);
      return null;
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    setAccidents([]);
    setPage(1);
    setHasMore(true);
    cache.current.clear();
  }, [selectedCountry]);

  useEffect(() => {
    const loadData = async () => {
      const newData = await fetchAccidentsByCountry(selectedCountry, page);
      if (newData) {
        if (newData.length < PAGE_SIZE) {
          setHasMore(false);
        }
        setAccidents(prev => [...prev, ...newData]);
      }
    };
    loadData();
  }, [selectedCountry, page]);

  const severityStats = accidents.reduce((acc, curr) => {
    acc[curr.accidentSeverity] = (acc[curr.accidentSeverity] || 0) + 1;
    return acc;
  }, {});

  const chartData = Object.entries(severityStats).map(([severity, count]) => ({
    severity,
    count,
  }));

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-50 to-purple-50 p-8">
      <div className="max-w-7xl mx-auto">
        <div className="flex justify-between items-center mb-8">
          <h1 className="text-4xl font-bold text-gray-800">Traffic Accident Analysis</h1>
          <select
            className="px-4 py-2 rounded-lg border border-gray-200 bg-white/80 backdrop-blur-sm
                       shadow-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            value={selectedCountry}
            onChange={(e) => setSelectedCountry(e.target.value)}
          >
            <option value="USA">United States</option>
            <option value="UK">United Kingdom</option>
            <option value="India">India</option>
            <option value="Japan"> </option>
          </select>
        </div>

        {error && (
          <div className="mb-8 bg-red-50/80 backdrop-blur-sm rounded-xl p-6 border border-red-100">
            <p className="text-red-600">{error}</p>
            <button
              className="mt-2 px-4 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 
                         transition-colors duration-200"
              onClick={() => {
                setPage(1);
                cache.current.clear();
              }}
            >
              Retry
            </button>
          </div>
        )}

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <StatBox
            title="Total Accidents"
            value={accidents.length}
            icon={<TrendingUp className="w-6 h-6 text-white" />}
            color="bg-gradient-to-r from-blue-500 to-blue-600"
          />
          <StatBox
            title="Total Fatalities"
            value={accidents.reduce((sum, acc) => sum + acc.numberOfFatalities, 0)}
            icon={<AlertTriangle className="w-6 h-6 text-white" />}
            color="bg-gradient-to-r from-red-500 to-red-600"
          />
          <StatBox
            title="Total Injuries"
            value={accidents.reduce((sum, acc) => sum + acc.numberOfInjuries, 0)}
            icon={<Users className="w-6 h-6 text-white" />}
            color="bg-gradient-to-r from-yellow-500 to-yellow-600"
          />
          <StatBox
            title="Avg Response Time"
            value={`${accidents.length > 0
              ? (accidents.reduce((sum, acc) => sum + acc.emergencyResponseTime, 0) / accidents.length).toFixed(1)
              : 0} min`}
            icon={<Clock className="w-6 h-6 text-white" />}
            color="bg-gradient-to-r from-green-500 to-green-600"
          />
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 mb-8">
          <GlassCard title="Severity Distribution" icon={<TrendingUp className="w-6 h-6 text-blue-500" />}>
            {chartData.length > 0 ? (
              <LineChart width={500} height={300} data={chartData}>
                <CartesianGrid strokeDasharray="3 3" className="opacity-50" />
                <XAxis dataKey="severity" />
                <YAxis />
                <Tooltip 
                  contentStyle={{ 
                    backgroundColor: 'rgba(255, 255, 255, 0.8)',
                    borderRadius: '8px',
                    border: 'none',
                    boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)'
                  }}
                />
                <Legend />
                <Line 
                  type="monotone" 
                  dataKey="count" 
                  stroke="#3b82f6"
                  strokeWidth={2}
                  dot={{ strokeWidth: 2 }}
                  activeDot={{ r: 8 }}
                />
              </LineChart>
            ) : (
              <p className="text-gray-500">No severity data available</p>
            )}
          </GlassCard>

          <GlassCard title="Latest Incidents" icon={<MapPin className="w-6 h-6 text-blue-500" />}>
            <div className="space-y-4 max-h-[300px] overflow-y-auto pr-2">
              {accidents.slice(0, 5).map((accident, index) => (
                <div
                  key={index}
                  className="p-4 rounded-xl bg-white/50 hover:bg-white/70 transition-colors duration-200"
                >
                  <div className="flex justify-between items-start">
                    <div>
                      <h3 className="font-semibold text-gray-800">
                        {accident.region}
                      </h3>
                      <p className="text-sm text-gray-600">
                        {accident.month} {accident.year}, {accident.timeOfDay}
                      </p>
                    </div>
                    <span className="px-3 py-1 rounded-full text-sm font-medium"
                          style={{
                            backgroundColor: accident.accidentSeverity === 'Fatal' ? 'rgba(239, 68, 68, 0.1)' : 
                                           accident.accidentSeverity === 'Serious' ? 'rgba(245, 158, 11, 0.1)' :
                                           'rgba(34, 197, 94, 0.1)',
                            color: accident.accidentSeverity === 'Fatal' ? 'rgb(239, 68, 68)' :
                                  accident.accidentSeverity === 'Serious' ? 'rgb(245, 158, 11)' :
                                  'rgb(34, 197, 94)'
                          }}>
                      {accident.accidentSeverity}
                    </span>
                  </div>
                  <p className="text-sm text-gray-600 mt-2">
                    {accident.accidentCause}
                  </p>
                </div>
              ))}
            </div>
          </GlassCard>
        </div>

        <GlassCard title="All Records" icon={<Users className="w-6 h-6 text-blue-500" />}>
          <div className="space-y-4">
            {accidents.map((accident, index) => (
              <div
                key={index}
                ref={index === accidents.length - 1 ? lastElementRef : null}
                className="p-6 rounded-xl bg-white/50 hover:bg-white/70 transition-all duration-200
                         transform hover:-translate-y-1"
              >
                <div className="flex justify-between items-start">
                  <div>
                    <h3 className="font-semibold text-gray-800">
                      {accident.region} - {accident.accidentSeverity}
                    </h3>
                    <p className="text-sm text-gray-600">
                      Date: {accident.month} {accident.year}, {accident.timeOfDay}
                    </p>
                  </div>
                  <span className="px-3 py-1 rounded-full text-sm font-medium"
                        style={{
                          backgroundColor: accident.accidentSeverity === 'Fatal' ? 'rgba(239, 68, 68, 0.1)' : 
                                         accident.accidentSeverity === 'Serious' ? 'rgba(245, 158, 11, 0.1)' :
                                         'rgba(34, 197, 94, 0.1)',
                          color: accident.accidentSeverity === 'Fatal' ? 'rgb(239, 68, 68)' :
                                accident.accidentSeverity === 'Serious' ? 'rgb(245, 158, 11)' :
                                'rgb(34, 197, 94)'
                        }}>
                    {accident.accidentSeverity}
                  </span>
                </div>
                <p className="text-sm text-gray-700 mt-2">Cause: {accident.accidentCause}</p>
              </div>
            ))}
          </div>
          {loading && (
            <div className="text-center py-6">
              <div className="inline-block animate-spin rounded-full h-8 w-8 border-4 border-blue-500 border-t-transparent"></div>
            </div>
          )}
        </GlassCard>
      </div>
    </div>
  );
};

export default TrafficDataDashboard;