layui.use(['form', 'layer', 'table', 'jquery', 'laydate', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    debugger;
    var logToken = sessionStorage.getItem('logToken')
    var loginAccount = sessionStorage.getItem('empId');
    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isEdit = false;   //编辑
    var isChange = false;   //变更
    var isStop = false;   //终止
    var isPlan = false;   //回款计划
    var isRecord = false;   //回款记录
    // 遍历资源权限
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/contract/add') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/contract/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/contract/exportIn') {
            $('#upload').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/contract/exportOut') {
            $('.export_btn').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/contract/update') {
            isEdit = true;
        }
        if (powerArr[i] == '/pc/contract/change') {
            isChange = true;
        }
        if (powerArr[i] == '/pc/contract/stop') {
            isStop = true;
        }
        if (powerArr[i] == 'html/payback_setup/payback/paybackAdd.html') {
            isPlan = true;
        }
        if (powerArr[i] == 'html/payback_setup/payback/paybackRecordSee.html') {
            isRecord = true;
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

    //初始化数据表格
    tableInit(table, 'pc/contract/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'code', title: "合同编号", align: 'center'},
        {field: 'name', title: "合同名称", align: 'center'},
        {field: 'merchantName', title: "合作商家", align: 'center'},
        {field: 'amount', title: "合同金额", align: 'center'},
        {field: 'deadlineStartTime', title: "生效日期", align: 'center'},
        {field: 'signingContact', title: "签约人", align: 'center'},
        {field: 'contractStatus', title: "合同状态", align: 'center',templet: function(d) {
            if (d.contractStatus == '1') {
                return "未开始"
            }else if (d.contractStatus == '2') {
                return "进行中"
            }else if (d.contractStatus == '3') {
                return "变更审批中"
            }else if (d.contractStatus == '4') {
                return "终止审批中"
            }else if (d.contractStatus == '5') {
                return "已结束"
            }else if (d.contractStatus == '6') {
                return "已终止"
            }else {
                return ""
            }
        }},
        {field: 'paybackType', title: "回款状态", align: 'center',templet: function(d) {
            if (d.paybackType == '1') {
                return "未回款"
            }else if (d.paybackType == '2') {
                return "部分回款"
            }else if (d.paybackType == '3') {
                return "已回款"
            }else {
                return ""
            }
        }},
        {
            title: "操作", width: 350, fixed: 'right', align: "center", templet: function (d) {
                debugger;
                var empId = d.approvalAccount.split(",");
                var flag = false;
                for(var i=0;i<empId.length;i++){
                    var approvalAccount = empId[i];
                    if(approvalAccount==loginAccount){
                        flag = true;
                    }
                }
                var str='<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="see">查看</a>';
                if(isEdit&&d.darftStatus==1){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>'
                }
                if(isChange&&d.darftStatus==0){
                    if(d.contractStatus==4||d.contractStatus==3||d.contractStatus==5||d.contractStatus==6){
                        str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">变更</a>'
                    }else {
                        str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="change">变更</a>'
                    }
                }
                if(isStop&&d.darftStatus==0){
                    if(d.contractStatus==4||d.contractStatus==3||d.contractStatus==5||d.contractStatus==6){
                        str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row layui-btn-disabled">终止</a>'
                    }else {
                        str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="stop">终止</a>'
                    }
                }
                if(isPlan&&d.darftStatus==0){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="plan">回款计划</a>'
                }
                if(isRecord&&d.darftStatus==0){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="record">回款记录</a>'
                }
                if(flag){
                    str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="approve">审批</a>'
                }
                return str;
            }
        }
    ]]);


    var deadlineStartTimeStr='', deadlineStartTimeEnd='';
    laydate.render({
        elem: '#deadlineStartTime',
        range: true,
        done: function (value) {
            deadlineStartTimeStr = value.split(' - ')[0];
            deadlineStartTimeEnd = value.split(' - ')[1];
        }
    });

    var deadlineEndTimeStr='', deadlineEndTimeEnd='';
    laydate.render({
        elem: '#deadlineEndTime',
        range: true,
        done: function (value) {
            deadlineEndTimeStr = value.split(' - ')[0];
            deadlineEndTimeEnd = value.split(' - ')[1];
        }
    });

    //编辑
    function addOrEdit(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/detail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractAdd.html",
                end:function(){
                    location.reload();
                },
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".projectList").attr("projectList", JSON.stringify(data.projectList));
                        body.find(".cityId").attr("cityId", data.cityId);
                        body.find(".code").val(data.code);
                        body.find(".name").val(data.name);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".merchantContact").val(data.merchantContact);
                        body.find(".merchantContactPhone").val(data.merchantContactPhone);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".signingContact").val(data.signingContact);
                        body.find(".signingContactPhone").val(data.signingContactPhone);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        body.find(".signingType").attr("signingType", data.signingType);
                        body.find(".contractType").attr("contractType", data.contractType);
                        if(data.amount){
                            body.find(".amount").html(toMoney(data.amount));
                        }
                        body.find(".margin").val(data.margin);
                        body.find(".remarks").html(data.remarks);
                        if (data.projectList) {
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var subtotal = "";
                                if(project.subtotal){
                                    subtotal = project.subtotal;
                                }
                                var str =
                                    ' <tr class="specTR" id="specTR'+project.id+'"> '+
                                    '<input type="hidden" id="project'+project.id+'" class="trLen" value="'+project.id+'">'+
                                    '<td> '+
                                    '<select name="projectId" lay-filter="projectId" class="projectId" lay-verify="required"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="pointIdOne" lay-filter="pointIdOne" class="pointIdOne" lay-verify="required"> '+
                                    '</select> '+
                                    '<select name="pointIdTwo" lay-filter="pointIdTwo" class="pointIdTwo" lay-verify="required"> '+
                                    '</select> '+
                                    '<select name="pointIdThree" lay-filter="pointIdThree" class="pointIdThree" lay-verify="required"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td>' +
                                    '<select xm-select="multiRole'+project.id+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
                                    'class="resourceList" lay-verify="required"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="tollMode" lay-filter="tollMode" class="tollMode" lay-verify="required"> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="1">一次性</option> '+
                                    '<option value="2">固定</option> '+
                                    '<option value="3">佣金</option> '+
                                    '<option value="4">其他</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="paymentCycle" lay-filter="paymentCycle" class="paymentCycle" lay-verify="required"> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="9">五年</option> '+
                                    '<option value="8">四年</option> '+
                                    '<option value="7">三年</option> '+
                                    '<option value="6">两年</option> '+
                                    '<option value="1">一年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '<option value="10">一次性</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td class="unitPriceTD"> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入单价" class="layui-input inputs unitPrice" name="unitPrice" value="'+project.unitPrice+'"  lay-verify="number"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input maxlength="20" type="text" class="layui-input inputs unitName" placeholder="请输入单位" name="unitName" value="'+project.unitName+'"> '+
                                    '</td> '+
                                    '<td class="numberTD"> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入数量" class="layui-input inputs number" name="number" value="'+project.number+'" lay-verify="integer"> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(subtotal)+'</td> '+
                                    '<td><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del">删除</a></td> '+
                                    '</tr>'
                                body.find(".specBody").append(str);
                            }
                        }
                        if(data.annexList){
                            for (var i=0;i<data.annexList.length;i++){
                                var Namefile =data.annexList[i].name;
                                var urlfile =data.annexList[i].url;
                                var tr = '<tr class="uploadTR">\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '<td><input type="hidden" class="name" value="'+Namefile+'"><input type="hidden" class="url" value="'+urlfile+'"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row delete">删除</a></td>'+
                                    '      </tr>';
                                body.find("#fileList").append(tr);
                            }
                        }
                        form.render();
                    }
                    // setTimeout(function () {
                    //     layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                    //         tips: 3
                    //     });
                    // }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        });
    }

    //查看
    function see(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/detail";
        ajaxJS(param,{id:edit.id,flag:"1"}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractSee.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".projectList").attr("projectList", JSON.stringify(data.projectList));
                        body.find(".cityId").attr("cityId", data.cityId);
                        body.find(".code").val(data.code);
                        body.find(".name").val(data.name);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".merchantContact").val(data.merchantContact);
                        body.find(".merchantContactPhone").val(data.merchantContactPhone);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".signingContact").val(data.signingContact);
                        body.find(".signingContactPhone").val(data.signingContactPhone);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        body.find(".signingType").attr("signingType", data.signingType);
                        body.find(".contractType").attr("contractType", data.contractType);
                        body.find(".amount").html(toMoney(data.amount));
                        body.find(".margin").html(toMoney(data.margin));
                        body.find(".remarks").html(data.remarks);
                        body.find(".changeReason").html(data.changeReason);
                        body.find(".terminationTime").html(data.terminationTime);
                        body.find(".notRecoveredAmount").html(data.notRecoveredAmount);
                        body.find(".reason").html(data.reason);
                        if (data.projectList) {
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var subtotal = "";
                                if(project.subtotal){
                                    subtotal = project.subtotal;
                                }
                                var str =
                                    ' <tr class="specTR" id="specTR'+project.id+'"> '+
                                    '<input type="hidden" id="project'+project.id+'" class="trLen" value="'+project.id+'">'+
                                    '<td> '+
                                    '<select name="projectId" lay-filter="projectId" class="projectId" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="pointIdOne" lay-filter="pointIdOne" class="pointIdOne" disabled> '+
                                    '</select> '+
                                    '<select name="pointIdTwo" lay-filter="pointIdTwo" class="pointIdTwo" disabled> '+
                                    '</select> '+
                                    '<select name="pointIdThree" lay-filter="pointIdThree" class="pointIdThree" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td>' +
                                    '<select xm-select="multiRole'+project.id+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
                                    'class="resourceList" lay-verify="required" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="tollMode" lay-filter="tollMode" class="tollMode" disabled> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="1">一次性</option> '+
                                    '<option value="2">固定</option> '+
                                    '<option value="3">佣金</option> '+
                                    '<option value="4">其他</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="paymentCycle" lay-filter="paymentCycle" class="paymentCycle" disabled> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="9">五年</option> '+
                                    '<option value="8">四年</option> '+
                                    '<option value="7">三年</option> '+
                                    '<option value="6">两年</option> '+
                                    '<option value="1">一年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '<option value="10">一次性</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs unitPrice" name="unitPrice" value="'+project.unitPrice+'"  lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input maxlength="20" type="text" class="layui-input inputs unitName" name="unitName" value="'+project.unitName+'" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs number" name="number" value="'+project.number+'" lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(subtotal)+'</td> '+
                                    '</tr>'
                                body.find(".specBody").append(str);
                            }
                        }
                        if(data.annexList){
                            for (var i=0;i<data.annexList.length;i++){
                                var Namefile =data.annexList[i].name;
                                var urlfile =data.annexList[i].url;
                                var tr = '<tr>\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '      </tr>';
                                body.find("#fileList").append(tr);
                            }
                        }
                        debugger;
                        if(data.stopAnnexList){
                            for (var i=0;i<data.stopAnnexList.length;i++){
                                var Namefile =data.stopAnnexList[i].name;
                                var urlfile =data.stopAnnexList[i].url;
                                var tr = '<tr>\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '      </tr>';
                                body.find("#stopFileList").append(tr);
                            }
                        }
                        if(data.changeAnnexList){
                            for (var i=0;i<data.changeAnnexList.length;i++){
                                var Namefile =data.changeAnnexList[i].name;
                                var urlfile =data.changeAnnexList[i].url;
                                var tr = '<tr>\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '      </tr>';
                                body.find("#changeFileList").append(tr);
                            }
                        }
                        if(data.approveList){
                            for (var i=0;i<data.approveList.length;i++){
                                var approve = data.approveList[i];
                                var tr = '<tr> '+
                                    '         <td>'+(i+1)+'</td>' +
                                    '         <td>'+approve.name+'</td>' +
                                    '         <td>'+approve.time+'</td>' +
                                    '         <td>'+approve.type+'</td>' +
                                    '         <td>'+approve.remark+'</td>' +
                                    '      </tr>';
                                body.find("#approveList").append(tr);
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

    //终止
    function stop(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/detail";
        ajaxJS(param,{id:edit.id,flag:"3"}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractStop.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    debugger;
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".amount").val(edit.amount);
                    var data = d.data;
                    if(data.approveList){
                        for (var i=0;i<data.approveList.length;i++){
                            var approve = data.approveList[i];
                            var tr = '<tr> '+
                                '         <td>'+(i+1)+'</td>' +
                                '         <td>'+approve.name+'</td>' +
                                '         <td>'+approve.time+'</td>' +
                                '         <td>'+approve.type+'</td>' +
                                '         <td>'+approve.remark+'</td>' +
                                '      </tr>';
                            body.find("#approveList").append(tr);
                        }
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            })
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        })
    }

    //变更
    function change(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/detail";
        ajaxJS(param,{id:edit.id,flag:"2"}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractChange.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".projectList").attr("projectList", JSON.stringify(data.projectList));
                        body.find(".cityId").attr("cityId", data.cityId);
                        body.find(".code").val(data.code);
                        body.find(".name").val(data.name);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".merchantContact").val(data.merchantContact);
                        body.find(".merchantContactPhone").val(data.merchantContactPhone);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".signingContact").val(data.signingContact);
                        body.find(".signingContactPhone").val(data.signingContactPhone);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        body.find(".signingType").attr("signingType", data.signingType);
                        body.find(".contractType").attr("contractType", data.contractType);
                        body.find(".amount").html(toMoney(data.amount));
                        body.find(".margin").val(data.margin);
                        body.find(".remarks").html(data.remarks);
                        if (data.projectList) {
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var subtotal = "";
                                if(project.subtotal){
                                    subtotal = project.subtotal;
                                }
                                var str =
                                    ' <tr class="specTR" id="specTR'+project.id+'"> '+
                                    '<input type="hidden" id="project'+project.id+'" class="trLen" value="'+project.id+'">'+
                                    '<input type="hidden" class="resourceProjectId" value="'+project.id+'">'+
                                    '<td> '+
                                    '<select name="projectId" lay-filter="projectId" class="projectId"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="pointIdOne" lay-filter="pointIdOne" class="pointIdOne"> '+
                                    '</select> '+
                                    '<select name="pointIdTwo" lay-filter="pointIdTwo" class="pointIdTwo"> '+
                                    '</select> '+
                                    '<select name="pointIdThree" lay-filter="pointIdThree" class="pointIdThree"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td>' +
                                    '<select xm-select="multiRole'+project.id+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
                                    'class="resourceList" lay-verify="required"> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="tollMode" lay-filter="tollMode" class="tollMode" lay-verify="required"> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="1">一次性</option> '+
                                    '<option value="2">固定</option> '+
                                    '<option value="3">佣金</option> '+
                                    '<option value="4">其他</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="paymentCycle" lay-filter="paymentCycle" class="paymentCycle" lay-verify="required"> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="9">五年</option> '+
                                    '<option value="8">四年</option> '+
                                    '<option value="7">三年</option> '+
                                    '<option value="6">两年</option> '+
                                    '<option value="1">一年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '<option value="10">一次性</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td class="unitPriceTD"> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入单价" class="layui-input inputs unitPrice" name="unitPrice" value="'+project.unitPrice+'"  lay-verify="number"> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input maxlength="20" type="text" class="layui-input inputs unitName" placeholder="请输入单位" name="unitName" value="'+project.unitName+'"> '+
                                    '</td> '+
                                    '<td class="numberTD"> '+
                                    '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入数量" class="layui-input inputs number" name="number" value="'+project.number+'" lay-verify="integer"> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(subtotal)+'</td> '+
                                    '<td><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del">删除</a></td> '+
                                    '</tr>'
                                body.find(".specBody").append(str);
                            }
                        }
                        if(data.annexList){
                            for (var i=0;i<data.annexList.length;i++){
                                var Namefile =data.annexList[i].name;
                                var urlfile =data.annexList[i].url;
                                var tr = '<tr>\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '      </tr>';
                                body.find("#fileList").append(tr);
                            }
                        }
                        if(data.approveList){
                            for (var i=0;i<data.approveList.length;i++){
                                var approve = data.approveList[i];
                                var tr = '<tr> '+
                                    '         <td>'+(i+1)+'</td>' +
                                    '         <td>'+approve.name+'</td>' +
                                    '         <td>'+approve.time+'</td>' +
                                    '         <td>'+approve.type+'</td>' +
                                    '         <td>'+approve.remark+'</td>' +
                                    '      </tr>';
                                body.find("#approveList").append(tr);
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

    //变更审批
    function changeApprove(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/changeDetail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractChangeApprove.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".projectList").attr("projectList", JSON.stringify(data.projectList));
                        body.find(".cityId").attr("cityId", data.cityId);
                        body.find(".code").val(data.code);
                        body.find(".name").val(data.name);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".merchantContact").val(data.merchantContact);
                        body.find(".merchantContactPhone").val(data.merchantContactPhone);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".signingContact").val(data.signingContact);
                        body.find(".signingContactPhone").val(data.signingContactPhone);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        body.find(".signingType").attr("signingType", data.signingType);
                        body.find(".contractType").attr("contractType", data.contractType);
                        body.find(".amount").html(toMoney(data.amount));
                        body.find(".margin").html(toMoney(data.margin));
                        body.find(".remarks").html(data.remarks);
                        body.find(".reason").html(data.reason);
                        if (data.projectList) {
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
                                var subtotal = "";
                                if(project.subtotal){
                                    subtotal = project.subtotal;
                                }
                                var str =
                                    ' <tr class="specTR" id="specTR'+project.id+'"> '+
                                    '<input type="hidden" id="project'+project.id+'" class="trLen" value="'+project.id+'">'+
                                    '<td> '+
                                    '<select name="projectId" lay-filter="projectId" class="projectId" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="pointIdOne" lay-filter="pointIdOne" class="pointIdOne" disabled> '+
                                    '</select> '+
                                    '<select name="pointIdTwo" lay-filter="pointIdTwo" class="pointIdTwo" disabled> '+
                                    '</select> '+
                                    '<select name="pointIdThree" lay-filter="pointIdThree" class="pointIdThree" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td>' +
                                    '<select xm-select="multiRole'+project.id+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
                                    'class="resourceList" lay-verify="required" disabled> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="tollMode" lay-filter="tollMode" class="tollMode" disabled> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="1">一次性</option> '+
                                    '<option value="2">固定</option> '+
                                    '<option value="3">佣金</option> '+
                                    '<option value="4">其他</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<select name="paymentCycle" lay-filter="paymentCycle" class="paymentCycle" disabled> '+
                                    '<option value="">请选择</option> '+
                                    '<option value="9">五年</option> '+
                                    '<option value="8">四年</option> '+
                                    '<option value="7">三年</option> '+
                                    '<option value="6">两年</option> '+
                                    '<option value="1">一年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '<option value="10">一次性</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs unitPrice" name="unitPrice" value="'+project.unitPrice+'" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input maxlength="20" type="text" class="layui-input inputs unitName" name="unitName" value="'+project.unitName+'" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs number" name="number" value="'+project.number+'" disabled> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(subtotal)+'</td> '+
                                    '</tr>'
                                body.find(".specBody").append(str);
                            }
                        }
                        if(data.annexList){
                            for (var i=0;i<data.annexList.length;i++){
                                var Namefile =data.annexList[i].name;
                                var urlfile =data.annexList[i].url;
                                var tr = '<tr>\n' +
                                    '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                    '      </tr>';
                                body.find("#fileList").append(tr);
                            }
                        }

                        if(data.approveList){
                            for (var i=0;i<data.approveList.length;i++){
                                var approve = data.approveList[i];
                                var tr = '<tr> '+
                                    '         <td>'+(i+1)+'</td>' +
                                    '         <td>'+approve.name+'</td>' +
                                    '         <td>'+approve.time+'</td>' +
                                    '         <td>'+approve.type+'</td>' +
                                    '         <td>'+approve.remark+'</td>' +
                                    '      </tr>';
                                body.find("#approveList").append(tr);
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

    //终止审批
    function stopApprove(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/contract/stopDetail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractStopApprove.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    body.find(".sign").val("edit").attr("signid", data.id);
                    body.find(".terminationTime").val(data.terminationTime);
                    body.find(".notRecoveredAmount").val(data.notRecoveredAmount);
                    body.find(".reason").val(data.reason);
                    if(data.annexList){
                        for (var i=0;i<data.annexList.length;i++){
                            var Namefile =data.annexList[i].name;
                            var urlfile =data.annexList[i].url;
                            var tr = '<tr>\n' +
                                '         <td>' +  '<a class="a-download" href="'+urlfile +'" target="_blank" download="' + Namefile + '">' + Namefile + '</a>'+ '</td>\n' +
                                '      </tr>';
                            body.find("#fileList").append(tr);
                        }
                    }
                    if(data.approveList){
                        for (var i=0;i<data.approveList.length;i++){
                            var approve = data.approveList[i];
                            var tr = '<tr> '+
                                '         <td>'+(i+1)+'</td>' +
                                '         <td>'+approve.name+'</td>' +
                                '         <td>'+approve.time+'</td>' +
                                '         <td>'+approve.type+'</td>' +
                                '         <td>'+approve.remark+'</td>' +
                                '      </tr>';
                            body.find("#approveList").append(tr);
                        }
                    }
                    setTimeout(function () {
                        layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                            tips: 3
                        });
                    }, 500)
                }
            })
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
        })
    }

    //回款计划
    function plan(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackPlan/detail";
        ajaxJS(param,{contractId:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "../../../html/payback_setup/payback/paybackSee.html",
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

    //回款记录
    function record(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/payBackRecord/detailRecords";
        ajaxJS(param,{contractId:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "../../../html/payback_setup/paybackRecord/paybackRecordSee.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    debugger;
                    if (data) {
                        body.find(".sign").val("edit").attr("signid", data.id);
                        body.find(".contractId").attr("contractId", data.contractId);
                        body.find(".merchantId").attr("merchantId", data.merchantId);
                        body.find(".contractAmount").val(data.contractAmount);
                        body.find(".deadlineStartTime").val(data.deadlineStartTime);
                        body.find(".signingTime").val(data.signingTime);
                        body.find(".deadlineEndTime").val(data.deadlineEndTime);
                        if (data.projectList) {
                            var strProject ='';
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
                                    paymentCycleName = '一年'
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
                                strProject = strProject+
                                    '<tbody> '+
                                    ' <tr style="background: #3a87ad"> '+
                                    '<td>'+project.resourceName+'</td> '+
                                    '<td>'+tollModeName+'</td> '+
                                    '<td>'+paymentCycleName+'</td> '+
                                    '<td>'+toMoney(unitPrice)+'</td> '+
                                    '<td>'+number+'</td> '+
                                    '<td>'+toMoney(subtotal)+'</td> '+
                                    '</tr> '+
                                    '</tbody>'
                                if(project.payBackList){
                                    for (var j = 0; j < project.payBackList.length; j++) {
                                        var payBack = project.payBackList[j];
                                        var receivablePayback = '';
                                        if(payBack.receivablePayback){
                                            receivablePayback = payBack.receivablePayback;
                                        }
                                        debugger;
                                        if(payBack.status==1){
                                            strProject=strProject+
                                                '<tr class="project" style="background: #dadada"> '+
                                                '<td></td> '+
                                                '<td>计划回款日期</td> '+
                                                '<td>计划回款金额（元）</td> '+
                                                '<td colspan="2">备注</td> '+
                                                '<td>计划状态</td> '+
                                                '</tr> '+
                                                '<tr class="payBackList"> '+
                                                '<td class="seq"></td> '+
                                                '<td>'+payBack.paybackTime+'</td> '+
                                                '<td>'+toMoney(receivablePayback)+'</td> '+
                                                '<td colspan="2">'+payBack.remarks+'</td> '+
                                                '<td>有效</td> '+
                                                '</tr>'
                                        }else {
                                            strProject=strProject+
                                                '<tr class="project" style="background: #dadada;color: #AAAAAA"> '+
                                                '<td></td> '+
                                                '<td>计划回款日期</td> '+
                                                '<td>计划回款金额（元）</td> '+
                                                '<td colspan="2">备注</td> '+
                                                '<td>计划状态</td> '+
                                                '</tr> '+
                                                '<tr class="payBackList" style="color: #AAAAAA"> '+
                                                '<td class="seq"></td> '+
                                                '<td>'+payBack.paybackTime+'</td> '+
                                                '<td>'+toMoney(receivablePayback)+'</td> '+
                                                '<td colspan="2">'+payBack.remarks+'</td> '+
                                                '<td>失效</td> '+
                                                '</tr>'
                                        }

                                        if(payBack.recordList.length>0){
                                            strProject=strProject+
                                                '<tr style="background: #dadada">'+
                                                '<td>序号</td>'+
                                                '<td>实际回款日期</td>'+
                                                '<td>回款金额（元）</td>'+
                                                '<td>回款发票</td>'+
                                                '<td>滞纳金</td>'+
                                                '<td>收款人</td>'+
                                                '</tr>'
                                            for (var k = 0; k < payBack.recordList.length; k++) {
                                                var record = payBack.recordList[k];
                                                var lastPayment='';
                                                if(record.lastPayment){
                                                    lastPayment = record.lastPayment;
                                                }
                                                strProject=strProject+
                                                    '<tr class="recordListTr"> '+
                                                    '<td class="seq">'+(k+1)+'</td> '+
                                                    '<td> '+
                                                    '<input type="hidden" class="recordId" value="'+record.id+'">'+record.paybackTime+'</td> '+
                                                    '<td> '+record.paybackAmount+'</td> '+
                                                    '<td> '+record.paybackInvoiceCode+'</td> '+
                                                    '<td> '+lastPayment+'</td> '+
                                                    '<td> '+record.receiptMember+'</td> '+
                                                    '</tr>'
                                            }
                                        }
                                    }
                                }
                            }
                            body.find("#table").append(strProject);
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

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'edit') { //编辑
            addOrEdit("编辑合同",data);
        }
        if (layEvent === 'see') { //查看
            see("查看合同",data);
        }
        if (layEvent === 'stop') { //终止
            stop("终止合同",data);
        }
        if (layEvent === 'change') { //变更
            change("变更合同",data);
        }
        if (layEvent === 'approve') { //审批
            if(data.contractStatus==3){
                changeApprove("审批变更合同",data);
            }
            if(data.contractStatus==4){
                stopApprove("审批终止合同",data);
            }
        }
        if (layEvent === 'plan') { //回款计划
            plan("查看回款计划",data);
        }
        if (layEvent === 'record') { //回款记录
            record("查看回款记录",data);
        }
    });

    $(".add_btn").on("click", function () {
        add("添加合同");
    });

    function add(title) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "contractAdd.html",
                end:function(){
                    location.reload();
                },
                success: function (layero, index) {

                    // setTimeout(function () {
                    //     layui.layer.tips("点击此处返回", '.layui-layer-setwin .layui-layer-close', {
                    //         tips: 3
                    //     });
                    // }, 500)
                }
            });
            layui.layer.full(index);
            $(window).on("resize", function () {
                layui.layer.full(index);
            })
    }

    //搜索
    $(".search_btn").on("click", function () {
        search($, table,form, {
            merchantCode: $(".merchantCode").val(),
            merchantName: $(".merchantName").val(),
            code: $(".code").val(),
            name: $(".name").val(),
            resourceCode: $(".resourceCode").val(),
            resourceName: $(".resourceName").val(),
            projectName: $(".projectName").val(),
            deadlineStartTimeStr: deadlineStartTimeStr,
            deadlineStartTimeEnd: deadlineStartTimeEnd,
            deadlineEndTimeStr: deadlineEndTimeStr,
            deadlineEndTimeEnd: deadlineEndTimeEnd,
            cityId: $(".cityId option:selected").val(),
        });
    });

    //导出
    $(".export_btn").on("click", function () {
        var form = $('<form>');
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', interfaceUrl + 'pc/contract/exportOut');
        form.append('<input type="hidden" name="token" value="' + logToken + '" />');
        form.append('<input type="hidden" name="merchantCode" value="' + $(".merchantCode").val() + '" />');
        form.append('<input type="hidden" name="merchantName" value="' + $(".merchantName").val() + '" />');
        form.append('<input type="hidden" name="code" value="' + $(".code").val() + '" />');
        form.append('<input type="hidden" name="name" value="' + $(".name").val() + '" />');
        form.append('<input type="hidden" name="deadlineStartTimeStr" value="' + deadlineStartTimeStr + '" />');
        form.append('<input type="hidden" name="deadlineStartTimeEnd" value="' + deadlineStartTimeEnd + '" />');
        form.append('<input type="hidden" name="deadlineEndTimeStr" value="' + deadlineEndTimeStr + '" />');
        form.append('<input type="hidden" name="deadlineEndTimeEnd" value="' + deadlineEndTimeEnd + '" />');
        form.append('<input type="hidden" name="cityId" value="' + $(".cityId option:selected").val() + '" />');
        $('body').append(form);
        form.submit()
    });

    //导入
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'pc/contract/exportIn'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls'
        ,done: function(res){
            top.layer.msg(res.message);
            if(res.code==200){
                location.reload();
            }
        }
    });

    //删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("是否删除当前选择的合同", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/contract/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    top.layer.msg(d.message);
                    if(d.code==200){
                        location.reload();
                        layer.close(index);
                    }
                })
            })
        } else {
            layer.msg("请选择需要删除的合同");
        }
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
