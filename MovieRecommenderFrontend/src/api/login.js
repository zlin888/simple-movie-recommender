import request from '@/utils/request'

export function userLogin(userId, password) {
  return request({
    url: '/user/login',
    method: 'POST',
    data: {
      "userId": userId,
      "password": password
    }
  })
}
