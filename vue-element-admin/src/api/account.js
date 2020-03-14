import request from '@/utils/request'

export function accountList() {
    return request({
        url: '/account/list',
        method: 'get'
    })
}
