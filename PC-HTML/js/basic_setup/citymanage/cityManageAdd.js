layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    setTimeout(function () {

        param.url = 'pc/centralcity/selectCentralCityByCitys';//中心城市列表
        ajaxJS(param, {roleId:"4"}, function (d) {
            var data = d.data;
            $('.centralCityName').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].id + '">' + data[i].centralCityName + '</option>';
                $('.centralCityName').append(str1)
            }
            $('.centralCityName').val($('.centralCityName').attr('val'));
            form.render();
        });

        param.url = 'pc/centralcity/centraleader';//城市分公司负责人
        ajaxJS(param, {roleId:"4"}, function (d) {
            var data = d.data;
            $('.cityLeaderName').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('.cityLeaderName').append(str)
            }
            $('.cityLeaderName').val($('.cityLeaderName').attr('val'));
            form.render();
        });

        //经营部门负责人
        ajaxJS(param, {roleId:"3"}, function (d) {
            var data = d.data;
            $('.departmentLeaderName').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('.departmentLeaderName').append(str)
            }
            $('.departmentLeaderName').val($('.departmentLeaderName').attr('val'));
            form.render();
        });

        //录入人员集合
        ajaxJS(param, {roleId:"2"}, function (d) {
            var data = d.data;
            $('.enterPersonIds').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
                $('.enterPersonIds').append(str)
            }
            var values = [];
            if ($('.enterPersonIds').attr('val')) {
                values = $('.enterPersonIds').attr('val').split(',');
            }
            formSelects.render('multiRole', {
                skin: 'primary',
                radio: false,
                init: values
            });
            $('.xm-input').attr('placeholder', values.length > 0 ? '' :"请选择");

            if ($(".sign").val() == "edit"){
                $(".enterPersonIds").next().attr("cscs","edit");
            }else if ($(".sign").val() == "detail"){
                $(".enterPersonIds").next().css("pointer-events","none");
            }
            form.render();
        });

    }, 50)



    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        // var roleIds = [];
        // roleIds.push($('.roleIds').val());
        if(field.centralCityName==""){
            layer.msg("请选择中心城市");
            return;
        }

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var enterPersonIds = field.enterPersonIds.split(",");
        var data = {
            centralCityId: field.centralCityName,
            cityName: field.cityName,
            cityPrincipal: field.cityLeaderName,
            businessPrincipal: field.departmentLeaderName,
            enterPersonIds: enterPersonIds,
            yearTarget: field.yearTarget
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/citymanage/update';
        } else {
            param.url = 'pc/citymanage/save';
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
