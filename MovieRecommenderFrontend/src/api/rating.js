import request from '@/utils/request'

export function rateFilm(userId, filmId, rating) {
  return request({
    url: "/rating/insert",
    method: 'POST',
    data: {
      "userid": userId,
      "filmid": filmId,
      "rating": rating
    }
  })
}

export function getUserRating(userId, filmId) {
  return request({
    url: "/rating/getRating",
    method: 'POST',
    data: {
      "userId": userId,
      "filmId": filmId
    }
  })
}
