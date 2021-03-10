<template>
    <div class="app-container">
        <h4>用户信息</h4>
        <table class="table table-striped table-condenseda table-bordered" width="100%">
        <tbody>
            <tr>
                <th width="15%">手机号</th>
                <td width="35%"><b>{{ userInfo.phone }}</b></td>
                <th width="15%">用户姓名</th>
                <td width="35%">{{ userInfo.name }}</td>
            </tr>
        <tr>
            <th>状态</th>
            <td>{{ userInfo.status == 0 ? '锁定' : '正常' }}</td>
            <th>注册时间</th>
            <td>{{ userInfo.createTime }}</td>
        </tr>
        </tbody>
        </table>

        <h4>认证信息</h4>
        <table class="table table-striped table-condenseda table-bordered" width="100%">
            <tbody>
            <tr>
                <th width="15%">姓名</th>
                <td width="35%"><b>{{ userInfo.name }}</b></td>
                <th width="15%">证件类型</th>
                <td width="35%">{{ userInfo.certificatesType }}</td>
            </tr>
            <tr>
                <th>证件号</th>
                <td>{{ userInfo.certificatesNo }}</td>
                <th>证件图片</th>
                <td><img :src="userInfo.certificatesUrl" width="80px"></td>
            </tr>
            </tbody>
        </table>

    <h4>就诊人信息</h4>
    <el-table
        v-loading="listLoading"
        :data="patientList"
        stripe
            style="width: 100%">

        <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
                {{ scope.$index + 1 }}
        </template>
        </el-table-column>

        <el-table-column prop="name" label="姓名"/>
        <el-table-column prop="param.certificatesTypeString" label="证件类型"/>
        <el-table-column prop="certificatesNo" label="证件编号"/>
        <el-table-column label="性别">
        <template slot-scope="scope">
                {{ scope.row.sex == 1 ? '男' : '女' }}
        </template>
        </el-table-column>
        <el-table-column prop="birthdate" label="出生年月"/>
        <el-table-column prop="phone" label="手机"/>
        <el-table-column label="是否结婚">
        <template slot-scope="scope">
                {{ scope.row.isMarry == 1 ? '时' : '否' }}
        </template>
    </el-table-column>
    <el-table-column prop="fullAddress" label="地址"/>
    <el-table-column prop="createTime" label="注册时间"/>
    </el-table>

    <br>
    <el-row>
    <el-button  @click="back">返回</el-button>
    </el-row>
    </div>
</template>
<script>
import userInfoApi from '@/api/userInfo'
export default {
    // 定义数据
    data() {
        return {
            id: this.$route.params.id,
            userInfo: {}, // 会员信息
            patientList: [] // 就诊人列表
        }
    },

    // 当页面加载时获取数据
    created() {
        this.fetchDataById()
    },

    methods: {
        // 根据id查询会员记录
        fetchDataById() {
            userInfoApi.show(this.id).then(response => {
                this.userInfo = response.data.userInfo
                this.patientList = response.data.patientList
            })
        },

        back() {
            window.history.back(-1)
        }
    }
}
</script>
