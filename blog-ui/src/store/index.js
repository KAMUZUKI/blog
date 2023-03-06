import { createStore } from 'vuex'

const store = createStore({
  state () {
    return {
      count: 0,
      msg:'Hello Vuex',
      isCertified:false,
<<<<<<< HEAD
      path:'http://localhost:8080/demo',
      wspath:'ws://localhost:8080/demo',      
=======
      path:'http://localhost:8080/blog',
      // wspath:'ws://localhost:8081/demo',      
>>>>>>> 8d12ac91f6c224e0616eeebaf6a5dcc3c88ffab0
      // path:'http://175.178.212.207:8080/demo',
      // wspath:'ws://175.178.212.207:8080/demo',
      // path:'http://localhost:8081/demo',
      // wspath:'ws://localhost:8081/demo',
      keywords:[],
    }
  },
  mutations: {
    increment (state) {
      state.count++
    }
  }
})

export default store