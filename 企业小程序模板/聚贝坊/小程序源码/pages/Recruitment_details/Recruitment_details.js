const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    job:null,
    jobs:null,
    jobDetail:null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getJobInfo(options.id)
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
  getJobInfo:function (id){
    var that = this;
  wx.request({
    url: "https://wxnature.nature-nt.com/visitor/detailJob.do",
    data: {
      id: id,
      SCcode: app.globalData.SCcode,
    },
    success: function (res) {
      console.log(res.data.job);
      console.log(res.data.jobs);
      that.setData({
        job:res.data.job,
        detailJob: res.data.job.cjiDetail.replace(/\<img/gi, '<img style="max-width:100%;height:auto;"'),
        jobs:res.data.jobs,
      })
    }
  })
  },
  zw_detail:function(e){
    var id = e.currentTarget.dataset.comjobid;
    console.log("=======company中的====id==========" + e.currentTarget.dataset.comjobid);
    wx.redirectTo({
      url: '../Recruitment_details/Recruitment_details?id=' + id,
    })
  }
})