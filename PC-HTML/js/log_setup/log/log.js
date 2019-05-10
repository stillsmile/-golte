layui.use(['form', 'layer', 'table', 'jquery', 'laydate', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    //全局设置ajax请求参数
    var param = {
        jquery: $,
        layer: layer,
        url: '',
        type: 'post',
    };


    //初始化数据表格
    tableInit(table, 'pc/log/select', [[
        {field: 'contractName', title: "合同名称", align: 'center'},
        {field: 'type', title: "操作类型", align: 'center',templet: function(d) {
                if (d.type == '1') {
                    return "终止合同"
                }else if (d.type == '2') {
                    return "变更合同"
                }else {
                    return ""
                }
            }},
        {field: 'status', title: "审批状态", align: 'center',templet: function(d) {
            if (d.status == '1') {
                return "审批中"
            }else if (d.status == '2') {
                return "通过"
            }else if (d.status == '3') {
                return "未通过"
            }else {
                return ""
            }
        }},
        {field: 'createName', title: "提交人", align: 'center'},
        {field: 'approveName', title: "审批人", align: 'center',templet: function(d) {
                if (d.status == '1') {
                    return d.approveName
                }else {
                    return d.approvedName
                }
            }},
        {field: 'createTime', title: "操作时间", align: 'center'},
        {
            title: "操作", width: 200, fixed: 'right', align: "center", templet: function (d) {
            var str='';
            if(d.type == '2'){
                str=str+ '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="see">变更详情</a>'
            }
            return str;
            }
        }
    ]]);


    //查看
    function see(title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        param.url = "pc/log/detail";
        ajaxJS(param,{id:edit.id}, function (d) {
            var index = layui.layer.open({
                title: title,
                type: 2,
                content: "logSee.html",
                success: function (layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var data = d.data;
                    if (data) {
                        var signingTypeStr = "";
                        if(data.signingType==1){
                            signingTypeStr = "新合同";
                        }
                        if(data.signingType==2){
                            signingTypeStr = "续签";
                        }
                        var signingTypeUpStr = "";
                        if(data.signingTypeUp==1){
                            signingTypeUpStr = "新合同";
                        }
                        if(data.signingTypeUp==2){
                            signingTypeUpStr = "续签";
                        }

                        var contractTypeStr = "";
                        if(data.contractType==1){
                            contractTypeStr = "广告资源";
                        }
                        if(data.contractType==2){
                            contractTypeStr = "场地资源";
                        }
                        if(data.contractType==3){
                            contractTypeStr = "临时摆展";
                        }
                        if(data.contractType==4){
                            contractTypeStr = "服务产品";
                        }
                        if(data.contractType==5){
                            contractTypeStr = "其他";
                        }
                        var contractTypeUpStr = "";
                        if(data.contractTypeUp==1){
                            contractTypeUpStr = "广告资源";
                        }
                        if(data.contractTypeUp==2){
                            contractTypeUpStr = "场地资源";
                        }
                        if(data.contractTypeUp==3){
                            contractTypeUpStr = "临时摆展";
                        }
                        if(data.contractTypeUp==4){
                            contractTypeUpStr = "服务产品";
                        }
                        if(data.contractTypeUp==5){
                            contractTypeUpStr = "其他";
                        }
                        body.find(".projectList").attr("projectList", JSON.stringify(data.projectList));
                        body.find(".merchantContact").html(data.merchantContact);
                        body.find(".merchantContactPhone").html(data.merchantContactPhone);
                        body.find(".signingTime").html(data.signingTime);
                        body.find(".signingContact").html(data.signingContact);
                        body.find(".signingContactPhone").html(data.signingContactPhone);
                        body.find(".deadlineStartTime").html(data.deadlineStartTime);
                        body.find(".deadlineEndTime").html(data.deadlineEndTime);
                        body.find(".signingType").html(signingTypeStr);
                        body.find(".contractType").html(contractTypeStr);
                        body.find(".amount").html(toMoney(data.amount));
                        body.find(".margin").html(toMoney(data.margin));

                        body.find(".projectListUp").attr("projectListUp", JSON.stringify(data.projectListUp));
                        body.find(".merchantContactUp").html(data.merchantContactUp);
                        body.find(".merchantContactPhoneUp").html(data.merchantContactPhoneUp);
                        body.find(".signingTimeUp").html(data.signingTimeUp);
                        body.find(".signingContactUp").html(data.signingContactUp);
                        body.find(".signingContactPhoneUp").html(data.signingContactPhoneUp);
                        body.find(".deadlineStartTimeUp").html(data.deadlineStartTimeUp);
                        body.find(".deadlineEndTimeUp").html(data.deadlineEndTimeUp);
                        body.find(".signingTypeUp").html(signingTypeUpStr);
                        body.find(".contractTypeUp").html(contractTypeUpStr);
                        body.find(".amountUp").html(toMoney(data.amountUp));
                        body.find(".marginUp").html(toMoney(data.marginUp));
                        if (data.projectList) {
                            for (var i = 0; i < data.projectList.length; i++) {
                                var project = data.projectList[i];
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
                                    '<option value="1">年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs unitPrice" name="unitPrice" value="'+project.unitPrice+'"  lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs number" name="number" value="'+project.number+'" lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(project.subtotal)+'</td> '+
                                    '</tr>'
                                body.find(".specBody").append(str);
                            }
                        }

                        if (data.projectListUp) {
                            for (var i = 0; i < data.projectListUp.length; i++) {
                                var projectUp = data.projectListUp[i];
                                var str =
                                    ' <tr class="specTRUp" id="specTRUp'+projectUp.id+'"> '+
                                    '<input type="hidden" id="project'+projectUp.id+'" class="trLen" value="'+projectUp.id+'">'+
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
                                    '<select xm-select="multiRoleUp'+projectUp.id+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
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
                                    '<option value="1">年</option> '+
                                    '<option value="2">半年</option> '+
                                    '<option value="3">季度</option> '+
                                    '<option value="4">月</option> '+
                                    '<option value="5">周</option> '+
                                    '</select> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs unitPrice" name="unitPrice" value="'+projectUp.unitPrice+'"  lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td> '+
                                    '<input type="number" class="layui-input inputs number" name="number" value="'+projectUp.number+'" lay-verify="required" disabled> '+
                                    '</td> '+
                                    '<td class="subtotal">'+toMoney(projectUp.subtotal)+'</td> '+
                                    '</tr>'
                                body.find(".specBodyUp").append(str);
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

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'see') { //查看
            see("查看变更详情",data);
        }
    });

    //搜索
    $(".search_btn").on("click", function () {
        search($, table,form, {
            contractName: $(".contractName").val(),
            status: $(".status option:selected").val(),
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
