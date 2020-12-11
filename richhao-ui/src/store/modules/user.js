import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken } from '@/utils/auth'
import {getAvatar} from "../../utils/auth";

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: [],
    permissions: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    }
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const userName = userInfo.userName.trim()
      const password = userInfo.password
      return new Promise((resolve, reject) => {
        login(userName, password).then(res => {
          setToken(res.data.token)
          commit('SET_TOKEN', res.data.token)
          //userInfoVo
     /*     const user = res.data.loginUser
          const avatar = user.userInfoVo.avatarUrl == "" ? require("@/assets/image/profile.jpg") : user.userInfoVo.avatarUrl;
          if (user.roles && user.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', user.roles)
            commit('SET_PERMISSIONS', user.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.userInfoVo.userName)
          commit('SET_AVATAR', avatar)*/
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 获取用户信息
    GetInfo({ commit, state }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.user
          const avatar = user.avatarUrl == "" ? require("@/assets/image/profile.jpg") :  user.avatarUrl;
          if (res.roles && res.roles.length > 0) { // 验证返回的roles是否是一个非空数组
            commit('SET_ROLES', res.roles)
            commit('SET_PERMISSIONS', res.permissions)
          } else {
            commit('SET_ROLES', ['ROLE_DEFAULT'])
          }
          commit('SET_NAME', user.nickName)
          commit('SET_AVATAR', avatar)
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default user
