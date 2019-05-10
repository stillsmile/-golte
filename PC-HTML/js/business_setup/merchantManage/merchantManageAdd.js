layui.use(['form', 'layer', 'jquery', 'upload'], function () {
    var form = layui.form, $ = layui.jquery,upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    param.url = 'pc/business/arealist';//区域列表接口
    ajaxJS(param, {}, function (d) {
        var data = d.data;
        $('.cProvince').append('<option value="">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].areaNo + '">' + data[i].name + '</option>';
            $('.cProvince').append(str1);
        }
        $('.cProvince').val($('.cProvince').attr('val'));
        form.render();

        if($('.cProvince').val()!= null && $('.cProvince').val() != ""){
            param.url = 'pc/business/arealist';//区域数据集合
            loadCity(param,$('.cProvince').val(),$('.cCity'))
        }
    });
    //公司地址联动(省份选择变动)
    form.on('select(cProvince)', function(data){
        param.url = 'pc/business/arealist';//录入人员集合
        var fNo = data.value;
        ajaxJS(param, {fNo:fNo}, function (d) {
            var data = d.data;
            $('.cCity').empty();
            $('.cCountry').empty();
            $('.cCity').append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].areaNo + '">' + data[i].name + '</option>';
                $('.cCity').append(str1)
            }
            $('.cCity').val($('.cCity').attr('val'));
            form.render();
        });
        form.render('select');
    });
    //公司地址联动（市区选择变动）
    form.on('select(cCity)', function(data){
        var fNo = data.value;
        param.url = 'pc/business/arealist';//区域列表
        loadCountry(param,fNo,$('.cCountry'));
    });
    //商家落地城市(省份选择变动)
    form.on('select(mProvince)', function(data){
        var fNo = data.value;
        param.url = 'pc/business/arealist';//区域列表
        loadCity(param,fNo,$('.mCity'));
    });

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

        var centralCityId = $('.centralCityId').val();
      if(centralCityId){
          param.url = 'pc/citymanage/select';//对应的权限下的城市列表
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
      }

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


    //监听是否置顶开关
    form.on('switch(switchsupplier)', function(data){
        if (this.checked) {
            $('.switchsupplier').val("1");
        }else{
            $('.switchsupplier').val("0");
        }
    });

    //指定允许上传的文件类型
    var fileName;
    var path;
    var fileList=[];
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'common/file/upload'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls|doc|docx|pdf|jpg|png|jpeg|gif'
        ,auto: false
        , before: function(obj) {
        }
        ,choose: function(obj){  //上传前选择回调方法
            var flag = true;
            obj.preview(function(index, file, result){
                // file表示文件信息，result表示文件src地址
                // console.log(file);
                if(file.size<10*1024*1024){
                    obj.upload(index, file); //满足条件调用上传方法
                }else{
                    flag = false;
                    layer.msg("文件大小不得超过10M",{icon:2})
                    return false;
                }
                return flag;
            });
        }
        ,done: function(res){
            if(res.code==200){
                fileName =res.data.fileName;
                path =res.data.path;
                var tr = '<tr class="specTR">\n' +
                    '         <td style="text-align: center">' +  '<a class="a-download" href="'+path +'" target="_blank">' + fileName + '</a>'+ '</td>\n' +
                    '         <td style="display: none">' +  '<input type="text" class="fileName" value="'+  fileName +'"/>'+ '</td>\n' +
                    '         <td style="display: none">' +  '<input type="text" class="url" value="'+  path +'"/>'+ '</td>\n' +
                    '         <td style="text-align: center">' +  '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row delAll_btn" >删除</a>'+ '</td>\n' +
                    '      </tr>';
                $("#fileList").append(tr);

                var data = {};
                data.name = fileName;
                data.url = path;
                fileList.push(data);
            }else{
                layer.msg("不支持的文件格式");
            }
        }
    });
    var merchantId = $(".sign").attr("signid");
    if(merchantId!="" &&merchantId!=undefined){
        param.url = 'pc/business/uploadfilelist';//上传文件列表
        ajaxJS(param, {associationId:merchantId,type:'2'}, function (d) {//2商家附件
            var data = d.data;
            for (var i=0;i<data.length;i++){
                var Namefile =data[i].name;
                var urlfile =data[i].url;
                var tr = '<tr class="specTR">\n' +
                    '         <td style="text-align: center">' +  '<a class="a-download" href="'+urlfile +'" target="_blank">' + Namefile + '</a>'+ '</td>\n' +
                    '         <td style="display: none">' +  '<input type="text" class="fileName" value="'+  Namefile +'"/>'+ '</td>\n' +
                    '         <td style="display: none">' +  '<input type="text" class="url" value="'+  urlfile +'"/>'+ '</td>\n' +
                    '         <td style="text-align: center">' +  '<a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row delAll_btn" >删除</a>'+ '</td>\n' +
                    '      </tr>';
                $("#fileList").append(tr);
            }


            form.render();
        });
    }

    //删除
    $('.specList').delegate('.delAll_btn', 'click', function () {
        var dom = $(this).parents('tr');
        layer.confirm("是否删除当前选择的文件", {
            icon: 3,
            title: "提示信息",
            btnAlign: 'c'
        }, function (index) {
            dom.remove();
            layer.close(index);
        })
    });

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var rfileList=[];
        $(".specTR").each(function () {
            rfileList.push({
                name : $(this).find(".fileName").val(),
                url : $(this).find(".url").val(),
            });
        });

        var conpanyAddress = returnValue($("select[name='cProvince']").val())+","+returnValue($("select[name='cCity']").val())+","+returnValue($("select[name='cCountry']").val());
        var city = returnValue($("select[name='centralCityId']").val())+","+returnValue($("select[name='cityId']").val());
        var data = {
            code: field.code,
            name: field.name,
            contact: field.contact,
            contactPhone: field.contactPhone,
            contact2: field.contact2,
            contactPhone2: field.contactPhone2,
            conpanyAddress: conpanyAddress,//公司地址
            detailedAddress: field.detailedAddress,
            city: field.cityId,//落地城市
            centralCityId: field.centralCityId,//落地中心城市
            supplierType: $('.switchsupplier').val(),//是否合格供应商
            level: field.level,
            fileList: rfileList,//文件上传
            remarks: field.remarks
        };

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/business/update';
        } else {
            param.url = 'pc/business/save';
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
    function returnValue(data) {
        if(data==undefined){
            return "";
        }
        return data;
    }

    function loadCity(param,fNo,item) {
        ajaxJS(param, {fNo:fNo}, function (d) {
            var data = d.data;
            item.empty();
            item.append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].areaNo + '">' + data[i].name + '</option>';
                item.append(str1)
            }
            item.val(item.attr('val'));
            form.render();


        });
        form.render('select');

    }

    setTimeout(function () {
        if($('.cCity').val()!= null && $('.cCity').val() != ""){
            param.url = 'pc/business/arealist';//区域数据集合
            loadCountry(param,$('.cCity').val(),$('.cCountry'));
        }
    }, 50)

    function loadCountry(param,fNo,item) {
        ajaxJS(param, {fNo:fNo}, function (d) {
            var data = d.data;
            item.empty();
            item.append('<option value="">请选择</option>');
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option value="' + data[i].areaNo + '">' + data[i].name + '</option>';
                item.append(str1)
            }
            item.val(item.attr('val'));
            form.render();
        });
        form.render('select');

    }
});
