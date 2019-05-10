layui.use(['form', 'layer', 'table', 'jquery','tree'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/resource/point/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/resource/point/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/resource/point/delete') {
            $('.delShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    treeInit();

    function treeInit() {
        param.url = "pc/resource/point/selecTree";
        ajaxJS(param, {}, function (d) {
            $('#treeview').treeview({
                data: d.data,
                levels: 1
            });
        });
    }


    form.render();

    //数据表格初始化
    tableInit(table, 'pc/resource/point/selecTable', [[
        // {type: "checkbox", fixed: "left", width: 50},
        {field: 'name', title: "三级目录", align: 'center'},
        {field: 'secondName', title: "二级目录", align: 'center'},
        {field: 'firstName', title: "一级目录", align: 'center' },
        {
            title: "操作", width: 150, align: "center", templet: function () {
                var html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>'+
                    '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="delete">删除</a>';

                return html;
            }
        }
    ]]);


    $(".add_btn").click(function () {
        addOrEdit("html/resource_setup/point/resourcePointAdd.html", "新增三级点位");
    });

    $('.treeview').click(function () {
        var node = $('#treeview').treeview('getSelected');

        if(node==""){
            return;
        }
        //搜索
        search($, table, form, {
            id: node[0].id,//点位Id
            level: node[0].level,//点位阶位
        });

    });

    $(".addPage").click(function () {
        nodeAddOrnodeEdit()
    });

    $(".addThirdPage").click(function () {
        ThirdNodeAdd()
    });

    $(".editPage").click(function () {
        var node = $('#treeview').treeview('getSelected');
        if (node.length == 0) {
            layer.msg("请选择节点");
            return;
        }

        nodeAddOrnodeEdit("edit");
    });


    $(".delPage").click(function () {
        del();
    });

    function nodeAddOrnodeEdit(edit) {
        var node = $('#treeview').treeview('getSelected');
        if (node.length == 1) {
            var index = layer.open({
                title: "添加节点",
                type: 2,
                area: ["750px", "580px"],
                content: "html/resource_setup/point/resourcePointAdd.html",
                success: function (layero, index) {
                    var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                    if (edit) {

                        body.find(".sign").val("edit").attr("signid", node[0].id);
                        body.find(".pointShow").remove();
                        if(node[0].level=='1'){
                            body.find(".pid").attr("pid", node[0].pid).attr("level",'1');
                            body.find(".firstPointName").val(node[0].name).attr("id",node[0].id);
                            body.find(".twoNode").remove();
                            body.find(".thirdNode").remove();
                        }
                        if(node[0].level=='2'){
                            body.find(".pid").attr("pid", node[0].pid).attr("level",'2');
                            body.find(".firstPointName").val(node[0].pname).attr("id",node[0].pid).attr("disabled",true);;
                            body.find(".secondPointName").val(node[0].name).attr("id",node[0].id);
                            body.find(".thirdNode").remove();
                        }

                        form.render();
                    } else {
                        if(node[0].level=='2'){
                            body.find(".pid").attr("pid", node[0].pid).attr("level",'2');
                            body.find(".firstPointName").val(node[0].pname).attr("disabled",true);
                        }else{
                            body.find(".pid").attr("pid", node[0].id).attr("level",'2');
                            body.find(".firstPointName").val(node[0].name).attr("disabled",true);
                        }
                        body.find(".secondPointName").attr("placeholder","请输入二级节点名称");
                        body.find(".thirdNode").remove();
                        form.render();
                    }
                }
            })
        } else {
            var index = layer.open({
                title: "添加节点",
                type: 2,
                area: ["750px", "580px"],
                content: "html/resource_setup/point/resourcePointAdd.html",
                success: function (layero, index) {
                    var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                    body.find(".pid").attr("pid", '0').attr("level",'1');
                    body.find(".firstPointName").attr("placeholder","请输入一级点位名称");
                    body.find(".twoNode").remove();
                    body.find(".thirdNode").remove();

                    form.render();
                }
            })
        }
    }

    //添加三级节点

    function ThirdNodeAdd(edit) {
        var node = $('#treeview').treeview('getSelected');
        if (node.length == 1 && node[0].level=='2') {
            var index = layer.open({
                title: "添加节点",
                type: 2,
                area: ["750px", "580px"],
                content: "html/resource_setup/point/resourcePointAdd.html",
                success: function (layero, index) {
                    var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);

                    body.find(".pid").attr("pid", node[0].id).attr("level",'3');
                    body.find(".firstPointName").val(node[0].pname).attr("disabled",true);
                    body.find(".secondPointName").val(node[0].name).attr("id",node[0].id).attr("disabled",true);
                    body.find(".thirdPointName").val("").attr("placeholder","请输入三级节点名称");
                    form.render();
                }
            })
        } else {
            layer.msg("请选择二级节点");
        }
    }

    // 删除操作
    function del() {
        var node = $('#treeview').treeview('getSelected');
        if (node.length == 1) {
            var pid = node[0].pid;
            // if (pid == "0" || pid == 0) {
            //     layer.msg("删除节点错误");
            //     return false;
            // }
            layer.confirm("是否删除当前选择的节点", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                delInfo();
            });
        } else {
            layer.msg("请选择需要删除的节点");
        }
    }

    // 删除请求
    function delInfo() {
        var node = $('#treeview').treeview('getSelected');
        var id = node[0].id;
        param.url = 'pc/resource/point/delete';
        ajaxJS(param, {ids: [id]}, function (d) {
            layer.closeAll('loading');
            layer.msg(d.message);
            treeInit();
        })
    }

    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["500px", "400px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {

                    body.find(".pid").attr("level",'3');
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".firstPointName").val(edit.firstName).attr("disabled",true);
                    body.find(".secondPointName").val(edit.secondName).attr("disabled",true);;
                    body.find(".thirdPointName").val(edit.name);
                    form.render();
                }
            }
        })
    }

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/resource_setup/point/resourcePointAdd.html", "编辑三级点位", data);
        }
        if (layEvent === 'delete') { //删除
            var checkStatus = table.checkStatus('tableList'), data = checkStatus.data, idArr = [];

            if (data){
                idArr.push(obj.data.id);

                layer.confirm("是否删除当前选择的三级点位", {
                    icon: 3,
                    title: "提示信息",
                    btnAlign: 'c'
                }, function (index) {
                    param.url = 'pc/resource/point/delete';
                    ajaxJS(param, {ids: idArr}, function (d) {
                        layer.msg(d.message);
                        location.reload();
                        layer.close(index);
                    })
                })
            }

            // if (data.length > 0) {
            //
            // } else {
            //     layer.msg("请选择需要删除的三级点位");
            // }
        }
    });
});

function goLogin() {
    parent.goLogin()
}
