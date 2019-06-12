/**
 * 社区活动图片管理管理初始化
 */
var CommunityPicture = {
    id: "CommunityPictureTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
CommunityPicture.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '图片id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '图片名称', field: 'pictureName', visible: true, align: 'center', valign: 'middle'},
            {title: '图片路径', field: 'picturePath', visible: true, align: 'center', valign: 'middle'},
            {title: '活动id', field: 'communityId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
CommunityPicture.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        CommunityPicture.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加社区活动图片管理
 */
CommunityPicture.openAddCommunityPicture = function () {
    var index = layer.open({
        type: 2,
        title: '添加社区活动图片管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/communityPicture/communityPicture_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看社区活动图片管理详情
 */
CommunityPicture.openCommunityPictureDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '社区活动图片管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/communityPicture/communityPicture_update/' + CommunityPicture.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除社区活动图片管理
 */
CommunityPicture.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/communityPicture/delete", function (data) {
            Feng.success("删除成功!");
            CommunityPicture.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("communityPictureId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询社区活动图片管理列表
 */
CommunityPicture.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    CommunityPicture.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = CommunityPicture.initColumn();
    var table = new BSTable(CommunityPicture.id, "/communityPicture/list", defaultColunms);
    table.setPaginationType("client");
    CommunityPicture.table = table.init();
});
