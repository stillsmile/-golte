layui.use(['form', 'layer', 'jquery', 'upload', 'laydate'], function () {
    var form = layui.form, $ = layui.jquery,upload = layui.upload,laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var formSelects = layui.formSelects;
    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    //年月选择器
    laydate.render({
        elem: '#mmonth'
        ,type: 'month'
        ,trigger: 'click'
    });
    //年选择器
    laydate.render({
        elem: '#yyear'
        ,type: 'year'
        ,trigger: 'click'
    });


    if($(".ycontractType").val()!=""&&$(".ycontractType").val()!=undefined){
        //加载激励角色
        loadRoleList();
    }

    //管理酬金赋值
    // yManagAmount();


    param.url = 'pc/resource/project/selectByCityIds';//相应权限的城市项目
    ajaxJS(param, {}, function (d) {
        var data = d.data.list;
        $('.yprojectId').empty().append('<option value="0">请选择</option>');
        $('.mprojectId').empty().append('<option value="0">请选择</option>');
        for (var i = 0; i < data.length; i++) {
            var str1 = '<option value="' + data[i].id + '">' + data[i].projectName + '</option>';
            $('.yprojectId').append(str1);
            $('.mprojectId').append(str1);
        }
        $('.mprojectId').val($('.mprojectId').attr('val'));
        $('.yprojectId').val($('.yprojectId').attr('val'));
        form.render();
    });

    //月度激励及年度激励联动
    if(!($(".sign").val()=="edit")){
        $(".yotherCost").val("0");
        $(".yprofitIndex").val("0");
    }

    form.on('select(excitationType)', function(data){
        var typeValue = data.value;
        //1---月度激励  2--年度激励
        if(typeValue==1){
            $(".mcontractType").val("");
            $(".mprojectId").val("");
            $(".mpropertyProfit").val("");
            $(".mtaxPercentage").val("");
            $(".yotherCost").val("0");
            $(".yprofitIndex").val("0");

            $(".monthShow").show();
            $(".yearShow").css("display","none")
        }
        if(typeValue==2){
            $(".yotherCost").val("");
            $(".yprofitIndex").val("");
            $(".mcontractType").val("0");
            $(".mprojectId").val("0");
            $(".mpropertyProfit").val("0");
            $(".mtaxPercentage").val("0");

            $(".yearShow").show();
            $(".monthShow").css("display","none")
        }
        form.render('select');
        form.render();
    });

    //月度---物业利润收入变动
    $(".mpropertyProfit").on("input",function(e){
        //物业利润数据变动产生的相应的数据变动
        TaxAndWuye();

        form.render();
    });


    //月度---税金百分比
    $(".mtaxPercentage").on("input",function(e){
        //税金数据变动产生的相应的数据变动
        TaxAndWuye();

        form.render();
    });

    //月度---激励类别联动
    form.on('select(mcontractType)', function(data){
        var typeValue = data.value;
        //1--临时摆展类  2---服务产品类
        //激励百分比 == 固定：临时摆展类50%  服务产品类30%
        if(typeValue==3){
            $(".mexcitationPercentage").val("50");
        }
        if(typeValue==4){
            $(".mexcitationPercentage").val("30");
        }

        if($(".mcardinalNumber").val()!=""&&$(".mexcitationPercentage").val()!=""){
            //激励金额计算 == 激励基数*激励百分比
            var mactualAmount = $(".mcardinalNumber").val() * $(".mexcitationPercentage").val()/100;
            if(mactualAmount){
                $(".mactualAmount").val(mactualAmount.toFixed(2));
            }else{
                $(".mactualAmount").val("0");
            }
        }

        form.render('select');
        form.render();
    });



    //年度--物业利润收入变动
    $(".ypropertyProfit").on("input",function(e){
        //计算全部数据
        countAllData();
        form.render();
    });

    //年度--税金
    $(".ytax").on("input",function(e){
        //计算全部数据
        countAllData();
        form.render();
    });

    //年度--税金百分比
    $(".ytaxPercentage").on("input",function(e){

        //计算全部数据
        countAllData();
        form.render();
    });

    //年度--其他成本
    $(".yotherCost").on("input",function(e){
        //计算全部数据
        countAllData();
        form.render();
    });

    //年度---年初利润指标联动
    $(".yprofitIndex").on("input",function(e){

        //计算全部数据
        countAllData();
        form.render();
    });

    //年度---到岗折算联动
    $(".yarrivalConversion").on("input",function(e){

        //计算全部数据
        countAllData();
        form.render();
    });

    //年度---其他折扣联动
    $(".yotherDeduction").on("input",function(e){
        //计算全部数据
        countAllData();
        form.render();
    });

    //年度--激励类别
    form.on('select(ycontractType)', function(data){

        //加载激励角色
        loadRoleList();

        //计算全部数据
        countAllData();

        form.render('select');
        form.render();
    });

    //年度--激励角色联动
    form.on('select(yroleId)', function(data){
        //计算全部数据
        countAllData();
        form.render('select');
        form.render();
    });


    form.on("submit(addOrEdit)", function (data) {
        var field = data.field;
        var subName = data.elem.name;

        if(field.excitationType =="1"){
            if(field.mmemberName ==undefined||field.mmemberName ==''){
                layer.msg("请输入激励人员");
                return false;
            }
            if(field.mcontractType =='0'||field.mcontractType ==''){
                layer.msg("请选择激励类别");
                return false;
            }
            if(field.mprojectId =='0'||field.mprojectId ==''){
                layer.msg("请选择项目名称");
                return false;
            }
            var limitNum = field.mtaxPercentage.replace(/[^0-9.]+/g, "");

            if(limitNum>=0&&limitNum<=100){
            }else {
                top.layer.msg("税金百分比请输入大于等于0小于100的数,可保留两位小数");
                return false;
            }

            var reg = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
            if(!reg.test(field.mpropertyProfit)){
                top.layer.msg("物业利润收入请输入大于等于0的数,保留两位小数");
                return false;
            }
            var data = {
                type: field.excitationType,//奖励类型（1月度 2年度）
                contractType: field.mcontractType,//激励类别 -->合同别中的一种
                projectId: field.mprojectId,//项目Id
                propertyProfit: field.mpropertyProfit,//物业利润收入
                taxPercentage: field.mtaxPercentage,//税金百分比
                tax: field.mtax,//税金==物业利润收入/税金百分比
                cardinalNumber: field.mcardinalNumber,//激励基数
                excitationPercentage: field.mexcitationPercentage,//激励百分比
                actualAmount: field.mactualAmount,//激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
                month: field.mmonth,//激励月度
                memberName: field.mmemberName,//激励人员
            };
        }
        if(field.excitationType =="2"){
            if(field.ypropertyProfit ==''){
                layer.msg("请输入物业利润收入");
                return false;
            }
            if(field.ymemberName ==''){
                layer.msg("请输入激励人员");
                return false;
            }
            if(field.ycontractType =='0'||field.ycontractType ==''){
                layer.msg("请选择激励类别");
                return false;
            }
            if(field.yprojectId =='0'||field.yprojectId ==''){
                layer.msg("请选择项目名称");
                return false;
            }
            if(field.yotherCost ==''){
                layer.msg("请输入其他成本");
                return false;
            }
            if(field.yprofitIndex ==''){
                layer.msg("请输入年初利润指标");
                return false;
            }
            var reg = /^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/;
            if(!reg.test(field.yotherCost)){
                top.layer.msg("其他成本请输入大于等于0的数,保留两位小数");
                return false;
            }
            if(!reg.test(field.yprofitIndex)){
                top.layer.msg("年初利润指标请输入大于等于0的数,保留两位小数");
                return false;
            }
            if(!reg.test(field.ytaxPercentage)){
                top.layer.msg("税金百分比请输入大于等于0的数,保留两位小数");
                return false;
            }
            if(!reg.test(field.ypropertyProfit)){
                top.layer.msg("物业利润收入请输入大于等于0的数,保留两位小数");
                return false;
            }

            var data = {
                type: field.excitationType,//奖励类型（1月度 2年度）
                contractType: field.ycontractType,//激励类别 -->合同别中的一种
                projectId: field.yprojectId,//项目Id
                propertyProfit: returnValue(field.ypropertyProfit),//物业利润收入
                taxPercentage: field.ytaxPercentage,//税金百分比
                tax: returnValue(field.ytax),//税金==物业利润收入/税金百分比
                managementAmount: returnValue(field.ymanagementAmount),//管理酬金
                otherCost: returnValue(field.yotherCost),//其他成本
                month: field.yyear,//激励年份
                profitIndex: returnValue(field.yprofitIndex),//年初利润指标,
                cardinalNumber: returnValue(field.ycardinalNumber),//激励基数
                roleId: field.yroleId,//激励角色
                excitationPercentage: returnValue(field.yexcitationPercentage),//激励百分比
                shouldAmount: returnValue(field.yshouldAmount),//应激励金额
                arrivalConversion: returnValue(field.yarrivalConversion),//到岗折算（在岗天数）
                otherDeduction: returnValue(field.yotherDeduction),//其他扣除
                actualAmount: returnValue(field.yactualAmount),//激励金额 == 实际激励金额（月度对应的就是实际激励金额，年度的除此还有一个应激励金额）
                memberName: field.ymemberName,//激励人员
            };
        }
        var index = top.layer.msg("数据提交中，请稍等", {icon: 16, time: false, shade: 0.8});

        if ($(".sign").val() == "edit") {
            data.id = $(".sign").attr("signid");
            param.url = 'pc/excitation/update';
        } else {
            param.url = 'pc/excitation/save';
        }
        if(subName=="caogao"){
            data.status = "1";
        }else{
            data.status = "0";
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
    

    function yManagAmount() {
        //计算全部数据
        countAllData();
    }
    function loadRoleList() {
        param.url = 'pc/excitation/selectRoleList';
        var ycontractType = $(".ycontractType").val();
        ajaxJS(param, {contractType:ycontractType}, function (d) {

            var data = d.data;
            $('.yroleId').empty();
            if(($(".sign").attr("detail")=="detail")){
                $('.yroleId').append('<option value="">未选择</option>');
            }else{
                $('.yroleId').append('<option value="">请选择</option>');
            }
            for (var i = 0; i < data.length; i++) {
                var str1 = '<option rate="'+  data[i].cogExtendOne +'" value="' + data[i].cogValue + '">' + data[i].cogName + '</option>';
                $('.yroleId').append(str1)
            }
            $('.yroleId').val($('.yroleId').attr('val'));

            //计算全部数据
            // countAllData();
            form.render();
        });
    }
    
    function TaxAndWuye() {

        //税金计算 == 物业利润收入 * 税金百分比
        var mtax = $(".mpropertyProfit").val() * $(".mtaxPercentage").val()/100;
        //月度税金赋值
        if(mtax){
            $(".mtax").val(mtax.toFixed(2));
        }else {
            $(".mtax").val("0");
        }
        //激励基数计算 == 物业利润收入 - 税金
        var mcardinalNumber = $(".mpropertyProfit").val()-$(".mtax").val();
        if(mcardinalNumber || mcardinalNumber=="0"){
            $(".mcardinalNumber").val(mcardinalNumber.toFixed(2));
            if($(".mexcitationPercentage").val()){
                //激励金额计算 == 激励基数*激励百分比
                var mactualAmount = $(".mcardinalNumber").val() * $(".mexcitationPercentage").val()/100;
                if(mactualAmount){
                    $(".mactualAmount").val(mactualAmount.toFixed(2));
                }else{
                    $(".mactualAmount").val("0");
                }
            }
        }
    }

    //监听输入框只能输入0~100（包括0和100）的数字
    $(document).on("input propertychange",".mtaxPercentage",function(){
        var limitNum = $(this).val().replace(/[^0-9.]+/g, "");
        if(limitNum>=0&&limitNum<=100){
            $(this).val(limitNum);
        }else{
            $(this).val("");
        }
    })
    //监听输入框只能输入0~100（包括0和100）的数字
    $(document).on("input propertychange",".ytaxPercentage",function(){

        var limitNum = $(this).val().replace(/[^0-9.]+/g, "");
        if(limitNum>=0&&limitNum<=100){
            $(this).val(limitNum);
        }else{
            $(this).val("");
        }
    })

    function returnValue(data) {
        if(data){
            var regPos = /^\d+(\.\d+)?$/; //非负浮点数
            var regNeg = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; //负浮点数
            if(regPos.test(data) || regNeg.test(data)){
                return data;
            }else{
                return "0";
            }
        }
        return 0;
    }

    function countAllData() {
        //设计方案，  统一计算方法   一次变动，全部计算一遍
        //输入值
        var ypropertyProfit = returnValue($(".ypropertyProfit").val());//物业利润收入
        var ytaxPercentage =returnValue($(".ytaxPercentage").val());//税金百分比(%)
        var yotherCost = returnValue($(".yotherCost").val());//其他成本
        var yprofitIndex = returnValue($(".yprofitIndex").val());//年初利润指标
        var yarrivalConversion = returnValue($(".yarrivalConversion").val());//在岗天数
        var yotherDeduction = returnValue($(".yotherDeduction").val());//其他扣除
        var gudingRate = "0";//管理酬金，固定百分比
        var yexcitationPercentage = "0";//激励百分比
        //激励类别变动赋值管理酬金固定比列
        if($(".ycontractType").val()){
            gudingRate = "0.06";
        }
        //激励人员选择带出激励百分比
        var rate = $(".yroleId option:selected").attr("rate");
        if(rate){
            yexcitationPercentage = rate;
        }
        $(".yexcitationPercentage").val(yexcitationPercentage);//激励百分比

        //计算值
        //税金 = 物业利润收入/税金百分比
         var ytax = ypropertyProfit *  ytaxPercentage/100;
        $(".ytax").val(ytax.toFixed(2));

        //管理酬金  =  物业利润的固定6%  根据激励类别自动带出
        var ymanagementAmount = ypropertyProfit * gudingRate;
        $(".ymanagementAmount").val(ymanagementAmount.toFixed(2));
        //激励基数  == 物业利润-税金-管理酬金-其他成本-年初利润指标
        var ycardinalNumber = ypropertyProfit - ytax - ymanagementAmount - yotherCost - yprofitIndex;
        $(".ycardinalNumber").val(ycardinalNumber.toFixed(2));

        //应激励金额 == 激励基数*激励百分比
        var yshouldAmount = ycardinalNumber *  yexcitationPercentage/100;
        $(".yshouldAmount").val(yshouldAmount.toFixed(2));

        //到岗折算 ==  应激励金额*在岗天数/365
        var zhesuan =  yshouldAmount * yarrivalConversion /365;

        //实际激励金额  ==   到岗折算-其他扣除
        var yactualAmount = zhesuan - yotherDeduction;
        $(".yactualAmount").val(yactualAmount.toFixed(2));
    }

});
