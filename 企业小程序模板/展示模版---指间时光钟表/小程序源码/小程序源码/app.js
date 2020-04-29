//app.js
var QQMapWX = require('utils/qqmap-wx.js');
App({
  onLaunch: function () {
    var that = this;
    //展示本地存储能力
    console.log("调用API从本地缓存中获取数据logs" + logs)
    var logs = wx.getStorageSync('user') || []
    //logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)
    if (logs.length <= 2) {
      // 登录
      console.log("===========开始登录=============");
      wx.login({
        success: function (res) {

          // 发送 res.code 到后台换取 openId, sessionKey, unionId
          console.log("code" + res.code)
          if (res.code) {
            //发起网络请求
            wx.request({
              url: 'https://wxnature.nature-nt.com/wx/login.do',
              data: {
                code: res.code,
                SCcode: that.globalData.SCcode
              },
              success: function (data) {
              console.log(data)
              that.globalData.logs = data.data.logs;
              console.log("logs=" + that.globalData.logs)
              wx.setStorageSync("user", data.data.logs);
              that.isReady = true
              }
            })
          } else {
            console.log('获取用户登录态失败！' + res.errMsg)
          }
        }
      });
    }
    //记录用户访问
    this.userVisit();

  },
  userVisit: function () {
    var that = this;
    wx.request({
      url: 'https://wxnature.nature-nt.com/visitor/userVisit.do',
      data: {
        logs: wx.getStorageSync("user"),
        SCcode: that.globalData.SCcode
      },
      success: function (data) {
        console.log("user visited");
      }
    })
  },
  globalData: {
    userInfo: null,
    SCcode: "SC460515",
    status: null,
    logs: null,
    comQRcode: null,
    comPhone: null,
    authorized:true,
  },
  
})