<template>

  <!-- header -->
  <div class="nav-container page-component">
    <!--左侧导航 #start -->
    <div class="nav left-nav">
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/user'">实名认证 </span>
      </div>
      <div class="nav-item ">
        <span class="v-link clickable dark" onclick="javascript:window.location='/order'"> 挂号订单 </span>
      </div>
      <div class="nav-item selected">
        <span class="v-link selected dark" onclick="javascript:window.location='/patient'"> 就诊人管理 </span>
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
      <div class="personal-patient">
          <div class="title" style="margin-top: 0px;font-size: 16px;"> 就诊人详情</div>
        <div>
          <div class="sub-title">
            <div class="block"></div>
            就诊人信息
          </div>
          <div class="content-wrapper">
            <el-form :model="patient" label-width="110px" label-position="left">
              <el-form-item label="姓名：">
                <div class=""><span>{{ patient.name }}</span></div>
              </el-form-item>
              <el-form-item label="证件类型：">
                <div class=""><span>{{ patient.param.certificatesTypeString }}</span></div>
              </el-form-item>
              <el-form-item label="证件号码：">
                <div class=""><span>{{ patient.certificatesNo }} </span></div>
              </el-form-item>
              <el-form-item label="性别：">
                <div class=""><span>{{ patient.sex == 1 ? '男' : '女' }} </span></div>
              </el-form-item>
              <el-form-item label="出生日期：">
                <div class=""><span>{{ patient.birthdate }} </span></div>
              </el-form-item>
              <el-form-item label="手机号码：">
                <div class=""><span>{{ patient.phone }} </span></div>
              </el-form-item>
              <el-form-item label="婚姻状况：">
                <div class=""><span>{{ patient.isMarry == 1 ? '已婚' : '未婚' }} </span></div>
              </el-form-item>
              <el-form-item label="当前住址：">
              <div class=""><span>{{ patient.param.provinceString }}/{{ patient.param.cityString }}/{{ patient.param.districtString }} </span></div>
            </el-form-item>
              <el-form-item label="详细地址：">
                <div class=""><span>{{ patient.address }} </span></div>
              </el-form-item>

              <br/>
              <el-form-item>
                <el-button class="v-button" type="primary" @click="remove()">删除就诊人</el-button>
                <el-button class="v-button" type="primary white" @click="edit()">修改就诊人</el-button>
              </el-form-item>

            </el-form>
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
import '~/assets/css/personal.css'

import patientApi from '@/api/user/patient'

export default {

  data() {
    return {
      patient: {
        param: {}
      }
    }
  },

  created() {
    this.fetchDataById();
  },

  methods: {
    fetchDataById() {
      debugger
      patientApi.getById(this.$route.query.id).then(response => {
        this.patient = response.data
      })
    },

    remove() {
      patientApi.removeById(this.patient.id).then(response => {
        this.$message.success('删除成功')
        window.location.href = '/patient'
      })
    },

    edit() {
      window.location.href = '/patient/add?id=' + this.patient.id
    }
  }
}
</script>
<style>
  .info-wrapper {
    padding-left: 0;
    padding-top: 0;
  }

  .content-wrapper {
    color: #333;
    font-size: 14px;
    padding-bottom: 0;
  }
  .el-form-item {
    margin-bottom: 5px;
  }

  .bottom-wrapper {
    width: 100%;
  }

  .button-wrapper {
    margin: 0;

  }

  .bottom-wrapper .button-wrapper {
    margin-top: 0;
  }
</style>
