/**
 * 初始化useractivity详情对话框
 */
var WxuseractivityInfoDlg = {
    wxuseractivityInfoData : {}
};

/**
 * 清除数据
 */
WxuseractivityInfoDlg.clearData = function() {
    this.wxuseractivityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxuseractivityInfoDlg.set = function(key, val) {
    this.wxuseractivityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
WxuseractivityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
WxuseractivityInfoDlg.close = function() {
    parent.layer.close(window.parent.Wxuseractivity.layerIndex);
}

/**
 * 收集数据
 */
WxuseractivityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    .set('acId')
    ;
}

/**
 * 提交添加
 */
WxuseractivityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxuseractivity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Wxuseractivity.table.refresh();
        WxuseractivityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxuseractivityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
WxuseractivityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/wxuseractivity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Wxuseractivity.table.refresh();
        WxuseractivityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.wxuseractivityInfoData);
    ajax.start();
}

$(function() {

});
