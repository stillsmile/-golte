layui.use(['form', 'layer', 'table', 'jquery', 'laydate', 'upload'], function () {
    var form = layui.form,
        $ = layui.jquery,
        laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post'
    };
    var formSelects = layui.formSelects;


    var contractId = UrlParm.parm('contractId');

    if(contractId){
        //打开回款计划编辑页
        param.url = "pc/payBackPlan/detail";
        ajaxJS(param,{contractId:contractId}, function (d) {
            var data = d.data;
            if (data) {
                $(".sign").val("edit").attr("signid", data.id);
                $(".contractId").attr("contractId", data.contractId);
                $(".merchantId").attr("merchantId", data.merchantId);
                $(".contractAmount").val(data.contractAmount);
                $(".deadlineStartTime").val(data.deadlineStartTime);
                $(".signingTime").val(data.signingTime);
                $(".deadlineEndTime").val(data.deadlineEndTime);
                if (data.projectList) {
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
                            '<td>'+unitPrice+'</td> '+
                            '<td>'+number+'</td> '+
                            '<td>'+subtotal+'</td> '+
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
                        $("#table").append(strProject);
                        form.render();
                        if(project.payBackList){
                            for (var j = 0; j < project.payBackList.length; j++) {
                                var payBack = project.payBackList[j];
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
                                    '<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs receivablePayback" placeholder="请输入计划回款金额" name="receivablePayback" value="'+payBack.receivablePayback+'"> '+
                                    '</td> '+
                                    '<td colspan="2"> '+
                                    '<input maxlength="200" type="text" class="layui-input inputs remarks" name="remarks" placeholder="请输入备注" value="'+payBack.remarks+'"> '+
                                    '</td> '+
                                    '<td class="caozuo"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row dels">删除</a></td> '+
                                    '</tr>'
                                $(".projectId"+project.id).append(strPayBack);
                                form.render();
                            }
                        }
                    }
                }
                form.render();
            }
        });
    }

    form.render();
    var trLen = 0;
    //初始化select
    setTimeout(function() {
        param.url = 'pc/contract/getMerchantList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                $('.merchantId').append(str)
            }
            if ($('.sign').val() == 'edit') {
                $(".merchantId").val($(".merchantId").attr('merchantId'));
            }
            form.render()
        });

        param.url = 'pc/contract/getContractList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                $('.contractId').append(str)
            }
            if ($('.sign').val() == 'edit') {
                $(".contractId").val($(".contractId").attr('contractId'));
            }
            form.render()
        });

        $(".payBackList").each(function () {
            var obj = $(this).find(".payBackId").val();
            laydate.render({
                elem: '#paybackTime'+obj,
                type: 'date',
                trigger: 'click'
            });
            form.render();
            trLen++;
        });
    }, 200)


    /*添加标签*/
    $('.add').click(function() {
        var projectId = $(this).parents('.project').find('.projectIds').val();
        if($('.backAmounts').text()==''||$('.backAmounts').text()==null||$('.backAmounts').text()==undefined){
            var str =
                '<tr class="payBackList" name="payBackList'+projectId+'"> '+
                '<td class="seq"></td> '+
                '<td> '+
                '<input type="hidden" class="payBackId" value="">'+
                '<input type="hidden" class="projectId" value="'+projectId+'">'+
                '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+trLen+'" lay-verify="required"> '+
                '</td> '+
                '<td> '+
                '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入计划回款金额" class="layui-input inputs receivablePayback" name="receivablePayback"> '+
                '</td> '+
                '<td colspan="2"> '+
                '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks"> '+
                '</td> '+
                '<td class="caozuo"> <a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del'+trLen+'">删除</a></td> '+
                '</tr>'
        }else {
            var str =
                '<tr class="payBackList" name="payBackList'+projectId+'"> '+
                '<td class="seq"></td> '+
                '<td> '+
                '<input type="hidden" class="payBackId" value="">'+
                '<input type="hidden" class="projectId" value="'+projectId+'">'+
                '<input type="text" class="layui-input paybackTime" placeholder="请选择计划回款日期" id="paybackTime'+trLen+'" lay-verify="required"> '+
                '</td> '+
                '<td> '+
                '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入计划回款金额" class="layui-input inputs receivablePayback" name="receivablePayback"> '+
                '</td> '+
                '<td colspan="3"> '+
                '<input maxlength="200" type="text" class="layui-input inputs remarks" placeholder="请输入备注" name="remarks"> '+
                '</td> '+
                '<td class="caozuo"> <a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del'+trLen+'">删除</a></td> '+
                '</tr>'
        }
        $('.projectId'+projectId).append(str)
        /*添加标签*/
        $('.del'+trLen).click(function() {
            debugger;
            if($(this).parents('.payBackList').find('.receivedPayback').val()!=0){
                top.layer.msg("该计划已回款，无法删除！");
                return false;
            }
            var projectId = $(this).parents('.payBackList').find('.projectId').val();
            if($('tr[name="payBackList'+projectId+'"]').length==1){
                top.layer.msg("最后一条计划，无法删除！");
                return false;
            }
            $(this)
                .parents('.payBackList')
                .remove()
            form.render();
            var seq=1;
            $(this).parents('tbody .projectId'+projectId).find('.payBackList').each(function() {
                $(this).find('.seq').html(seq);
                seq++;
            })
            form.render();
        })
        laydate.render({
            elem: '#paybackTime'+trLen,
            type: 'date',
            trigger: 'click'
        });
        form.render();
        debugger;
        var seq=1;
        $('.projectId'+projectId).find('.payBackList').each(function() {
            $(this).find('.seq').html(seq);
            seq++;
        })
        form.render();
        trLen++;
    })


    /*添加标签*/
    $('.dels').click(function() {
        debugger;
        if($(this).parents('.payBackList').find('.receivedPayback').val()!=0){
            top.layer.msg("该计划已回款，无法删除！");
            return false;
        }
        var projectId = $(this).parents('.payBackList').find('.projectId').val();
        if($('tr[name="payBackList'+projectId+'"]').length==1){
            top.layer.msg("最后一条计划，无法删除！");
            return false;
        }
        $(this)
            .parents('.payBackList')
            .remove()
        form.render();
        var seq=1;
        $(this).parents('tbody .projectId'+projectId).find('.payBackList').each(function() {
            $(this).find('.seq').html(seq);
            seq++;
        })
        form.render();
    })


    form.on("submit(addOrEdit)", function (data) {
        add(data,0);
    })

    function add(data,status) {
        var field = data.field;
        //弹出loading
        var index = top.layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.8});

        var paybackList = [];
        var flagPayback = false;
        var flag = false;
        var msg = "";
        $(".payBackList").each(function () {
            if($(this).find(".paybackTimeOld").val()){
                var month = datemonth($(this).find(".paybackTime").val(),$(this).find(".paybackTimeOld").val());
                if(month){
                    if(msg){
                        if(msg.indexOf($(this).find(".projectResource").val())==-1){
                            msg=msg+"，资源："+$(this).find(".projectResource").val()+"中序号"+$(this).find(".seq").text()+"的回款计划超出原计划时间一个月";
                        }else {
                            msg=msg+"，序号"+$(this).find(".seq").text()+"的回款计划超出原计划时间一个月";
                        }
                    }else {
                        msg="资源："+$(this).find(".projectResource").val()+"中序号"+$(this).find(".seq").text()+"的回款计划超出原计划时间一个月";
                    }
                    flagPayback = true;
                }
            }
            var reg = /^([\+ \-]?(([1-9]\d*)|(0)))([.]\d{0,2})?$/;
            if($(this).find(".receivablePayback").val()){
                if(!reg.test($(this).find(".receivablePayback").val())){
                    flag = true;
                }
            }
            paybackList.push({
                id: $(this).find(".payBackId").val(),
                contractProjectId: $(this).find(".projectId").val(),
                contractId: field.contractId,
                paybackTime: $(this).find(".paybackTime").val(),
                receivablePayback: $(this).find(".receivablePayback").val(),
                remarks: $(this).find(".remarks").val(),
            });
        });

        if(flagPayback){
            top.layer.msg(msg);
            return false;
        }
        if(flag){
            top.layer.msg("回款计划金额格式不合法，只能保留两位小数");
            return false;
        }

        var flagAmount = false;
        debugger;
        $(".project").each(function () {
            var projectId = $(this).find(".projectIds").val();
            var subTotal = $(this).find(".subtotal").val();
            var backAmount = $(this).find(".backAmount").val();
            var back =0
            if(backAmount){
                back = backAmount;
            }
            var amount=0;
            $('.projectId'+projectId).find('.payBackList').each(function() {
                var receivablePayback = $(this).find(".receivablePayback").val();
                amount=Number(amount)+Number(receivablePayback);
            })
            if(amount<(Number(subTotal)-Number(back))&&subTotal!=0){
                flagAmount = true;
            }
        })
        if(flagAmount){
            top.layer.msg("计划回款金额不能小于资源合同中的小计");
            return false;
        }

        var data = {
            payBackPlanList: paybackList,
        };
        param.url = 'pc/payBackPlan/update';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
            parent.layer.close(index); //再执行关闭
            //刷新父页面
            parent.location.reload();
        });
    };

    $('.cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
        //刷新父页面
        parent.location.reload();
    });
});

//计算日期之间的月数
function datemonth(date1,date2){
    debugger;
    // 拆分年月日
    date1 = date1.split('-');
    // 得到月数
    var month1 = parseInt(date1[0]) * 12 + parseInt(date1[1]);
    // 拆分年月日
    date2 = date2.split('-');
    // 得到月数
    var month2 = parseInt(date2[0]) * 12 + parseInt(date2[1]);
    var m = month1 - month2;
    if(m==1){
        if(parseInt(date1[2])>parseInt(date2[2])){
            return true;
        }else {
            return false;
        }
    }
    if(m>1){
        return true;
    }
}

function goLogin() {
    parent.goLogin()
}
