layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();
    var merchantId = $(".merchantId").attr("merchantId");
    var level = $(".merchantId").attr("level");
    //数据表格初始化
    tableInit(table, 'pc/business/contractlist', [[
        {field: 'sortNum', title: "序号", align: 'center'},
        {field: 'code', title: "合同编号", align: 'center' },
        {field: 'name', title: "合同名称", align: 'center'},
        {field: 'amount', title: "合同金额", align: 'center'},
        {field: 'deadlineStartTime', title: "生效日期", align: 'center'},
        {field: 'signingContact', title: "签约人", align: 'center'},
        {
            field: 'contractStatus', title: "合同状态", align: 'center', templet: function (d) {
                return contractStatusEnum.properties[d.contractStatus].name;
            }
        },
        {
            field: 'paybackType', title: "回款状态", align: 'center', templet: function (d) {
                return paybackTypeEnum.properties[d.paybackType].name;
            }
        }
    ]],{id:merchantId,level:level});

});

function goLogin() {
    parent.goLogin()
}
