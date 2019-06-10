/**
 * 初始化导师活动管理详情对话框
 */
var TeacheractivityInfoDlg = {
    teacheractivityInfoData : {}
};

/**
 * 清除数据
 */
TeacheractivityInfoDlg.clearData = function() {
    this.teacheractivityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacheractivityInfoDlg.set = function(key, val) {
    this.teacheractivityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TeacheractivityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TeacheractivityInfoDlg.close = function() {
    parent.layer.close(window.parent.Teacheractivity.layerIndex);
}

/**
 * 收集数据
 */
TeacheractivityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    .set('acId')
    ;
}

/**
 * 提交添加
 */
TeacheractivityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacheractivity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Teacheractivity.table.refresh();
        TeacheractivityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacheractivityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TeacheractivityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/teacheractivity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Teacheractivity.table.refresh();
        TeacheractivityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.teacheractivityInfoData);
    ajax.start();
}

$(function() {

});
