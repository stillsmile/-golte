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
        //初始化项目下拉
        param.url = 'pc/contract/getProjectList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
                $('.projectId').append(str)
            }
            var object = $(".projectList").attr('projectList');
            var objectUp = $(".projectListUp").attr('projectListUp');
            // 转换成数组
            var myobj = eval(object);
            var myobjUp = eval(objectUp);
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
            for (var i = 0; i < myobjUp.length; i++) {
                var projectUp = myobjUp[i];
                var id = '#specTRUp'+projectUp.id;
                $(id).find('.projectId').val(projectUp.projectId);
                $(id).find('.tollMode').val(projectUp.tollMode);
                $(id).find('.paymentCycle').val(projectUp.paymentCycle);
                if(projectUp.tollMode==1){
                    $(id).find('.paymentCycle').attr("disabled",true);
                }
            }
            form.render()
        });

        //初始化点位下拉
        param.url = 'pc/contract/getPointList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            var str = '<option value=""></option>';
            $('.pointIdOne').append(str)
            for (var i = 0; i < data.length; i++) {
                str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                $('.pointIdOne').append(str)
            }
            form.render()
            var object = $(".projectList").attr('projectList');
            var objectUp = $(".projectListUp").attr('projectListUp');
            // 转换成数组
            var myobj = eval(object);
            var myobjUp = eval(objectUp);
            for (var i = 0; i < myobj.length; i++) {
                var project = myobj[i];
                var id = '#specTR'+project.id;
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
            }

            for (var i = 0; i < myobjUp.length; i++) {
                var projectUp = myobjUp[i];
                var id = '#specTRUp'+projectUp.id;
                if(projectUp.pointIdOne!=''&&projectUp.pointIdOne!=null){
                    $(id).find('.pointIdOne').val(projectUp.pointIdOne);
                    param.url = 'pc/contract/getPointChildList';
                    ajaxJS(param, {pointId:projectUp.pointIdOne}, function (dataTwo) {
                        var dataTwoUp = dataTwo.data;
                        var strTwoUp = '<option value=""></option>';
                        $(id).find('.pointIdTwo').append(strTwoUp)
                        for (var i = 0; i < dataTwoUp.length; i++) {
                            strTwoUp = '<option value="' + dataTwoUp[i].id + '">' + dataTwoUp[i].name + '</option>';
                            $(id).find('.pointIdTwo').append(strTwoUp)
                        }
                        $(id).find('.pointIdTwo').val(projectUp.pointIdTwo);
                        form.render()

                        param.url = 'pc/contract/getPointChildList';
                        ajaxJS(param, {pointId:projectUp.pointIdTwo}, function (dataThree) {
                            var dataThreeUp = dataThree.data;
                            var strThreeUp = '<option value=""></option>';
                            $(id).find('.pointIdThree').append(strThreeUp)
                            for (var i = 0; i < dataThreeUp.length; i++) {
                                strThreeUp = '<option value="' + dataThreeUp[i].id + '">' + dataThreeUp[i].name + '</option>';
                                $(id).find('.pointIdThree').append(strThreeUp)
                            }
                            $(id).find('.pointIdThree').val(projectUp.pointIdThree);
                            form.render()

                            //资源集合
                            param.url = 'pc/contract/getResourceList';
                            ajaxJS(param, {pointId:projectUp.pointIdThree}, function (d) {
                                var resourceDataUp = d.data;
                                var resourceUpStr='';
                                $(id).find('.resourceList').append('<option value="">请选择资源</option>');
                                for (var i = 0; i < resourceDataUp.length; i++) {
                                    var resourceUpStr = '<option value="' + resourceDataUp[i].id + '">' + resourceDataUp[i].assetsName + '</option>';
                                    $(id).find('.resourceList').append(resourceUpStr)
                                }
                                debugger;
                                var values = [];
                                if (projectUp.resourceList) {
                                    for(var i = 0; i < projectUp.resourceList.length; i++){
                                        values.push(projectUp.resourceList[i].resourceId);
                                    }
                                }
                                formSelects.render('multiRoleUp'+projectUp.id, {
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
            }
            form.render()
        });
    }, 200)

});

//将数字转换成金额显示
function toMoney(num){
    if(num){
        num = num.toFixed(2);
        num = parseFloat(num)
        num = num.toLocaleString();
        return num;//返回的是字符串23,245.12保留2位小数
    }else {
        return num;
    }
}

//将1,234,567.00转换为1234567.00
function moneyToNumValue(val) {
    var num = val.trim();
    var ss = num.toString();
    if (ss.length == 0) {
        return "0";
    }
    return ss.replace(/,/g, "");
}

function goLogin() {
    parent.goLogin()
}
