import request from '@/utils/request'

// 获取所有用户信息
export function accountList(pageIndex, pageSize, sortType, sortName) {
  return request({
    url: '/account/list',
    method: 'get',
    params: { pageIndex: pageIndex, pageSize: pageSize, sortName: sortName, sortType: sortType }
  })
}

// 根据条件查询用户信息
export function sortAccountList(pageIndex, pageSize, sortType, sortName, sortAccount) {
  return request({
    url: '/account/lists',
    method: 'get',
    params: { pageIndex: pageIndex, pageSize: pageSize, sortName: sortName, sortType: sortType,
             username: sortAccount.username, email: sortAccount.email, phone: sortAccount.phone }
  })
}

// 添加用户
export function postAccount(data) {
  return request({
    url: '/account/list',
    method: 'post',
    data
  })
}

// 修改用户
export function putAccount(data, user_id) {
  return request({
    url: '/account/list/' + user_id,
    method: 'put',
    data
  })
}

// 单个删除
export function deleteAccount(user_id) {
  return request({
    url: '/account/list/' + user_id,
    method: 'delete'
  })
}

// 批量删除
export function deleteMultiAccount(user_ids) {
  return request({
    url: '/account/list',
    method: 'delete',
    params: { userIds: user_ids }
  })
}

