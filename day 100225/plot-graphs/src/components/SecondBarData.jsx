import React, { useEffect, useRef } from 'react';
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components';
import { BarChart } from 'echarts/charts';
import { CanvasRenderer } from 'echarts/renderers';

echarts.use([
  TitleComponent,
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  BarChart,
  CanvasRenderer
]);

const BarChartComponent1 = ({ barData }) => {
  const chartRef = useRef(null);

  useEffect(() => {
    if (chartRef.current) {
      const myChart = echarts.init(chartRef.current);
      const xAxisData = barData.map(item => item.amount);
      const yAxisData = barData.map(item => item.count);

      const option = {
        title: {
          text: 'Distribution Chart'
        },
        legend: {
          data: ['Count']
        },
        toolbox: {
          feature: {
            magicType: {
              type: ['stack']
            },
            dataView: {},
            saveAsImage: {
              pixelRatio: 2
            }
          }
        },
        tooltip: {},
        xAxis: {
            name: 'Count',
          type: 'category',
          data: xAxisData,
          splitLine: {
            show: true
          }
        },
        yAxis: {},
        series: [
          {
            
            name: 'Number of Restaurant',
            type: 'bar',
            data: yAxisData,
            emphasis: {
              focus: 'series'
            },
            animationDelay: function (idx) {
              return idx * 10;
            }
          }
        ],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function (idx) {
          return idx * 5;
        }
      };
      myChart.setOption(option);
    }
  }, [barData]);

  return <div ref={chartRef} style={{ width: '100%', height: '400px' }} />;
};

export default BarChartComponent1;
