layui.use(['form', 'layer', 'laydate', 'table', 'laytpl'], function () {
    var form = layui.form, $ = layui.jquery
    layer = parent.layer === undefined ? layui.layer : top.layer;


    var param = {
        jquery: $,
        layer: layer,
        url: 'pc/updatePwd',
        type: 'post',
    };

    $('.reset').click(function () {
        $('input[type="password"]').val('');
        return false
    });

    $('#username').val(sessionStorage.getItem('loginName'));

    $(document).keydown(function (e) {
        if (e.keyCode == 13) {
            return false;
        }
    });
    //添加验证规则
    form.verify({
        newPwd: function (value, item) {
            if (value.length < 6 || value.length > 20) {
                return "请保持密码长度在6到20位";
            }
        },
        confirmPwd: function (value, item) {
            if (!new RegExp($("input[name='newPwd']").val()).test(value)) {
                return "两次输入密码不一致，请重新输入！";
            }
        }
    });

    //修改密码
    form.on("submit(changePwd)", function (data) {
        var field = data.field;
        var index = layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.8});

        var data = {
            passwordOld: field.oldPwd,
            passwordNew: field.newPwd
        };
        ajaxJS(param, data, function (d) {
            layer.msg(d.message);
            setTimeout(function () {
                localStorage.setItem("realPwd", "");
                parent.location.href = sessionStorage.getItem('loginUrl');
            }, 500)
        });
        return false;
    });

})