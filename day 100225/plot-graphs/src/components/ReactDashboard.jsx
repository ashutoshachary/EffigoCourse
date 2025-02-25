import React, { useState, useEffect, useCallback } from 'react';
import { PieChart, BarChart, LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Pie, Bar } from 'recharts';
import '../index.css';
import myImg from '../assets/location-map.png'
import BarGraphComponent from './BarGraph';
import PieChartComponent from './PieChartComponemt';
import BarChartComponent1 from './SecondBarData';
import FlexibleDashboard from './InteractiveComponent';
import FoodScatterPlot from './ScatterPlot';
import LocationDistribution from './LocationsAndResturants';
import RestaurantDashboardConnected from './ConnectdComponent';
import RestaurantDashboard123 from './NewComponent';
// Data transformation functions
const transformPieData = (data) => {
  return Object.entries(data)
    .map(([name, value]) => ({
      name: name.length > 20 ? name.substring(0, 20) + '...' : name,
      value: Number(value)
    }))
    .filter(item => item.value > 0);
};

const transformLineData = (data) => {
  return Object.entries(data)
    .map(([time, rating]) => ({
      time: parseInt(time),
      rating: parseFloat(rating.toFixed(2))
    }))
    .sort((a, b) => a.time - b.time);
};

const transformBarData = (data) => {
  return Object.entries(data)
    .map(([amount, count]) => ({
      amount: parseInt(amount),
      count: Number(count)
    }))
    .sort((a, b) => a.amount - b.amount);
};

