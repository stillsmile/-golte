layui.use(['form', 'layer', 'table', 'jquery', 'laydate', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var loginAccount = sessionStorage.getItem('empId')
    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isEdit = false;   //编辑
    var isRecord = false;   //回款记录
    var isDelay = false;   //延期
    // 遍历资源权限
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/payBackPlan/update') {
            isEdit = true;
        }
        if (powerArr[i] == '/pc/payBackRecord/isRecord') {
            isRecord = true;
        }
        if (powerArr[i] == '/pc/payBackApproval/delay') {
            isDelay = true;
        }
    }
    form.render()

    //全局设置ajax请求参数
    var param = {
        jquery: $,
        layer: layer,
        url: '',
        type: 'post',
    };


    param.url = 'pc/contract/getCityList';
    ajaxJS(param, null, function (d) {
        var data = d.data;
        for (var i = 0; i < data.length; i++) {
            var str = '<option value="' + data[i].cityId + '">' + data[i].cityName + '</option>';
            $('.cityId').append(str)
        }
        form.render()
    });

    param.url = 'pc/contract/getCenterCityList';
    ajaxJS(param, null, function (d) {
        var data = d.data;
        for (var i = 0; i < data.length; i++) {
            var str = '<option value="' + data[i].cityId + '">' + data[i].cityName + '</option>';
            $('.centralCityId').append(str)
        }
        form.render()
    });

    //初始化数据表格
    tableInit(table, 'pc/payBackPlan/select', [[
        {field: 'paybackTime', title: "计划回款日期", align: 'center'},
        {field: 'receivablePayback', title: "计划回款金额", align: 'center'},
        {field: 'receivedAmount', title: "已回款金额", align: 'center',templet: function(d) {
                if(d.receivedAmount==null){
                    return "0.00";
                }else {
                    return d.receivedAmount.toFixed(2);
                }
            }
        },
        {field: '', title: "未回款金额", align: 'center',templet: function(d) {
            if(d.receivablePayback>0){
                if((d.receivablePayback-d.receivedAmount)<0){
                    return "0.00";
                }else {
                    return (d.receivablePayback-d.receivedAmount).toFixed(2);
                }
            }else {
                if((d.receivablePayback-d.receivedAmount)>0){
                    return "0.00";
                }else {
                    return (d.receivablePayback-d.receivedAmount).toFixed(2);
                }
            }

            }
        },
        {field: 'paybackStatus', title: "回款状态", align: 'center',templet: function(d) {
            if (d.paybackStatus == '1') {
                return "未到期"
            }else if (d.paybackStatus == '2') {
                return "逾期未回款"
            }else if (d.paybackStatus == '3') {
                return "已完成"
            }else {
                return ""
            }
        }},
        {field: 'name', title: "合同名称", align: 'center'},
        {field: 'merchantName', title: "合作商家", align: 'center'},
        {field: 'createName', title: "负责人", align: 'center'},
        {field: 'status', title: "回款计划状态", align: 'center',templet: function(d) {
                if (d.status == '0') {
                    return "失效"
                }else if (d.status == '1') {
                    return "有效"
                }else if (d.status == '2') {
                    return "终止"
                }else {
                    return ""
                }
            }},
        {
            title: "操作", width: 250, fixed: 'right', align: "center", templet: function (d) {
                var empId = d.approvalAccount.split(",");
                var flag = false;
                for(var i=0;i<empId.length;i++){
                    var approvalAccount = empId[i];
                    if(approvalAccount==loginAccount){
                        flag = true;
                    }
                }
            var str='';
                if(d.status==1){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="see">查看</a>'
                }else {
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">查看</a>'
                }

            if(isEdit){
                if(d.status==1&&d.paybackStatus != '3'&&d.editStatus==0){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>'
                }else {
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">编辑</a>'
                }
            }
            if(isRecord){
                if(d.status==1&&d.paybackStatus != '3'){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="record">回款记录</a>'
                }else {
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">回款记录</a>'
                }
            }
            if(isDelay&&(d.isApprove==0||d.isApprove==3)){
                if(d.status==1&&d.paybackStatus != '3'){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="delay">延期</a>'
                }else {
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">延期</a>'
                }
            }
            if(d.isApprove==1&&flag){
                str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="approve">审批</a>'
            }
            return str;
            }
        }
    ]]);


    var paybackStartTime='', paybackEndTime='';
    laydate.render({
        elem: '#paybackTime',
        range: true,
        done: function (value) {
            paybackStartTime = value.split(' - ')[0] +' 00:00:00';
            paybackEndTime = value.split(' - ')[1] +' 23:59:59';
        }
    });

    form.on('select(centralCityId)', function (data) {
        param.url = 'pc/contract/getCityListByCenterCity';
        ajaxJS(param, {centralCityId:$('.centralCityId').val()}, function (d) {
            var data = d.data;
            $('.cityId').empty();
            $('.cityId').append('<option value=""></option>')
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].cityName + '</option>';
                $('.cityId').append(str)
            }
            form.render();
        });
    });

    //编辑回款计划
    function addOrEdit(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackPlan/detail";
        ajaxJS(param,{contractId:edit.contractId}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "paybackAdd.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        if (data.projectList) {
                            var flag = false;
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                if(project.backAmount){
                                    flag = true;
                                }
                            }
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var tollModeName = '';
                                if(project.tollMode==1){
                                    tollModeName = '一次性'
                                }
                                if(project.tollMode==2){
                                    tollModeName = '固定'
                                }
                                if(project.tollMode==3){
                                    tollModeName = '佣金'
                                }
                                if(project.tollMode==4){
                                    tollModeName = '其他'
                                }
                                var paymentCycleName = '';
                                if(project.paymentCycle==1){
                                    paymentCycleName = '年'
                                }
                                if(project.paymentCycle==2){
                                    paymentCycleName = '半年'
                                }
                                if(project.paymentCycle==3){
                                    paymentCycleName = '季度'
                                }
                                if(project.paymentCycle==4){
                                    paymentCycleName = '月'
                                }
                                if(project.paymentCycle==5){
                                    paymentCycleName = '周'
                                }
                                if(project.paymentCycle==6){
                                    paymentCycleName = '两年'
                                }
                                if(project.paymentCycle==7){
                                    paymentCycleName = '三年'
                                }
                                if(project.paymentCycle==8){
                                    paymentCycleName = '四年'
                                }
                                if(project.paymentCycle==9){
                                    paymentCycleName = '五年'
                                }
                                if(project.paymentCycle==10){
                                    paymentCycleName = '一次性'
                                }
                                // var str =
                                //
                                // body.find(".specBody").append(str);
                                // form.render();
                                if(flag){
                                    body.find(".backAmount").css('display', 'block')
                                    var backAmount = 0;
                                    if(project.backAmount){
                                        backAmount = project.backAmount;
                                    }
                                    var number = '';
                                    if(project.number){
                                        number = project.number;
                                    }
                                    var unitPrice = '';
                                    if(project.unitPrice){
                                        unitPrice = project.unitPrice;
                                    }
                                    var subtotal = '';
                                    if(project.subtotal){
                                        subtotal = project.subtotal;
                                    }
                                    var strProject =
                                        '<tbody> '+
                                        '<thead> '+
                                        ' <tr> '+
                                        '<td>'+project.resourceName+'</td> '+
                                        '<td>'+tollModeName+'</td> '+
                                        '<td>'+paymentCycleName+'</td> '+
                                        '<td>'+toMoney(unitPrice)+'</td> '+
                                        '<td>'+number+'</td> '+
                                        '<td class="backAmounts">'+toMoney(backAmount)+'</td> '+
                                        '<td>'+toMoney(subtotal)+'</td> '+
                                        '</tr> '+
                                        '</thead> '+
                                        '</tbody>'+
                                        '<tr class="project"> '+
                                        '<td>序号</td> '+
                                        '<td>计划回款日期</td> '+
                                        '<td>计划回款金额（元）</td> '+
                                        '<td colspan="3">备注</td> '+
                                        '<td><input type="hidden" class="backAmount" value="'+project.backAmount+'"><input type="hidden" class="subtotal" value="'+project.subtotal+'"><input type="hidden" class="projectIds" value="'+project.id+'">操作:<span class="add layui-icon">&#xe654;</span></td> '+
                                        '</tr> '+
                                        '<tbody class="projectId'+project.id+'"> '+
                                        '</tbody>'
                                }else {
                                    var number = '';
                                    if(project.number){
                                        number = project.number;
                                    }
                                    var unitPrice = '';
                                    if(project.unitPrice){
                                        unitPrice = project.unitPrice;
                                    }
                                    var subtotal = '';
                                    if(project.subtotal){
                                        subtotal = project.subtotal;
                                    }
                                    var strProject =
                                        '<tbody> '+
                                        '<thead> '+
                                        ' <tr> '+
                                        '<td>'+project.resourceName+'</td> '+
                                        '<td>'+tollModeName+'</td> '+
                                        '<td>'+paymentCycleName+'</td> '+
                                        '<td>'+toMoney(unitPrice)+'</td> '+
                                        '<td>'+number+'</td> '+
                                        '<td>'+toMoney(subtotal)+'</td> '+
                                        '</tr> '+
                                        '</thead> '+
                                        '</tbody>'+
                                        '<tr class="project"> '+
                                        '<td>序号</td> '+
                                        '<td>计划回款日期</td> '+
                                        '<td>计划回款金额（元）</td> '+
                                        '<td colspan="2">备注</td> '+
                                        '<td><input type="hidden" class="subtotal" value="'+project.subtotal+'"><input type="hidden" class="projectIds" value="'+project.id+'">操作:<span class="add layui-icon">&#xe654;</span></td> '+
                                        '</tr> '+
                                        '<tbody class="projectId'+project.id+'"> '+
                                        '</tbody>'
                                }
                                body.find("#table").append(strProject);
                                form.render();
                                if(project.payBackList){
                                    for (var j = 0; j < project.payBackList.length; j++) {
                                        var payBack = project.payBackList[j];
                                        if(flag){
                                            if(payBack.status==1){
                                                var strPayBack=
                                                    '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                    '<td class="seq">'+(j+1)+'</td> '+
                                                    '<td> '+
                                                    '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                    '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                    '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                    '<input type="hidden" class="projectResource" value="'+project.resourceName+'">'+
                                                    '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                    '<input type="hidden" class="receivedPayback" value="'+payBack.receivedPayback+'">'+
                                                    '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required"> '+
                                                    '</td> '+
                                                    '<td> '+
                                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'"> '+
                                                    '</td> '+
                                                    '<td colspan="3"> '+
                                                    '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks" value="'+payBack.remarks+'"> '+
                                                    '</td> '+
                                                    '<td class="caozuo"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row dels">删除</a></td> '+
                                                    '</tr>'
                                            }else {
                                                var strPayBack=
                                                    '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                    '<td class="seq">'+(j+1)+'</td> '+
                                                    '<td> '+
                                                    '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                    '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                    '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                    '<input type="hidden" class="projectResource" value="'+project.resourceName+'">'+
                                                    '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                    '<input type="hidden" class="receivedPayback" value="'+payBack.receivedPayback+'">'+
                                                    '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required" disabled> '+
                                                    '</td> '+
                                                    '<td> '+
                                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'" disabled> '+
                                                    '</td> '+
                                                    '<td colspan="3"> '+
                                                    '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks" value="'+payBack.remarks+'" disabled> '+
                                                    '</td> '+
                                                    '<td class="caozuo"></td> '+
                                                    '</tr>'
                                            }
                                        }else {
                                            if(payBack.status==1){
                                                var strPayBack=
                                                    '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                    '<td class="seq">'+(j+1)+'</td> '+
                                                    '<td> '+
                                                    '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                    '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                    '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                    '<input type="hidden" class="projectResource" value="'+project.resourceName+'">'+
                                                    '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                    '<input type="hidden" class="receivedPayback" value="'+payBack.receivedPayback+'">'+
                                                    '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required"> '+
                                                    '</td> '+
                                                    '<td> '+
                                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'"> '+
                                                    '</td> '+
                                                    '<td colspan="2"> '+
                                                    '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks" value="'+payBack.remarks+'"> '+
                                                    '</td> '+
                                                    '<td class="caozuo"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row dels">删除</a></td> '+
                                                    '</tr>'
                                            }else {
                                                var strPayBack=
                                                    '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                    '<td class="seq">'+(j+1)+'</td> '+
                                                    '<td> '+
                                                    '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                    '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                    '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                    '<input type="hidden" class="projectResource" value="'+project.resourceName+'">'+
                                                    '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                    '<input type="hidden" class="receivedPayback" value="'+payBack.receivedPayback+'">'+
                                                    '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required" disabled> '+
                                                    '</td> '+
                                                    '<td> '+
                                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'" disabled> '+
                                                    '</td> '+
                                                    '<td colspan="2"> '+
                                                    '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks" value="'+payBack.remarks+'" disabled> '+
                                                    '</td> '+
                                                    '<td class="caozuo"></td> '+
                                                    '</tr>'
                                            }

                                        }
                                        body.find(".projectId"+project.id).append(strPayBack);
                                        form.render();
                                    }
                                }
                            }
                        }
                        form.render();
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //查看回款计划
    function see(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackPlan/detail";
        ajaxJS(param,{contractId:edit.contractId}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "paybackSee.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        if (data.projectList) {
                            var flag = false;
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                if(project.backAmount){
                                    flag = true;
                                }
                            }
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var tollModeName = '';
                                if(project.tollMode==1){
                                    tollModeName = '一次性'
                                }
                                if(project.tollMode==2){
                                    tollModeName = '固定'
                                }
                                if(project.tollMode==3){
                                    tollModeName = '佣金'
                                }
                                if(project.tollMode==4){
                                    tollModeName = '其他'
                                }
                                var paymentCycleName = '';
                                if(project.paymentCycle==1){
                                    paymentCycleName = '年'
                                }
                                if(project.paymentCycle==2){
                                    paymentCycleName = '半年'
                                }
                                if(project.paymentCycle==3){
                                    paymentCycleName = '季度'
                                }
                                if(project.paymentCycle==4){
                                    paymentCycleName = '月'
                                }
                                if(project.paymentCycle==5){
                                    paymentCycleName = '周'
                                }
                                if(project.paymentCycle==6){
                                    paymentCycleName = '两年'
                                }
                                if(project.paymentCycle==7){
                                    paymentCycleName = '三年'
                                }
                                if(project.paymentCycle==8){
                                    paymentCycleName = '四年'
                                }
                                if(project.paymentCycle==9){
                                    paymentCycleName = '五年'
                                }
                                if(project.paymentCycle==10){
                                    paymentCycleName = '一次性'
                                }
                                // var str =
                                //
                                // body.find(".specBody").append(str);
                                // form.render();
                                if(flag){
                                    body.find(".backAmount").css('display', 'block')
                                    var backAmount = 0;
                                    if(project.backAmount){
                                        backAmount = project.backAmount;
                                    }
                                    var number = '';
                                    if(project.number){
                                        number = project.number;
                                    }
                                    var unitPrice = '';
                                    if(project.unitPrice){
                                        unitPrice = project.unitPrice;
                                    }
                                    var subtotal = '';
                                    if(project.subtotal){
                                        subtotal = project.subtotal;
                                    }
                                    var strProject =
                                        '<tbody> '+
                                        '<thead> '+
                                        ' <tr> '+
                                        '<td>'+project.resourceName+'</td> '+
                                        '<td>'+tollModeName+'</td> '+
                                        '<td>'+paymentCycleName+'</td> '+
                                        '<td>'+toMoney(unitPrice)+'</td> '+
                                        '<td>'+number+'</td> '+
                                        '<td class="backAmounts">'+toMoney(backAmount)+'</td> '+
                                        '<td>'+toMoney(subtotal)+'</td> '+
                                        '</tr> '+
                                        '</thead> '+
                                        '</tbody>'+
                                        '<tr class="project"> '+
                                        '<td>序号</td> '+
                                        '<td>计划回款日期</td> '+
                                        '<td>计划回款金额（元）</td> '+
                                        '<td colspan="3">备注</td> '+
                                        '<td><input type="hidden" class="backAmount" value="'+project.backAmount+'"><input type="hidden" class="subtotal" value="'+project.subtotal+'"><input type="hidden" class="projectIds" value="'+project.id+'"></td> '+
                                        '</tr> '+
                                        '<tbody class="projectId'+project.id+'"> '+
                                        '</tbody>'
                                }else {
                                    var number = '';
                                    if(project.number){
                                        number = project.number;
                                    }
                                    var unitPrice = '';
                                    if(project.unitPrice){
                                        unitPrice = project.unitPrice;
                                    }
                                    var subtotal = '';
                                    if(project.subtotal){
                                        subtotal = project.subtotal;
                                    }
                                    var strProject =
                                        '<tbody> '+
                                        '<thead> '+
                                        ' <tr> '+
                                        '<td>'+project.resourceName+'</td> '+
                                        '<td>'+tollModeName+'</td> '+
                                        '<td>'+paymentCycleName+'</td> '+
                                        '<td>'+toMoney(unitPrice)+'</td> '+
                                        '<td>'+number+'</td> '+
                                        '<td>'+toMoney(subtotal)+'</td> '+
                                        '</tr> '+
                                        '</thead> '+
                                        '</tbody>'+
                                        '<tr class="project"> '+
                                        '<td>序号</td> '+
                                        '<td>计划回款日期</td> '+
                                        '<td>计划回款金额（元）</td> '+
                                        '<td colspan="2">备注</td> '+
                                        '<td><input type="hidden" class="subtotal" value="'+project.subtotal+'"><input type="hidden" class="projectIds" value="'+project.id+'"></td> '+
                                        '</tr> '+
                                        '<tbody class="projectId'+project.id+'"> '+
                                        '</tbody>'
                                }
                                body.find("#table").append(strProject);
                                form.render();
                                if(project.payBackList){
                                    for (var j = 0; j < project.payBackList.length; j++) {
                                        var payBack = project.payBackList[j];
                                        if(flag){
                                            var strPayBack=
                                                '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                '<td class="seq">'+(j+1)+'</td> '+
                                                '<td> '+
                                                '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                '<input type="text" class="layui-input paybackTime" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required" disabled> '+
                                                '</td> '+
                                                '<td> '+
                                                '<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'" disabled> '+
                                                '</td> '+
                                                '<td colspan="3"> '+
                                                '<input maxlength="200" type="text" class="layui-input inputs remarks" name="remarks" value="'+payBack.remarks+'" disabled> '+
                                                '</td> '+
                                                '<td class="caozuo"></td> '+
                                                '</tr>'
                                        }else {
                                            var strPayBack=
                                                '<tr class="payBackList" name="payBackList'+project.id+'"> '+
                                                '<td class="seq">'+(j+1)+'</td> '+
                                                '<td> '+
                                                '<input type="hidden" class="payBackId" value="'+payBack.id+'">'+
                                                '<input type="hidden" class="contractId" value="'+data.contractId+'">'+
                                                '<input type="hidden" class="projectId" value="'+project.id+'">'+
                                                '<input type="hidden" class="paybackTimeOld" value="'+payBack.paybackTime+'">'+
                                                '<input type="text" class="layui-input paybackTime" id="paybackTime'+payBack.id+'" value="'+payBack.paybackTime+'" lay-verify="required" disabled> '+
                                                '</td> '+
                                                '<td> '+
                                                '<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs receivablePayback" name="receivablePayback" value="'+payBack.receivablePayback+'" disabled> '+
                                                '</td> '+
                                                '<td colspan="2"> '+
                                                '<input maxlength="200" type="text" class="layui-input inputs remarks" name="remarks" value="'+payBack.remarks+'" disabled> '+
                                                '</td> '+
                                                '<td class="caozuo"></td> '+
                                                '</tr>'
                                        }
                                        body.find(".projectId"+project.id).append(strPayBack);
                                        form.render();
                                    }
                                }
                            }
                        }
                        form.render();
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //编辑回款记录
    function record(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackRecord/detail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "../paybackRecord/paybackRecordAdd.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        var tollModeName = '';
                        if(data.tollMode==1){
                            tollModeName = '一次性'
                        }
                        if(data.tollMode==2){
                            tollModeName = '固定'
                        }
                        if(data.tollMode==3){
                            tollModeName = '佣金'
                        }
                        if(data.tollMode==4){
                            tollModeName = '其他'
                        }
                        var paymentCycleName = '';
                        if(data.paymentCycle==1){
                            paymentCycleName = '年'
                        }
                        if(data.paymentCycle==2){
                            paymentCycleName = '半年'
                        }
                        if(data.paymentCycle==3){
                            paymentCycleName = '季度'
                        }
                        if(data.paymentCycle==4){
                            paymentCycleName = '月'
                        }
                        if(data.paymentCycle==5){
                            paymentCycleName = '周'
                        }
                        if(data.paymentCycle==6){
                            paymentCycleName = '两年'
                        }
                        if(data.paymentCycle==7){
                            paymentCycleName = '三年'
                        }
                        if(data.paymentCycle==8){
                            paymentCycleName = '四年'
                        }
                        if(data.paymentCycle==9){
                            paymentCycleName = '五年'
                        }
                        if(data.paymentCycle==10){
                            paymentCycleName = '一次性'
                        }
                        body.find(".resourceName").html(data.resourceName);
                        body.find(".tollMode").html(tollModeName);
                        body.find(".paymentCycle").html(paymentCycleName);
                        body.find(".unitPrice").html(toMoney(data.unitPrice));
                        body.find(".number").html(data.number);
                        body.find(".subtotal").html(toMoney(data.subtotal));
                        body.find(".planPaybackTime").html(data.planPaybackTime);
                        if(data.receivablePayback){
                            body.find(".receivablePayback").html(toMoney(data.receivablePayback));
                        }
                        body.find(".remarks").html(data.remarks);
                        body.find(".contractProjectId").val(data.contractProjectId);
                        body.find(".paybackPlanId").val(data.paybackPlanId);
                        body.find(".recordListData").attr("recordList", JSON.stringify(data.recordList));
                        if(data.recordList.length>0){
                            for (var j = 0; j < data.recordList.length; j++) {
                                var record = data.recordList[j];
                                var str=
                                    '<tr class="recordListTr" id="recordListTr'+record.id+'"> '+
                                    '<td class="seq">'+(j+1)+'</td> '+
                                    '<td> '+
                                    '<input type="hidden" class="recordId" value="'+record.id+'">'+
                                    '<input type="text" class="layui-input paybackTime" placeholder="请选择实际回款日期" id="paybackTime'+record.id+'" value="'+record.paybackTime+'" lay-verify="required"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input paybackAmount" name="paybackAmount" value="'+record.paybackAmount+'" lay-verify="number"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input maxlength="50" type="text" class="layui-input paybackInvoiceCode" placeholder="请输入回款发票" name="paybackInvoiceCode" value="'+record.paybackInvoiceCode+'"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入滞纳金" class="layui-input lastPayment" name="lastPayment" value="'+record.lastPayment+'"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="receiptMember" lay-filter="receiptMember" class="receiptMember" lay-verify="required"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td class="caozuo"></td> '+
                                    '</tr>'
                                body.find(".recordList").append(str);
                                form.render();
                            }
                        }else {
                            debugger;
                            var str =
                                '<tr class="recordListTr"> '+
                                '<input type="hidden" id="record1" class="trLen" value="1">'+
                                '<td class="seq">1</td> '+
                                '<td> '+
                                '<input type="hidden" class="recordId" value="">'+
                                '<input type="text" class="layui-input paybackTime" placeholder="请选择实际回款日期" id="paybackTime" lay-verify="required"> '+
                                '</td> '+
                                '<td> '+
                                '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入回款金额" class="layui-input paybackAmount" name="paybackAmount" lay-verify="number"> '+
                                '</td> '+
                                '<td> '+
                                '<input maxlength="50" type="text" class="layui-input paybackInvoiceCode" placeholder="请输入回款发票" name="paybackInvoiceCode"> '+
                                '</td> '+
                                '<td> '+
                                '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入滞纳金" class="layui-input lastPayment" name="lastPayment"> '+
                                '</td> '+
                                '<td> '+
                                '<select name="receiptMember" lay-filter="receiptMember" class="receiptMember" lay-verify="required"> '+
                                '</select> '+
                                '</td> '+
                                '<td class="caozuo"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del">删除</a></td> '+
                                '</tr>'
                            body.find(".recordList").append(str);
                            form.render();
                        }
                        form.render();
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //申请回款延期
    function delay(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackApproval/detail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "../paybackApproval/paybackApprovalAdd.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        body.find(".extensionAmount").val((data.receivablePayback-data.receivedAmount).toFixed(2));
                        var tollModeName = '';
                        if(data.tollMode==1){
                            tollModeName = '一次性'
                        }
                        if(data.tollMode==2){
                            tollModeName = '固定'
                        }
                        if(data.tollMode==3){
                            tollModeName = '佣金'
                        }
                        if(data.tollMode==4){
                            tollModeName = '其他'
                        }
                        var paymentCycleName = '';
                        if(data.paymentCycle==1){
                            paymentCycleName = '年'
                        }
                        if(data.paymentCycle==2){
                            paymentCycleName = '半年'
                        }
                        if(data.paymentCycle==3){
                            paymentCycleName = '季度'
                        }
                        if(data.paymentCycle==4){
                            paymentCycleName = '月'
                        }
                        if(data.paymentCycle==5){
                            paymentCycleName = '周'
                        }
                        if(data.paymentCycle==6){
                            paymentCycleName = '两年'
                        }
                        if(data.paymentCycle==7){
                            paymentCycleName = '三年'
                        }
                        if(data.paymentCycle==8){
                            paymentCycleName = '四年'
                        }
                        if(data.paymentCycle==9){
                            paymentCycleName = '五年'
                        }
                        if(data.paymentCycle==10){
                            paymentCycleName = '一次性'
                        }
                        body.find(".resourceName").html(data.resourceName);
                        body.find(".tollMode").html(tollModeName);
                        body.find(".paymentCycle").html(paymentCycleName);
                        body.find(".unitPrice").html(toMoney(data.unitPrice));
                        body.find(".number").html(data.number);
                        body.find(".subtotal").html(toMoney(data.subtotal));
                        body.find(".planPaybackTime").html(data.planPaybackTime);
                        body.find(".receivablePayback").html(toMoney(data.receivablePayback));
                        body.find(".contractProjectId").val(data.contractProjectId);
                        body.find(".paybackPlanId").val(data.paybackPlanId);
                        body.find(".planPaybackTime").html(data.planPaybackTime);
                        body.find(".receivedAmount").html(toMoney(data.receivedAmount));
                        body.find(".noReceivedAmount").html(toMoney(data.receivablePayback-data.receivedAmount));

                        form.render();
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //回款延期审批
    function approve(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackApproval/detail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "../paybackApproval/paybackApproval.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        var tollModeName = '';
                        if(data.tollMode==1){
                            tollModeName = '一次性'
                        }
                        if(data.tollMode==2){
                            tollModeName = '固定'
                        }
                        if(data.tollMode==3){
                            tollModeName = '佣金'
                        }
                        if(data.tollMode==4){
                            tollModeName = '其他'
                        }
                        var paymentCycleName = '';
                        if(data.paymentCycle==1){
                            paymentCycleName = '年'
                        }
                        if(data.paymentCycle==2){
                            paymentCycleName = '半年'
                        }
                        if(data.paymentCycle==3){
                            paymentCycleName = '季度'
                        }
                        if(data.paymentCycle==4){
                            paymentCycleName = '月'
                        }
                        if(data.paymentCycle==5){
                            paymentCycleName = '周'
                        }
                        if(data.paymentCycle==6){
                            paymentCycleName = '两年'
                        }
                        if(data.paymentCycle==7){
                            paymentCycleName = '三年'
                        }
                        if(data.paymentCycle==8){
                            paymentCycleName = '四年'
                        }
                        if(data.paymentCycle==9){
                            paymentCycleName = '五年'
                        }
                        if(data.paymentCycle==10){
                            paymentCycleName = '一次性'
                        }
                        body.find(".resourceName").html(data.resourceName);
                        body.find(".tollMode").html(tollModeName);
                        body.find(".paymentCycle").html(paymentCycleName);
                        body.find(".unitPrice").html(toMoney(data.unitPrice));
                        body.find(".number").html(data.number);
                        body.find(".subtotal").html(toMoney(data.subtotal));
                        body.find(".planPaybackTime").html(data.planPaybackTime);
                        body.find(".receivablePayback").html(toMoney(data.receivablePayback));
                        body.find(".contractProjectId").val(data.contractProjectId);
                        body.find(".paybackPlanId").val(data.paybackPlanId);
                        body.find(".planPaybackTime").html(data.planPaybackTime);
                        body.find(".receivablePayback").html(toMoney(data.receivablePayback));
                        body.find(".receivedAmount").html(data.receivedAmount);
                        body.find(".noReceivedAmount").html(toMoney(data.receivablePayback-data.receivedAmount));
                        body.find(".extensionTime").html(data.extensionTime);
                        body.find(".extensionAmount").html(data.extensionAmount);
                        body.find(".extensionReason").html(data.extensionReason);

                        form.render();
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'edit') { //编辑回款计划
            addOrEdit("编辑回款计划",data);
        }
        if (layEvent === 'see') { //查看回款计划
            see("查看回款计划",data);
        }
        if (layEvent === 'record') { //编辑回款记录
            record("编辑回款记录",data);
        }
        if (layEvent === 'delay') { //申请回款延期
            delay("申请回款延期",data);
        }
        if (layEvent === 'approve') { //回款延期审批
            approve("回款延期审批",data);
        }
    });


    //搜索
    $(".search_btn").on("click", function () {
        search($, table,form, {
            paybackStatus: $(".paybackStatus").val(),
            name: $(".name").val(),
            contractPerson: $(".contractPerson").val(),
            merchantName: $(".merchantName").val(),
            paybackStartTime: paybackStartTime,
            paybackEndTime: paybackEndTime,
            cityId: $(".cityId option:selected").val(),
            centralCityId: $(".centralCityId option:selected").val(),
        });
    });

});

//将数字转换成金额显示
function toMoney(num){
    if(num){
        num = num.toFixed(2);
        num = parseFloat(num)
        num = num.toLocaleString();
        return num;//返回的是字符串23,245.12保留2位小数
    }else {
        return num;
    }
}

function goLogin() {
    parent.goLogin()
}
