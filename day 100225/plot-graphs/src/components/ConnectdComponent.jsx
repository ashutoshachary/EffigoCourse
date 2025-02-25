import React, { useState, useEffect } from 'react';
import {
  LineChart, Line, BarChart, Bar, PieChart, Pie, Scatter, ScatterChart,
  XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer,
  Cell, RadarChart, Radar, PolarGrid, PolarAngleAxis, PolarRadiusAxis
} from 'recharts';
import { Loader } from 'lucide-react';
import './ConnectdCss.css'

const COLORS = ['#FF6B6B', '#4ECDC4', '#45B7D1', '#96CEB4', '#FFEEAD', '#D4A5A5'];

const RestaurantDashboardConnected = () => {
  const [selectedRestaurant, setSelectedRestaurant] = useState(null);
  const [detailData, setDetailData] = useState(null);
  const [chartData, setChartData] = useState({
    scatterData: [],
    ratingCorrelation: {},
    cuisineAnalysis: {},
    priceAnalysis: {}
  });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchDashboardData();
  }, []);

  const fetchDashboardData = async () => {
    try {
      const [scatterRes, ratingRes, cuisineRes, priceRes] = await Promise.all([
        fetch('http://localhost:8083/api/restaurants/scatter-data'),
        fetch('http://localhost:8083/api/restaurants/rating-time-correlation'),
        fetch('http://localhost:8083/api/restaurants/cuisine-analysis'),
        fetch('http://localhost:8083/api/restaurants/price-range-analysis')
      ]);

      const [scatterData, ratingData, cuisineData, priceData] = await Promise.all([
        scatterRes.json(),
        ratingRes.json(),
        cuisineRes.json(),
        priceRes.json()
      ]);

      setChartData({
        scatterData,
        ratingCorrelation: ratingData,
        cuisineAnalysis: cuisineData,
        priceAnalysis: priceData
      });
      setLoading(false);
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
      setLoading(false);
    }
  };

  const fetchRestaurantDetails = async (id) => {
    try {
      const response = await fetch(`http://localhost:8083/api/restaurants/${id}`);
      const data = await response.json();
      setDetailData(data);
      setSelectedRestaurant(data.restaurant);
    } catch (error) {
      console.error('Error fetching restaurant details:', error);
    }
  };

  const handleDataPointClick = (data) => {
    if (data && data.id) {
      fetchRestaurantDetails(data.id);
    }
  };

  const RestaurantCard = ({ restaurant, isMain = false }) => (
    <div className={`restaurant-card ${isMain ? 'main-card' : ''}`}
         onClick={() => handleDataPointClick({ id: restaurant.id })}>
      <h3>{restaurant.hotelName}</h3>
      <div className="restaurant-info">
        <div className="info-row">
          <span className="label">Rating:</span>
          <span className="value">{restaurant.rating}⭐</span>
        </div>
        <div className="info-row">
          <span className="label">Delivery Time:</span>
          <span className="value">{restaurant.timeMinutes} mins</span>
        </div>
        <div className="info-row">
          <span className="label">Location:</span>
          <span className="value">{restaurant.location}</span>
        </div>
        <div className="info-row">
          <span className="label">Cuisine:</span>
          <span className="value">{restaurant.foodType}</span>
        </div>
        {restaurant.offerPercentage && (
          <div className="offer-badge">
            {restaurant.offerPercentage}% OFF above ₹{restaurant.offerAbove}
          </div>
        )}
      </div>
    </div>
  );

  const renderScatterPlot = () => (
    <div className="chart-container">
      <h3>Delivery Time vs Rating Distribution</h3>
      <ResponsiveContainer width="100%" height={300}>
        <ScatterChart margin={{ top: 20, right: 20, bottom: 20, left: 20 }}>
          <CartesianGrid strokeDasharray="3 3" />
          <XAxis type="number" dataKey="deliveryTime" name="Delivery Time" unit=" mins" />
          <YAxis type="number" dataKey="rating" name="Rating" unit="⭐" />
          <Tooltip cursor={{ strokeDasharray: '3 3' }} />
          <Scatter
            data={chartData.scatterData}
            fill="#8884d8"
            onClick={handleDataPointClick}
            cursor="pointer"
          >
            {chartData.scatterData.map((entry, index) => (
              <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
            ))}
          </Scatter>
        </ScatterChart>
      </ResponsiveContainer>
    </div>
  );

  const renderCuisineRadar = () => {
    const data = Object.entries(chartData.cuisineAnalysis.averageRatings || {})
      .map(([name, value]) => ({
        name,
        value: parseFloat(value.toFixed(2))
      }));

    return (
      <div className="chart-container">
        <h3>Cuisine Rating Analysis</h3>
        <ResponsiveContainer width="100%" height={300}>
          <RadarChart cx="50%" cy="50%" outerRadius="80%" data={data}>
            <PolarGrid />
            <PolarAngleAxis dataKey="name" />
            <PolarRadiusAxis />
            <Radar
              name="Rating"
              dataKey="value"
              stroke="#FF6B6B"
              fill="#FF6B6B"
              fillOpacity={0.6}
              onClick={handleDataPointClick}
            />
          </RadarChart>
        </ResponsiveContainer>
      </div>
    );
  };

  const renderPriceAnalysis = () => {
    const data = Object.entries(chartData.priceAnalysis.priceRanges || {}).map(([range, stats]) => ({
      range,
      count: stats.count,
      rating: parseFloat(stats.averageRating.toFixed(2))
    }));

    return (
      <div className="chart-container">
        <h3>Price Range Analysis</h3>
        <ResponsiveContainer width="100%" height={300}>
          <BarChart data={data}>
            <CartesianGrid strokeDasharray="3 3" />
            <XAxis dataKey="range" />
            <YAxis yAxisId="left" orientation="left" stroke="#FF6B6B" />
            <YAxis yAxisId="right" orientation="right" stroke="#4ECDC4" />
            <Tooltip />
            <Legend />
            <Bar yAxisId="left" dataKey="count" name="Number of Restaurants" fill="#FF6B6B" onClick={handleDataPointClick} />
            <Bar yAxisId="right" dataKey="rating" name="Average Rating" fill="#4ECDC4" onClick={handleDataPointClick} />
          </BarChart>
        </ResponsiveContainer>
      </div>
    );
  };

  return (
    <div className="dashboard-container">
      {loading ? (
        <div className="loading-container">
          <Loader className="loading-spinner" />
          <p>Loading dashboard data...</p>
        </div>
      ) : (
        <>
          <div className="charts-grid">
            {renderScatterPlot()}
            {renderCuisineRadar()}
            {renderPriceAnalysis()}
          </div>

          {selectedRestaurant && (
            <div className="detail-section">
              <h2>Restaurant Details</h2>
              <RestaurantCard restaurant={selectedRestaurant} isMain={true} />
              
              {detailData && (
                <>
                  <div className="related-section">
                    <h3>Similar Cuisine</h3>
                    <div className="cards-container">
                      {detailData.similarCuisine?.map(restaurant => (
                        <RestaurantCard key={restaurant.id} restaurant={restaurant} />
                      ))}
                    </div>
                  </div>

                  <div className="related-section">
                    <h3>Nearby Restaurants</h3>
                    <div className="cards-container">
                      {detailData.nearbyRestaurants?.map(restaurant => (
                        <RestaurantCard key={restaurant.id} restaurant={restaurant} />
                      ))}
                    </div>
                  </div>
                </>
              )}
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default RestaurantDashboardConnected;