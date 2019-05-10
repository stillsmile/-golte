layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var data = {
            typeId: $(".typeId").val(),
            roleNameId: $(".roleNameId").val(),
            typeValue: field.typeValue,
            roleName: field.roleName,
            perCent: field.perCent,
            perCent: field.perCent,
        };

        if ($(".sign").val() == "edit") {
            param.url = 'pc/config/update';
        } else {
            param.url = 'pc/config/save';
        }

        ajaxJS(param, data, function (d) {
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();

        });
        return false;
    });
    $('.cancel').click(function () {
        layer.closeAll("iframe");
    });
});
