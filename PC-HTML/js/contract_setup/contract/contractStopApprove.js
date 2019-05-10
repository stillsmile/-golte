layui.use(['form', 'layer','laydate', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.on("submit(pass)", function (data) {
        approve(data,2);
    });

    form.on("submit(unPass)", function (data) {
        approve(data,3);
    });
    function approve (data,status) {
        var field = data.field;

        var terminationTime = $("#terminationTime").val();

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var data = {
            id:$(".sign").attr("signid"),
            approvalStatus: status,
            opinion:field.opinion,
        };
        param.url = 'pc/contract/approveStop';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();

        });
    };
});
