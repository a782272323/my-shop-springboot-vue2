import request from '@/utils/request'

export function login(data) {
    return request({
        url: '/user/login',
        method: 'post',
        params: { username: data.username, password: data.password }
    })
}

export function getInfo(token) {
    return request({
        url: '/user/info',
        method: 'get',
        params: { Authentication: token }
    })
}

export function logout() {
    return request({
        url: '/user/logout',
        method: 'post'
    })
}
