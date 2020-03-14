<template>
  <div class="app-container">
    <div class="filter-container">
      <!-- 表格 -->
      <el-table
        :data="getAccountList"
        style="margin-top: 30px;width: 100%"
        height="600"
        border
        fit
        highlight-current-row
      >
        <el-table-column label="ID" prop="userId" sortable="custom" align="center" width="80">
          <template slot-scope="scope">
            <span>{{ scope.row.userId }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户名" prop="username" sortable="custom" align="center" >
          <template slot-scope="scope">
            <span>{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column label="邮箱" prop="email" sortable="custom" align="center" >
          <template slot-scope="scope">
            <span>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column label="手机号码" prop="phone" sortable="custom" align="center" >
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="created" sortable="custom" align="center" >
          <template slot-scope="scope">
            <span>{{ scope.row.created | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
            <!--          <span></span>-->
          </template>
        </el-table-column>
        <el-table-column label="更新时间" prop="updated" sortable="custom" align="center" >
          <template slot-scope="scope">
            <span>{{ scope.row.updated | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <!--        <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">-->

        <!--        </el-table-column>-->

      </el-table>
    </div>

  </div>
</template>

<script>
import {accountList} from '../../api/account';

export default {
  name: 'AccountList',
  data() {
    return {

        getAccountList: []
    }
  },
    // 刷新界面
  created() {
    this.list()
  },
  methods: {
    list() {
      accountList().then( res =>{
        console.log(res.getAccountList)
          this.getAccountList = res.data.getAccountList;
      })
    }
  }
}
</script>

<style scoped>

</style>
