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
              <el-dialog
                :visible.sync="dialogAddAccount"
                title="添加用户"
                width="30%"
                center
                :before-close="handleClose"
              >
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
                  <el-form-item label="密码" prop="password">
                    <el-input v-model.trim="addAccountParams.password" show-password />
                  </el-form-item>
                  <el-form-item label="邮箱" prop="email">
                    <el-input v-model.trim="addAccountParams.email" />
                  </el-form-item>
                  <el-form-item label="手机号码" prop="phone">
                    <el-input v-model.trim="addAccountParams.phone" />
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="AddAccount('addAccountParams')">添 加</el-button>
                    <el-button type="primary" @click="cancelAddAccount('addAccountParams')">取 消</el-button>
                  </el-form-item>
                </el-form>
              </el-dialog>
            </div>

            <!-- 修改用户 -->
            <div class="add">
              <el-dialog
                :visible.sync="dialogEditAccount"
                title="修改用户信息"
                width="30%"
                center
                :before-close="handleClose"
              >
                <el-form
                  label-width="100px"
                  class="demo-ruleForm"
                />
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
        <!-- 多选框实现批量删除 -->
        <el-table-column type="selection" width="50" align="center" />
        <!-- 展开查看用户详情 -->
        <el-table-column type="expand" label="详情" width="55">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="ID">
                <span>{{ props.row.user_id }}</span>
              </el-form-item>
              <el-form-item label="用户名">
                <span>{{ props.row.username }}</span>
              </el-form-item>
              <el-form-item label="邮箱">
                <span>{{ props.row.email }}</span>
              </el-form-item>
              <el-form-item label="手机号码">
                <span>{{ props.row.phone }}</span>
              </el-form-item>
              <el-form-item label="创建时间">
                <span>{{ props.row.created | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </el-form-item>
              <el-form-item label="修改时间">
                <span>{{ props.row.updated | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="ID" prop="user_id" sortable="custom" align="center" width="70">
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
            <span>{{ scope.row.created | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
            <!--          <span></span>-->
          </template>
        </el-table-column>
        <el-table-column label="更新时间" prop="updated" sortable="custom" align="center">
          <template slot-scope="scope">
            <span>{{ scope.row.updated | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" width="230" class-name="small-padding fixed-width">
          <template slot-scope="">
            <el-button type="text" @click="Check">修改</el-button>
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
    import { accountList, sortAccountList, postAccount } from '../../api/account'

    export default {
        name: 'AccountList',
        data() {
            return {
                // 打开添加用户弹窗
                dialogAddAccount: false,
                // 打开修改用户信息弹窗
                dialogEditAccount: false,
                // 获取全部用户信息
                getAccountList: [],
                // 用户信息字段
                accountParams: {
                    userId: ''
                },
                // 默认的排序和分页
                defaultPage: {
                    pageIndex: 1,
                    pageSize: 10,
                    pageSizes: [5, 10, 20, 50],
                    sortName: 'user_id',
                    sortType: 'DESC',
                    total: undefined // 总数目
                },
                // 添加用户
                addAccountParams: {
                    username: '', // 用户名
                    password: '',
                    email: '', // 邮箱
                    phone: '' // 手机号码
                },
                // 输入框校验规则
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { max: 20, message: '用户名过长', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'blur' },
                        { min: 6, message: '请输入至少6位数密码', trigger: 'blur' }
                    ],
                    email: [
                        { required: true, message: '请输入邮箱', trigger: 'blur' },
                        { max: 25, message: '邮箱过长', trigger: 'blur' }
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
            AddAccount(params) {
                this.$refs[params].validate((valid) => {
                    if (valid) {
                        postAccount(this.addAccountParams).then(res => {
                            console.log('code = ' + res.code)
                            if (res.code === 20000) {
                                this.$notify({
                                    title: '操作成功',
                                    message: '添加成功',
                                    type: 'success',
                                    // 自动关闭时间
                                    duration: 2000
                                })
                                // 清空信息
                                this.cancelAddAccount(params)
                                // 刷新页面
                                this.refreshPage()
                                // 关闭对话框
                                this.closeDialogAddAccount()
                            } else {
                                this.$notify({
                                    title: '失败',
                                    message: '系统异常，请稍后重试',
                                    type: 'error',
                                    duration: 2000
                                })
                                this.closeDialogAddAccount()
                            }
                        })
                    } else {
                        return false
                    }
                })
            },
            // 取消添加用户按钮
            cancelAddAccount(params) {
                console.log(' 取消添加用户 ')
                this.dialogAddAccount = false
                this.$refs[params].resetFields()
            },
            // 查看信息
            Check(index, row) {
                this.dialogEditAccount = true
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
            },
            // 弹出框不小心关闭提醒（或者点击弹出框意外的界面提醒是否关闭)
            handleClose(done) {
                this.$confirm('确认关闭?').then(_ => {
                    done()
                })
                    .catch(_ => {})
            },
            // 关闭对话框
            closeDialogAddAccount() {
                this.dialogAddAccount = false
                this.dialogEditAccount = false
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

  .demo-table-expand {
    font-size: 0;
  }

  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }

  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
