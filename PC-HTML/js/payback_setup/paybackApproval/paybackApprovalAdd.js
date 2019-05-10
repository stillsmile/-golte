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

    }, 200)


    laydate.render({
        elem: '#extensionTime',
        type: 'date'
    });

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        //弹出loading
        var index = top.layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.8});

        var data = {
            paybackPlanId: $('.paybackPlanId').val(),
            contractId: field.contractId,
            extensionTime: $(".extensionTime").val(),
            extensionAmount: field.extensionAmount,
            extensionReason: field.extensionReason,
        };
        param.url = 'pc/payBackApproval/delay';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        });
    })


    $('.cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});

function goLogin() {
    parent.goLogin()
}
