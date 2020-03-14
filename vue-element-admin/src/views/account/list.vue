<template>
  <div class="app-container">
    <div class="filter-container">
      <el-row :gutter="20">
        <el-col :span="24" :offset="0">
          <div>
            <!-- 高级查询 -->
            <el-form :inline="true" class="demo-form-inline">
              <el-form-item>
                <el-input v-model.trim="sortParams.username" placeholder="输入用户名搜索" />
              </el-form-item>
              <el-form-item>
                <el-input v-model.trim="sortParams.email" placeholder="输入邮箱搜索" />
              </el-form-item>
              <el-form-item>
                <el-input v-model.trim="sortParams.phone" placeholder="输入手机号码搜索" />
              </el-form-item>
              <el-form-item class="filter-item">
                <el-button class="filter-item" type="primary" icon="el-icon-search" @click="sortAccount">
                  高级查询
                </el-button>
              </el-form-item>
              <el-form-item class="filter-item">
                <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="openAddAccount">
                  添加
                </el-button>
              </el-form-item>
              <el-form-item class="filter-item">
                <el-button class="filter-item" type="danger" icon="el-icon-delete" plain>
                  批量删除
                </el-button>
              </el-form-item>
            </el-form>

            <!-- 添加用户 -->
            <div class="add">
              <el-dialog :visible.sync="dialogAddAccount" title="添加用户" width="30%" center>
                <el-form
                  ref="addAccountParams"
                  :model="addAccountParams"
                  :rules="rules"
                  label-width="100px"
                  class="demo-ruleForm"
                >
                  <el-form-item label="用户名" prop="username">
                    <el-input v-model.trim="addAccountParams.username" />
                  </el-form-item>
                  <el-form-item label="邮箱" prop="username">
                    <el-input v-model.trim="addAccountParams.email" />
                  </el-form-item>
                  <el-form-item label="手机号码" prop="username">
                    <el-input v-model.trim="addAccountParams.phone" />
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary">添加</el-button>
                    <el-button type="primary" @click="cancelAddAccount">取消</el-button>
                  </el-form-item>
                </el-form>
              </el-dialog>
            </div>
          </div>
        </el-col>
      </el-row>

      <!-- 表格 -->
      <el-table
        :data="getAccountList"
        :default-sort="{prop: 'user_id', order: 'ascending'}"
        style="margin-top: 0px;width: 100%"
        border
        fit
        stripe
        highlight-current-row
        @sort-change="sortChange"
      >
        <el-table-column label="ID" prop="user_id" sortable="custom" align="center" width="80">
          <template slot-scope="scope">
            <span>{{ scope.row.user_id }}</span>
          </template>
        </el-table-column>
        <el-table-column label="用户名" prop="username" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.username }}</span>
          </template>
        </el-table-column>
        <el-table-column label="邮箱" prop="email" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.email }}</span>
          </template>
        </el-table-column>
        <el-table-column label="手机号码" prop="phone" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" prop="created" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.created | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
            <!--          <span></span>-->
          </template>
        </el-table-column>
        <el-table-column label="更新时间" prop="updated" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.updated | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
          <template slot-scope="">
            <el-button type="text">查看</el-button>
            <el-button type="text">修改</el-button>
            <el-button type="text" style="color:#CD2626">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <el-row :gutter="20">
        <el-col :span="10" :offset="6">
          <div>
            <div class="block">
              <el-pagination
                v-show="defaultPage.total>0"
                :current-page="defaultPage.pageIndex"
                :page-sizes="defaultPage.pageSizes"
                :page-size="defaultPage.pageSize"
                :total="defaultPage.total"
                style="margin-top: 30px"
                layout="total, sizes, prev, pager, next, jumper"
                @size-change="handleChangeSize"
                @current-change="handleChangeCurrent"
              />
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
    import { accountList, sortAccountList } from '../../api/account'

    export default {
        name: 'AccountList',
        data() {
            return {
                // 打开添加用户弹窗
                dialogAddAccount: false,
                // 获取全部用户信息
                getAccountList: [],
                // 默认的排序和分页
                defaultPage: {
                    pageIndex: 1,
                    pageSize: 10,
                    pageSizes: [5, 10, 20, 50],
                    sortName: 'user_id',
                    sortType: 'ASC',
                    total: undefined // 总数目
                },
                // 添加用户
                addAccountParams: {
                    username: '', // 用户名
                    email: '', // 邮箱
                    phone: '' // 手机号码
                },
                // 输入框校验规则
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { max: 10, message: '用户名过长', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { max: 10, message: '用户名过长', trigger: 'blur' }
                    ],
                    phone: [
                        { required: true, message: '请输入手机号码', trigger: 'blur' },
                        { min: 11, max: 11, message: '手机号码只能是11位', trigger: 'blur' }
                    ]
                },
                // 查询用数据
                sortParams: {
                    username: '',
                    email: '',
                    phone: ''
                }
            }
        },
        // 刷新界面
        created() {
            this.refreshPage()
        },
        methods: {
            // 刷新界面
            refreshPage() {
                accountList(this.defaultPage.pageIndex, this.defaultPage.pageSize, this.defaultPage.sortType, this.defaultPage.sortName).then(res => {
                    this.getAccountList = res.data.getAccountList
                    this.defaultPage.total = res.data.total
                })
            },
            // 高级搜索
            sortAccount() {
                sortAccountList(this.defaultPage.pageIndex, this.defaultPage.pageSize, this.defaultPage.sortType, this.defaultPage.sortName, this.sortParams).then(res => {
                    this.defaultPage.total = res.data.total
                    this.getAccountList = res.data.getAccountList
                })
            },
            // 排序
            sortChange(column) {
              this.defaultPage.sortName = column.prop
                console.log('排序的字段 = ' + column.prop)
              if (column.order === 'ascending') {
                  console.log('排序的类型 = ' + column.order)
                  this.defaultPage.sortType = 'ASC'
                  this.sortAccount()
              } else if (column.order === 'descending') {
                  console.log('排序的类型 = ' + column.order)
                  this.defaultPage.sortType = 'DESC'
                  this.sortAccount()
              }
            },
            // 添加用户按钮
            openAddAccount() {
                this.dialogAddAccount = true
            },
            // 确认添加用户按钮
            AddAccount() {

            },
            // 取消添加用户按钮
            cancelAddAccount() {
                this.dialogAddAccount = false
            },
            // 点击改变显示条数
            handleChangeSize(val) {
                console.log('点击改变显示条数val = ' + val)
                this.defaultPage.pageSize = val
                this.refreshPage()
            },
            // 切换页数
            handleChangeCurrent(val) {
                console.log('切换页数val = ' + val)
                this.defaultPage.pageIndex = val
                console.log('点击改变显示条数 = ' + this.defaultPage.pageIndex)
                this.refreshPage()
            }
        }
    }
</script>

<style scoped>
  .add {
    .el-dialog-center {
      border-radius: 12px;
    }
  }
</style>
