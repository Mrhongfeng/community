/**
 * 初始化社区活动图片管理详情对话框
 */
var CommunityPictureInfoDlg = {
    communityPictureInfoData : {}
};

/**
 * 清除数据
 */
CommunityPictureInfoDlg.clearData = function() {
    this.communityPictureInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityPictureInfoDlg.set = function(key, val) {
    this.communityPictureInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
CommunityPictureInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
CommunityPictureInfoDlg.close = function() {
    parent.layer.close(window.parent.CommunityPicture.layerIndex);
}

/**
 * 收集数据
 */
CommunityPictureInfoDlg.collectData = function() {
    this
    .set('id')
    .set('pictureName')
    .set('picturePath')
    ;
}

/**
 * 提交添加
 */
CommunityPictureInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/communityPicture/add", function(data){
        Feng.success("添加成功!");
        window.parent.CommunityPicture.table.refresh();
        CommunityPictureInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.communityPictureInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
CommunityPictureInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/communityPicture/update", function(data){
        Feng.success("修改成功!");
        window.parent.CommunityPicture.table.refresh();
        CommunityPictureInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.communityPictureInfoData);
    ajax.start();
}

$(function() {

});
