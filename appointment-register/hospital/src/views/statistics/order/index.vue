<template>
    <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
            <el-input v-model="searchObj.hosname" placeholder="点击输入医院名称"/>
        </el-form-item>

        <el-form-item>
            <el-date-picker
                v-model="searchObj.reserveDateBegin"
                type="date"
                placeholder="选择开始日期"
                value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-form-item>
            <el-date-picker
                v-model="searchObj.reserveDateEnd"
                type="date"
                placeholder="选择截止日期"
                value-format="yyyy-MM-dd"/>
        </el-form-item>
        <el-button
            :disabled="btnDisabled"
            type="primary"
            icon="el-icon-search"
            @click="showChart()">查询</el-button>
    </el-form>

    <div class="chart-container">
        <div id="chart" ref="chart"
            class="chart" style="height:500px;width:100%"/>
    </div>
    </div>
</template>

<script>
import * as echarts from 'echarts'
import statisticsApi from '@/api/sta'

export default {

    data() {
        return {
            searchObj: {
                hosname: '',
                reserveDateBegin: '',
                reserveDateEnd: ''
            },
            btnDisabled: false,
            chart: null,
            title: '',
            xData: [], // x轴数据
            yData: [] // y轴数据
        }
    },

    methods: {
        // 初始化图表数据
        showChart() {
            statisticsApi.getCountMap(this.searchObj).then(response => {
                this.yData = response.data.countList
                this.xData = response.data.dateList
                this.setChartData()
            })
        },

        setChartData() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('chart'))
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: this.title + '挂号量统计'
                },
                tooltip: {},
                legend: {
                    data: [this.title]
                },
                xAxis: {
                    data: this.xData
                },
                yAxis: {
                    minInterval: 1
                },
                series: [{
                    name: this.title,
                    type: 'line',
                    data: this.yData
                }]
            }
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option)
        },
    }
}
</script>
