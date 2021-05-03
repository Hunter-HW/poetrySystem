<template>
  <div class="login-container">
    <!-- 导航栏 -->
    <van-nav-bar class="page-nav-bar" title="登录" />
    <van-form @submit="onSubmit">
      <van-field
        v-model="user.mobile"
        name="手机号"
        label="手机号"
        placeholder="手机号"
        :rules="userLoginFormRules.mobile"
        type="number"
        maxlength="11"
      />
      <van-field
        v-model="user.password"
        type="password"
        name="密码"
        label="密码"
        placeholder="密码"
        :rules="userLoginFormRules.password"
      />
      <div style="margin: 16px">
        <van-button round block type="info" native-type="submit"
          >登录</van-button
        >
      </div>
    </van-form>
  </div>
</template>

<script>
import { userLogin } from '../../api/index'
export default {
  name: 'LoginIndex',
  components: {},
  props: {},
  data () {
    return {
      user: {
        mobile: '',
        password: ''
      },
      userLoginFormRules: {
        mobile: [{
          required: true,
          message: '手机号不能为空'
        }, {
          pattern: /1[3|5|7|8]\d{9}/,
          message: '手机号格式错误'
        }],
        password: [{ required: true, message: '密码不能为空' }]
      }

    }
  },
  computed: {},
  watch: {},
  created () {},
  mounted () {},
  methods: {
    async onSubmit () {
      // 获取表单数据
      const params = new URLSearchParams()
      params.append('mobile', this.user.mobile)
      params.append('password', this.user.password)
      // 表单验证

      // 在组件中必须通过this.$toast来调用

      this.$toast.loading({
        message: '登录中...',
        forbidClick: true, // 禁用背景点击
        duration: 0 // 持续时间默认2000, 如果为0则持续展示
      })
      // 提交请求
      userLogin(params)
        .then(res => {
          if (res.code === 1) {
            this.$toast.success(res.msg)
            // console.log(res.msg)
          } else {
            // console.log(res.msg)
            this.$toast.fail(res.msg)
          }
        })
        .catch(err => {
          // console.log(err)
          this.$toast.fail(err)
        })
      // 根据响应结果处理后续
    }
  }
}
</script>

<style lang="sass" scoped>
</style>>
