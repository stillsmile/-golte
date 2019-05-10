layui.use(['form', 'layer', 'table', 'jquery', 'upload','laydate'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,upload = layui.upload,laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var logToken = sessionStorage.getItem('logToken');
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/excitation/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/excitation/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/excitation/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/excitation/exportIn') {
            $('.importShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    laydate.render({
        elem: '#starttime'
        ,type: 'month'
    });
    laydate.render({
        elem: '#endtime'
        ,type: 'month'
    });

    //年选择器
    laydate.render({
        elem: '#yyear'
        ,type: 'year'
        ,trigger: 'click'
    });

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/excitation/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'paybackAmount', title: "已收回款", align: 'center'},
        {
            field: 'contractType', title: "激励类别", align: 'center', templet: function (d) {
                return CONTRACTTYPE.properties[d.contractType].name;
            }
        },
        {field: 'projectName', title: "所在项目", align: 'center'},
        {field: 'cityName', title: "所在城市", align: 'center'},
        {
            field: 'type', title: "激励类型", align: 'center', templet: function (d) {
                return EXCITATIONTYPE.properties[d.type].name;
            }
        },
        {field: 'memberName', title: "激励人员", align: 'center'},
        {field: 'propertyProfit', title: "利润金额", align: 'center'},
        {field: 'actualAmount', title: "激励金额", align: 'center'},
        {
          field: 'status', title: "操作",align: "center", templet: function (d) {
                var html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="detail">查看</a>';
                var edit = "";
                if(d.status=="1"){
                    edit = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>';
                }
                if (isUpdate) {
                    html = edit + html;
                }
                return html;
            }
        }
    ]]);

    //搜索
    $(".search_btn").on("click", function () {
        search($, table, form, {
            type: $('.type').val(),//激励类型（1月度激励 2年度激励）
            projectName: $('.projectName').val(),//项目名称
            memberName: $('.memberName').val(),//联系人
            contractType: $('.contractType').val(),//联系方式
            starttime: $('#starttime').val(),//创建时间
            endtime: $('#endtime').val(),//结束时间
            yyear: $('#yyear').val(),//年度
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/excitation_setup/excitationManage/excitationManageAdd.html", "新增激励");
    });


    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["800px", "650px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    body.find(".excitationType").attr("disabled","true");
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".excitationType").val(edit.type);
                    if(edit.type=="1"){//月度
                        body.find(".mcontractType").val(edit.contractType);
                        body.find(".mprojectId").attr("val",edit.projectId);
                        body.find(".mpropertyProfit").val(edit.propertyProfit);
                        body.find(".mtaxPercentage").val(edit.taxPercentage);
                        body.find(".mtax").val(edit.tax);
                        body.find(".mcardinalNumber").val(edit.cardinalNumber);
                        body.find(".mexcitationPercentage").val(edit.excitationPercentage);
                        body.find(".mactualAmount").val(edit.actualAmount);
                        body.find(".mmonth").val(edit.month);
                        body.find(".mmemberName").val(edit.memberName);
                        body.find(".yearShow").remove();
                    } else if(edit.type=="2"){//年度
                        body.find(".yearShow").show();
                        body.find(".monthShow").css("display","none")
                        body.find(".ycontractType").val(edit.contractType);
                        body.find(".yprojectId").attr("val",edit.projectId);
                        body.find(".ypropertyProfit").val(edit.propertyProfit);
                        body.find(".ytaxPercentage").val(edit.taxPercentage);
                        body.find(".ytax").val(edit.tax);
                        body.find(".ymanagementAmount").val(edit.managementAmount);
                        body.find(".yotherCost").val(edit.otherCost);
                        body.find(".yyear").val(edit.month);
                        body.find(".yprofitIndex").val(edit.profitIndex);
                        body.find(".ycardinalNumber").val(edit.cardinalNumber);
                        body.find(".yroleId").attr("val",edit.roleId);
                        body.find(".yexcitationPercentage").val(edit.excitationPercentage);
                        body.find(".yshouldAmount").val(edit.shouldAmount);
                        body.find(".yarrivalConversion").val(edit.arrivalConversion);
                        body.find(".yotherDeduction").val(edit.otherDeduction);
                        body.find(".yactualAmount").val(edit.actualAmount);
                        body.find(".ymemberName").val(edit.memberName);
                        body.find(".monthShow").remove();
                    }
                    if (edit.showType == "detail"){
                        body.find(".sign").attr("detail", "detail");
                        body.find(".isShowBtn").css('display', 'none');
                        if(edit.type=="1"){//月度
                            body.find(".mcontractType").attr("disabled","true");
                            body.find(".mprojectId").attr("disabled","true");
                            body.find(".mpropertyProfit").attr("disabled","true");
                            body.find(".mtaxPercentage").attr("disabled","true");
                            body.find(".mmonth").attr("disabled","true");
                            if(!edit.month){body.find(".mmonth").attr("placeholder","")}
                            body.find(".mmemberName").attr("disabled","true");
                            if(!edit.memberName){body.find(".mmemberName").attr("placeholder","")}
                        }else if(edit.type=="2"){//年度
                            if(!edit.roleId){body.find(".yroleId").attr("placeholder","")}
                            if(!edit.otherDeduction){body.find(".yotherDeduction").attr("placeholder","")}
                            body.find(".ycontractType").attr("disabled","true");
                            body.find(".yprojectId").attr("disabled","true");
                            body.find(".ypropertyProfit").attr("disabled","true");
                            body.find(".ytaxPercentage").attr("disabled","true");
                            body.find(".yyear").attr("disabled","true");
                            if(!edit.month){body.find(".yyear").attr("placeholder","")}
                            body.find(".yotherCost").attr("disabled","true");
                            body.find(".yprofitIndex").attr("disabled","true");
                            body.find(".yroleId").attr("disabled","true");
                            if(!edit.roleId){body.find(".yroleId").attr("placeholder","")}
                            body.find(".yotherDeduction").attr("disabled","true");
                            body.find(".yarrivalConversion").attr("disabled","true");
                            if(!edit.arrivalConversion){body.find(".yarrivalConversion").attr("placeholder","")}
                            body.find(".ymemberName").attr("disabled","true");
                            if(!edit.memberName){body.find(".ymemberName").attr("placeholder","")}
                        }
                    }
                    //月度
                    body.find(".mtax").attr("disabled","true");
                    body.find(".mcardinalNumber").attr("disabled","true");
                    body.find(".mexcitationPercentage").attr("disabled","true");
                    body.find(".mactualAmount").attr("disabled","true");
                    //年度
                    body.find(".ymanagementAmount").attr("disabled","true");
                    body.find(".ytax").attr("disabled","true");
                    body.find(".ycardinalNumber").attr("disabled","true");
                    body.find(".yshouldAmount").attr("disabled","true");
                    body.find(".yactualAmount").attr("disabled","true");
                    body.find(".yexcitationPercentage").attr("disabled","true");

                    body.find(".level").val(edit.level);
                    body.find(".remarks").val(edit.remarks);
                    form.render();
                }
            }
        })
    }

    //删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("是否删除当前选择的激励信息", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/excitation/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的激励信息");
        }
    });

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            // data.type="edit";
            addOrEdit("html/excitation_setup/excitationManage/excitationManageAdd.html", "编辑激励", data);
        }
        if (layEvent === 'detail') { //商家评估
            data.showType="detail";
            addOrEdit("html/excitation_setup/excitationManage/excitationManageAdd.html", "激励详情", data);
        }
    });

    //月度导入
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'pc/excitation/exportIn'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls'
        ,done: function(res){
            if(res.code=="403"){
                var split = res.message.split(",");
                var mes = "";
                for (var i=0;i<split.length;i++) {
                    mes = mes+split[i]+'<br>';
                }
                layer.msg(mes, {
                    time: 20000, //20s后自动关闭
                    btn: ['确定']
                    ,btnAlign: 'c' //按钮居中
                });
            }else{
                top.layer.msg(res.message);
                location.reload();
            }

        }
    });
    //年度导入
    upload.render({
        elem: '#yearUpload'
        ,url: interfaceUrl+'pc/excitation/exportYearIn'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls'
        ,done: function(res){
            if(res.code=="403"){
                var split = res.message.split(",");
                var mes = "";
                for (var i=0;i<split.length;i++) {
                    mes = mes+split[i]+'<br>';
                }
                layer.msg(mes, {
                    time: 20000, //20s后自动关闭
                    btn: ['确定']
                    ,btnAlign: 'c' //按钮居中
                });
            }else{
                top.layer.msg(res.message);
                location.reload();
            }
        }
    });

    //导出
    $(".export_btn").on("click", function () {
        var stattime = "";
        if($(".starttime").val()) stattime = $(".starttime").val();

        var endtime = "";
        if($(".endtime").val()) endtime = $(".endtime").val();


        var form = $('<form>');
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.append('<input type="hidden" name="token" value="' + logToken + '" />');
        form.attr('action', interfaceUrl + 'pc/excitation/exportOut');
        form.append('<input type="hidden" name="type" value="' + $(".type").val() + '" />');
        form.append('<input type="hidden" name="projectName" value="' + $(".projectName").val() + '" />');
        form.append('<input type="hidden" name="memberName" value="' + $(".memberName").val() + '" />');
        form.append('<input type="hidden" name="contractType" value="' + $(".contractType").val() + '" />');
        form.append('<input type="hidden" name="starttime" value="' + stattime + '" />');
        form.append('<input type="hidden" name="endtime" value="' + endtime + '" />');
        $('body').append(form);
        form.submit()
    });


    //搜索选择商家等级
    form.on('select(type)', function(data){
        var typeNo = data.value;
        if(typeNo =="1"){//月度激励
            $(".monthShow").show();
            $(".yearShow").hide();

        }else if(typeNo =="2"){//年度激励
            $(".monthShow").hide();
            $(".yearShow").show();
        }else {//全部
            $(".monthShow").hide();
            $(".yearShow").hide();
        }
    });

});

function goLogin() {
    parent.goLogin()
}