const RestaurantDashboard = () => {
  const [restaurants, setRestaurants] = useState([]);
  const [pieData, setPieData] = useState([]);
  const [lineData, setLineData] = useState([]);
  const [barData, setBarData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [searchTerm, setSearchTerm] = useState('');
  const [selectedCategory, setSelectedCategory] = useState('all');
  const [locations, setLocations] = useState([]);
  const [foodTypes, setFoodTypes] = useState([]);
  const [selectedLocation, setSelectedLocation] = useState('');
  const [selectedFoodType, setSelectedFoodType] = useState('');
  const [error, setError] = useState(null);
  
  // console.log(restaurants)

  const pageSize = 9;
  
  const times = lineData.map(entry => entry.time);
  const ratings = lineData.map(entry => entry.rating);

  const mixedData = {
    name: 'Offer Performance by Time & Category',
    data: barData.map((item, index) => ({
      category: pieData[index % pieData.length]?.name || `Category ${index + 1}`,
      time: lineData[index % lineData.length]?.time || (10 + index),
      offerAmount: item.amount,
      offerCount: item.count,
      rating: lineData[index % lineData.length]?.rating || 0
    }))
  };
  // console.log("mix data", mixedData)




  const fetchLocations = async () => {
    try {
      const response = await fetch('http://localhost:8083/api/restaurants/locations');
      if (!response.ok) throw new Error('Failed to fetch locations');
      const data = await response.json();
      const validLocations = Array.isArray(data) ? data.filter(Boolean) : [];
      setLocations(validLocations);
    } catch (error) {
      console.error('Error fetching locations:', error);
      setError('Failed to load locations');
      setLocations([]);
    }
  };

  const fetchFoodTypes = async () => {
    try {
      const response = await fetch('http://localhost:8083/api/restaurants/foodtypes');
      if (!response.ok) throw new Error('Failed to fetch food types');
      const data = await response.json();
      const validFoodTypes = Array.isArray(data) ? data.filter(Boolean) : [];
      setFoodTypes(validFoodTypes);
    } catch (error) {
      console.error('Error fetching food types:', error);
      setError('Failed to load food types');
      setFoodTypes([]);
    }
  };

  const fetchRestaurants = async (page, search = searchTerm) => {
    try {
      const params = new URLSearchParams({
        page: page.toString(),
        size: pageSize.toString(),
      });

      if (search) params.append('search', search);
      if (selectedLocation) params.append('location', selectedLocation);
      if (selectedFoodType) params.append('foodType', selectedFoodType);

      const response = await fetch(`http://localhost:8083/api/restaurants?${params.toString()}`);
      if (!response.ok) throw new Error('Failed to fetch restaurants');

      const data = await response.json();
      setRestaurants(data.content || []);
      setTotalPages(data.totalPages || 0);
      setCurrentPage(page);
    } catch (error) {
      console.error('Error fetching restaurants:', error);
      setError('Failed to load restaurants');
      setRestaurants([]);
    }
  };

  const fetchChartData = async () => {
    try {
      const [pieRes, lineRes, barRes] = await Promise.all([
        fetch(`http://localhost:8083/api/restaurants/charts/pie?category=${selectedCategory}`),
        fetch('http://localhost:8083/api/restaurants/charts/line'),
        fetch('http://localhost:8083/api/restaurants/charts/bar')
      ]);

      const [pieData, lineData, barData] = await Promise.all([
        pieRes.json(),
        lineRes.json(),
        barRes.json()
      ]);

      setPieData(transformPieData(pieData));
      setLineData(transformLineData(lineData));
      setBarData(transformBarData(barData));
      setLoading(false);
    } catch (error) {
      console.error('Error fetching chart data:', error);
      setLoading(false);
    }
  };

  // Debounced search function
  const debouncedSearch = useCallback(
    debounce((term) => {
      fetchRestaurants(0, term);
    }, 500),
    []
  );

  useEffect(() => {
    fetchLocations();
    fetchFoodTypes();
  }, []);

  useEffect(() => {
    fetchRestaurants(currentPage);
    fetchChartData();
  }, [currentPage, selectedLocation, selectedFoodType, selectedCategory]);

  const handleSearch = (e) => {
    const term = e.target.value;
    setSearchTerm(term);
    debouncedSearch(term);
  };

  const handleLocationChange = (e) => {
    setSelectedLocation(e.target.value);
    setCurrentPage(0);
  };

  const handleFoodTypeChange = (type) => {
    setSelectedFoodType(type);
    setCurrentPage(0);
  };

  const handleCategoryChange = (e) => {
    setSelectedCategory(e.target.value);
  };

  if (loading) {
    return (
      <div className="loading-container">
        <div className="loading-text">Loading dashboard data...</div>
      </div>
    );
  }

  if (error) {
    return <div className="error-message">{error}</div>;
  }

  return (
    <div className="dashboard">
      <div className="nav-filters">
        <input
          type="text"
          placeholder="Search restaurants..."
          value={searchTerm}
          onChange={handleSearch}
          className="search-input"
        />
        <img src={myImg} alt="Description" width={30} height={30} />
        <select
          value={selectedLocation}
          onChange={handleLocationChange}
          className="location-select"
        >

          <option value="">All Locations</option>
          {Array.isArray(locations) && locations.map(location => (
            <option key={location} value={location}>
              {location}
            </option>
          ))}
        </select>
      </div>

      <div className="food-types-row">
        <button
          className={`food-type-btn ${selectedFoodType === '' ? 'active' : ''}`}
          onClick={() => handleFoodTypeChange('')}
        >
          All
        </button>
        {Array.isArray(foodTypes) && foodTypes.map(type => (
          <button
            key={type}
            className={`food-type-btn ${selectedFoodType === type ? 'active' : ''}`}
            onClick={() => handleFoodTypeChange(type)}
          >
            {type}
          </button>
        ))}
      </div>

      <div className="restaurant-grid">
        {restaurants.map(restaurant => (
          <div key={restaurant.id} className="restaurant-card">
            <div className="restaurant-header">
              <h3>{restaurant.hotelName}</h3>
              <span className="rating">★ {restaurant.rating}</span>
            </div>
            <p className="food-type">{restaurant.foodType}</p>
            <p>{restaurant.location}</p>
            <div className="restaurant-footer">
              <span>{restaurant.timeMinutes} mins</span>
              <span className="offer">
                {restaurant.offerPercentage
                  ? `${restaurant.offerPercentage}% OFF above ₹${restaurant.offerAbove}`
                  : restaurant.offerAbove
                    ? `Upto ₹${restaurant.offerAbove} OFF`
                    : "No offer available"
                }
              </span>
            </div>
          </div>
        ))}
      </div>

      <div className="pagination">
        <button
          onClick={() => fetchRestaurants(currentPage - 1)}
          disabled={currentPage === 0}
          className="pagination-button"
        >
          Previous
        </button>
        <span className="page-info">
          Page {currentPage + 1} of {totalPages}
        </span>
        <button
          onClick={() => fetchRestaurants(currentPage + 1)}
          disabled={currentPage === totalPages - 1}
          className="pagination-button"
        >
          Next
        </button>
      </div>

      <select
        value={selectedCategory}
        onChange={handleCategoryChange}
        className="category-select"
      >
        <option value="all">All Categories</option>
        <option value="Indian">Indian Cuisine</option>
        <option value="Fast Food">Fast Food</option>
        <option value="International">International Cuisine</option>
        <option value="Desserts">Desserts & Bakery</option>
      </select>

      <div className="charts-grid" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', flexWrap: 'wrap' }}>
        <div className="card">
          <div className="card-header">
            <h2>Food Type Distribution</h2>
          </div>
          <PieChart width={300} height={300}>
            <Pie
              data={pieData}
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

        <div className="card">
          <div className="card-header">
            <h2>Average Ratings by Time</h2>
          </div>
          <LineChart width={300} height={300} data={lineData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="time" label={{ value: 'Time (hours)', position: 'bottom' }} />
            <YAxis label={{ value: 'Rating', angle: -90, position: 'insideLeft' }} />
            <Tooltip />
            <Line type="monotone" dataKey="rating" stroke="#8884d8" />
          </LineChart>
        </div>

        <div className="card">
          <div className="card-header">
            <h2>Offers Distribution</h2>
          </div>
          <BarChart width={300} height={300} data={barData}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="amount" label={{ value: 'Minimum Order Amount (₹)', position: 'bottom' }} />
            <YAxis label={{ value: 'Number of Restaurants', angle: -90, position: 'insideLeft' }} />
            <Tooltip />
            <Bar dataKey="count" fill="#8884d8" />
          </BarChart>
        </div>
        <PieChartComponent data={pieData} />
        <BarGraphComponent times={times} ratings={ratings}
          title="Rating vs Time"
          subtitle="Ratings Visualization" />
        <BarChartComponent1 barData={barData} />
        <FlexibleDashboard barData={barData} pieData={pieData} lineData={lineData} />
        <FoodScatterPlot data={mixedData.data} />

        <LocationDistribution/>

      </div>
      <RestaurantDashboardConnected/>
      <RestaurantDashboard123/>

    </div>
  );
};

function debounce(func, wait) {
  let timeout;
  return function executedFunction(...args) {
    const later = () => {
      clearTimeout(timeout);
      func(...args);
    };
    clearTimeout(timeout);
    timeout = setTimeout(later, wait);
  };
}

export default RestaurantDashboard;