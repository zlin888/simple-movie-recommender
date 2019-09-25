import request from '@/utils/request'

export function recommendMovies(userId, amount) {
  return request({
    url: 'film/listTopRecommend',
    method: 'POST',
    data: {
      "userId": userId,
      "howMany": amount
    }
  })
}
