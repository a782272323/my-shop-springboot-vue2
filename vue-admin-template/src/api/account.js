import request from '@/utils/request'

export function getInfo() {
  return request({
    url: '/v1/account/list',
    method: 'get'
  })
}
