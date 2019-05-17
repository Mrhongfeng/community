/**
 * 初始化微信用户管理详情对话框
 */
var WxuserInfoDlg = {
    wxuserInfoData : {}
};

/**
 * 清除数据
 */
WxuserInfoDlg.clearData = function() {
    this.wxuserInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxuserInfoDlg.set = function(key, val) {
    this.wxuserInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxuserInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WxuserInfoDlg.close = function() {
    parent.layer.close(window.parent.Wxuser.layerIndex);
}

/**
 * 收集数据
 */
WxuserInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    .set('userName')
    .set('gender')
    .set('avatarUrl')
    .set('country')
    .set('province')
    .set('city')
    .set('authority')
    .set('credit')
    ;
}

/**
 * 提交添加
 */
WxuserInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxuser/add", function(data){
        Feng.success("添加成功!");
        window.parent.Wxuser.table.refresh();
        WxuserInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxuserInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WxuserInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxuser/update", function(data){
        Feng.success("修改成功!");
        window.parent.Wxuser.table.refresh();
        WxuserInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxuserInfoData);
    ajax.start();
}

$(function() {

});
