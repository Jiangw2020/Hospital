<template>
  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item selected">
        <span class="v-link selected dark" :onclick="'javascript:window.location=\'/hospital/'+schedule.hoscode+'\''">预约挂号 </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/hospital/detail/'+schedule.hoscode+'\''"> 医院详情 </span>
      </div>
      <div class="nav-item">
        <span class="v-link clickable dark" :onclick="'javascript:window.location=\'/hospital/notice/'+schedule.hoscode+'\''"> 预约须知 </span>
      </div>
      <div class="nav-item "><span
        class="v-link clickable dark"> 停诊信息 </span>
      </div>
      <div class="nav-item "><span
        class="v-link clickable dark"> 查询/取消 </span>
      </div>
    </div>
    <!-- 左侧导航 #end -->

    <!-- 右侧内容 #start -->
    <div class="page-container">
      <div class="hospital-order">
        <div class="header-wrapper">
          <div class="title mt20"> 确认挂号信息</div>
          <div>
            <div class="sub-title">
              <div class="block"></div>
              选择就诊人：
            </div>
            <div class="patient-wrapper">
              <div >
                <div class="v-card clickable item ">
                  <div class="inline" v-for="(item,index) in patientList" :key="item.id"
                       @click="selectPatient(index)" style="margin-right: 10px;">
                    <!-- 选中 selected  未选中去掉selected-->
                    <div :class="activeIndex == index ? 'item-wrapper selected' : 'item-wrapper'">
                      <div>
                        <div class="item-title">{{ item.name }}</div>
                        <div>{{ item.param.certificatesTypeString }}</div>
                        <div>{{ item.certificatesNo }}</div>
                      </div>
                      <img src="//img.114yygh.com/static/web/checked.png" class="checked">
                    </div>
                  </div>
                </div>
              </div>
              <div class="item space add-patient v-card clickable">
                <div class="">
                  <div class="item-add-wrapper" @click="addPatient()"> +
                    添加就诊人
                  </div>
                </div>
              </div>
              <div class="el-loading-mask" style="display: none;">
                <div class="el-loading-spinner">
                  <svg viewBox="25 25 50 50" class="circular">
                    <circle cx="50" cy="50" r="20" fill="none" class="path"></circle>
                  </svg>
                </div>
              </div>
            </div>
            <!-- 就诊人，选中显示 -->
            <div class="sub-title" v-if="patientList.length > 0">
              <div class="block"></div>
              选择就诊卡： <span class="card-tips"><span
              class="iconfont"></span> 如您持社保卡就诊，请务必选择医保预约挂号，以保证正常医保报销</span>
            </div>

            <el-card class="patient-card" shadow="always" v-if="patientList.length > 0">
              <div slot="header" class="clearfix">
                <div><span class="name"> {{ patient.name }} {{ patient.certificatesNo }} 居民身份证</span></div>
              </div>
              <div class="card SELF_PAY_CARD">
                <div class="info"><span class="type">{{ patient.isInsure == 0 ? '自费' : '医保'}}</span><span class="card-no">{{ patient.certificatesNo }}</span><span
                  class="card-view">居民身份证</span></div>
                <span class="operate"></span></div>
              <div class="card">
                <div class="text bind-card"></div>
              </div>
            </el-card>


            <div class="sub-title">
              <div class="block"></div>
              挂号信息
            </div>
            <div class="content-wrapper">
              <el-form ref="form">
                <el-form-item label="就诊日期：">
                  <div class="content"><span>{{ schedule.workDate }} {{ schedule.param.dayOfWeek }} {{ schedule.workTime == 0 ? '上午' : '下午' }}</span></div>
                </el-form-item>
                <el-form-item label="就诊医院：">
                  <div class="content"><span>{{ schedule.param.hosname }} </span></div>
                </el-form-item>
                <el-form-item label="就诊科室：">
                  <div class="content"><span>{{ schedule.param.depname }} </span></div>
                </el-form-item>
                <el-form-item label="医生姓名：">
                  <div class="content"><span>{{ schedule.docname }} </span></div>
                </el-form-item>
                <el-form-item label="医生职称：">
                  <div class="content"><span>{{ schedule.title }} </span></div>
                </el-form-item>
                <el-form-item label="医生专长：">
                  <div class="content"><span>{{ schedule.skill }}</span></div>
                </el-form-item>
                <el-form-item label="医事服务费：">
                  <div class="content">
                    <div class="fee">{{ schedule.amount }}元</div>
                  </div>
                </el-form-item>
              </el-form>
            </div>

            <!-- 用户信息 #start-->
            <div>
              <div class="sub-title">
                <div class="block"></div>
                用户信息
              </div>
              <div class="content-wrapper">
                <el-form ref="form" :model="form">
                  <el-form-item class="form-item" label="就诊人手机号：">
                    {{ patient.phone }}
                  </el-form-item>
                </el-form>
              </div>
            </div>
            <!-- 用户信息 #end -->
            <div class="bottom-wrapper">
              <div class="button-wrapper">
                <div class="v-button" @click="submitOrder()">{{ submitBnt }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 右侧内容 #end -->
  </div>
  <!-- footer -->
</template>

<script>
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'

import hospitalApi from '@/api/hosp/hospital'
import patientApi from '@/api/user/patient'
import orderInfoApi from '@/api/order/orderInfo'

export default {

  data() {
    return {
      scheduleId: null,
      schedule: {
        param: {}
      },
      patientList: [],
      patient: {},

      activeIndex: 0,
      submitBnt: '确认挂号'
    }
  },

  created() {
    this.scheduleId = this.$route.query.scheduleId

    this.init()
  },

  methods: {
    init() {
      this.getSchedule()

      this.findPatientList()
    },

    getSchedule() {
      hospitalApi.getSchedule(this.scheduleId).then(response => {
        this.schedule = response.data
      })
    },

    findPatientList() {
      patientApi.findList().then(response => {
        this.patientList = response.data
        if(this.patientList.length > 0) {
          this.patient = this.patientList[0]
        }
      })
    },

    selectPatient(index) {
      this.activeIndex = index;
      this.patient = this.patientList[index]
    },

    submitOrder() {
      if(this.patient.id == null) {
        this.$message.error('请选择就诊人')
        return
      }
      // 防止重复提交
      if(this.submitBnt == '正在提交...') {
        this.$message.error('重复提交')
        return
      }

      this.submitBnt = '正在提交...'
      orderInfoApi.submitOrder(this.scheduleId, this.patient.id).then(response => {
        let orderId = response.data
        window.location.href = '/order/show?orderId=' + orderId
      }).catch(e => {
        this.submitBnt = '确认挂号'
      })
    },

    addPatient() {
      window.location.href = '/patient/add'
    }
  }
}
</script>
<style>
  .hospital-order .header-wrapper {
    display: -webkit-box;
    display: -ms-flexbox;
    display: block !important;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
  }
  .hospital-order .sub-title {
    letter-spacing: 1px;
    color: #999;
    margin-top: 60px;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-align: center;
    -ms-flex-align: center;
    align-items: center;
  }
  .hospital-order .content-wrapper .content {
    color: #333;
  }
  .el-form-item {
    margin-bottom: 5px;
  }
  .hospital-order .content-wrapper {
    margin-left: 140px;
    margin-top: 20px;
  }
</style>
