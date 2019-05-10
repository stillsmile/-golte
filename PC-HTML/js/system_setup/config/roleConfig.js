layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/config/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/config/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/config/update') {
            isUpdate = true;
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };


    //数据表格初始化
    tableInit(table, 'pc/config/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'type', title: "激励类型", align: 'center'},
        {field: 'roleName', title: "激励角色", align: 'center'},
        {field: 'perCent', title: "激励百分比(%)", align: 'center'},
        {
            title: "操作", width: 100, align: "center", templet: function () {
                if (isUpdate) {
                    return '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>';
                }
            }
        }
    ]], {});


    // //搜索
    // $(".search_btn").on("click", function () {
    //     search($, table, form, {
    //         empAccount: $(".empAccount").val(),
    //         empName: $(".empName").val()
    //     });
    // });

    $(".add_btn").click(function () {
        addOrEdit("html/system_setup/config/roleConfigAdd.html", "新增角色");
    });


    //删除
    $(".delAll_btn").click(function () {
        var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [],typeIds = [];
        if (data.length > 0) {
            for (var i in data) {
                idArr.push(data[i].roleNameId);
            }
            for (var i in data) {
                typeIds.push(data[i].typeId);
            }
            layer.confirm("是否删除当前选择的激励角色", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/config/delete';
                ajaxJS(param, {ids: idArr,typeIds:typeIds}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的激励角色");
        }
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
                    body.find(".typeId").val(edit.typeId);
                    body.find(".roleNameId").val(edit.roleNameId);
                    body.find(".typeValue").val(edit.typeValue).attr('disabled', 'true');
                    body.find(".roleName").val(edit.roleName).attr('disabled', 'true');
                    body.find(".perCent").val(edit.perCent);
                    form.render();
                }
            }
        })
    }

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;
        if (layEvent === 'edit') { //编辑
            addOrEdit("html/system_setup/config/roleConfigAdd.html", "编辑角色", data);
        }
    });

});

function goLogin() {
    parent.goLogin()
}
