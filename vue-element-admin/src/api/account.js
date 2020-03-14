import request from '@/utils/request'

// 获取所有用户信息
export function accountList(pageIndex, pageSize, sortType, sortName) {
  return request({
    url: '/account/list',
    method: 'get',
    params: {pageIndex: pageIndex, pageSize: pageSize, sortName: sortName, sortType: sortType}
  })
}

// 根据条件查询用户信息
export function sortAccountList(pageIndex, pageSize, sortType, sortName, sortAccount) {
  return request({
    url: '/account/lists',
    method: 'get',
    params: {pageIndex: pageIndex, pageSize: pageSize, sortName: sortName, sortType: sortType,
             username: sortAccount.username, email: sortAccount.email, phone: sortAccount.phone}
  })

}
