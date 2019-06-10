/**
 * 初始化角色申请管理详情对话框
 */
var AuthorityapplicationInfoDlg = {
    authorityapplicationInfoData : {}
};

/**
 * 清除数据
 */
AuthorityapplicationInfoDlg.clearData = function() {
    this.authorityapplicationInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuthorityapplicationInfoDlg.set = function(key, val) {
    this.authorityapplicationInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AuthorityapplicationInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
AuthorityapplicationInfoDlg.close = function() {
    parent.layer.close(window.parent.Authorityapplication.layerIndex);
}

/**
 * 收集数据
 */
AuthorityapplicationInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    .set('targetAuth')
    .set('path')
    .set('content')
    ;
}

/**
 * 提交添加
 */
AuthorityapplicationInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/authorityapplication/add", function(data){
        Feng.success("添加成功!");
        window.parent.Authorityapplication.table.refresh();
        AuthorityapplicationInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.authorityapplicationInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
AuthorityapplicationInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/authorityapplication/update", function(data){
        Feng.success("修改成功!");
        window.parent.Authorityapplication.table.refresh();
        AuthorityapplicationInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.authorityapplicationInfoData);
    ajax.start();
}

$(function() {

});
