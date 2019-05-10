layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    var isGrant = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/role/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/role/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/role/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/role/authorization') {
            isGrant = true;
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    // $('.roleStatusRadio').html(
    //     '<input type="radio" name="roleStatus" lay-filter="roleStatus" value="'+enableEnum.VALID+'" title="'+enableEnum.properties[enableEnum.VALID].name+'">' +
    //     '<input type="radio" name="roleStatus" lay-filter="roleStatus" value="'+enableEnum.INVALID+'" title="'+enableEnum.properties[enableEnum.INVALID].name+'">'
    // );
    // form.render();
    var initRole = [1,2,3,4,5,6,7];

    //渲染数据表格
    tableInit(table, 'pc/role/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'roleName', title: "角色名称", align: 'center'},
        {field: 'roleSortValue', title: "排序号", align: 'center'},
        {field: 'roleDes', title: "角色描述", align: 'center'},
        {
            title: "操作", width: 150, fixed: "right", align: "center", templet: function () {
                var html ='';
                if (isUpdate) {
                    html += '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>';
                }
                if (isGrant) {
                    html += '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="grant">授权</a>';
                }
                return html;
            }
        }
    ]]);

    // var roleStatus;
    // form.on('radio(roleStatus)', function (data) {
    //     roleStatus = data.value;
    // });

    //搜索
    $(".search_btn").on("click", function () {
        search($, table,form, {
            roleName: $(".roleName").val()
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/system_setup/role/roleAdd.html", "新增角色");
    });

    //添加和编辑角色
    function addOrEdit(url, title, edit) {  //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["750px", "385px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".roleName").val(edit.roleName);
                    if ($.inArray(edit.id, initRole) >=0) {
                        body.find(".roleName").attr("disabled","disabled");
                    }
                    body.find(".roleDes").val(edit.roleDes);
                    body.find(".roleSortValue").val(edit.roleSortValue);
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
            layer.confirm("是否删除所选角色", {icon: 3, title: "提示信息", btnAlign :'c'}, function (index) {
                param.url = 'pc/role/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    layer.msg(d.message);
                    location.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的角色");
        }
    });

    //列表操作
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/system_setup/role/roleAdd.html", "编辑角色", data);
        } else if (layEvent === 'grant') {
            var index = layer.open({
                title: "授权",
                type: 2,
                area: ["750px", "500px"],
                resize: false,
                content: "html/system_setup/role/roleGrant.html?roleId=" + data.id,
                success: function (layero, index) {
                    var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                    body.find(".grant_title span").html(data.roleName);
                }
            })
        }
    });
});

function goLogin() {
    parent.goLogin()
}
