import React, { useState, useEffect } from 'react';
import { Search } from 'lucide-react';
import { PieChart, LineChart, BarChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Pie, Bar } from 'recharts';

const RDboard = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [chartData, setChartData] = useState({ pie: [], line: [], bar: [] });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [page, setPage] = useState(0);
  const [searchQuery, setSearchQuery] = useState('');

  const fetchRestaurants = async () => {
    try {
      const response = await fetch(`http://localhost:8083/api/restaurants?page=${page}&size=10`);
      const data = await response.json();
      setRestaurants(prev => [...prev, ...data.content]);
    } catch (err) {
      setError('Failed to fetch restaurants');
    }
  };

  const fetchChartData = async () => {
    try {
      const [pieRes, lineRes, barRes] = await Promise.all([
        fetch('http://localhost:8083/api/restaurants/charts/pie'),
        fetch('http://localhost:8083/api/restaurants/charts/line'),
        fetch('http://localhost:8083/api/restaurants/charts/bar')
      ]);

      const [pieData, lineData, barData] = await Promise.all([
        pieRes.json(),
        lineRes.json(),
        barRes.json()
      ]);

      setChartData({
        pie: Object.entries(pieData).map(([name, value]) => ({ name, value })),
        line: Object.entries(lineData).map(([time, rating]) => ({ 
          time: parseInt(time), 
          rating: parseFloat(rating.toFixed(2)) 
        })),
        bar: Object.entries(barData).map(([amount, count]) => ({ 
          amount: parseInt(amount), 
          count 
        }))
      });
    } catch (err) {
      setError('Failed to fetch chart data');
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async (query) => {
    try {
      setLoading(true);
      const response = await fetch(
        `http://localhost:8083/api/restaurants/location/search?query=${query}`
      );
      const data = await response.json();
      setRestaurants(data.content);
    } catch (err) {
      setError('Search failed');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchRestaurants();
    fetchChartData();
  }, []);

  useEffect(() => {
    if (searchQuery) {
      const timeoutId = setTimeout(() => {
        handleSearch(searchQuery);
      }, 300);
      return () => clearTimeout(timeoutId);
    }
  }, [searchQuery]);

  if (loading) {
    return (
      <div className="loading-container">
        <div className="loading-spinner"></div>
      </div>
    );
  }

  return (
    <div className="dashboard-container">
      {error && <div className="error-alert">{error}</div>}

      <div className="search-container">
        <Search className="search-icon" size={20} />
        <input
          type="text"
          className="search-input"
          placeholder="Search restaurants by location..."
          onChange={(e) => setSearchQuery(e.target.value)}
        />
      </div>

      <div className="restaurant-grid">
        {restaurants.map(restaurant => (
          <div key={restaurant.id} className="restaurant-card">
            <div className="restaurant-header">
              <h3 className="restaurant-name">{restaurant.hotelName}</h3>
              <div className="rating-container">
                <span className="rating-star">★</span>
                <span className="rating-value">{restaurant.rating}</span>
              </div>
            </div>
            <p className="food-type">{restaurant.foodType}</p>
            <div className="restaurant-footer">
              <span className="delivery-time">{restaurant.timeMinutes} mins</span>
              {restaurant.offerPercentage > 0 && (
                <span className="offer">
                  {restaurant.offerPercentage}% OFF above ₹{restaurant.offerAbove}
                </span>
              )}
            </div>
          </div>
        ))}
      </div>

      <div className="charts-grid">
        <div className="chart-container">
          <h3 className="chart-title">Food Type Distribution</h3>
          <PieChart width={300} height={300}>
            <Pie
              data={chartData.pie}
              cx={150}
              cy={150}
              innerRadius={60}
              outerRadius={100}
              fill="#8884d8"
              dataKey="value"
              label={({ name }) => name}
            />
            <Tooltip />
          </PieChart>
        </div>

        <div className="chart-container">
          <h3 className="chart-title">Average Ratings by Time</h3>
          <LineChart width={300} height={300} data={chartData.line} >
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="time" />
            <YAxis />
            <Tooltip />
            <Line type="monotone" dataKey="rating" stroke="#8884d8" />
          </LineChart>
        </div>

        <div className="chart-container">
          <h3 className="chart-title">Offers Distribution</h3>
          <BarChart width={300} height={300} data={chartData.bar}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="amount" />
            <YAxis />
            <Tooltip />
            <Bar dataKey="count" fill="#8884d8" />
          </BarChart>
        </div>
      </div>
    </div>
  );
};

export default RDboard;