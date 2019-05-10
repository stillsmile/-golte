layui.use(['form', 'layer', 'table', 'jquery', 'upload'], function () {
    var form = layui.form,
        table = layui.table,upload = layui.upload,
        $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    form.render();

    //指定允许上传的文件类型
    var fileName;
    var path;
    var fileList=[];
    upload.render({
        elem: '#upload'
        ,url: interfaceUrl+'common/file/upload'
        ,accept: 'file' //普通文件
        ,auto: false
        ,exts: 'xlsx|xls|doc|docx|pdf|jpg|png|jpeg|gif'
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

    var merchantId = $(".merchantId").attr("merchantId");
    if(merchantId){
        param.url = 'pc/business/uploadfilelist';//上传文件列表
        ajaxJS(param, {associationId:merchantId,type:'3'}, function (d) {//2商家附件
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

        param.url = 'pc/business/selectEvaluationInfo';//商家评估信息
        ajaxJS(param, {id:merchantId}, function (d) {
            var data = d.data;
            $(".cooperationEvaluation").val(data.cooperationEvaluation);
            $(".evaluationId").val(data.id);
            form.render();
        });
    }

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

        var data = {
            cooperationEvaluation: field.cooperationEvaluation
        };

        var  evaluationId = $(".evaluationId").val();
        data.id = $(".merchantId").attr("merchantId");
        data.evaluationId = $(".evaluationId").val();
        if(evaluationId){
            param.url = 'pc/business/updateEvaluationInfo';
            data.fileList = rfileList;
        }else{
            param.url = 'pc/business/saveEvaluationInfo';
            data.fileList = fileList;
        }

        ajaxJS(param, data, function (d) {
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();

        });
        return false;
    });
    form.render();
    $('.cancel').click(function () {
        layer.closeAll("iframe");
    });
});

function goLogin() {
    parent.goLogin()
}
