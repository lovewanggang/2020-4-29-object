//获取应用实例  
var app = getApp();
Page({
  data: {
    clear:true,//清空按钮的显示
    searchValue:'',//搜索框的值
    barcode: "",
    hiddenLoading: true,
    hiddenData: true,
    hiddenClear: true,
    winHeight:0,
    scroll:0,
    Product: {},
    selected_left:0,//选中的左边菜单
    goods:1,
    productClasses:null,
    toView: '0',
    authorized: app.globalData.authorized,
  },
  //获取系统高度
getHeight:function(){
  
  var that=this;
  wx.getSystemInfo({
    success: function (res) {
      var clientHeight = res.windowHeight,
        clientWidth = res.windowWidth,
        rpxR = 750 / clientWidth;
      var calc = clientHeight * rpxR
      console.log(calc)
      that.setData({
        winHeight: calc
      });
      console.log(that.data.winHeight + "@@@@" + clientHeight + "rpxR:" + rpxR);
    }
  });
},
  
 
//输入框获取焦点
  bindjd:function(){
    wx.navigateTo({
      url: '../search/search',
    })
  },
  selectMenu: function (e) {
    console.log("======selectMenu===============");
    var that = this;
    var id = e.currentTarget.dataset.bigclassid;
   
    wx.request({
      url: 'https://wxnature.nature-nt.com/visitor/findProductClassByBCid.do',
      data: {
        id: id,
      },
      success: function (res) {
        console.log("======success===============");
        console.log(res.data.productClasses);
        that.setData({
          productClasses: res.data.productClasses,
        })
      }
    })


    var index = e.currentTarget.dataset.value;
    console.log(e)
    this.setData({
      selected_left: index,
      scroll:0
      // toView: 'order' + index.toString()
    })
    // console.log(this.data.toView);
  },
  toProductDetail:function(e){
  var id = e.currentTarget.dataset.mydata;
   wx.navigateTo({
     url: '../mdetail/mdetail?id='+id
   })

},

  /**
    * 用户点击右上角分享
    */
  onShareAppMessage: function (menu) {
    if (res.from === 'button') {
      // 来自页面内转发按钮
      console.log(res.target)
    }
    return {
      title: '这里有一些产品需要你看一下',
      path: '/pages/category/category'
    }
  },


  onLoad: function (options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.getHeight();
    this.getProductData();
  },
  onReady: function () {
    // 页面渲染完成
  },
  onShow: function () {
    // 页面显示
    this.setData({
      authorized: app.globalData.authorized,
    });
  },
  onHide: function () {
    // 页面隐藏
  },
  onUnload: function () {
    // 页面关闭
  },
  hotphone: function () {
    var that = this;
    wx.makePhoneCall({
      phoneNumber: app.globalData.comPhone //仅为示例，并非真实的电话号码
    })
  },
  shareewm: function (e) {
    wx.previewImage({
      current: app.globalData.comQRcode, // 当前显示图片的http链接
      urls: [app.globalData.comQRcode] // 需要预览的图片http链接列表
    })
  },
  getProductData: function () {
    console.log("==========getProductData=========");
    console.log(app.globalData.SCcode);
    var that = this;
    wx.request({
      url: "https://wxnature.nature-nt.com/visitor/productHomePage.do",
      data: {
        SCcode: app.globalData.SCcode,
      },
      success: function (res) {
        console.log(res.data.productClasses);
        console.log(">>>>>>>>>>>>>>");
        console.log(res.data.productBigClasses);
        that.setData({
          goods: res.data.productBigClasses,
          productClasses: res.data.productClasses,
        })
      }
    })
},
  viewPCpic:function (e){
    console.log("e>>>>",e);
      wx.previewImage({
        urls: [e.currentTarget.dataset.picurl],
      })
  }

})  