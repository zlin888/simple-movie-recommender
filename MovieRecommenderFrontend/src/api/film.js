import request from '@/utils/request'

/*
list top n recommended movie for the user.
 */
export function listTopNMovies(userId, n) {
  return request({
    url: '/film/listTopNFilms',
    method: 'POST',
    data: {
      "userId": userId,
      "n": n
    }
  })
}

/*
list all films
*/
export function listAllMovies() {
  return request({
    url: '/film/listAll',
    method: 'GET'
  })
}
