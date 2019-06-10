/**
 * 角色申请管理管理初始化
 */
var Authorityapplication = {
    id: "AuthorityapplicationTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Authorityapplication.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '申请者Id', field: 'openId', visible: true, align: 'center', valign: 'middle'},
            {title: '申请身份', field: 'targetAuth', visible: true, align: 'center', valign: 'middle'},
            {title: '凭证路径', field: 'path', visible: true, align: 'center', valign: 'middle'},
            {title: '申请理由', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '申请状态', field: 'state', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Authorityapplication.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Authorityapplication.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加角色申请管理
 */
Authorityapplication.openAddAuthorityapplication = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色申请管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/authorityapplication/authorityapplication_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看角色申请管理详情
 */
Authorityapplication.openAuthorityapplicationDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色申请管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/authorityapplication/authorityapplication_update/' + Authorityapplication.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除角色申请管理
 */
Authorityapplication.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/authorityapplication/delete", function (data) {
            Feng.success("删除成功!");
            Authorityapplication.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("authorityapplicationId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询角色申请管理列表
 */
Authorityapplication.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Authorityapplication.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Authorityapplication.initColumn();
    var table = new BSTable(Authorityapplication.id, "/authorityapplication/list", defaultColunms);
    table.setPaginationType("client");
    Authorityapplication.table = table.init();
});
