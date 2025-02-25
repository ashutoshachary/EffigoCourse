import React, { useState, useEffect } from 'react';
import ReactECharts from 'echarts-for-react';
// import { InfoCircle, RefreshCw } from 'lucide-react';
import './NewComponent.css'

const RestaurantDashboard123 = () => {
  const [selectedLocation, setSelectedLocation] = useState(null);
  const [selectedCuisine, setSelectedCuisine] = useState(null);
  const [scatterData, setScatterData] = useState([]);
  const [locationData, setLocationData] = useState({});
  const [cuisineData, setCuisineData] = useState({});
  const [ratingTimeData, setRatingTimeData] = useState({});
  const [priceRangeData, setPriceRangeData] = useState({});
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetchAllData();
  }, []);

  useEffect(() => {
    if (selectedLocation || selectedCuisine) {
      fetchFilteredData();
    }
  }, [selectedLocation, selectedCuisine]);

  const fetchAllData = async () => {
    setLoading(true);
    setError(null);
    try {
      const [scatter, location, cuisine, ratingTime, priceRange] = await Promise.all([
        fetch('http://localhost:8083/api/restaurants/scatter-data').then(res => res.json()),
        fetch('http://localhost:8083/api/restaurants/locations/distribution').then(res => res.json()),
        fetch('http://localhost:8083/api/restaurants/cuisine-analysis').then(res => res.json()),
        fetch('http://localhost:8083/api/restaurants/rating-time-correlation').then(res => res.json()),
        fetch('http://localhost:8083/api/restaurants/price-range-analysis').then(res => res.json())
      ]);

      setScatterData(scatter);
      setLocationData(location);
      setCuisineData(cuisine);
      setRatingTimeData(ratingTime);
      setPriceRangeData(priceRange);
    } catch (error) {
      setError('Failed to fetch data. Please try again.');
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  const fetchFilteredData = async () => {
    setLoading(true);
    setError(null);
    try {
      let queryParams = new URLSearchParams();
      if (selectedLocation) queryParams.append('location', selectedLocation);
      if (selectedCuisine) queryParams.append('foodType', selectedCuisine);

      const [scatter, cuisine, ratingTime] = await Promise.all([
        fetch(`http://localhost:8083/api/restaurants/scatter-data?${queryParams}`).then(res => res.json()),
        fetch(`http://localhost:8083/api/restaurants/cuisine-analysis?${queryParams}`).then(res => res.json()),
        fetch(`http://localhost:8083/api/restaurants/rating-time-correlation?${queryParams}`).then(res => res.json())
      ]);

      setScatterData(scatter);
      setCuisineData(cuisine);
      setRatingTimeData(ratingTime);
    } catch (error) {
      setError('Failed to fetch filtered data. Please try again.');
      console.error('Error fetching filtered data:', error);
    } finally {
      setLoading(false);
    }
  };

  const onChartClick = (params, type) => {
    if (type === 'location') {
      setSelectedLocation(prevLocation => 
        prevLocation === params.name ? null : params.name
      );
    } else if (type === 'cuisine') {
      setSelectedCuisine(prevCuisine => 
        prevCuisine === params.name ? null : params.name
      );
    }
  };

  const getScatterOption = () => ({
    title: {
      text: 'Rating vs Delivery Time',
      textStyle: {
        fontFamily: 'Arial',
        fontSize: 16
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: function(params) {
        return `${params.data.name}<br/>
                Rating: ${params.data.rating}<br/>
                Delivery Time: ${params.data.deliveryTime} mins`;
      }
    },
    xAxis: {
      name: 'Delivery Time (minutes)',
      type: 'value'
    },
    yAxis: {
      name: 'Rating',
      type: 'value'
    },
    series: [{
      type: 'scatter',
      data: scatterData.map(item => ({
        name: item.name,
        value: [item.deliveryTime, item.rating],
        rating: item.rating,
        deliveryTime: item.deliveryTime
      })),
      itemStyle: {
        color: function(params) {
          const value = params.data.rating;
          return `rgba(51, 153, 255, ${value / 5})`;
        }
      },
      emphasis: {
        focus: 'series',
        itemStyle: {
          shadowBlur: 10,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }]
  });

  const getLocationOption = () => ({
    title: {
      text: 'Restaurant Distribution by Location',
      textStyle: {
        fontFamily: 'Arial',
        fontSize: 16
      }
    },
    tooltip: {
      trigger: 'item',
      formatter: '{b}: {c} ({d}%)'
    },
    series: [{
      type: 'pie',
      radius: '70%',
      data: Object.entries(locationData).map(([name, value]) => ({
        name,
        value,
        itemStyle: {
          color: selectedLocation === name ? '#ff9999' : undefined
        }
      })),
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }]
  });

  const getCuisineOption = () => ({
    title: {
      text: 'Cuisine Distribution',
      textStyle: {
        fontFamily: 'Arial',
        fontSize: 16
      }
    },
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    xAxis: {
      type: 'category',
      data: Object.keys(cuisineData.cuisineCounts || {}),
      axisLabel: {
        rotate: 45,
        interval: 0
      }
    },
    yAxis: {
      type: 'value'
    },
    series: [{
      type: 'bar',
      data: Object.entries(cuisineData.cuisineCounts || {}).map(([name, value]) => ({
        value,
        itemStyle: {
          color: selectedCuisine === name ? '#ff9999' : '#3399ff'
        }
      })),
      name: 'Number of Restaurants'
    }]
  });

  const getRatingTimeOption = () => ({
    title: {
      text: 'Average Rating by Time Range',
      textStyle: {
        fontFamily: 'Arial',
        fontSize: 16
      }
    },
    tooltip: {
      trigger: 'axis'
    },
    xAxis: {
      type: 'category',
      data: Object.keys(ratingTimeData.timeRanges || {})
    },
    yAxis: {
      type: 'value',
      name: 'Average Rating',
      min: 3,
      max: 5
    },
    series: [{
      type: 'line',
      data: Object.values(ratingTimeData.timeRanges || {}),
      smooth: true,
      name: 'Average Rating',
      lineStyle: {
        width: 3,
        color: '#3399ff'
      },
      symbol: 'circle',
      symbolSize: 8,
      itemStyle: {
        color: '#3399ff'
      }
    }]
  });

  return (
    <div className="dashboard">
      {error && (
        <div className="error-message">
          
          <span>{error}</span>
        </div>
      )}

      <div className="filters">
        {(selectedLocation || selectedCuisine) && (
          <div className="active-filters">
            <span>Active Filters:</span>
            {selectedLocation && (
              <div className="filter-tag">
                Location: {selectedLocation}
              </div>
            )}
            {selectedCuisine && (
              <div className="filter-tag">
                Cuisine: {selectedCuisine}
              </div>
            )}
            <button 
              className="clear-filters"
              onClick={() => {
                setSelectedLocation(null);
                setSelectedCuisine(null);
                fetchAllData();
              }}
            >
             
              Clear Filters
            </button>
          </div>
        )}
      </div>

      <div className="charts-grid">
        <div className="chart-card">
          <ReactECharts 
            option={getScatterOption()} 
            style={{ height: '400px' }}
            onEvents={{
              click: (params) => onChartClick(params, 'scatter')
            }}
            showLoading={loading}
          />
        </div>

        <div className="chart-card">
          <ReactECharts 
            option={getLocationOption()} 
            style={{ height: '400px' }}
            onEvents={{
              click: (params) => onChartClick(params, 'location')
            }}
            showLoading={loading}
          />
        </div>

        <div className="chart-card">
          <ReactECharts 
            option={getCuisineOption()} 
            style={{ height: '400px' }}
            onEvents={{
              click: (params) => onChartClick(params, 'cuisine')
            }}
            showLoading={loading}
          />
        </div>

        <div className="chart-card">
          <ReactECharts 
            option={getRatingTimeOption()} 
            style={{ height: '400px' }}
            showLoading={loading}
          />
        </div>
      </div>
    </div>
  );
};

export default RestaurantDashboard123;