layui.use(['form', 'layer', 'table', 'jquery', 'laydate', 'upload'], function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery,
        laydate = layui.laydate,
        upload = layui.upload,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post'
    };
    var formSelects = layui.formSelects;

    form.render();
    //初始化select
    setTimeout(function() {
        param.url = 'pc/contract/getCityList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].cityId + '">' + data[i].cityName + '</option>';
                $('.cityId').append(str)
            }
            if ($('.sign').val() == 'edit') {
                param.url = 'pc/contract/getMerchantList';
                ajaxJS(param, {cityId:$(".cityId").attr('cityId')}, function (d) {
                    var data = d.data;
                    for (var i = 0; i < data.length; i++) {
                        var str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                        $('.merchantId').append(str)
                    }
                    if ($('.sign').val() == 'edit') {
                        $(".cityId").val($(".cityId").attr('cityId'));
                        $(".merchantId").val($(".merchantId").attr('merchantId'));
                    }
                    form.render()
                });
                form.render()
            }
            form.render()
        });
        if ($('.sign').val() == 'edit') {
            $(".signingType").val($(".signingType").attr('signingType'));
            $(".contractType").val($(".contractType").attr('contractType'));
        }
        form.render()

        //初始化项目下拉
        param.url = 'pc/contract/getProjectList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
                $('.projectId').append(str)
            }
            if ($('.sign').val() == 'edit') {
                var object = $(".projectList").attr('projectList');
                // 转换成数组
                var myobj = eval(object);
                for (var i = 0; i < myobj.length; i++) {
                    var project = myobj[i];
                    var id = '#specTR'+project.id;
                    $(id).find('.projectId').val(project.projectId);
                    $(id).find('.tollMode').val(project.tollMode);
                    $(id).find('.paymentCycle').val(project.paymentCycle);
                    if(project.tollMode==1){
                        $(id).find('.paymentCycle').attr("disabled",true);
                    }
                }
                form.render()
            }
        });

        if ($('.sign').val() == 'edit') {
            var object = $(".projectList").attr('projectList');
            // 转换成数组
            var myobj = eval(object);
            for (var i = 0; i < myobj.length; i++) {
                var project = myobj[i];
                var id = '#specTR'+project.id;
                //初始化点位下拉
                param.url = 'pc/contract/getPointList';
                ajaxJS(param, {projectId:project.projectId}, function (d) {
                    var data = d.data;
                    debugger;
                    var str = '<option value=""></option>';
                    $(id).find('.pointIdOne').append(str)
                    for (var i = 0; i < data.length; i++) {
                        str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                        $(id).find('.pointIdOne').append(str)
                    }
                    $(id).find('.pointIdOne').val(project.pointIdOne);
                    form.render()

                    if(project.pointIdOne!=''&&project.pointIdOne!=null){
                        $(id).find('.pointIdOne').val(project.pointIdOne);
                        param.url = 'pc/contract/getPointChildList';
                        ajaxJS(param, {pointId:project.pointIdOne}, function (dataTwo) {
                            var dataTwo = dataTwo.data;
                            var strTwo = '<option value=""></option>';
                            $(id).find('.pointIdTwo').append(strTwo)
                            for (var i = 0; i < dataTwo.length; i++) {
                                strTwo = '<option value="' + dataTwo[i].id + '">' + dataTwo[i].name + '</option>';
                                $(id).find('.pointIdTwo').append(strTwo)
                            }
                            $(id).find('.pointIdTwo').val(project.pointIdTwo);
                            form.render()

                            param.url = 'pc/contract/getPointChildList';
                            ajaxJS(param, {pointId:project.pointIdTwo}, function (dataThree) {
                                var dataThree = dataThree.data;
                                var strThree = '<option value=""></option>';
                                $(id).find('.pointIdThree').append(strThree)
                                for (var i = 0; i < dataThree.length; i++) {
                                    strThree = '<option value="' + dataThree[i].id + '">' + dataThree[i].name + '</option>';
                                    $(id).find('.pointIdThree').append(strThree)
                                }
                                $(id).find('.pointIdThree').val(project.pointIdThree);
                                form.render()

                                //资源集合
                                param.url = 'pc/contract/getResourceList';
                                ajaxJS(param, {pointId:project.pointIdThree}, function (d) {
                                    var resourceData = d.data;
                                    var resourceStr='';
                                    $(id).find('.resourceList').append('<option value="">请选择资源</option>');
                                    for (var i = 0; i < resourceData.length; i++) {
                                        var resourceStr = '<option value="' + resourceData[i].id + '">' + resourceData[i].assetsName + '</option>';
                                        $(id).find('.resourceList').append(resourceStr)
                                    }
                                    var values = [];
                                    if (project.resourceList) {
                                        for(var i = 0; i < project.resourceList.length; i++){
                                            values.push(project.resourceList[i].resourceId);
                                        }
                                    }
                                    formSelects.render('multiRole'+project.id, {
                                        skin: 'primary',
                                        radio: false,
                                        init: values
                                    });
                                    form.render()

                                    $('.xm-input').attr('placeholder', values.length > 0 ? '' :"请选择资源");
                                    form.render();
                                });
                            });
                        });
                    }

                });
            }
            form.render()
        }
    }, 200)


    form.on("submit(pass)", function (data) {
        add(data,2);
    })

    form.on("submit(unPass)", function (data) {
        add(data,3);
    })

    function add(data,status) {
        var field = data.field;

        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        var data = {
            id:$(".sign").attr("signid"),
            approvalStatus: status,
            opinion:field.opinion,
        };
        param.url = 'pc/contract/approveChange';
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            top.layer.msg(d.message);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();

        });
    };
});

function goLogin() {
    parent.goLogin()
}
