import React, { useState, useEffect } from 'react';
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts';
import './LocationDistribution.css';

const LocationDistribution = () => {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('http://localhost:8083/api/restaurants/locations/distribution');
        if (!response.ok) {
          throw new Error('Failed to fetch data');
        }
        const jsonData = await response.json();
        const transformedData = Object.entries(jsonData)
          .map(([location, count]) => ({
            location,
            count: count || 0
          }))
          .sort((a, b) => b.count - a.count);
        setData(transformedData);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, []);

  if (loading) {
    return (
      <div className="location-card">
        <div className="location-loading-container">
          <p>Loading data...</p>
        </div>
      </div>
    );
  }

  if (error) {
    return (
      <div className="location-card">
        <div className="location-error-container">
          <p>Error: {error}</p>
        </div>
      </div>
    );
  }

  return (
    <div className="location-card">
      <div className="location-card-header">
        <h2 className="location-card-title">Restaurants by Location</h2>
      </div>
      <div className="location-card-content">
        <div className="location-chart-container">
          <ResponsiveContainer width="100%" height="100%">
            <BarChart
              data={data}
              layout="vertical"
              margin={{
                top: 20,
                right: 120,
                left: 200,
                bottom: 20
              }}
            >
              <CartesianGrid strokeDasharray="3 3" horizontal={false} />
              <XAxis 
                type="number"
                label={{ 
                  value: 'Number of Restaurants', 
                  position: 'bottom',
                  offset: 0
                }}
                domain={[0, 'auto']}
              />
              <YAxis 
                dataKey="location" 
                type="category"
                width={180}
                tick={{ 
                  fontSize: 12,
                  width: 170,
                  wordWrap: 'break-word'
                }}
                interval={0}
              />
              <Tooltip />
              <Legend />
              <Bar
                dataKey="count"
                name="Number of Restaurants"
                radius={[0, 4, 4, 0]}
                label={{ 
                  position: 'right',
                  formatter: (value) => value > 0 ? value : '',
                }}
                minPointSize={3}
              />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  );
};

export default LocationDistribution;