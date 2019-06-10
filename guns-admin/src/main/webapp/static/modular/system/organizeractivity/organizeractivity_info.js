/**
 * 初始化组织者活动管理详情对话框
 */
var OrganizeractivityInfoDlg = {
    organizeractivityInfoData : {}
};

/**
 * 清除数据
 */
OrganizeractivityInfoDlg.clearData = function() {
    this.organizeractivityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizeractivityInfoDlg.set = function(key, val) {
    this.organizeractivityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrganizeractivityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrganizeractivityInfoDlg.close = function() {
    parent.layer.close(window.parent.Organizeractivity.layerIndex);
}

/**
 * 收集数据
 */
OrganizeractivityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('openId')
    ;
}

/**
 * 提交添加
 */
OrganizeractivityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organizeractivity/add", function(data){
        Feng.success("添加成功!");
        window.parent.Organizeractivity.table.refresh();
        OrganizeractivityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizeractivityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrganizeractivityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/organizeractivity/update", function(data){
        Feng.success("修改成功!");
        window.parent.Organizeractivity.table.refresh();
        OrganizeractivityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.organizeractivityInfoData);
    ajax.start();
}

$(function() {

});
