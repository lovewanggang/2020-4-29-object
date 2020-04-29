// pages/company_index/company_index.js
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    imgUrls:null,
    indicatorDots: true,
    autoplay: true,
    interval: 5000,
    duration: 1000,
    lookMore:false,//公司简介查看更多
    loadMore:false,//团队风采加载更多
    comLogo:null,
    comContactLogo:null,
    comContact:null,
    comFax:null,
    comEmail:null,
    comName:null,
    comAdrr:null,
    comQRcode:null,
    comProfile:null,
    comPhone:null,
    business_on_off:false,
    hotProduct_on_off:false,
    comVedio_on_off:false,
    comActivity_on_off:false,
    comImage_on_off:false,
    comHonors_on_off:false,
    comPartner_on_off:false,
    comJob_on_off:false,
    business4:null,
    business3:null,
    business2:null,
    business1:null,
    businessPic1:null,
    businessPic2:null,
    businessPic3:null,
    productPic1:null,
    productPic1of:true,
    productPic2:null,
    productPic2of:true,
    productPic3:null,
    productPic3of:true,
    vedioTitle:null,
    vedioUrl:null,
    activityList:null,
    comImageList:null,
    honorList:null,
    partnerList:null,
    jobList:null,
    phoneList:null,
    qqList:null,
    activityIndex:4,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getCompanyInfo();
    this.loading();
   
  },
  loading:function(){
    wx.showToast({
      title: '正在加载',
      icon: 'loading',
      duration: 1000
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
  //查看更多
  lookMore:function(){
    var that= this;
    that.setData({
      lookMore: !that.data.lookMore
    })
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
  //进入商品详情
  detail:function(){
    wx.navigateTo({
      url: '../mdetail/mdetail',
    })
  },
  //点击团队风采文章进入详情
  team_detail:function(e){
    var id = e.currentTarget.dataset.aid;
    console.log("=========文章ID=============="+id)
    wx.navigateTo({
      url: '../Team/Team?id='+id,
    })
  },
  //点击我们的形象放大图片
  bigImg:function(){
    wx.previewImage({
      current: '', // 当前显示图片的http链接
      urls: [] // 需要预览的图片http链接列表
    })
  },


  //点击招聘列表进入职位详情
  zp_detail:function(e){
    var id = e.currentTarget.dataset.testid;
    console.log("=======company中的====id==========" + e.currentTarget.dataset.testid);
    wx.navigateTo({
      url: '../Recruitment_details/Recruitment_details?id='+id,
    })
  },
  //拨打电话
  callit:function(e){
    console.log(e.currentTarget.dataset.value)
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.value //仅为示例，并非真实的电话号码
    })
  },
  //打开位置地图
  getLocation:function(e){
    wx.getLocation({
      type: 'gcj02', //返回可以用于wx.openLocation的经纬度
      success: function (res) {
        var latitude = res.latitude
        var longitude = res.longitude
        wx.openLocation({
          address:e.currentTarget.dataset.value,
          scale: 28
        })
      }
    })
  },
  //团队风采加载更多
  loadMore:function(){
    console.log("======loadMore========");
    var that = this;
    that.setData({
      loadMore: !that.data.loadMore,
      activityIndex: that.data.activityIndex+4
      
    })
  },
  //团队风采收起
  packUp: function () {
    console.log("======packUp========");
    var that = this;
    that.setData({
      loadMore: !that.data.loadMore,
      activityIndex: 4

    })
  },
  //拨打公司电话
  callConpany:function(e){
    console.log(e.currentTarget.dataset.value)
    wx.makePhoneCall({
      phoneNumber: e.currentTarget.dataset.value //仅为示例，并非真实的电话号码
    })
  },
  shareewm:function(e){
    wx.previewImage({
      current: app.globalData.comQRcode, // 当前显示图片的http链接
      urls: [app.globalData.comQRcode] // 需要预览的图片http链接列表
    })
  },
  hotphone:function(){
    wx.makePhoneCall({
      phoneNumber: '1340000' //仅为示例，并非真实的电话号码
    })
  },
  getCompanyInfo: function () {
    var that = this;
    wx.request({
      url: "https://wxnature.nature-nt.com/visitor/showCompany.do",
      data: {
        SCcode: app.globalData.SCcode,
      },
      success: function (res) {
        app.globalData.comQRcode = res.data.company.comQRcode;
        that.setData({
          business_on_off: res.data.module.business_on_off,
          hotProduct_on_off: res.data.module.hotProduct_on_off,
          comVedio_on_off: res.data.module.comVedio_on_off,
          comActivity_on_off: res.data.module.comActivity_on_off,
          comImage_on_off: res.data.module.comImage_on_off,
          comHonors_on_off: res.data.module.comHonors_on_off,
          comPartner_on_off: res.data.module.comPartner_on_off,
          comJob_on_off: res.data.module.comJob_on_off,
          comLogo: res.data.company.comLogo,
          comName: res.data.company.comName,
          comQRcode: res.data.company.comQRcode,
          comAdrr: res.data.company.comProvince + res.data.company.comCity + res.data.company.comDistrict + res.data.company.comAddr,
          comProfile: res.data.company.comProfile,
          comContactLogo: res.data.company.comContactLogo,
          comContact: res.data.company.comContact,
          comFax: res.data.company.comFax,
          comEmail: res.data.company.comEmail,
          comContactLogo: res.data.company.comContactLogo,
          phoneList: res.data.companyContactPhone,
          qqList: res.data.companyContactQQ,
        })
        if (res.data.companyContactPhone.length > 0) {
          that.setData({
            comPhone: res.data.companyContactPhone[0].comPhone
          })
        }
        var ss = new Array();
        for (var i = 0; i < res.data.companyPosters.length; i++) {
          ss.push(res.data.companyPosters[i].posterUrl);
        }
        that.setData({
          imgUrls: ss
        })
       //提供服务
        if (res.data.module.business_on_off){
          that.setData({
            business1: res.data.companyBusiness.business1,
            business2: res.data.companyBusiness.business2,
            business3: res.data.companyBusiness.business3,
            business4: res.data.companyBusiness.business4,
            businessPic1: res.data.companyBusiness.showPic1,
            businessPic2: res.data.companyBusiness.showPic2,
            businessPic3: res.data.companyBusiness.showPic3,
          })
        }
        //人气商品
        if (res.data.module.hotProduct_on_off){
          if (res.data.products.length == 3){
            that.setData({
              productPic1: res.data.products[0].productPic1,
              productPic2: res.data.products[1].productPic1,
              productPic3: res.data.products[2].productPic1
            })
          } else if (res.data.products.length == 2){
            that.setData({
              productPic1: res.data.products[0].productPic1,
              productPic2: res.data.products[1].productPic1,
              productPic3of: false
            })
          } else if (res.data.products.length == 1) {
            that.setData({
              productPic1: res.data.products[0].productPic1,
              productPic2of: false,
              productPic3of: false
            })
          }
          
        }
        //视频展示
        if (res.data.module.comVedio_on_off) {
          that.setData({
            vedioTitle: res.data.vedio.vedioTitle,
            vedioUrl: res.data.vedio.vedioUrl
          })
        }
        //团队风采
        if (res.data.module.comActivity_on_off) {
          that.setData({
            activityList: res.data.companyActivities
          })
        }
        //我们的形象
        if (res.data.module.comImage_on_off) {
          that.setData({
            comImageList: res.data.companyImages
          })
        }
        //荣誉资质
        if (res.data.module.comHonors_on_off) {
          that.setData({
            honorList: res.data.companyHonors
          })
        }
        //合作伙伴
        if (res.data.module.comPartner_on_off) {
          that.setData({
            partnerList: res.data.companyPartners
          })
        }
        //公司招聘
        if (res.data.module.comJob_on_off) {
          that.setData({
            jobList: res.data.companyJobInfo
          })
        }
       
      }
    })

  },

 
  
})