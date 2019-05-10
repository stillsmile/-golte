layui.use(['form', 'layer','table', 'jquery'], function () {
    var form = layui.form, $ = layui.jquery,table = layui.table,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };


    loadInitTable();
    function loadInitTable(){
        var numberPointKeyup = "this.value=this.value.replace(/[^\\d.]/g,'');this.value=this.value.replace(/\\.{2,}/g,'.');this.value=this.value.replace('.','$#$').replace(/\\./g,'').replace('$#$','.');this.value=this.value.replace(/^(\\-)*(\\d+)\\.(\\d\\d).*$/,'$1$2.$3')";
        var tr = '<tr class="specTR"><input type="hidden" class="specPropNames" value="' + 1 + '"/>';
        // tr += '<td><input type="text" class="layui-input buying_price"></td>';//
        tr += '<td><input type="text" class="layui-input assetsCode title-center" placeholder="请输入资产编号" maxlength="60" lay-verify="required"></td>';//
        tr += '<td><input type="text" class="layui-input assetsName title-center" placeholder="请输入资产名称" maxlength="60" lay-verify="required"></td>';//
        tr += '<td><input placeholder="请输入资产规模" onafterpaste="' + numberPointKeyup + '" type="text" maxlength="30" class="layui-input assetsUnit title-center"></td>';//
        tr += '<td><a class="layui-btn layui-btn-normal layui-btn-xs delSpec">' + ("删除" || "删除") + '</a></td>';
        tr += '</tr>';
        $(".specBody").append(tr);
    }

    param.url = 'pc/resource/project/selectByCityIds';//对权限城市下的资源项目
    ajaxJS(param, {}, function (d) {
        var data = d.data.list;
        $('.projectName').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
            $('.projectName').append(str1)
        }
        $('.projectName').val($('.projectName').attr('val'));
        form.render();
    });



    //点位数据加载
    param.url = 'pc/resource/point/selectBypid';//资源点位列表
    ajaxJS(param, {parentId:0}, function (d) {
        var data = d.data;
        $('.oneDom').empty();
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
            item.val(item.attr('val'));
            form.render();
        });
    }

    $('.add_btn_row').click(function () {
        loadInitTable();
    });

    var deleteSpecs = [];
    //规格明细删除
    $('.specList').delegate('.delSpec', 'click', function () {
        if ($(".specTR").length <= 1) {
            layer.msg("至少保留一个条资产明细" || "至少保留一个条资产明细");
            return;
        }
        $(this).parents('tr').remove();
        if ($(".sign").val() == 'edit') {
            if ($(this).parents('tr').find(".specPath").val()) {
                var specPaths = $(this).parents('tr').find(".specPath").val().split(",");
            }
            var noDeletePath = [];
            $(".specTR").each(function () {
                if ($(this).find(".specPath").val()) {
                    var paths = $(this).find(".specPath").val().split(",");
                }
                for (var i in paths) {
                    noDeletePath.push(paths[i]);
                }
            });
            for (var j in specPaths) {
                if ($.inArray(specPaths[j], noDeletePath) < 0) {
                    deleteSpecs.push(specPaths[j]);
                }
            }
        }
    });

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var rProjectDetailList=[];
        $(".specTR").each(function () {
            rProjectDetailList.push({
                projectId : field.projectName,//项目Id
                pointId : field.ThrDom,//三级点位Id
                assetsCode : $(this).find(".assetsCode").val(),
                assetsName : $(this).find(".assetsName").val(),
                assetsUnit : $(this).find(".assetsUnit").val(),
            });
        });

        var data = {
            centralCityId: field.centralCityId,
            cityId: field.cityId,
            rProjectDetailList: rProjectDetailList
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/resource/project/detail/update';
        } else {
            if(field.ThrDom==undefined||field.ThrDom==""){
                layer.alert('请选择三级级点位！', {
                    icon: 5,
                    title: "提示"
                });
                return;
            }
            param.url = 'pc/resource/project/detail/save';
        }

        ajaxJS(param, data, function (d) {
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
        });
        return false;
    });
    $('.cancel').click(function () {
        layer.closeAll("iframe");
    });

    $('#pointAdd').click(function () {

        var parentId = $('.TwoDom').val();
        if( parentId!=undefined&&parentId !="" ){
            $(this).hide();
            $('.pointBt').css('display', 'inline-block');
        }else{
            layer.alert('请选择二级点位！', {
                icon: 5,
                title: "提示"
            });
        }
    });

    $('#pointBtSub').click(function () {
       var  thrPointName = $(".thrPointId").val();
       if(!thrPointName){
           layer.msg("请输入三级点位");
           return;
       }
        var data = {
            name: $(".thrPointId").val(),
            parentId: $(".TwoDom").val()};
        data.level = '3';//固定添加三级点位
        param.url = 'pc/resource/point/save';
        ajaxJS(param, data, function (d) {
            top.layer.msg(d.message);

            $('.pointBt').css('display', 'none');
            var parentId = $('.TwoDom').val();
            $('.ThrDom').empty();

            //添加成功后数据重新加载列表
            param.url = 'pc/resource/point/selectBypid';//资源点位列表
            loadPointDom($('.ThrDom'),parentId);
            $('#pointAdd').show();
            $('.thrPointId').val("");
            form.render('select');
        });
    });
});
