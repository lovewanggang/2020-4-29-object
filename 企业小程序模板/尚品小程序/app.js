//app.js
var QQMapWX = require('utils/qqmap-wx.js');
App({
  onLaunch: function () {
    var that = this;
    // 展示本地存储能力
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
              that.globalData.logs = JSON.parse(JSON.stringify(data.data.logs));
              console.log("logs=" + that.globalData.logs)
              wx.setStorageSync("user", that.globalData.logs)
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
    // 获取用户信息
    this.getUserInfo();
    //     wx.getSetting({
    //       success: res => {
    //         console.log("11111111111111111111111");
    //         if (res.authSetting['scope.userInfo']) {
    //           // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //           wx.getUserInfo({
    //             success: res => {
    //               console.log("222222222222222222222");
    //               // 可以将 res 发送给后台解码出 unionId
    //               this.globalData.userInfo = res.userInfo
    //               console.log(this.globalData.userInfo)
    //               // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //               // 所以此处加入 callback 以防止这种情况
    //               if (this.userInfoReadyCallback) {
    //                 this.userInfoReadyCallback(res)
    //               }
    //             }
    //           })
    //         }else{
    // console.log("==========else===")

    //         }
    //       }
    //     })
  },
  userVisit: function (){
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
  getUserInfo: function () {
    var that = this;
    wx.getUserInfo({
      success: res => {
        // 更新当前用户的信息
        wx.request({
          url: 'https://wxnature.nature-nt.com/wx/getUserInfo.do',
          data: {
            iv: res.iv,
            encryptedData: res.encryptedData,
            logs: wx.getStorageSync("user"),
          },
          success: function (data) {
            console.log(data);
          }
        })
        that.isReady = true
        that.globalData.userInfo = res.userInfo;
        that.globalData.status = 1
        if (this.userInfoReadyCallback) {
          this.userInfoReadyCallback(res)
        }


      },
      fail() {
        // re.UserUnauthorized(user.id)
        that.isAuthorization = false
        // console.log('user', user)
        wx.showModal({
          title: '温馨提示',
          content: '为了提供更好的服务和良好的使用体验，需要使用您的头像和昵称等用户信息',
          showCancel: false,
          confirmText: '立即授权',
          success: function () {
            wx.authorize({
              scope: 'scope.userInfo',
              success() {
              },
              fail: function () {
                wx.openSetting({
                  success: (res) => {

                    that.checkAuthorize()

                  }
                })

              }

            })



          }
        })

        wx.hideToast()
      }
    });
  },
  checkAuthorize: function () {
    var that = this;
    wx.getSetting({
      success(res) {

        if (!(res.authSetting['scope.userInfo'] && res.authSetting['scope.userLocation'])) {

          wx.authorize({
            scope: 'scope.userInfo',
            success() {


              that.globalData.status = 1
            },
            fail: function () {
              that.globalData.status = 0
              wx.showModal({
                title: '温馨提示',
                content: '为了提供更好的服务和良好的使用体验，需要使用您的头像和昵称等用户信息',
                showCancel: false,
                confirmText: '立即授权',
                success: function () {
                  wx.authorize({
                    scope: 'scope.userInfo',
                    success() {
                    },
                    fail: function () {
                      wx.openSetting({
                        success: (res) => {

                          that.checkAuthorize()

                        }
                      })
                    }
                  })



                }

              })

            }
          })
          wx.authorize({
            scope: 'scope.userLocation',
            success() {
              console.log("getrLocatiorn")

              that.globalData.status = 1
            },
            fail: function () {
              that.globalData.status = 0
              wx.showModal({
                title: '温馨提示',
                content: '为了提供更好的服务和良好的使用体验，需要使用您的头像和昵称等用户信息',
                showCancel: false,
                confirmText: '立即授权',
                success: function () {
                  wx.authorize({
                    scope: 'scope.userLocation',
                    success() {
                    },
                    fail: function () {
                      wx.openSetting({
                        success: (res) => {

                          that.checkAuthorize()

                        }
                      })
                    }
                  })



                }

              })

            }
          })

        }
        else {
          that.getUserInfo();
          that.globalData.status = 1
        }

      }
    })

  },
  globalData: {
    userInfo: null,
    SCcode: "SC658869",
    status: null,
    logs: null,
    comQRcode:null
  }
})