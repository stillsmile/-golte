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

    form.on("submit(pass)", function (data) {
        approve(data,2);
    });

    form.on("submit(unPass)", function (data) {
        approve(data,3);
    });
    function approve (data,status) {
        var field = data.field;

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var data = {
            id:$(".sign").attr("signid"),
            status: status,
            opinion:field.opinion,
        };
        param.url = 'pc/payBackApproval/approve';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();

        });
    };

});

function goLogin() {
    parent.goLogin()
}
