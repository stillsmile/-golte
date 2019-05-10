layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var data = { };

        if ($(".sign").val() == "edit") {
            data.name = field.thirdPointName;
            data.id = $(".sign").attr("signid");
            param.url = 'pc/resource/point/update';
            if($(".pid").attr("level")=='1'){
                data.name = field.firstPointName;
                data.level = "1";
            }else if($(".pid").attr("level")=='2'){
                //二级点位保存名称
                data.name = field.secondPointName;
                data.level = "2";
            }else if($(".pid").attr("level")=='3'){
                //三级点位保存名称
                data.name = field.thirdPointName;
                data.level = "3";
            }

        } else {

            if($(".pid").attr("level")=='1'){
                data.name = field.firstPointName;
            }else if($(".pid").attr("level")=='2'){
                //二级点位保存名称
                data.name = field.secondPointName;
            }else if($(".pid").attr("level")=='3'){
                //三级点位保存名称
                data.name = field.thirdPointName;
            }
            data.parentId = $(".pid").attr("pid");
            data.level =  $(".pid").attr("level");
            param.url = 'pc/resource/point/save';
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
