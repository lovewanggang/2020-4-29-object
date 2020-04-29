var app = getApp()
Page({
  data: {
    imgUrls: null,
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    currentTab: 0, //预设当前项的值
    scrollLeft: 0, //tab标题的滚动条位置
    winHeight:0,
    scrollTop:0,
    id:null,
    product:null,
    products:null,
    productParamers:null,
    showProduct:null,
  },
  changeIndicatorDots: function (e) {
    this.setData({
      indicatorDots: !this.data.indicatorDots
    })
  },
  changeAutoplay: function (e) {
    this.setData({
      autoplay: !this.data.autoplay
    })
  },
  intervalChange: function (e) {
    this.setData({
      interval: e.detail.value
    })
  },
  durationChange: function (e) {
    this.setData({
      duration: e.detail.value
    })
  },
  //点击进入产品详情
  go_detail: function (e) {
    var id = e.currentTarget.dataset.id;
    wx.redirectTo({
      url: '../mdetail/mdetail?id=' + id
    })
  },
  // 滚动切换标签样式
  switchTab: function (e) {
    this.setData({
      currentTab: e.detail.current
    });
    this.checkCor();
  },
  // 点击标题切换当前页时改变样式
  swichNav: function (e) {
    var cur = e.target.dataset.current;
    if (this.data.currentTaB == cur) {
       return false; 
       } else {
      this.setData({
        currentTab: cur
      })
    }
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
      title: '这个产品你一定要看一下',
      path: '/pages/mdetail/mdetail?id='+this.data.id
    }
  },
  //判断当前滚动超过一屏时，设置tab标题滚动条。
  checkCor: function () {
    if (this.data.currentTab > 4) {
      this.setData({
        scrollLeft: 300
      })
    } else {
      this.setData({
        scrollLeft: 0
      })
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({ id: options.id })
    this.lodingData(options.id);

  },
  lodingData:function(pid){
    var that = this;
      wx.request({
        url: 'https://wxnature.nature-nt.com/visitor/detailProduct.do',
     data: {
       id: pid,
       logs: wx.getStorageSync("user"),
       SCcode: app.globalData.SCcode
     },
     success: function (res) {
       var headPics = new Array();
       console.log(res.data.product);
       if (res.data.product.productPic1 != null){
         headPics.push(res.data.product.productPic1);
       }
       if (res.data.product.productPic2 != null){
         headPics.push(res.data.product.productPic2);
       }
       if (res.data.product.productPic3 != null){
         headPics.push(res.data.product.productPic3);
       }
       if (res.data.product.productPic4 != null){
         headPics.push(res.data.product.productPic4);
       }
       if (res.data.product.productPic5 != null){
         headPics.push(res.data.product.productPic5);
       }
       that.setData({
         imgUrls: headPics,
         product: res.data.product,
         products: res.data.products,
         productParamers: res.data.productParamers,
         showProduct: res.data.product.showProduct.replace(/\<img/gi,'<img style="max-width:100%;height:auto;"'),
       })
     }
   })
  },

  //滚动条监听
  scroll: function (e) {
    console.log(e.detail.scrollTop)
    this.setData({ scrollTop: e.detail.scrollTop })
  },
  tap() {
    console.log('tap')
  }
})