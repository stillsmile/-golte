layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/centralcity/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/centralcity/update') {
            isUpdate = true;
        }
        // if (powerArr[i] == '/pc/centralcity/delete') {
        //     $('.delShow').css('display', 'inline-block');
        // }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/centralcity/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'centralCityName', title: "中心城市", align: 'center'},
        {field: 'empName', title: "中心城市负责人", align: 'center'},
        {field: 'createName', title: "创建人", align: 'center' },
        {field: 'createTime', title: "创建时间", align: 'center'},
        {
            title: "操作", width: 150, align: "center", templet: function () {
                var html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="detail">查看</a>';
                if (isUpdate) {
                    html += '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>';
                }
                return html;
            }
        }
    ]]);

    param.url = 'pc/centralcity/centraleader';
    ajaxJS(param, {roleId:"5"}, function (d) {
        var data = d.data;
        $('.centraLeader').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
            $('.centraLeader').append(str1)
        }
        $('.centraLeader').val($('.centraLeader').attr('val'));
        form.render();
    });

    //搜索
    $(".search_btn").on("click", function () {
        search($, table, form, {
            centralCityName: $('.centralCity').val(),
            empId: $('.centraLeader').val()
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/basic_setup/centralcity/centralCityAdd.html", "新增中心城市");
    });

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
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".centralCityName").val(edit.centralCityName);//attr('disabled', 'true')
                    body.find(".centralCityLeaders").attr('empId', edit.empId);
                    body.find(".layui-badge-dot").css('background-color', '#ff5722');
                    if(edit.type=="detail"){
                        body.find(".centralCityLeaders").attr("disabled",true);
                        body.find(".modleBtn").css('display', 'none');
                        body.find(".layui-badge-dot").css('background-color', '#fff');
                    }
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
            layer.confirm("是否删除当前选择的城市", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/centralcity/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的城市");
        }
    });

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/basic_setup/centralcity/centralCityAdd.html", "编辑中心城市", data);
        }
        if (layEvent === 'detail') { //查看查看
            data.type="detail";
            addOrEdit("html/basic_setup/centralcity/centralCityAdd.html", "查看中心城市", data);
        }
    });
});

function goLogin() {
    parent.goLogin()
}
