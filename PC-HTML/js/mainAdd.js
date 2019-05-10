layui.use(['form', 'layer', 'table', 'jquery','laydate'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery, laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    setTimeout(function () {
        var type = $(".type").val();
        var typeName = $(".typeName").val();
        var year = $(".year").val();
        var cityId = $(".cityId").val();
        table.page =false;
        if(type=="1"){
            //2.资源类别使用情况Top6
            tableInit(table, 'pc/main/selectTop6Detail', [[
                {field: 'sortNum', title: "序号", align: 'center'},
                {field: 'name', title: "资源类别", align: 'center'},
                {field: 'contractName', title: "合同名称", align: 'center'},
                {field: 'startTime', title: "开始时间", align: 'center' },
                {field: 'endTime', title: "结束时间", align: 'center' },
            ]],{year:year,cityId:cityId,typeName:typeName});
            form.render();
        }
        if(type=="2"){
            //3.项目成交金额Top10
            tableInit(table, 'pc/main/selectTop10Detail', [[
                {field: 'sortNum', title: "序号", align: 'center'},
                {field: 'projectName', title: "项目名称", align: 'center'},
                {field: 'contractName', title: "合同名称", align: 'center'},
                {field: 'paybackTime', title: "回款时间", align: 'center' },
                {field: 'peceiptMember', title: "收款人", align: 'center'},
            ]],{year:year,cityId:cityId,typeName:typeName});
            form.render();
        }
        if(type=="3"){
            //4.合同类别成交金额占比
            tableInit(table, 'pc/main/selectContractTop6Detail', [[
                {field: 'sortNum', title: "序号", align: 'center'},
                {field: 'name', title: "合同类别", align: 'center'},
                {field: 'contractName', title: "合同名称", align: 'center'},
                {field: 'startTime', title: "开始时间", align: 'center' },
                {field: 'endTime', title: "结束时间", align: 'center' }
            ]],{year:year,cityId:cityId,typeName:typeName});
            form.render();
        }
        if(type=="4"){
            //5.商家成交金额Top10
            tableInit(table, 'pc/main/selectMerchantTop10Detail', [[
                {field: 'sortNum', title: "序号", align: 'center'},
                {field: 'name', title: "商家名称", align: 'center'},
                {field: 'contractName', title: "合同名称", align: 'center'},
                {field: 'startTime', title: "回款时间", align: 'center' },
                {field: 'peceiptMember', title: "收款人", align: 'center'},
            ]],{year:year,cityId:cityId,typeName:typeName});
            form.render();
        }
    },100)

});

function goLogin() {
    parent.goLogin()
}
