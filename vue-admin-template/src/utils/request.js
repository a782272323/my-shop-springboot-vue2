import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// 创建一个axios实例
const service = axios.create({
  // VUE_APP_BASE_API 这个在生产环境那里的 http://localhost:1234对应起来
  baseURL: process.env.VUE_APP_BASE_API, // url = base url + request url
  // withCredentials: true, // 当跨域请求时发送cookie
  timeout: 5000 // request timeout
})

// request 请求拦截器
service.interceptors.request.use(
  config => {
    // 在发送请求之前做些什么

    if (store.getters.token) {
      // 让每个请求携带令牌 token
      // ['Authentication'] 是自定义的token头
      // 请根据实际情况修改
      config.headers['Authentication'] = getToken()
    }
    return config
  },
  error => {
    // 处理请求错误
    console.log(error) // 调试
    return Promise.reject(error)
  }
)

// response 响应拦截器
service.interceptors.response.use(
  /**
   * 如果您想获得http信息，如头信息或状态信息
   * 请返回响应=>响应  response => response
  */

  /**
   * 通过自定义 HTTP状态码 确定请求状态
   * 这只是一个例子
   * 您还可以通过HTTP状态码来判断状态
   */
  response => {
    const res = response.data

    // 如果自定义代码不是20000，则判断为错误。
    if (res.code !== 20000) {
      Message({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: 非法的令牌 token; 50012:其他客户端登录; 50014: Token 令牌过期;
      if (res.code === 50008 || res.code === 50012 || res.code === 50014) {
        // to re-login
        MessageBox.confirm('您已经登出，您可以取消以停留在此页面，或再次登录', '确认注销', {
          confirmButtonText: 'Re-Login',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // 调试
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
