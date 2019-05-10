layui.use(['form', 'layer', 'table', 'jquery','laydate'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery, laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/resource/project/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/resource/project/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/resource/project/delete') {
            $('.delShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/resource/project/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'cityName', title: "城市", align: 'center'},
        {field: 'projectName', title: "项目", align: 'center'},
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

    // laydate.render({
    //     elem: '#starttime'
    // });
    // laydate.render({
    //     elem: '#endtime'
    // });
    var nowTime = new Date().valueOf();
    var max = null;
    var start = laydate.render({
        elem: '#starttime',
        type: 'date',
        btns: ['clear', 'confirm'],
        trigger: 'click',
        done: function(value, date){
            endMax = end.config.max;
            end.config.min = date;
            end.config.min.month = date.month -1;
        }
    });
    var end = laydate.render({
        elem: '#endtime',
        trigger: 'click',
        type: 'date',
            done: function(value, date){
            if($.trim(value) == ''){
                var curDate = new Date();
                date = {'date': curDate.getDate(), 'month': curDate.getMonth()+1, 'year': curDate.getFullYear()};
            }
            start.config.max = date;
            start.config.max.month = date.month -1;
        }
    });

    param.url = 'pc/centralcity/select';//中心城市列表
    ajaxJS(param, {}, function (d) {
        var data = d.data.list;
        $('.centralCityId').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].centralCityName + '</option>';
            $('.centralCityId').append(str1)
        }
        $('.centralCityId').val($('.centralCityId').attr('val'));
        form.render();
    });

    //中心城市联动城市信息
    form.on('select(centralCityId)', function(data){
        param.url = 'pc/citymanage/select';//对应的权限下的城市列表
        var centralCityId = $('.centralCityId').val();
        ajaxJS(param, {centralCityId:centralCityId}, function (d) {
            var data = d.data.list;
            $('.cityId').empty();
            $('.cityId').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].id + '">' + data[i].cityName + '</option>';
                $('.cityId').append(str1)
            }
            $('.cityId').val($('.cityId').attr('val'));
            form.render();
        });
        form.render('select');
    });


    //搜索
    $(".search_btn").on("click", function () {
        var enterPersonIds = $('.enterPersonName').val();
        search($, table, form, {
            centralCityId: $('.centralCityId').val(),//中心城市
            cityId: $('.cityId').val(),//城市名称
            projectName: $('.projectName').val(),//项目名称
            createName: $('.createName').val(),//经营部门负责人
            starttime: $('#starttime').val(),//创建时间
            endtime: $('#endtime').val(),//结束时间
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/resource_setup/project/resourceProjectAdd.html", "新增项目");
    });

    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["700px", "500px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".centralCityId").attr("val",edit.centralCityId);
                    body.find(".cityId").attr("val",edit.cityId);
                    body.find(".projectName").val(edit.projectName);
                    body.find(".layui-badge-dot").css('background-color', '#ff5722');
                    if(edit.type=="detail"){
                        // body.find(".centralCityLeaders").attr("disabled",true);
                        body.find(".sign").val("detail").attr("signid", edit.id);
                        body.find(".projectName").attr("disabled",true);
                        body.find(".centralCityId").attr('disabled', true);
                        body.find(".cityId").attr('disabled', true);

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
            layer.confirm("是否删除当前选择的项目", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/resource/project/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.msg(d.message);
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的项目");
        }
    });

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/resource_setup/project/resourceProjectAdd.html", "编辑项目", data);
        }
        if (layEvent === 'detail') { //查看查看
            data.type="detail";
            addOrEdit("html/resource_setup/project/resourceProjectAdd.html", "项目详细", data);
        }
    });
});

function goLogin() {
    parent.goLogin()
}
