layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };


    setTimeout(function () {

        param.url = 'pc/centralcity/selectCentralCityByCitys';//中心城市列表
        ajaxJS(param, {}, function (d) {
            var data = d.data;
            $('.centralCityId').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].id + '">' + data[i].centralCityName + '</option>';
                $('.centralCityId').append(str1)
            }
            $('.centralCityId').val($('.centralCityId').attr('val'));
            form.render();
            var centralCityId =$('.centralCityId').val();

            if(centralCityId!=undefined && centralCityId!=""){
                param.url = 'pc/citymanage/select';//对应的权限下的城市列表
                ajaxJS(param, {centralCityId:centralCityId}, function (d) {
                    var data = d.data.list;
                    $('.cityId').empty();
                    $('.cityId').append('<option value="">请选择</option>');
                    for (var i = 0; i < data.length; i++) {
                        var str1 = '<option value="' + data[i].id + '">' + data[i].cityName + '</option>';
                        $('.cityId').append(str1)
                    }
                    $('.cityId').val($('.cityId').attr('val'));
                    form.render();
                });
            }
        });

        //中心城市联动城市信息
        form.on('select(centralCityId)', function(data){
            param.url = 'pc/citymanage/select';//对应的权限下的城市列表
            var centralCityId = $('.centralCityId').val();
            ajaxJS(param, {centralCityId:centralCityId}, function (d) {
                var data = d.data.list;
                $('.cityId').empty();
                $('.cityId').append('<option value="">请选择</option>');
                for (var i = 0; i < data.length; i++) {
                    var str1 = '<option value="' + data[i].id + '">' + data[i].cityName + '</option>';
                    $('.cityId').append(str1)
                }
                $('.cityId').val($('.cityId').attr('val'));
                form.render();
            });
            form.render('select');
        });

    }, 50)


    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        // var roleIds = [];
        // roleIds.push($('.roleIds').val());

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var data = {
            centralCityId: field.centralCityId,
            cityId: field.cityId,
            projectName: field.projectName
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/resource/project/update';
        } else {
            param.url = 'pc/resource/project/save';
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
