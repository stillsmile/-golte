layui.use(['form', 'layer', "jquery"], function () {
    var form = layui.form,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    sessionStorage.setItem('loginUrl', location.href);
    var loginOK = true;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post'
    };
    code();
    var verifyCode;
    function code() {
        param.url = 'common/picture/verificationCode';
        ajaxJS(param, {}, function (d) {
            verifyCode = d.data.code;
            $('#imgCode img').attr('src', 'data:image/jpeg;base64,' + d.data.base64)
        });
    }

    $('#codeClick').click(function (e) {
        code()
    });

    //登录按钮
    $(".login").click(function (data) {
        var data = {
            loginAccount: $("#loginAccount").val(),
            password: $("#password").val()
        };
        checkInput(data)
    });

    //键盘回车事件
    $(document).keydown(function (e) {
        var data = {
            loginAccount: $("#loginAccount").val(),
            password: $("#password").val()
        };
        if (e.keyCode == 13) {
            checkInput(data)
        }
    });


    //封装登录方法
    function login(data) {
        $.ajax({
            url: interfaceUrl + 'pc/login',
            type: 'post',
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            crossDomain: true == !(document.all),
            beforeSend: function (request) {
                request.setRequestHeader("X-Token", logToken);
                if ($(".rememberPwd")[0].checked == true) {
                    localStorage.setItem("rememberPwd", true);
                    localStorage.setItem("realPwd",$("#password").val() );
                    localStorage.setItem('realAccount',$("#loginAccount").val());
                } else {
                    localStorage.removeItem("rememberPwd");
                }
            },
            success: function (d) {
                if (d.code === 200) {
                    sessionStorage.setItem("loginName", d.data.loginName);
                    sessionStorage.setItem("empId", d.data.empId);
                    sessionStorage.setItem("logToken", d.data.token);
                    sessionStorage.setItem("powerArr", JSON.stringify(d.data.resourceList || []));
                    setTimeout(function () {
                        window.location.href = "index.html";
                    }, 200);
                    if (loginOK) {
                        layer.msg(d.message);
                        loginOK = false;
                    }
                } else {
                    layer.msg(d.message);
                }
                $('.login').removeAttr("disabled").css("background-color", "#08ACD7");
                form.render();
            }
        });

    }

    //验证输入框信息
    function checkInput(data) {
        if ($("#loginAccount").val() == "") {
            layer.msg("账号不能为空！", {icon: 5});
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
            return;
        }
        if ($("#password").val() == "") {
            layer.msg("密码不能为空！", {icon: 5});
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
            return;
        }
        if ($("#code").val() == "") {
            layer.msg("请输入验证码", {icon: 5});
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
            return;
        }
        if ($("#code").val().toUpperCase() != verifyCode.toUpperCase()) {
            layer.msg("验证码错误", {icon: 5});
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
            code();
            return;
        }
        $('.login').attr("disabled","disabled").css("background-color", "#b2b2b2");
        form.render();
        login(data)
    }

    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    });
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    });
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
});
var chars = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];

function generateMixed(n) {
    var res = "";
    for (var i = 0; i < n; i++) {
        var id = Math.ceil(Math.random() * 35);
        res += chars[id];
    }
    return res;
}

$(document).ready(function () {
    if (localStorage.getItem("rememberPwd") != "true") {
        setTimeout(function () {
            $("#loginAccount").val("");
            $("#password").val("");
        }, 90);
    } else {
        $(".rememberPwd").attr("checked", true);
        $("#loginAccount").val(localStorage.getItem('realAccount'))
        $("#password").val(localStorage.getItem('realPwd'))
        // $("*").addClass("layui-input-focus");
        $("*").addClass("layui-input-active");
    }
});