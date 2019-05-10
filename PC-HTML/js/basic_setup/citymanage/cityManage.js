layui.use(['form', 'layer', 'table', 'jquery'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/citymanage/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/citymanage/update') {
            isUpdate = true;
        }
        // if (powerArr[i] == '/pc/citymanage/delete') {
        //     $('.delShow').css('display', 'inline-block');
        // }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/citymanage/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'centralCityName', title: "中心城市", align: 'center'},
        {field: 'cityName', title: "城市", align: 'center'},
        {field: 'cityLeaderName', title: "城市分公司负责人", align: 'center' },
        {field: 'departmentLeaderName', title: "经营部分负责人", align: 'center'},
        {field: 'enterPersonName', title: "录入人员", align: 'center'},
        {field: 'yearTarget', title: "年度指标（万元）", align: 'center'},
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

    param.url = 'pc/centralcity/select';//中心城市列表
    ajaxJS(param, {}, function (d) {
        var data = d.data.list;
        $('.centralCity').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].centralCityName + '</option>';
            $('.centralCity').append(str1)
        }
        $('.centralCity').val($('.centralCity').attr('val'));
        form.render();
    });

    param.url = 'pc/centralcity/centraleader';//城市分公司负责人
    ajaxJS(param, {roleId:"4"}, function (d) {
        var data = d.data;
        $('.cityLeaderName').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
            $('.cityLeaderName').append(str1)
        }
        $('.cityLeaderName').val($('.cityLeaderName').attr('val'));
        form.render();
    });

    // param.url = 'pc/city/centraleader';//经营部门负责人
    ajaxJS(param, {roleId:"3"}, function (d) {
        var data = d.data;
        $('.departmentLeaderName').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
            $('.departmentLeaderName').append(str1)
        }
        $('.departmentLeaderName').val($('.departmentLeaderName').attr('val'));
        form.render();
    });
    // param.url = 'pc/city/centraleader';//录入人员集合
    ajaxJS(param, {roleId:"2"}, function (d) {
        var data = d.data;
        $('.enterPersonName').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].empId + '">' + data[i].empName + '</option>';
            $('.enterPersonName').append(str1)
        }
        $('.enterPersonName').val($('.enterPersonName').attr('val'));
        form.render();
    });

    //搜索
    $(".search_btn").on("click", function () {
        var enterPersonIds = $('.enterPersonName').val();
        search($, table, form, {
            centralCityId: $('.centralCity').val(),//中心城市
            cityName: $('.city').val(),//城市名称
            cityPrincipal: $('.cityLeaderName').val(),//城市分公司负责人
            businessPrincipal: $('.departmentLeaderName').val(),//经营部门负责人
            enterPerson: $('.enterPersonName').val(),//录入人员
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/basic_setup/citymanage/cityManageAdd.html", "新增城市");
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
                    body.find(".centralCityName").attr("val",edit.centralCityId);
                    body.find(".cityLeaderName").attr("val",edit.cityPrincipal);
                    body.find(".departmentLeaderName").attr("val",edit.businessPrincipal);
                    body.find(".enterPersonIds").attr("val",edit.enterPersonIds);
                    body.find(".cityName").val(edit.cityName);
                    body.find(".yearTarget").val(edit.yearTarget);
                    body.find(".layui-badge-dot").css('background-color', '#ff5722');
                    if(edit.type=="detail"){
                        // body.find(".centralCityLeaders").attr("disabled",true);
                        body.find(".sign").val("detail").attr("signid", edit.id);
                        body.find(".centralCityName").attr("disabled","disabled");
                        body.find(".cityLeaderName").attr("disabled","true");
                        body.find(".departmentLeaderName").attr("disabled",true);
                        body.find(".cityName").attr('disabled', true);
                        body.find(".yearTarget").attr('disabled', true);
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
                param.url = 'pc/citymanage/delete';
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
            addOrEdit("html/basic_setup/citymanage/cityManageAdd.html", "编辑城市", data);
        }
        if (layEvent === 'detail') { //查看查看
            data.type="detail";
            addOrEdit("html/basic_setup/citymanage/cityManageAdd.html", "城市详细", data);
        }
    });
});

function goLogin() {
    parent.goLogin()
}
