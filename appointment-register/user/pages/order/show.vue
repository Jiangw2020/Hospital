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
      <div class="order-detail">
        <div class="title"> 挂号详情</div>
        <div class="status-bar">
          <div class="left-wrapper">
            <div class="status-wrapper BOOKING_SUCCESS">
              <span class="iconfont"></span> {{ orderInfo.param.orderStatusString }}
            </div>
          </div>
          <div class="right-wrapper">
            <img src="//img.114yygh.com/static/web/code_order_detail.png" class="code-img">
            <div class="content-wrapper">
              <div> 微信<span class="iconfont"></span>关注“北京114预约挂号”</div>
              <div class="watch-wrapper"> 快速挂号，轻松就医</div>
            </div>
          </div>
        </div>
        <div class="info-wrapper">
          <div class="title-wrapper">
            <div class="block"></div>
            <div>挂号信息</div>
          </div>
          <div class="info-form">
            <el-form ref="form" :model="form">

              <el-form-item label="就诊人信息：">
                <div class="content"><span>{{ orderInfo.patientName }}</span></div>
              </el-form-item>
              <el-form-item label="就诊日期：">
                <div class="content"><span>{{ orderInfo.reserveDate }} {{ orderInfo.reserveTime == 0 ? '上午' : '下午' }}</span></div>
              </el-form-item>
              <el-form-item label="就诊医院：">
                <div class="content"><span>{{ orderInfo.hosname }} </span></div>
              </el-form-item>
              <el-form-item label="就诊科室：">
                <div class="content"><span>{{ orderInfo.depname }} </span></div>
              </el-form-item>
              <el-form-item label="医生职称：">
                <div class="content"><span>{{ orderInfo.title }} </span></div>
              </el-form-item>
              <el-form-item label="医事服务费：">
                <div class="content">
                  <div class="fee">{{ orderInfo.amount }}元
                  </div>
                </div>
              </el-form-item>
              <el-form-item label="挂号单号：">
                <div class="content"><span>{{ orderInfo.outTradeNo }} </span></div>
              </el-form-item>
              <el-form-item label="挂号时间：">
                <div class="content"><span>{{ orderInfo.createTime }}</span></div>
              </el-form-item>
            </el-form>
          </div>
        </div>
        <div class="rule-wrapper mt40">
          <div class="rule-title"> 注意事项</div>
          <div>1、请确认就诊人信息是否准确，若填写错误将无法取号就诊，损失由本人承担；<br>
            <span style="color:red">2、【取号】就诊当天需在{{ orderInfo.fetchTime }}在医院取号，未取号视为爽约，该号不退不换；</span><br>
            3、【退号】在{{ orderInfo.quitTime }}前可在线退号 ，逾期将不可办理退号退费；<br>
            4、北京114预约挂号支持自费患者使用身份证预约，同时支持北京市医保患者使用北京社保卡在平台预约挂号。请于就诊当日，携带预约挂号所使用的有效身份证件到院取号；<br>
            5、请注意北京市医保患者在住院期间不能使用社保卡在门诊取号。
          </div>
        </div>
        <div class="bottom-wrapper mt60" v-if="orderInfo.orderStatus == 0 || orderInfo.orderStatus == 1">
          <div class="button-wrapper">
            <div class="v-button white" @click="cancelOrder()">取消预约</div>
          </div>
          <div class="button-wrapper ml20" v-if="orderInfo.orderStatus == 0">
            <div class="v-button" @click="pay()">支付</div>
          </div>
        </div>
      </div>
    </div>
    <!-- 右侧内容 #end -->

    <!-- 微信支付弹出框 -->
    <el-dialog :visible.sync="dialogPayVisible" style="text-align: left" :append-to-body="true" width="500px" @close="closeDialog">
      <div class="container">

        <div class="operate-view" style="height: 350px;">
          <div class="wrapper wechat">
            <div>
              <!--                <img src="images/weixin.jpg" alt="">-->
              <qriously :value="payObj.codeUrl" :size="220"/>
              <div style="text-align: center;line-height: 25px;margin-bottom: 40px;">
                请使用微信扫一扫<br/>
                扫描二维码支付
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>

  </div>
  <!-- footer -->
</template>

<script>
import '~/assets/css/hospital_personal.css'
import '~/assets/css/hospital.css'

import orderInfoApi from '@/api/order/orderInfo'
import weixinApi from '@/api/order/weixin'
export default {

  data() {
    return {
      orderId: null,
      orderInfo: {
        param: {}
      },

      dialogPayVisible: false,
      payObj: {},
      timer: null  // 定时器名称
    }
  },

  created() {
    this.orderId = this.$route.query.orderId

    this.init()
  },

  methods: {
    init() {
      orderInfoApi.getOrderInfo(this.orderId).then(response => {
        console.log(response.data);
        this.orderInfo = response.data
      })
    },

    cancelOrder() {
      this.$confirm('确定取消预约吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => { // promise
        // 点击确定，远程调用
        return orderInfoApi.cancelOrder(this.orderId)
      }).then((response) => {
        this.$message.success('取消成功')
        this.init()
      }).catch(() => {
        this.$message.info('已取消取消预约')
      })
    },

    pay() {
      this.dialogPayVisible = true

      weixinApi.createNative(this.orderId).then(response => {
        this.payObj = response.data
        if(this.payObj.codeUrl == '') {
          this.dialogPayVisible = false
          this.$message.error("支付错误")
        } else {
          this.timer = setInterval(() => {
            this.queryPayStatus(this.orderId)
          }, 3000);
        }
      })
    },

    queryPayStatus(orderId) {
      weixinApi.queryPayStatus(orderId).then(response => {
        debugger
        if (response.message == '支付中') {
          return
        }
        clearInterval(this.timer);
        window.location.reload()
      })
    },

    closeDialog() {
      debugger
      if(this.timer) {
        clearInterval(this.timer);
      }
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

  .bottom-wrapper {
    width: 100%;
  }

  .button-wrapper {
    margin: 0;

  }
  .el-form-item {
    margin-bottom: 5px;
  }

  .bottom-wrapper .button-wrapper {
    margin-top: 0;
  }
</style>
