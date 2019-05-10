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

    //初始化select
    setTimeout(function() {
        param.url = 'pc/contract/getCityList';
        ajaxJS(param, null, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].cityId + '">' + data[i].cityName + '</option>';
                $('.cityId').append(str)
            }
            form.render()
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
                        param.url = 'pc/contract/getPointTwoList';
                        ajaxJS(param, {projectId:project.projectId,pointId:project.pointIdOne}, function (dataTwo) {
                            var dataTwo = dataTwo.data;
                            var strTwo = '<option value=""></option>';
                            $(id).find('.pointIdTwo').append(strTwo)
                            for (var i = 0; i < dataTwo.length; i++) {
                                strTwo = '<option value="' + dataTwo[i].id + '">' + dataTwo[i].name + '</option>';
                                $(id).find('.pointIdTwo').append(strTwo)
                            }
                            $(id).find('.pointIdTwo').val(project.pointIdTwo);
                            form.render()

                            param.url = 'pc/contract/getPointThreeList';
                            ajaxJS(param, {projectId:project.projectId,pointId:project.pointIdTwo}, function (dataThree) {
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
                                ajaxJS(param, {projectId:project.projectId,pointId:project.pointIdThree}, function (d) {
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

    //初始化项目下拉
    param.url = 'pc/contract/getProjectList';
    ajaxJS(param, {cityId:$('.cityId').val()}, function (d) {
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
                if(project.tollMode==1){
                    $(id).find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" value="'+project.unitPrice+'" lay-verify="number">');
                    $(id).find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number" value="'+project.number+'" lay-verify="integer">');
                    $(id).find('.paymentCycle').append('<option value="10">一次性</option>')
                    $(id).find('.paymentCycle').val("10");
                    $(id).find('.paymentCycle').attr("disabled",true);
                }else if(project.tollMode==3){
                    $(id).find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" value="'+project.unitPrice+'" lay-verify="number">');
                    $(id).find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" name="number" value="'+project.number+'" disabled>');
                    $(id).find('.paymentCycle').val("");
                    $(id).find(".paymentCycle option[value='10']").remove();
                    $(id).find('.paymentCycle').attr("disabled",false);
                }else if(project.tollMode==4){
                    $(id).find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" value="'+project.unitPrice+'">');
                    $(id).find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number" value="'+project.number+'">');
                    $(id).find('.paymentCycle').val("");
                    $(id).find(".paymentCycle option[value='10']").remove();
                    $(id).find('.paymentCycle').attr("disabled",false);
                }else {
                    $(id).find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" value="'+project.unitPrice+'" lay-verify="number">');
                    $(id).find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number" value="'+project.number+'" lay-verify="integer">');
                    $(id).find('.paymentCycle').val("");
                    $(id).find(".paymentCycle option[value='10']").remove();
                    $(id).find('.paymentCycle').attr("disabled",false);
                }
                $(id).find('.projectId').val(project.projectId);
                $(id).find('.tollMode').val(project.tollMode);
                $(id).find('.paymentCycle').val(project.paymentCycle);
            }
            form.render()
        }
    });

    form.on('select(cityId)', function (data) {
        var index = data.othis;
        param.url = 'pc/contract/getProjectList';
        ajaxJS(param, {cityId:$('.cityId').val()}, function (d) {
            var data = d.data;
            $('.projectId').empty();
            $('.pointIdOne').empty();
            $('.pointIdTwo').empty();
            $('.pointIdThree').empty();
            $('.resourceList').empty();
            $('.projectId').append('<option value=""></option>')
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
                $('.projectId').append(str)
            }
            $(".specTR").each(function () {
                var trLen = $(this).find('.trLen').val()
                var values=[];
                formSelects.render('multiRole'+trLen, {
                    skin: 'primary',
                    radio: false,
                    init: values
                });
            })
            form.render();
        });

        param.url = 'pc/contract/getMerchantList';
        ajaxJS(param, {cityId:$(".cityId").val()}, function (d) {
            var data = d.data;
            $(".merchantId").empty();
            $(".merchantId").append('<option value=""></option>');
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].name + '</option>';
                $('.merchantId').append(str)
            }
            $(".merchantId").val($(".merchantId").attr('merchantId'));
            form.render()
        });
    });

    form.on('select(projectId)', function (data) {
        var index = data.othis;
        param.url = 'pc/contract/getPointList';
        ajaxJS(param, {projectId:index.parents('.specTR').find('.projectId').val()}, function (dataOne) {
            var dataOne = dataOne.data;
            index.parents('.specTR').find('.pointIdOne').empty();
            index.parents('.specTR').find('.pointIdTwo').empty();
            index.parents('.specTR').find('.pointIdThree').empty();
            index.parents('.specTR').find('.resourceList').empty();
            var trLen = index.parents('.specTR').find('.trLen').val()
            var values=[];
            formSelects.render('multiRole'+trLen, {
                skin: 'primary',
                radio: false,
                init: values
            });
            index.parents('.specTR').find('.pointIdOne').append('<option value="">请选择</option>');
            for (var i = 0; i < dataOne.length; i++) {
                var strOne = '<option value="' + dataOne[i].id + '">' + dataOne[i].name + '</option>';
                index.parents('.specTR').find('.pointIdOne').append(strOne)
            }
            form.render();
        });
    });

    form.on('select(pointIdOne)', function (data) {
        var index = data.othis;
        param.url = 'pc/contract/getPointTwoList';
        ajaxJS(param, {projectId:index.parents('.specTR').find('.projectId').val(),pointId:index.parents('.specTR').find('.pointIdOne').val()}, function (dataTwo) {
            var dataTwo = dataTwo.data;
            index.parents('.specTR').find('.pointIdTwo').empty();
            index.parents('.specTR').find('.pointIdThree').empty();
            index.parents('.specTR').find('.resourceList').empty();
            var trLen = index.parents('.specTR').find('.trLen').val()
            var values=[];
            formSelects.render('multiRole'+trLen, {
                skin: 'primary',
                radio: false,
                init: values
            });
            index.parents('.specTR').find('.pointIdTwo').append('<option value="">请选择</option>');
            for (var i = 0; i < dataTwo.length; i++) {
                var strTwo = '<option value="' + dataTwo[i].id + '">' + dataTwo[i].name + '</option>';
                index.parents('.specTR').find('.pointIdTwo').append(strTwo)
            }
            form.render();
        });
    });

    form.on('select(pointIdTwo)', function (data) {
        var index = data.othis;
        param.url = 'pc/contract/getPointThreeList';
        ajaxJS(param, {projectId:index.parents('.specTR').find('.projectId').val(),pointId:index.parents('.specTR').find('.pointIdTwo').val()}, function (dataThree) {
            var dataThree = dataThree.data;
            index.parents('.specTR').find('.pointIdThree').empty();
            index.parents('.specTR').find('.resourceList').empty();
            var trLen = index.parents('.specTR').find('.trLen').val()
            var values=[];
            formSelects.render('multiRole'+trLen, {
                skin: 'primary',
                radio: false,
                init: values
            });
            index.parents('.specTR').find('.pointIdThree').append('<option value="">请选择</option>')
            for (var i = 0; i < dataThree.length; i++) {
                var strThree = '<option value="' + dataThree[i].id + '">' + dataThree[i].name + '</option>';
                index.parents('.specTR').find('.pointIdThree').append(strThree)
            }
            form.render();
        });
    });

    form.on('select(pointIdThree)', function (data) {
        $this = data.othis;
        //资源集合
        param.url = 'pc/contract/getResourceList';
        ajaxJS(param, {projectId:$this.parents('.specTR').find('.projectId').val(),pointId:$this.parents('.specTR').find('.pointIdThree').val()}, function (d) {
            var resourceData = d.data;
            $this.parents('.specTR').find('.resourceList').empty();
            $this.parents('.specTR').find('.resourceList').append('<option value="">请选择资源</option>');
            for (var i = 0; i < resourceData.length; i++) {
                var resourceStr = '<option value="' + resourceData[i].id + '">' + resourceData[i].assetsName + '</option>';
                $this.parents('.specTR').find('.resourceList').append(resourceStr)
            }
            var trLen = $this.parents('.specTR').find('.trLen').val()
            var values=[];
            formSelects.render('multiRole'+trLen, {
                skin: 'primary',
                radio: false,
                init: values
            });
            form.render()

            $('.xm-input').attr('placeholder', values.length > 0 ? '' :"请选择资源");
            form.render();
        })
    });


    form.on('select(tollMode)', function (data) {
        $this = data.othis;
        if($this.parents('.specTR').find('.tollMode').val()==1){
            $this.parents('.specTR').find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" lay-verify="number">');
            $this.parents('.specTR').find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number" lay-verify="integer">');
            $this.parents('.specTR').find('.paymentCycle').attr('disabled',true);
            $this.parents('.specTR').find('.paymentCycle').append("<option value='10'>一次性</option>");
            $this.parents('.specTR').find('.paymentCycle').val('10');
            $this.parents('.specTR').find('.subtotal').html('');
        }else if($this.parents('.specTR').find('.tollMode').val()==3){
            $this.parents('.specTR').find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" lay-verify="number">');
            $this.parents('.specTR').find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" name="number" disabled>');
            $this.parents('.specTR').find('.paymentCycle').attr('disabled',false);
            $this.parents('.specTR').find('.paymentCycle').val('');
            $this.parents('.specTR').find(".paymentCycle option[value='10']").remove();
            $this.parents('.specTR').find('.subtotal').html('');
        }else if($this.parents('.specTR').find('.tollMode').val()==4){
            $this.parents('.specTR').find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice">');
            $this.parents('.specTR').find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number">');
            $this.parents('.specTR').find('.paymentCycle').attr('disabled',false);
            $this.parents('.specTR').find('.paymentCycle').val('');
            $this.parents('.specTR').find(".paymentCycle option[value='10']").remove();
            $this.parents('.specTR').find('.subtotal').html('');
        }else {
            $this.parents('.specTR').find('.unitPriceTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs unitPrice" placeholder="请输入单价" name="unitPrice" lay-verify="number">');
            $this.parents('.specTR').find('.numberTD').html('<input oninput="if(value>9999999)value=9999999" type="number" class="layui-input inputs number" placeholder="请输入数量" name="number" lay-verify="integer">');
            $this.parents('.specTR').find('.paymentCycle').attr('disabled',false);
            $this.parents('.specTR').find('.paymentCycle').val('');
            $this.parents('.specTR').find(".paymentCycle option[value='10']").remove();
            $this.parents('.specTR').find('.subtotal').html('');
        }
        form.render();
        var sum = 0;
        $(".specTR").each(function () {
            var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
            sum = sum+total;
        })
        $('.amount').html(toMoney(sum));

        $('input').blur(function (){
            var total = $(this).parents('.specTR').find('.unitPrice').val()*$(this).parents('.specTR').find('.number').val();
            $(this).parents('.specTR').find('.subtotal').html(toMoney(total));

            var sum = 0;
            $(".specTR").each(function () {
                var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
                sum = sum+total;
            })
            $('.amount').html(toMoney(sum));
        })
    });


    var trLen = 0;
    /*添加标签*/
    $('.projectAdd').click(function() {
        if($('.cityId').val()==''||$('.cityId').val()==undefined){
            top.layer.msg("请先选择城市");
            return false;
        }
        trLen =  trLen+1 ;
        var str =
            ' <tr class="specTR"> '+
            '<input type="hidden" id="project'+trLen+'" class="trLen" value="'+trLen+'">'+
            '<td> '+
            '<select name="projectId" lay-filter="projectId" class="projectId" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '</select> '+
            '</td> '+
            '<td> '+
            '<select name="pointIdOne" lay-filter="pointIdOne" class="pointIdOne" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '</select> '+
            '<select name="pointIdTwo" lay-filter="pointIdTwo" class="pointIdTwo" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '</select> '+
            '<select name="pointIdThree" lay-filter="pointIdThree" class="pointIdThree" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '</select> '+
            '</td> '+
            '<td>' +
            '<select xm-select="multiRole'+trLen+'" xm-select-skin="primary" name="resourceList" lay-filter="resourceList" '+
            'class="resourceList" lay-verify="required"> '+
            '</select> '+
            '</td> '+
            '<td> '+
            '<select name="tollMode" lay-filter="tollMode" class="tollMode" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '<option value="1">一次性</option> '+
            '<option value="2">固定</option> '+
            '<option value="3">佣金</option> '+
            '<option value="4">其他</option> '+
            '</select> '+
            '</td> '+
            '<td> '+
            '<select name="paymentCycle" lay-filter="paymentCycle" class="paymentCycle" lay-verify="required"> '+
            '<option value="">请选择</option> '+
            '<option value="9">五年</option> '+
            '<option value="8">四年</option> '+
            '<option value="7">三年</option> '+
            '<option value="6">两年</option> '+
            '<option value="1">一年</option> '+
            '<option value="2">半年</option> '+
            '<option value="3">季度</option> '+
            '<option value="4">月</option> '+
            '<option value="5">周</option> '+
            '<option value="10">一次性</option> '+
            '</select> '+
            '</td> '+
            '<td class="unitPriceTD"> '+
            '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入单价" class="layui-input inputs unitPrice" name="unitPrice" lay-verify="number"> '+
            '</td> '+
            '<td> '+
            '<input maxlength="20" type="text" class="layui-input inputs unitName" placeholder="请输入单位" name="unitName"> '+
            '</td> '+
            '<td class="numberTD"> '+
            '<input oninput="if(value>9999999)value=9999999" type="number" placeholder="请输入数量" class="layui-input inputs number" name="number" lay-verify="integer"> '+
            '</td> '+
            '<td class="subtotal"></td> '+
            '<td><a class="layui-btn layui-btn-xs layui-btn-primary layui-btn-row del">删除</a></td> '+
            '</tr>'
        $('.specBody').append(str)
        $('.del').each(function() {
            $(this).click(function() {
                $(this)
                    .parents('.specTR')
                    .remove()
                var sum = 0;
                $(".specTR").each(function () {
                    var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
                    sum = sum+total;
                })
                $('.amount').html(toMoney(sum));
                form.render();
            })
        })
        $('input').blur(function (){
            var total = $(this).parents('.specTR').find('.unitPrice').val()*$(this).parents('.specTR').find('.number').val();
            $(this).parents('.specTR').find('.subtotal').html(toMoney(total));
            var sum = 0;
            $(".specTR").each(function () {
                var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
                sum = sum+total;
            })
            $(this).parents('#table').find('.amount').html(toMoney(sum));
        })
        param.url = 'pc/contract/getProjectList';
        ajaxJS(param, {cityId:$('.cityId').val()}, function (d) {
            var data = d.data;
            for (var i = 0; i < data.length; i++) {
                var str = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
                $('#project'+trLen).parents('.specTR').find('.projectId').append(str)
            }
            form.render();
        });

        form.render();
    })

    $('input').blur(function (){
        var total = $(this).parents('.specTR').find('.unitPrice').val()*$(this).parents('.specTR').find('.number').val();
        $(this).parents('.specTR').find('.subtotal').html(toMoney(total));

        var sum = 0;
        $(".specTR").each(function () {
            var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
            sum = sum+total;
        })
        $(this).parents('#table').find('.amount').html(toMoney(sum));
        form.render();
    })

    /*删除标签*/
    $('.del').click(function() {
                $(this)
                    .parents('.specTR')
                    .remove()
        form.render();
        var sum = 0;
        $(".specTR").each(function () {
            var total = $(this).find(".unitPrice").val()*$(this).find(".number").val();
            sum = sum+total;
        })
        $('.amount').html(toMoney(sum));
        form.render();
    })

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

    $('.delete').click(function() {
        $(this)
            .parents('.uploadTR')
            .remove()
    })

    laydate.render({
        elem: '#signingTime',
        type: 'date'
    });

    laydate.render({
        elem: '#deadlineStartTime',
        type: 'date'
    });

    laydate.render({
        elem: '#deadlineEndTime',
        type: 'date'
    });

    form.on("submit(addExample)", function (data) {
        add(data,1);
    })

    form.on("submit(addOrEdit)", function (data) {
        add(data,0);
    })

    function add(data,status) {
        var field = data.field;
        //弹出loading
        var index = top.layer.msg("数据提交中，请稍候", {icon: 16, time: false, shade: 0.8});

        var projects = [];
        var flag = false;
        $(".specTR").each(function () {
            var obj = $(this).find(".trLen").val();
            var resourceList = formSelects.value('multiRole'+obj, 'value');
            var resources = [];
            if(resourceList){
                for(var i=0;i<resourceList.length;i++){
                    resources.push({
                        resourceId:resourceList[i].value,
                    });
                }
            }
            var reg = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
            if($(this).find(".unitPrice").val()){
                if($(this).find(".tollMode").val()==3){
                    if(!reg.test($(this).find(".unitPrice").val())){
                        flag = true;
                        top.layer.msg("请输入正确的佣金");
                        return;
                    }else {
                        if($(this).find(".unitPrice").val()<0||$(this).find(".unitPrice").val()>100){
                            flag = true;
                            top.layer.msg("请输入0到100的佣金值");
                            return;
                        }
                    }
                }else {
                    if(!reg.test($(this).find(".unitPrice").val())){
                        flag = true;
                        top.layer.msg("请输入正确的单价");
                        return;
                    }
                }
            }
            projects.push({
                projectId: $(this).find(".projectId").val(),
                pointId: $(this).find(".pointIdThree").val(),
                tollMode: $(this).find(".tollMode").val(),
                paymentCycle: $(this).find(".paymentCycle").val(),
                unitPrice: $(this).find(".unitPrice").val(),
                number: $(this).find(".number").val(),
                unitName: $(this).find(".unitName").val(),
                subtotal: moneyToNumValue($(this).find(".subtotal").text()),
                resourceList:resources,
            });
        });

        if(flag){
            return false;
        }

        var fileList=[];
        $(".uploadTR").each(function () {
            fileList.push({
                associationId: $(".sign").attr("signid"),
                name:$(this).find(".name").val(),
                url: $(this).find(".url").val(),
                type: 1,
            });
        });
        if(projects.length==0){
            top.layer.msg("请添加合作项目");
            return false;
        }

        if(moneyToNumValue($(".amount").text())>999999999){
            top.layer.msg("合同金额过大");
            return false;
        }
        var reg = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
        if(!reg.test(field.margin)){
            top.layer.msg("请输入大于等于0的履约保证金");
            return false;
        }

        var signingTime = $("#signingTime").val();
        var deadlineStartTime = $("#deadlineStartTime").val();
        var deadlineEndTime = $("#deadlineEndTime").val();


        var data = {
            code: field.code,
            name: field.name,
            cityId: $(".cityId option:selected").val(),
            merchantId: $(".merchantId option:selected").val(),
            merchantContact: field.merchantContact,
            merchantContactPhone: field.merchantContactPhone,
            signingTime:signingTime,
            signingContact:field.signingContact,
            signingContactPhone:field.signingContactPhone,
            deadlineStartTime:deadlineStartTime,
            deadlineEndTime: deadlineEndTime,
            signingType: $(".signingType option:selected").val(),
            contractType: $(".contractType option:selected").val(),
            projectList:projects,
            amount:moneyToNumValue($(".amount").text()),
            margin:field.margin,
            darftStatus:status,
            remarks:field.remarks,
            annexList: fileList,
        };
        param.url = 'pc/contract/add';
        if ($(".sign").val() == 'edit') {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/contract/update';
        }
        ajaxJS(param, data, function (d) {
            top.layer.close(index);
            if(d.data){
                window.location.href="../../../html/payback_setup/payback/paybackAdd.html?contractId="+d.data;
            }else {
                layer.closeAll("iframe");
                //刷新父页面
                parent.location.reload();
            }
        });
    };

    $('.cancel').click(function () {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    });
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
