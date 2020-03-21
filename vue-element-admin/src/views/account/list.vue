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
                <!-- 批量删除按钮,disabled设置是否可用 -->
                <el-button
                  class="filter-item"
                  type="danger"
                  icon="el-icon-delete"
                  plain
                  :disabled="this.multipleSelection.length === 0"
                  @click="delMultiAccount(multipleSelection)"
                >
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
                  ref="updateAccountParams"
                  :model="updateAccountParams"
                  :rules="rules"
                  label-width="100px"
                  class="demo-ruleForm"
                >
                  <el-form-item label="用户名" prop="username">
                    <el-input v-model.trim="updateAccountParams.username" />
                  </el-form-item>
                  <el-form-item label="手机号码" prop="phone">
                    <el-input v-model.trim="updateAccountParams.phone" />
                  </el-form-item>
                  <el-form-item label="邮箱" prop="email">
                    <el-input v-model.trim="updateAccountParams.email" />
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="updateAccount('updateAccountParams')">修 改</el-button>
                    <el-button @click="cancelUpdateAccount('updateAccountParams')">取 消</el-button>
                  </el-form-item>
                </el-form>
              </el-dialog>
            </div>
          </div>
        </el-col>
      </el-row>

      <!-- 表格 -->
      <el-table
        :data="getList"
        :default-sort="{prop: 'user_id', order: 'ascending'}"
        style="margin-top: 0px;width: 100%"
        border
        fit
        stripe
        highlight-current-row
        @sort-change="sortChange"
        @selection-change="handleSelectionChange"
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
          <template slot-scope="scope">
            <el-button type="text" @click="Check(scope.$index,scope.row)">修改</el-button>
            <el-button type="text" style="color:#CD2626" @click="Delete(scope.$index,scope.row)">删除</el-button>
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
    import { accountList, sortAccountList, postAccount, putAccount, deleteAccount, deleteMultiAccount } from '../../api/account'

    export default {
        name: 'AccountList',
        data() {
            return {
                // 打开添加用户弹窗
                dialogAddAccount: false,
                // 打开修改用户信息弹窗
                dialogEditAccount: false,
                // 获取全部用户信息
                getList: [],
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
                // 修改用户
                updateAccountParams: {
                  username: '',
                  phone: '',
                  email: ''
                },
                // user_id
                userId: '',
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
                },
                // 批量删除选择的数据(字符串数组)
              multipleSelection: []
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
                    this.getList = res.data.getList
                    this.defaultPage.total = res.data.total
                })
            },
            // 高级搜索
            sortAccount() {
                sortAccountList(this.defaultPage.pageIndex, this.defaultPage.pageSize, this.defaultPage.sortType, this.defaultPage.sortName, this.sortParams).then(res => {
                    this.defaultPage.total = res.data.total
                    this.getList = res.data.getList
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
            // 确认修改按钮
          updateAccount(params) {
            this.$refs[params].validate((valid) => {
              if (valid) {
                console.log('修改商品')
                putAccount(this.updateAccountParams, this.userId).then(res => {
                  if (res.code === 20000) {
                    this.$notify({
                      title: '成功',
                      type: 'success',
                      message: '修改成功',
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
                      message: '系统异常',
                      type: 'error',
                      duration: 2000
                    })
                  }
                })
              } else {
                return false
              }
            })
          },
            // 取消修改按钮
            cancelUpdateAccount(params) {
              console.log('取消修改用户')
              this.dialogEditAccount = false
              this.$refs[params].resetFields()
            },
            // 查看信息
            Check(index, row) {
                this.userId = row.user_id
                this.updateAccountParams.username = row.username
                this.updateAccountParams.email = row.email
                this.updateAccountParams.phone = row.phone
                console.log(this.userId)
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
            // 删除单行数据
          Delete(index, row) {
              console.log('删除用户' + row.user_id)
            this.$confirm('此操作将删除商品且不可恢复 ,是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            }).then(() => {
              deleteAccount(row.user_id).then(res => {
                console.log('要删除的id' + ' = ' + row.user_id)
                if (res.code === 20000) {
                  this.$notify({
                    title: '成功',
                    type: 'success',
                    message: '删除成功',
                    duration: 2000
                  })
                  // 刷新页面
                  this.refreshPage()
                } else {
                  this.$notify({
                    ttile: '失败',
                    message: '该数据不可删除或者系统异常',
                    type: 'error',
                    duration: 2000
                  })
                }
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消删除'
              })
            })
          },
            // 关闭对话框
            closeDialogAddAccount() {
                this.dialogAddAccount = false
                this.dialogEditAccount = false
            },
            // 批量删除
          delMultiAccount() {
            // 获取所有选中行的id组成的字符串，使用逗号分隔
            var ids = this.multipleSelection.map(item => item.user_id).join()
            console.log(ids)
            this.$confirm('此操作将永久删除，是否继续', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消'
            }).then(() => {
              deleteMultiAccount(ids).then(res => {
                if (res.code === 20000) {
                  this.$notify({
                    title: '提示',
                    message: '批量删除成功',
                    type: 'success',
                    duration: 2000
                  })
                  // 刷新页面
                  this.refreshPage()
                } else if (res.code === 50000) {
                  this.$notify({
                    title: '失败',
                    type: 'error',
                    message: '删除失败!',
                    duration: 2000
                  })
                } else {
                  this.$notify({
                    title: '提示',
                    message: '删除失败，有数据不能删除',
                    type: 'error',
                    duration: 2000
                  })
                }
              })
            }).catch(() => {
              this.$message({
                type: 'info',
                message: '已取消批量删除'
              })
            })
          },
          // 选中时触发，选中得到的数据
          handleSelectionChange(val) {
              this.multipleSelection = val
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
