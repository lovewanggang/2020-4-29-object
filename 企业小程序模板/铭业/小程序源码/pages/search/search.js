var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    historyShow: true,//历史搜索的显示
    searchValue: '',//输入框的值
    bjSearch: true,//便捷搜索模块,一开始默认true
    datalistShow: false,//datalist的显示
    barcode: "",
    hiddenLoading: true,
    hiddenData: true,
    winHeight: 0,
    searchResults: false,// 搜索结果
    goods: null,
    historyList: null,
    productList: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getHistory();
    this.getHeight();
  },
  getHistory: function () {
    var that = this;
    wx.request({
      url: 'https://wxnature.nature-nt.com/visitor/querySearchHistory.do',
      data: {
        logs: wx.getStorageSync('user')
      },
      success: function (res) {
        that.setData({ historyList: res.data.historyList });

      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  //获取系统高度
  getHeight: function () {
    var that = this;
    wx.getSystemInfo({
      success: function (res) {
        var clientHeight = res.windowHeight,
          clientWidth = res.windowWidth,
          rpxR = 750 / clientWidth;
        var calc = clientHeight * rpxR - 80;
        console.log(calc)
        that.setData({
          winHeight: calc
        });
      }
    });
  },
  //输入框获取焦点
  bindjd: function () {
    var that = this;
    if (that.data.searchValue != '') {//当输入框有值时,便捷搜索消失
      that.setData({
        bjSearch: false,
        datalistShow: true,
        searchResults: false,
        clear: false
      })
    } else {
      that.setData({
        bjSearch: true,
        datalistShow: false,
        searchResults: false,
        clear: true
      })
    }


  },
  //输入框失去焦点
  bindBarcodeBlur: function () {
    var that = this;
    that.setData({
      clear: true
    })
  },
  //点击清除按钮
  clear_search: function () {
    console.log('aaaa')
    var that = this;
    that.setData({
      clear: true,
      searchValue: '',
      bjSearch: true,
      datalistShow: false,
      searchResults: false,
    })
  },
  //拿到搜索框的值
  bindBarcodeInput: function (e) {
    var that = this;
    var value;
    that.setData({
      searchValue: e.detail.value
    })
    if (that.data.searchValue != '') {//当输入框有值时,便捷搜索消失
      that.setData({
        bjSearch: false,
        datalistShow: true,
        searchResults: false,
        clear: false
      })
    } else {
      that.setData({
        bjSearch: true,
        datalistShow: false,
        searchResults: false,
        clear: true
      })
    }
  },
  //软键盘自带的搜索按钮
  query: function (e) {
    console.log("软键盘自带的搜索按钮");
    var url = "https://wxnature.nature-nt.com/visitor/searchProduct.do";//查询数据的URL  
    var that = this;
    // if (that.data.searchValue == undefined
    //   || that.data.searchValue == null
    //   || that.data.searchValue.length <= 0) {
    //   that.setData({ hiddenData: true });
    //   wx.showToast({
    //     icon: 'none',
    //     title: '请输入分类或编号',
    //     duration: 1000
    //   });
    //   return;
    // }
    wx.request({
      url: url,
      data: { 
        searchValue: that.data.searchValue,
        logs: wx.getStorageSync('user')
         },
      method: 'GET',
      success: function (res) {
        console.log("搜索成功==================" + that.data.searchResults);
        that.setData({ searchResults:true})
        console.log("搜索成功==================" + that.data.searchResults);
        console.log(res.data.products);
        console.log(res);
        that.setData({ 
          productList: res.data.products,
          hiddenData: false,
          datalistShow: false,
        });
        // wx.showToast({
        //   title: "获取数据成功",
        //   image: '/images/ok.png',
        //   duration: 2000
        // })
      },
      fail: function (e) {
        var toastText = '获取数据失败' + JSON.stringify(e);
        that.setData({
          hiddenLoading: !that.data.hiddenLoading,
          hiddenData: true
        });
        wx.showToast({
          title: toastText,
          icon: '',
          duration: 2000
        })
      },
      complete: function () {
        // complete  
      }
    })
  },
  //删除历史搜索
  delHistory: function () {
    var that = this;
    that.setData({
      historyShow: false
    })
  },
  //点击便捷搜索中的标签
  searchIt: function (e) {
    var that = this;
    console.log("点击便捷搜索中的标签"+e.currentTarget.dataset.value);//拿到标签的值
    that.setData({
      searchValue: e.currentTarget.dataset.value,//赋值给输入框
      bjSearch: false,//关闭便捷操作
      searchResults: true//搜索块出现
    })
    /*进行搜索*/
  },
//进入产品详情
  toProductDetail: function (e) {
    var id = e.currentTarget.dataset.mydata;
    wx.navigateTo({
      url: '../mdetail/mdetail?id=' + id
    })

  },

  //点击datalist的Li
  Search_datalist: function (e) {
    var that = this;
    console.log(e.currentTarget.dataset.value);
    that.setData({
      searchValue: e.currentTarget.dataset.value,//输入框获取值
      datalistShow: false,//datalist消失
      searchResults: true//搜索块出现
    })
  }
})