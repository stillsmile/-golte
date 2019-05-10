layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    // $('.roleStatus').html(
    //     '<input type="radio" name="roleStatus" value="'+enableEnum.VALID+'" title="'+enableEnum.properties[enableEnum.VALID].name+'" checked>' +
    //     '<input type="radio" name="roleStatus" value="'+enableEnum.INVALID+'" title="'+enableEnum.properties[enableEnum.INVALID].name+'">'
    // );
    //
    // setTimeout(function () {
    //     $(".roleStatus input[value=" + $('.roleStatus').attr('rolestatus') + "]").attr("checked", "checked");
    //     form.render();
    // },100)


    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        if(field.roleDes.length>100){
            top.layer.msg("角色描述内容过长");
            return false;
        }
        //弹出loading
        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var data = {
            roleName: field.roleName,
            roleDes: field.roleDes,
            roleSortValue: field.roleSortValue,
        };
        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/role/update';
        } else {
            param.url = 'pc/role/save';
        }
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();

        })
        return false;
    });

    $(".cancel").click(function () {
        layer.closeAll("iframe");
    });
});
