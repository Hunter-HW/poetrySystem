import { post } from '../utils/request'

// 用户登录
export const userLogin = (params) => post('user/userLogin', params)
