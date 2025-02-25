import React, { useEffect, useRef } from 'react';
import * as echarts from 'echarts/core';
import { ScatterChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  MarkLineComponent,
  MarkAreaComponent,
  MarkPointComponent
} from 'echarts/components';
import { CanvasRenderer } from 'echarts/renderers';
import './ScatterPlot.css';

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  MarkLineComponent,
  MarkAreaComponent,
  MarkPointComponent,
  ScatterChart,
  CanvasRenderer
]);

const FoodScatterPlot = ({ data }) => {
  const chartRef = useRef(null);
  const chartInstance = useRef(null);

  useEffect(() => {
    console.log("data", data);
    if (chartRef.current) {
      if (!chartInstance.current) {
        chartInstance.current = echarts.init(chartRef.current);
      }

      const timeVsRatingData = data.map(item => [item.time, item.rating, item.category]);
      const offerData = data.map(item => [item.offerAmount, item.offerCount, item.category]);

      const option = {
        title: {
          text: 'Food Category Analysis',
         
        },
        tooltip: {
          showDelay: 0,
          formatter: function(params) {
            return `${params.data[2]}<br/>${params.seriesName}<br/>` +
                   `X: ${params.value[0]}<br/>Y: ${params.value[1]}`;
          },
          axisPointer: {
            show: true,
            type: 'cross',
            lineStyle: {
              type: 'dashed',
              width: 1
            }
          }
        },
        legend: {
          data: ['Time vs Rating', 'Offer Analysis'],
          left: 'center',
          top: 30
        },
        xAxis: {
          type: 'value',
          name: 'Time (hours) / Offer Amount ($)',
          axisLabel: {
            formatter: function(value, index) {
              // Use different formatters based on the series being viewed
              return value + (index < data.length ? 'h' : '$');
            }
          }
        },
        yAxis: {
          type: 'value',
          name: 'Rating (%) / Offer Count',
          axisLabel: {
            formatter: function(value) {
              return value;
            }
          }
        },
        series: [
          {
            name: 'Time vs Rating',
            type: 'scatter',
            data: timeVsRatingData,
            markPoint: {
              data: [
                { type: 'max', name: 'Max Rating' },
                { type: 'min', name: 'Min Rating' }
              ]
            },
            markLine: {
              data: [{ type: 'average', name: 'Average' }]
            }
          },
          {
            name: 'Offer Analysis',
            type: 'scatter',
            data: offerData,
            markPoint: {
              data: [
                { type: 'max', name: 'Max Offers' },
                { type: 'min', name: 'Min Offers' }
              ]
            },
            markLine: {
              data: [{ type: 'average', name: 'Average' }]
            }
          }
        ]
      };

      chartInstance.current.setOption(option);
    }

    return () => {
      if (chartInstance.current) {
        chartInstance.current.dispose();
        chartInstance.current = null;
      }
    };
  }, [data]);

  return (
    <div className="scatter-plot-container">
      <div
        ref={chartRef}
        style={{ width: '100%', height: '500px', padding: '30px' }}
      />
    </div>
  );
};

export default FoodScatterPlot;