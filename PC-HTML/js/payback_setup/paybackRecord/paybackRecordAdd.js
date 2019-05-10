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

    form.render();
    var trLen = 1;
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

        param.url = 'pc/payBackRecord/getEmpList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            $('.receiptMember').append('<option value=""></option>')
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('.receiptMember').append(str)
            }
            if ($('.sign').val() == 'edit') {
                var object = $(".recordListData").attr('recordList');
                // 转换成数组
                var myobj = eval(object);
                if(myobj){
                    for (var i = 0; i < myobj.length; i++) {
                        var project = myobj[i];
                        var id = '#recordListTr'+project.id;
                        $(id).find('.receiptMember').val(project.receiptMember);
                    }
                }
            }
            form.render()
        });

        $(".recordListTr").each(function () {
            var obj = $(this).find(".recordId").val();
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
        var str =
            '<tr class="recordListTr"> '+
            '<input type="hidden" id="record'+trLen+'" class="trLen" value="'+trLen+'">'+
            '<td class="seq">'+trLen+'</td> '+
            '<td> '+
            '<input type="hidden" class="recordId" value="">'+
            '<input type="text" class="layui-input paybackTime" placeholder="请选择实际回款日期" id="paybackTime'+trLen+'" lay-verify="required"> '+
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
        $('.recordList').append(str)
        $('.del').each(function() {
            $(this).click(function() {
                $(this)
                    .parents('.recordListTr')
                    .remove()
            })
        })

        param.url = 'pc/payBackRecord/getEmpList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            debugger
            $('#record'+trLen).parents('.recordListTr').find('.receiptMember').append('<option value=""></option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('#record'+trLen).parents('.recordListTr').find('.receiptMember').append(str)
            }
            form.render()
        });

        laydate.render({
            elem: '#paybackTime'+trLen,
            type: 'date',
            trigger: 'click'
        });
        form.render();
        trLen++;
    })


    /*添加标签*/
    $('.del').click(function() {
        $(this)
            .parents('.recordListTr')
            .remove()
        form.render();
    })


    form.on("submit(addOrEdit)", function (data) {
        add(data,0);
    })

    function add(data,status) {
        var field = data.field;
        //弹出loading
        var index = top.layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.8});

        if($(".receivablePayback").text()==null||$(".receivablePayback").text()==undefined||$(".receivablePayback").text()==0){
            top.layer.msg("请先填写计划回款金额，再进行回款操作");
            return false;
        }

        var recordList = [];
        var flag = false;
        var flag1 = false;
        var flag2 = false;
        $(".recordListTr").each(function () {
            var month = datemonth($(this).find(".paybackTime").val(),$('.planPaybackTime').html());
            if(month&&($(this).find(".lastPayment").val()==''||Number($(this).find(".lastPayment").val())==0)){
                flag = true;
            }
            var reg = /^([\+ \-]?(([1-9]\d*)|(0)))([.]\d{0,2})?$/;
            if($(this).find(".paybackAmount").val()){
                if(!reg.test($(this).find(".paybackAmount").val())){
                    flag1 = true;
                }
            }

            var reg2 = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
            if($(this).find(".lastPayment").val()){
                if(!reg2.test($(this).find(".lastPayment").val())){
                    flag2 = true;
                }
            }
            recordList.push({
                id: $(this).find(".recordId").val(),
                contractProjectId: $('.contractProjectId').val(),
                paybackPlanId: $('.paybackPlanId').val(),
                contractId: field.contractId,
                paybackAmount: $(this).find(".paybackAmount").val(),
                paybackInvoiceCode: $(this).find(".paybackInvoiceCode").val(),
                lastPayment: $(this).find(".lastPayment").val(),
                receiptMember: $(this).find(".receiptMember").val(),
                paybackTime: $(this).find(".paybackTime").val(),
            });
        });
        if(recordList.length==0){
            top.layer.msg("请添加回款记录");
            return false;
        }

        if(flag){
            top.layer.msg("回款时间超出计划时间一个月，需提交延期申请或缴纳滞纳金");
            return false;
        }
        if(flag1){
            top.layer.msg("请输入正确的回款金额");
            return false;
        }
        if(flag2){
            top.layer.msg("请输入大于等于0的滞纳金");
            return false;
        }

        var data = {
            recordList: recordList,
        };
        param.url = 'pc/payBackRecord/update';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        });
    };

    $('.cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
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
