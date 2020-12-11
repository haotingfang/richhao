import request from '@/utils/request'

// 登录方法
export function login(userName, password) {
  const data = {
    userName,
    password
  }
  return request({
    url: '/auth/web_login',
    method: 'post',
    data: data
  })
}


// 获取用户详细信息
export function getInfo() {
  return request({
    url: '/auth/getInfo',
    method: 'get'
  })
}

// 退出方法
export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

