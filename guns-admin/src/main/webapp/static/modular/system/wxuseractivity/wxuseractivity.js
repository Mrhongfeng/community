/**
 * useractivity管理初始化
 */
var Wxuseractivity = {
    id: "WxuseractivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Wxuseractivity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '参与者id', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '活动id', field: 'acId', visible: true, align: 'center', valign: 'middle'},
            {title: '签到标记', field: 'sign', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Wxuseractivity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Wxuseractivity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加useractivity
 */
Wxuseractivity.openAddWxuseractivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加useractivity',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/wxuseractivity/wxuseractivity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看useractivity详情
 */
Wxuseractivity.openWxuseractivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: 'useractivity详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/wxuseractivity/wxuseractivity_update/' + Wxuseractivity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除useractivity
 */
Wxuseractivity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/wxuseractivity/delete", function (data) {
            Feng.success("删除成功!");
            Wxuseractivity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("wxuseractivityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询useractivity列表
 */
Wxuseractivity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Wxuseractivity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Wxuseractivity.initColumn();
    var table = new BSTable(Wxuseractivity.id, "/wxuseractivity/list", defaultColunms);
    table.setPaginationType("client");
    Wxuseractivity.table = table.init();
});
