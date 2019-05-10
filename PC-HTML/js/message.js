layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    $(".messageNum",parent.document).html("0").hide();

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/message/select', [[
        {field: 'mesTitle', title: "消息标题", align: 'center', width: 200},
        {field: 'mesContent', title: "消息内容", align: 'center'},
        {field: 'createTime', title: "发送时间", align: 'center',width: 200},
    ]]);

});

function goLogin() {
    parent.goLogin()
}
