import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/v1/user/login?username='+data.username+'&password='+data.password,
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/v1/user/info',
    method: 'get'
    // params: { Authentication: token }
  })
}

export function logout() {
  return request({
    url: '/v1/user/logout',
    method: 'post'
  })
}
