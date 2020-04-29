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
    showProduct: null,
    html: '<h1 style="font-size: 32px; font-weight: bold; border-bottom-color: rgb(204, 204, 204); border-bottom-width: 2px; border-bottom-style: solid; padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;">这是一个标题</h1><p style="text-indent: 2em;">啊数据库的发<em><strong>生的积分阿斯兰多夫</strong></em>就哈萨<span style="text-decoration: underline;">克两极</span>分化ad复健<span style="text-decoration: line-through;">科哈</span>士大夫啊<span style="border: 1px solid rgb(0, 0, 0);">速度快放假</span>好的发<br/></p><p style="text-indent: 0em;"><img src="/ueditor/jsp/upload/image/20180529/1527558082310007663.jpg" title="1527558082310007663.jpg" alt="deepin.jpg"/></p><h1 style="font-size: 32px; font-weight: bold; border-bottom-color: rgb(204, 204, 204); border-bottom-width: 2px; border-bottom-style: solid; padding: 0px 4px 0px 0px; text-align: center; margin: 0px 0px 20px;">这是一个标题</h1><p style="text-indent: 2em;">啊数据库的发<em><strong>生的积分阿斯兰多夫</strong></em>就哈萨<span style="text-decoration: underline;">克两极</span>分化ad复健<span style="text-decoration: line-through;">科哈</span>士大夫啊<span style="border: 1px solid rgb(0, 0, 0);">速度快放假</span>好的发<br/></p><p style="text-indent: 0em;"><img src="/ueditor/jsp/upload/image/20180529/1527558082310007663.jpg" title="1527558082310007663.jpg" alt="deepin.jpg"/></p><ol class=" list-paddingleft-2" style="list-style-type: decimal;"><li><p style="text-indent: 0em;">奥斯卡剪短发送快递费</p></li><li><p style="text-indent: 0em;">奥斯卡大姐夫</p></li><li><p style="text-indent: 0em;">阿拉格纳罗斯电饭锅那里</p></li><li><p style="text-indent: 0em;">东方国际看来你电饭锅</p></li><li><p style="text-indent: 0em;">代理费噶十多个</p></li></ol><ul class=" list-paddingleft-2" style="list-style-type: disc;"><li><p style="text-indent: 0em;">盛大发售</p></li><li><p style="text-indent: 0em;">阿萨德佛教看</p></li><li><p style="text-indent: 0em;">sad李开复噶三等奖gas快乐大局观</p></li><li><p style="text-indent: 0em;">阿萨德管理局阿斯兰的公交卡十多个<br/></p></li></ul>',
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
})