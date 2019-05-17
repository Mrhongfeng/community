/**
 * 初始化社区活动管理详情对话框
 */
var CommunityInfoDlg = {
    communityInfoData : {}
};

/**
 * 清除数据
 */
CommunityInfoDlg.clearData = function() {
    this.communityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityInfoDlg.set = function(key, val) {
    this.communityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommunityInfoDlg.close = function() {
    parent.layer.close(window.parent.Community.layerIndex);
}

/**
 * 收集数据
 */
CommunityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('acOrg')
    .set('acTitle')
    .set('acContent')
    .set('acLocation')
    .set('acstartTime')
    .set('acendTime')
    .set('acThreshold')
    .set('acBonus')
    .set('acState')
    ;
}

/**
 * 提交添加
 */
CommunityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/add", function(data){
        Feng.success("添加成功!");
        window.parent.Community.table.refresh();
        CommunityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.communityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CommunityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/community/update", function(data){
        Feng.success("修改成功!");
        window.parent.Community.table.refresh();
        CommunityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.communityInfoData);
    ajax.start();
}

$(function() {

});
