layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;


    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/employee/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/employee/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/employee/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/employee/resetPassword') {
            $('.resetShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/employee/enable') {
            $('.enableShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/employee/disable') {
            $('.disableShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    // $('.empStatusRadio').html(
    //     '<input type="radio" name="empStatus" lay-filter="empStatus" value="'+enableEnum.VALID+'" title="'+enableEnum.properties[enableEnum.VALID].name+'">' +
    //     '<input type="radio" name="empStatus" lay-filter="empStatus" value="'+enableEnum.INVALID+'" title="'+enableEnum.properties[enableEnum.INVALID].name+'">');
    // form.render();

    //数据表格初始化
    tableInit(table, 'pc/employee/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'empAccount', title: "账号", align: 'center'},
        {field: 'empName', title: "姓名", align: 'center'},
        {field: 'empJobTitle', title: "职称", align: 'center'},
        {
            field: 'empStatus', title: "状态", align: 'center', templet: function (d) {
                return enableEnum.properties[d.empStatus].name;
            }
        },
        {field: 'roleNames', title: "角色", align: 'center'},
        {
            title: "操作", width: 100, align: "center", templet: function () {
                if (isUpdate) {
                    return '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>';
                }
            }
        }
    ]], {}, tableAfter);

    function tableAfter() {
        LayUIDataTable.SetJqueryObj($);// 第一步：设置jQuery对象
        var currentRowDataList = LayUIDataTable.ParseDataTable();

        // 对相关数据进行判断处理--此处对【竞猜数量】大于30的进行高亮显示
        $.each(currentRowDataList, function (index, obj) {
            if (obj['empStatus'] && obj['empStatus'].value == enableEnum.properties[enableEnum.INVALID].name) {
                obj['empStatus'].row.css("color", "#808080");
            }
        })
    }

    // var empStatus;
    // form.on('radio(empStatus)', function (data) {
    //     empStatus = data.value;
    // });

    //搜索
    $(".search_btn").on("click", function () {
        search($, table, form, {
            empAccount: $(".empAccount").val(),
            empName: $(".empName").val()
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/system_setup/employee/empAdd.html", "新增用户");
    });

    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["800px", "650px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".empName").val(edit.empName);
                    body.find(".empAccount").val(edit.empAccount).attr('disabled', 'true');
                    body.find(".empJobTitle").val(edit.empJobTitle);
                    body.find(".empSortValue").val(edit.empSortValue);
                    body.find(".empEmail").val(edit.empEmail);
                    body.find(".roleIds").attr('val', edit.roleIds);
                    form.render();
                }
            }
        })
    }

    //删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("是否删除当前选择的用户", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/employee/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的用户");
        }
    });

    //重置密码
    $(".resetPassword_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("您确认要对选中的账号重置密码吗？初始密码:123456", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/employee/resetPassword';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要重置密码的账号");
        }
    });

    //启用
    $(".enable_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("您确认要对选中的用户启用账号吗？", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/employee/enable';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要启用的账号");
        }
    });

    //禁用
    $(".disable_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].id);
            }
            layer.confirm("您确认要对选中的用户禁用账号吗？", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/employee/disable';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要禁用的账号");
        }
    });

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/system_setup/employee/empAdd.html", "编辑用户", data);
        }
    });
});

function goLogin() {
    parent.goLogin()
}
