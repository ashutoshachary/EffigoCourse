import React, { useEffect, useRef, useState } from 'react';
import * as echarts from 'echarts/core';
import { TitleComponent, TooltipComponent, LegendComponent, GridComponent, DatasetComponent, TransformComponent, ToolboxComponent } from 'echarts/components';
import { PieChart, BarChart, LineChart } from 'echarts/charts';
import { LabelLayout } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

echarts.use([
    TitleComponent, TooltipComponent, LegendComponent, GridComponent,
    DatasetComponent, TransformComponent, ToolboxComponent,
    PieChart, BarChart, LineChart, CanvasRenderer, LabelLayout
]);

const FlexibleDashboard = ({ barData, pieData, lineData }) => {
    const chartRef = useRef(null);
    const [chartInstance, setChartInstance] = useState(null);
    const [selectedData, setSelectedData] = useState('barData');
    const [selectedChart, setSelectedChart] = useState('bar');

    
    
    // Corrected dataset mappings
    const datasets = {
        barData: {
            name: 'Offer Distribution',
            data: barData.map(item => ({ category: item.amount, value: item.count }))
        },
        lineData: {
            name: 'Rating',
            data: lineData.map(item => ({ time: item.time, value: item.rating }))
        },
        pieData: {
            name: 'Foods',
            data: pieData.map(item => ({ name: item.name, value: item.value }))
        },

    };

    // Function to get chart options dynamically
    const getChartOption = (dataKey, chartType) => {
        const dataset = datasets[dataKey];
        const baseOption = {
            title: { text: dataset.name, left: 'center', top: 20 },
            tooltip: { trigger: 'item' },
            legend: { bottom: 10 },
            toolbox: { feature: { saveAsImage: {}, dataView: {}, restore: {} } }
        };

        switch (chartType) {
            case 'bar':
                return {
                    ...baseOption,
                    xAxis: { type: 'category', data: dataset.data.map(item => item.category) },
                    yAxis: { type: 'value' },
                    series: [{ type: 'bar', data: dataset.data.map(item => item.value), itemStyle: { borderRadius: 5 } }]
                };
            case 'line':
                return {
                    ...baseOption,
                    xAxis: { type: 'category', data: dataset.data.map(item => item.time) },
                    yAxis: { type: 'value' },
                    series: [{ type: 'line', data: dataset.data.map(item => item.value), smooth: true, symbol: 'circle', symbolSize: 8 }]
                };
            case 'pie':
                return {
                    ...baseOption,
                    series: [{
                        type: 'pie',
                        radius: '50%',
                        data: dataset.data,
                        emphasis: { itemStyle: { shadowBlur: 10, shadowColor: 'rgba(0,0,0,0.5)' } }
                    }]
                };
            default:
                return baseOption;
        }
    };

    useEffect(() => {
        if (!chartInstance && chartRef.current) {
            const instance = echarts.init(chartRef.current);
            setChartInstance(instance);
            instance.setOption(getChartOption(selectedData, selectedChart));
        }
        return () => chartInstance?.dispose();
    }, []);

    useEffect(() => {
        if (chartInstance) {
            chartInstance.setOption(getChartOption(selectedData, selectedChart), true);
        }
    }, [selectedData, selectedChart]);

    return (
        <div style={{ minHeight: '100vh', backgroundColor: '#f9f9f9', padding: '20px' , width:'100%'}}>
            <div style={{ backgroundColor: '#fff', borderRadius: '10px', boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)', padding: '20px' ,width:'100%'}}>
                <h1 style={{ fontSize: '24px', fontWeight: 'bold', marginBottom: '20px', color: '#333' }}>📊 Dynamic Chart Visualization</h1>
                <div style={{ display: 'flex', gap: '20px', marginBottom: '20px' }}>
                    <div>
                        <label style={{ fontSize: '14px', color: '#666', display: 'block' }}>Select Dataset</label>
                        <select style={{ padding: '8px', border: '1px solid #ddd', borderRadius: '5px' }} value={selectedData} onChange={(e) => setSelectedData(e.target.value)}>
                            {Object.keys(datasets).map(key => (<option key={key} value={key}>{datasets[key].name}</option>))}
                        </select>
                    </div>
                    <div>
                        <label style={{ fontSize: '14px', color: '#666', display: 'block' }}>Select Chart Type</label>
                        <select style={{ padding: '8px', border: '1px solid #ddd', borderRadius: '5px' }} value={selectedChart} onChange={(e) => setSelectedChart(e.target.value)}>
                            <option value="bar">Bar Chart</option>
                            <option value="line">Line Chart</option>
                            <option value="pie">Pie Chart</option>
                        </select>
                    </div>
                </div>
                <div ref={chartRef} style={{ width: 'auto', height: '600px' , alignSelf: 'center'}} />
            </div>
        </div>
    );
};

export default FlexibleDashboard;
