<template>
  <div class="app-container">
    <el-form ref="data" :model="data" label-width="120px">
      <el-form-item label="ID">
        <el-input v-model="data.userId" />
      </el-form-item>
      <el-form-item label="用户名">
        <el-input v-model="data.username" />
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="data.email" />
      </el-form-item>
      <el-form-item label="手机号">
        <el-input v-model="data.phone" />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-input v-model="data.created" />
      </el-form-item>
      <el-form-item label="最后登录">
        <el-input v-model="data.updated" />
      </el-form-item>

    </el-form>
  </div>
</template>

<script>
  import { getInfo } from "../../api/account";

  export default {
        name: "AccountInfo",
        data() {
            return {
                data: {
                    userId: '',
                    username: '',
                    email: '',
                    phone: '',
                    created: '',
                    updated: ''
                }
            }
        },
        created() {
            this.fetchData()
        },
      methods: {
            fetchData() {
                getInfo(this.$store.getters.name).then(response =>{
                    console.log(response.data);
                    this.data = response.data;
                })
            }
      }
  }
</script>

<style scoped>

</style>
