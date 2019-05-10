layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    setTimeout(function () {
        param.url = 'pc/centralcity/centraleader';
        ajaxJS(param, {roleId:"5"}, function (d) {
            var data = d.data;
            $('.centralCityLeaders').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('.centralCityLeaders').append(str)
            }
            $('.centralCityLeaders').val($('.centralCityLeaders').attr("empId"));
            form.render();
        });

    }, 50)


    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        var roleIds = [];
        roleIds.push($('.roleIds').val());

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var data = {
            centralCityName: field.centralCityName,
            empId: field.centralCityLeaders
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/centralcity/update';
        } else {
            param.url = 'pc/centralcity/save';
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
