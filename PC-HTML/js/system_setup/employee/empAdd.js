layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };
    var formSelects = layui.formSelects;
    // $('.empStatus').html(
    //     '<input type="radio" name="empStatus" value="'+enableEnum.VALID+'" title="'+enableEnum.properties[enableEnum.VALID].name+'" checked>' +
    //     '<input type="radio" name="empStatus" value="'+enableEnum.INVALID+'" title="'+enableEnum.properties[enableEnum.INVALID].name+'">'
    // );
    //
    // setTimeout(function () {
    //
    //     $(".empStatus input[value=" + $('.empStatus').attr('empstatus') + "]").prop("checked", "checked");
    //     form.render()
    // }, 100)


    setTimeout(function () {

        param.url = 'pc/role/all/select';
        ajaxJS(param, {}, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].roleName + '</option>';
                $('.roleIds').append(str)
            }
            var values = [];
            if ($('.roleIds').attr('val')) {
                values = $('.roleIds').attr('val').split(',');
            }
            formSelects.render('multiRole', {
                skin: 'primary',
                radio: false,
                init: values
            });
            $('.xm-input').attr('placeholder', values.length > 0 ? '' :"请选择角色");
            form.render();
        });

    }, 50)


    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});
        var roleIds = field.roleIds.split(",");
        var data = {
            empAccount: field.empAccount,
            empName: field.empName,
            empJobTitle: field.empJobTitle,
            empSortValue: field.empSortValue,
            empEmail: field.empEmail,
            roleIds: roleIds,
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/employee/update';
        } else {
            param.url = 'pc/employee/save';
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
