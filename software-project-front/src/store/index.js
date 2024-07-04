// store.js
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: {
      isLoggedIn: false,
      roles: [] // 用户角色或权限列表
    }
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    }
  },
  actions: {
    fetchUser({ commit }) {
      // 模拟从服务器获取用户信息
      const user = {
        isLoggedIn: true,
        roles: ['admin', 'user'] // 这里是用户的角色或权限
      };
      commit('setUser', user);
    }
  },
  getters: {
    isLoggedIn: state => state.user.isLoggedIn,
    userRoles: state => state.user.roles
  }
});
