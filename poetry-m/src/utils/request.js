/**
 * 请求模块
 */
// import axios from 'axios'

// const request = axios.create({
//   baseURL: 'http://localhost:8888/'
// })

// // 请求拦截器

// // 响应拦截器

// export default request

import axios from 'axios'
import router from '../router'

axios.defaults.timeout = 5000 // 超时时间为5s
axios.defaults.withCredentials = true // 允许跨域

// Content-Type 响应头
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencode;charsett=UTF-8'
// 基础url
axios.defaults.baseURL = 'http://localhost:8888'

// 响应拦截器
axios.interceptors.response.use(
  response => {
    // 如果response中的stauts是200，说明访问到接口了，否则就是错误
    if (response.status === 200) {
      return Promise.resolve(response)
    } else {
      return Promise.reject(response)
    }
  },
  error => {
    if (error.response.status) {
      switch (error.response.status) {
        // 如果是401，说明未登录，则重定向到默认的位置
        case 401:
          router.replace({
            path: '/',
            query: {
              redirect: router.currentRoute.fullPath
            }
          })
          break
        case 404: // 没找到
          break
      }
    }
    return Promise.reject(error.response)
  }
)

/**
 * 封装get方法
 */
export function get (url, params = {}) {
  return new Promise((resolve, reject) => {
    axios.get(url, { params: params })
      .then(response => { // 成功之后的处理
        resolve(response.data)
      })
      .catch(err => { // 失败之后的处理
        reject(err)
      })
  })
}
/**
 * 封装post方法
 */
export function post (url, data = {}) {
  return new Promise((resolve, reject) => {
    axios.post(url, data)
      .then(response => { // 成功之后的处理
        resolve(response.data)
      })
      .catch(err => { // 失败之后的处理
        reject(err)
      })
  })
}
