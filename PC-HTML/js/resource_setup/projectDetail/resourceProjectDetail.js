layui.use(['form', 'layer', 'table', 'jquery','laydate', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery, laydate = layui.laydate,upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var logToken = sessionStorage.getItem('logToken');
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

        if (powerArr[i] == '/pc/resource/project/exportIn') {
            $('.importShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/resource/project/detail/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {field: 'cityName', title: "城市", align: 'center'},
        {field: 'projectName', title: "项目", align: 'center'},
        {field: 'zcName', title: "资产类型", align: 'center' },
        {field: 'assetsCode', title: "资源编号", align: 'center' },
        {field: 'assetsName', title: "资源名称", align: 'center' },
        {field: 'merchantName', title: "合作商", align: 'center' },
        {field: 'contractName', title: "合同名称", align: 'center' },
        {field: 'amount', title: "合同单价(元)", align: 'center' },
        {
            title: "操作", width: 150, align: "center", templet: function () {
                var html = '';
                // var html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="detail">查看</a>';
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

    param.url = 'pc/centralcity/selectCentralCityByCitys';//中心城市列表
    ajaxJS(param, {}, function (d) {
        var data = d.data;
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

    param.url = 'pc/resource/point/selectBypid';//资源点位列表
    ajaxJS(param, {parentId:0}, function (d) {
        var data = d.data;
        $('.oneDom').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
            $('.oneDom').append(str1)
        }
        $('.oneDom').val($('.oneDom').attr('val'));
        form.render();
    });

    //点位信息联动
    form.on('select(oneDom)', function(data){
        param.url = 'pc/resource/point/selectBypid';//资源点位列表
        var parentId = $('.oneDom').val();
        $('.TwoDom').empty();
        $('.ThrDom').empty();
        loadPointDom($('.TwoDom'),parentId);
        form.render('select');
    });
    //点位信息联动
    form.on('select(TwoDom)', function(data){
        param.url = 'pc/resource/point/selectBypid';//资源点位列表
        var parentId = $('.TwoDom').val();
        $('.ThrDom').empty();
        loadPointDom($('.ThrDom'),parentId);
        form.render('select');
    });

    function loadPointDom(item,parentId){
        ajaxJS(param, {parentId:parentId}, function (d) {
            var data = d.data;
            item.append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                item.append(str1)
            }
            form.render();
        });
    }


    //搜索
    $(".search_btn").on("click", function () {
        var level="";
        var firstPointId = $('.oneDom').val();
        var twoPointId = $('.TwoDom').val();
        var thrPointId = $('.ThrDom').val();
        var resourceId="";
        if(firstPointId){
            resourceId = firstPointId;
            level = "1";
            if(twoPointId){
                resourceId = twoPointId;
                level = "2";
                if(thrPointId){
                    resourceId = thrPointId;
                    level = "3";
                }
            }
        }

        search($, table, form, {
            centralCityId: $('.centralCityId').val(),//中心城市
            cityId: $('.cityId').val(),//城市名称
            projectName: $('.projectName').val(),//项目名称
            merchantName: $('.merchantName').val(),//商家名称
            contractName: $('.contractName').val(),//合同名称
            isSigned: $('.isSigned').val(),//是否签约
            // parentId: PIds,//资源点位父Id
            resourceId: resourceId,//资源点位Id
            level: level,//判断为几级点位
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/resource_setup/projectDetail/resourceProjectDetailAdd.html", "新增资源");
    });

    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["800px", "600px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".centralCityId").attr("val",edit.centralCityId);
                    body.find(".cityId").attr("val",edit.cityId);

                    body.find(".oneDom").attr("val",edit.firstPointId).attr('disabled', true);
                    body.find(".TwoDom").attr("val",edit.towPointId).attr('disabled', true);
                    body.find(".ThrDom").attr("val",edit.pointId).attr('disabled', true);
                    body.find("#pointAdd").remove();

                    body.find(".asCode").val(edit.assetsCode);
                    body.find(".asName").val(edit.assetsName);
                    body.find(".asUnit").val(edit.assetsUnit);

                    body.find(".projectName").val(edit.projectName).attr("val",edit.projectId).attr('disabled', true);

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
                param.url = 'pc/resource/project/detail/delete';
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
            addOrEdit("html/resource_setup/projectDetail/resourceProjectDetailEdit.html", "编辑明细", data);
        }
    });

    //导入
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'pc/resource/project/detail/exportIn'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls'
        ,done: function(res){
            top.layer.msg(res.message);
            location.reload();
        }
    });

    //导出
    $(".export_btn").on("click", function () {
        var PIds = "";
        var resourceId = "";
        var tempId = $('.oneDom').val();
        if(tempId!=""&&tempId!=undefined){
            PIds =tempId;
            tempId = $('.TwoDom').val();
            if(tempId!=""&&tempId!=undefined){
                PIds =tempId;
                tempId = $('.ThrDom').val();
                if(tempId!=""&&tempId!=undefined){
                    resourceId =tempId;
                    PIds ="";
                }
            }
        }

        var form = $('<form>');
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.append('<input type="hidden" name="token" value="' + logToken + '" />');
        form.attr('action', interfaceUrl + 'pc/resource/project/detail/exportOut');
        form.append('<input type="hidden" name="centralCityId" value="' + $(".centralCityId").val() + '" />');
        form.append('<input type="hidden" name="cityId" value="' + $(".cityId").val()==null?"":$(".cityId").val() + '" />');
        form.append('<input type="hidden" name="projectName" value="' + $(".projectName").val() + '" />');
        form.append('<input type="hidden" name="merchantName" value="' + $(".merchantName").val() + '" />');
        form.append('<input type="hidden" name="contractName" value="' + $(".contractName").val() + '" />');
        // form.append('<input type="hidden" name="deadlineStartTimeEnd" value="' + deadlineStartTimeEnd + '" />');//是否签约
        form.append('<input type="hidden" name="parentId" value="' + PIds + '" />');
        form.append('<input type="hidden" name="resourceId" value="' + resourceId + '" />');
        $('body').append(form);
        form.submit()
    });


});

function goLogin() {
    parent.goLogin()
}
