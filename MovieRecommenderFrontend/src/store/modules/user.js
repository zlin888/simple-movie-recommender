import {
  userLogin
} from '@/api/login'

const state = {
  name: 'Steve',
  id: 1
}

const getters = {
  name: state => {
    return state.name
  },
  id: state => {
    return state.id
  }
}


const actions = {
  login({
    commit
  }, {
    userId,
    password
  }) {
    return new Promise((resolve, reject) => {
      userLogin(userId, password).then(response => {
        const userName = response.data.data
        commit('setId', Number(userId))
        commit('setName', userName)
        console.log(userName)
        resolve('success')
      }).catch(error => {
        // alert("get error")
        reject(error)
      })
    })
  },

  logOut({
    commit
  }) {
    //todo
    return 0
  }
}

const mutations = {
  setName: (state, name) => {
    state.name = name
  },
  setId: (state, id) => {
    state.id = id
  }
}


export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations
}
