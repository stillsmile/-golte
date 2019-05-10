layui.use(['form', 'layer','laydate', 'jquery', 'upload'], function () {
    var form = layui.form, $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    laydate.render({
        elem: '#terminationTime',
        type: 'date'
    });

    param.url = 'pc/contract/getAmount';
    ajaxJS(param, {id:$(".sign").attr("signid")}, function (d) {
        var data = d.data;
        var notRecoveredAmount = Number($(".amount").val())-Number(data);
        $(".notRecoveredAmount").val(notRecoveredAmount);
        form.render()
    });

    //指定允许上传的文件类型
    var fileName;
    var path;
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'common/file/upload'
        ,accept: 'file' //普通文件
        ,exts: 'xlsx|xls|doc|docx|pdf|jpg|png|jpeg|gif'
        ,size:"10240"
        ,done: function(res){
            if(res.code==200){
                fileName =res.data.fileName;
                path =res.data.path;
                var tr = '<tr class="uploadTR">\n' +
                    // '        <td>'+ fileName +'</td>\n' +
                    '         <td>' +  '<a class="a-download" href="'+path +'" target="_blank" download="'+fileName+'">' + fileName + '</a>'+ '</td>\n' +
                    '<td><input type="hidden" class="name" value="'+fileName+'"><input type="hidden" class="url" value="'+path+'"><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row delete">删除</a></td>'+
                    '      </tr>';
                $("#fileList").append(tr);
                $('.delete').each(function() {
                    $(this).click(function() {
                        $(this)
                            .parents('.uploadTR')
                            .remove()
                    })
                })
            }else {
                top.layer.msg(res.message);
            }
        }
    });

    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;

        var terminationTime = $("#terminationTime").val();

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var fileList=[];
        $(".uploadTR").each(function () {
            fileList.push({
                associationId: $(".sign").attr("signid"),
                name:$(this).find(".name").val(),
                url: $(this).find(".url").val(),
                type: 5,
            });
        });

        var reg = /^([\+ \-]?(([1-9]\d*)|(0)))([.]\d{0,2})?$/;
        if(!reg.test(field.notRecoveredAmount)){
            flag = true;
            top.layer.msg("请输入正确的未收款项金额");
            return;
        }

        if(fileList.length==0){
            top.layer.msg("请上传附件");
            return false;
        }

        var data = {
            id:$(".sign").attr("signid"),
            terminationTime: terminationTime,
            notRecoveredAmount: field.notRecoveredAmount,
            reason: field.reason,
            annexList: fileList,
        };
        param.url = 'pc/contract/stop';

        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();

        });
        return false;
    });
    $('.cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
});
