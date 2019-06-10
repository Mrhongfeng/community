/**
 * 组织者活动管理管理初始化
 */
var Organizeractivity = {
    id: "OrganizeractivityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Organizeractivity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '组织者Id', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '活动Id', field: 'acId', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Organizeractivity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Organizeractivity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加组织者活动管理
 */
Organizeractivity.openAddOrganizeractivity = function () {
    var index = layer.open({
        type: 2,
        title: '添加组织者活动管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/organizeractivity/organizeractivity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看组织者活动管理详情
 */
Organizeractivity.openOrganizeractivityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '组织者活动管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/organizeractivity/organizeractivity_update/' + Organizeractivity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除组织者活动管理
 */
Organizeractivity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/organizeractivity/delete", function (data) {
            Feng.success("删除成功!");
            Organizeractivity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("organizeractivityId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询组织者活动管理列表
 */
Organizeractivity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Organizeractivity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Organizeractivity.initColumn();
    var table = new BSTable(Organizeractivity.id, "/organizeractivity/list", defaultColunms);
    table.setPaginationType("client");
    Organizeractivity.table = table.init();
});
