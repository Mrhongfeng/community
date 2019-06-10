/**
 * 导师活动管理管理初始化
 */
var Teacheractivity = {
    id: "TeacheractivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Teacheractivity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '导师Id', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '活动Id', field: 'acId', visible: true, align: 'center', valign: 'middle'},
            {title: '签到标记', field: 'sign', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Teacheractivity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Teacheractivity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加导师活动管理
 */
Teacheractivity.openAddTeacheractivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加导师活动管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/teacheractivity/teacheractivity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看导师活动管理详情
 */
Teacheractivity.openTeacheractivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '导师活动管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/teacheractivity/teacheractivity_update/' + Teacheractivity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除导师活动管理
 */
Teacheractivity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/teacheractivity/delete", function (data) {
            Feng.success("删除成功!");
            Teacheractivity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("teacheractivityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询导师活动管理列表
 */
Teacheractivity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Teacheractivity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Teacheractivity.initColumn();
    var table = new BSTable(Teacheractivity.id, "/teacheractivity/list", defaultColunms);
    table.setPaginationType("client");
    Teacheractivity.table = table.init();
});
