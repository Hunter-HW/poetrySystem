module.exports = {
  plugins: {
    // 'autoprefixer': {
    //   browsers: ['Android >= 4.0', 'iOS >= 8']
    // },
    // 把px转换为rem
    'postcss-pxtorem': {
      // rootValue: 37.5,
      rootValue({file}){
        // console.log('处理的css文件:', file)
        // return 37.5
        return file.indexOf('vant') !== -1 ? 37.5 : 75
      },
      propList: ['*']
    }
  }
}