layui.use(['form', 'layer', 'table', 'jquery', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var powerArr = sessionStorage.getItem('powerArr') ? JSON.parse(sessionStorage.getItem('powerArr')) : [];
    var logToken = sessionStorage.getItem('logToken')
    var isUpdate = false;
    for (var i = 0; i < powerArr.length; i++) {
        if (powerArr[i] == '/pc/business/save') {
            $('.addShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/business/update') {
            isUpdate = true;
        }
        if (powerArr[i] == '/pc/business/delete') {
            $('.delShow').css('display', 'inline-block');
        }
        if (powerArr[i] == '/pc/business/exportIn') {
            $('.importShow').css('display', 'inline-block');
        }
    }

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //数据表格初始化
    tableInit(table, 'pc/business/select', [[
        {type: "checkbox", fixed: "left", width: 50},
        {
            field: 'level', title: "商家等级", align: 'center', templet: function (d) {
                return levelEnum.properties[d.level].name;
            }
        },
        {field: 'name', title: "商家名称", align: 'center'},
        {field: 'contact', title: "联系人", align: 'center' },
        {field: 'contactPhone', title: "联系方式", align: 'center'},
        {
            field: 'supplierType', title: "是否合格供应商", align: 'center', templet: function (d) {
                return heGeEnum.properties[d.supplierType].name;
            }
        },
        {field: 'heTongNum', title: "合同数量", align: 'center'},
        {field: 'amount', title: "合同金额（元）", align: 'center'},
        {
            title: "操作", width: 250, align: "center", templet: function (d) {
                var html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row-a" style="width: 80px" lay-event="htInfo">已签约合同</a>' +
                                '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="mEvaluation">商家评估</a>';
                if (d.allCity=="1" || (d.editCity=="1"&&d.allCity=="0")) {
                    html = '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row" lay-event="edit">编辑</a>' + html;
                }
                return html;
            }
        }
    ]]);

    param.url = 'pc/centralcity/selectCentralCityByCitys';//中心城市接口
    ajaxJS(param, {}, function (d) {
        var data = d.data;
        $('.centralCityId').append('<option value="">请选择中心城市</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].centralCityName + '</option>';
            $('.centralCityId').append(str1)
        }
        $('.centralCityId').val($('.centralCityId').attr('val'));
        form.render();

    });

    //城市地址联动
    form.on('select(centralCityId)', function(data){
        param.url = 'pc/citymanage/select';//对应的权限下的城市列表
        var centralCityId = $('.centralCityId').val();
        ajaxJS(param, {centralCityId:centralCityId}, function (d) {
            var data = d.data.list;
            $('.cityId').empty();
            $('.cityId').append('<option value="">请选择城市</option>');
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
        var city;
        if($("select[name='centralCityId']").val()){
            if($("select[name='cityId']").val()){
                //不处理
            }else{
                layer.msg("请选择落地城市")
                return false;
            }
        }

        search($, table, form, {
            level: $('.level').val(),//商家等级（1全国 2城市 3项目）
            name: $('.name').val(),//商家名称
            contact: $('.contact').val(),//联系人
            contactPhone: $('.contactPhone').val(),//联系方式
            supplierType: $('.supplierType').val(),//是否合格供应商（0不合格 1合格）
            city: $("select[name='cityId']").val()//城市（市）
        });
    });

    $(".add_btn").click(function () {
        addOrEdit("html/business_setup/merchantManage/merchantManageAdd.html", "新增商家");
    });

    //已签约合同
    function contractedInfo(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["1050px", "550px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);

                body.find(".merchantId").attr("merchantId", edit.id).attr("level",edit.level);
                body.find(".cCode").text( edit.code);
                body.find(".mName").text(edit.name);
                form.render();
            }
        })
    }


    //添加和编辑
    function addOrEdit(url, title, edit) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["850px", "650px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                if (edit) {
                    var detailedAddress = edit.conpanyAddress.split(",");
                    var city = edit.cityId;
                    body.find(".sign").val("edit").attr("signid", edit.id);
                    body.find(".code").val(edit.code);
                    body.find(".name").val(edit.name);
                    body.find(".contact").val(edit.contact);
                    body.find(".contactPhone").val(edit.contactPhone);
                    body.find(".contact2").val(edit.contact2);
                    body.find(".contactPhone2").val(edit.contactPhone2);
                    body.find(".cProvince").attr("val",detailedAddress[0]);
                    body.find(".cCity").attr("val",detailedAddress[1]);
                    body.find(".cCountry").attr("val",detailedAddress[2]);
                    body.find(".detailedAddress").val(edit.detailedAddress);
                    body.find(".centralCityId").attr("val",edit.centralCityId);
                    body.find(".cityId").attr("val",edit.cityId);
                    body.find(".switchsupplier").val(edit.supplierType);
                    if(edit.supplierType == 0){
                        body.find(".switchsupplier").prop('checked', false);
                    }
                    body.find(".level").val(edit.level);
                    body.find(".remarks").val(edit.remarks);
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
            layer.confirm("是否删除当前选择的商家", {
                icon: 3,
                title: "提示信息",
                btnAlign: 'c'
            }, function (index) {
                param.url = 'pc/business/delete';
                ajaxJS(param, {ids: idArr}, function (d) {
                    location.reload();
                    layer.close(index);
                })
            })
        } else {
            layer.msg("请选择需要删除的商家");
        }
    });

    //数据表格操作按钮
    table.on('tool(tableList)', function (obj) {
        var layEvent = obj.event, data = obj.data;

        if (layEvent === 'edit') { //编辑
            addOrEdit("html/business_setup/merchantManage/merchantManageAdd.html", "编辑商家", data);
        }
        if (layEvent === 'htInfo') { // 已签约合同
            data.type="htInfo";
            contractedInfo("html/business_setup/merchantManage/contractInfo.html", "已签约合同", data);
        }
        if (layEvent === 'mEvaluation') { //商家评估
            data.type="mEvaluation";
            contractedInfo("html/business_setup/merchantManage/mEvaluation.html", "商家评估", data);
        }
    });

    //导入
    upload.render({
        elem: '#exportIn_btn'
        ,url: interfaceUrl+'pc/business/exportIn'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls'
        ,done: function(res){
            if(res.code=="403"){
                var split = res.message.split(",");
                var mes = "";
                for (var i=0;i<split.length;i++) {
                    mes = mes+split[i]+'<br>';
                }
                layer.msg(mes, {
                    time: 20000 //20s后自动关闭
                    ,btnAlign: 'c' //按钮居中
                    ,btn: ['确定']
                });
            }else{
                top.layer.msg(res.message);
                location.reload();
            }

        }
    });

    //导出
    $(".export_btn").on("click", function () {
        var city = "";
        if($("select[name='mProvince']").val()!=undefined){
            city = $("select[name='mProvince']").val();
        }
        if($("select[name='mCity']").val()!=undefined){
            city +=","+$("select[name='mCity']").val();
        }
        var form = $('<form>');
        form.attr('style', 'display:none');
        form.attr('target', '');
        form.attr('method', 'post');
        form.attr('action', interfaceUrl + 'pc/business/exportOut');
        form.append('<input type="hidden" name="token" value="' + logToken + '" />');
        form.append('<input type="hidden" name="level" value="' + $(".level").val() + '" />');
        form.append('<input type="hidden" name="name" value="' + $(".name").val() + '" />');
        form.append('<input type="hidden" name="contact" value="' + $(".contact").val() + '" />');
        form.append('<input type="hidden" name="contactPhone" value="' + $(".contactPhone").val() + '" />');
        form.append('<input type="hidden" name="supplierType" value="' + $(".supplierType").val() + '" />');
        form.append('<input type="hidden" name="city" value="' + city + '" />');//落地城市
        $('body').append(form);
        form.submit()
    });

});

function goLogin() {
    parent.goLogin()
}
