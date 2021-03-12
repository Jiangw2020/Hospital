<template>

  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/user'">实名认证 </span>
      </div>
      <div class="nav-item selected">
        <span class="v-link selected dark" onclick="javascript:window.location='/order'"> 挂号订单 </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/patient'"> 就诊人管理 </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark"> 修改账号信息 </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark"> 意见反馈 </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div class="personal-order">
        <div class="title"> 挂号订单</div>
        <el-form :inline="true">
          <el-form-item label="就诊人：">
            <el-select v-model="searchObj.patientId" placeholder="请选择就诊人" class="v-select patient-select">
              <el-option
                v-for="item in patientList"
                :key="item.id"
                :label="item.name + '【' + item.certificatesNo + '】'"
                :value="item.id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="订单状态：" style="margin-left: 80px">
            <el-select v-model="searchObj.orderStatus" placeholder="全部" class="v-select patient-select" style="width: 200px;">
              <el-option
                v-for="item in statusList"
                :key="item.status"
                :label="item.comment"
                :value="item.status">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="text" class="search-button v-link highlight clickable selected" @click="fetchData()">
              查询
            </el-button>
          </el-form-item>
        </el-form>
        <div class="table-wrapper table">
          <el-table
            :data="list"
            stripe
            style="width: 100%">
            <el-table-column
              label="就诊时间"
              width="120">
              <template slot-scope="scope">
                {{ scope.row.reserveDate }} {{ scope.row.reserveTime === 0 ? '上午' : '下午' }}
              </template>
            </el-table-column>
            <el-table-column
              prop="hosname"
              label="医院"
              width="100">
            </el-table-column>
            <el-table-column
              prop="depname"
              label="科室">
            </el-table-column>
            <el-table-column
              prop="title"
              label="医生">
            </el-table-column>
            <el-table-column
              prop="amount"
              label="医事服务费">
            </el-table-column>
            <el-table-column
              prop="patientName"
              label="就诊人">
            </el-table-column>
            <el-table-column
              prop="param.orderStatusString"
              label="订单状态">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="text" class="v-link highlight clickable selected" @click="show(scope.row.id)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <!-- 分页 -->
        <el-pagination
          class="pagination"
          layout="prev, pager, next"
          :current-page="page"
          :total="total"
          :page-size="limit"
          @current-change="fetchData">
        </el-pagination>
      </div>
    </div>
    <!-- 右侧内容 #end -->
  </div>
  <!-- footer -->
</template>

<script>
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'

import orderInfoApi from '@/api/order/orderInfo'
import patientApi from '@/api/user/patient'

export default {

  data() {
    return {
      list: [], // banner列表
      total: 0, // 数据库中的总记录数
      page: 1, // 默认页码
      limit: 10, // 每页记录数
      searchObj: {}, // 查询表单对象

      patientList: [],
      statusList: []
    }
  },

  created() {
    this.orderId = this.$route.query.orderId

    this.fetchData()
    this.findPatientList()
    this.getStatusList()
  },

  methods: {
    fetchData(page = 1) {
      this.page = page
      orderInfoApi.getPageList(this.page, this.limit, this.searchObj).then(response => {
        console.log(response.data);
        this.list = response.data.records
        this.total = response.data.total
      })
    },

    findPatientList() {
      patientApi.findList().then(response => {
        this.patientList = response.data
      })
    },

    getStatusList() {
      orderInfoApi.getStatusList().then(response => {
        this.statusList = response.data
      })
    },

    changeSize(size) {
      console.log(size)
      this.limit = size
      this.fetchData(1)
    },

    show(id) {
      window.location.href = '/order/show?orderId=' + id
    }
  }
}
</script>
