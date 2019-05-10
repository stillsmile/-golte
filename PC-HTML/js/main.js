layui.use(['form', 'layer', 'table', 'jquery', 'element', 'laydate'], function () {
    var form = layui.form,
        $ = layui.jquery,
        laydate = layui.laydate,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    var myDate = new Date();
    //年选择器
    laydate.render({
        elem: '#yyear'
        ,type: 'year'
        ,value:  myDate.getFullYear()
        ,done:function(value,date){//value, date, endDate点击日期、清空、现在、确定均会触发。回调返回三个参数，分别代表：生成的值、日期时间对象、结束的日期时间对象
            setTimeout(function () {
                //日期选择的变动时，加载表格的数据
                loadTable();
                //加载图标的数据
                loadCharts();
            }, 200);
        }
    });

    //加载对应权限下的城市列表
    param.url = 'pc/citymanage/selectCitys';
    ajaxJS(param, {}, function (d) {
        if(d.data.type =='1'){
            $(".cityId").empty().append('<option value="">全国</option>');
        }
        var data = d.data.cityList;
        var defaultValue = "";
        var allCity = "";
        for (var i = 0; i < data.length; i++) {
            if(d.data.type =="" && i=='0'){
                defaultValue = data[i].id;
            }
            var str1 = '<option value="' + data[i].id + '">' + data[i].cityName + '</option>';
            $(".cityId").append(str1)
        }
        $(".cityId").val(defaultValue);
        form.render();

    });


    //城市联动
    form.on('select(cityId)', function(data){
        //城市选择的变动时，加载对应的图标的数据
        loadCharts();
        loadTable();
        form.render('select');
        form.render();
    });

    //加载年度数据
    function loadTable() {
        // //数据表格初始化
        var cityId = $(".cityId").val();
        var year = $(".yyear").val();
        param.url = 'pc/main/select';
        ajaxJS(param, {cityId: cityId ,year:year}, function (d) {
            $("#yearTarget").html("￥" + d.data.yearTarget);
            $("#amount").html("￥" + d.data.amount);
            $("#rate").html( d.data.rate + "%");

        });
    }

    //页面初始化加载图标数据
    setTimeout(function () {
        //加载图标信息
        loadCharts();
        loadTable();
    }, 200);


    //加载饼图和柱状图
    function loadCharts() {
        var args={
            cityId: $('.cityId').val(),//城市Id
            year: $('.yyear').val(),//年份
        };
        //资源类别使用情况 Top6
        param.url = 'pc/main/selectTop6';
        ajaxJS(param, args, function (d) {
            if (d.data) {
                drawChart('monthChart',d.data);
            }

        });
        //项目成交金额 Top10
        param.url = 'pc/main/selectTop10';
        ajaxJS(param, args, function (d) {
            if (d.data) {
                drawChart3('resourceChart',d.data);
            }
        });
        //资源类别使用情况 Top6
        param.url = 'pc/main/selectContractTop6';
        ajaxJS(param, args, function (d) {
            if (d.data) {
                drawChart('contractChart',d.data);
            }

        });
        //项目成交金额 Top10
        param.url = 'pc/main/selectMerchantTop10';
        ajaxJS(param, args, function (d) {
            if (d.data) {
                drawChart3('merchatChart',d.data);
            }
        });

    }

    //添加和编辑
    function deTail(url, title, data) {     //两个参数，title：弹出框标题。edit：如果有值表示该操作为编辑
        var index = layer.open({
            title: title,
            type: 2,
            area: ["900px", "600px"],
            content: url,
            resize: false,
            success: function (layero, index) {
                var body = $($(".layui-layer-iframe", parent.document).find("iframe")[0].contentWindow.document.body);
                body.find(".type").val(data.type);
                body.find(".typeName").val(data.typeName);
                body.find(".year").val(data.year);
                body.find(".cityId").val(data.cityId);
            }
        })
    }


    function drawChart(id,data) {
        var datas;
        var showFlag = true;

        if (data.series==null){
            datas=[];
            showFlag = false;
        }else{
            datas = data.series[0].data;
        }
        if (data.legend==null){
            data.legend=[];
        }
        if (data.title==null){
            data.title ={
                text:""
            };
        }

        var myChart = echarts.init(document.getElementById(id));
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data:data.legend.data
            },
            series:  [
                {
                    name:data.title.text,
                    type:'pie',
                    radius: ['30%', '50%'],
                    avoidLabelOverlap: false,
                    label: {
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            },
                            formatter:'{d}%'
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:datas
                }
            ]
        };

        myChart.setOption(option);
        if (!showFlag){
            myChart.setOption(option ,false);
            $("#"+id).css("background","url(../images/no_data.png) no-repeat center center");
            myChart.setOption(option,false);
        } else {
            $("#"+id).css("background","");
        }

        myChart.off('click');//防止事件重复注册，弹出多个frame框
        myChart.on('click', function (params) {

            //类型名称
            var typeName =params.name;
            var  type = "";
            if(params.seriesName=="资源"){
                type="1"
            }
            if(params.seriesName=="类别"){
                type="3"
            }
            var detailData = {
                type: type,
                cityId: $(".cityId").val(),
                year: $(".yyear").val(),
                typeName: typeName
            }
            deTail("html/mainAdd.html",  "排行详细", detailData);
        });
    }

    function drawChart3(id,data) {
        var myChart = echarts.init(document.getElementById(id));

        var showFlag = true;
        if (data.values==null){
            data.values=[];
            showFlag = false;
        }
        if (data.legend==null){
            data.legend=[];
        }
        if (data.title==null){
            data.title ={
                text:""
            };
        }

        var option = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            xAxis : [
                {
                    type : 'category',
                    triggerEvent: true,
                    show : showFlag,
                    data : data.legend.data,
                    name : data.xAxis,
                    axisLabel: {
                        interval:0,
                        rotate:20,
                        formatter: function (value, index) {
                            // 10 6 这些你自定义就行
                            var v = value.substring(0, 8) + '...'
                            return value.length > 10 ? v : value
                        }
                    },
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    show : showFlag,
                    name : "金额（元）",
                    type : 'value'
                }
            ],grid: {
                left: '18%',
                bottom:'18%'
            },
            series : [
                {
                    name:data.title.text,
                    type:'bar',
                    barWidth: '60%',
                    data:data.values
                }
            ]
        };

        myChart.setOption(option);

        extension(myChart);

        function extension(mychart) {
            //判断是否创建过div框,如果创建过就不再创建了
            var id = document.getElementById("extension");
            if(!id) {
                var div = "<div id = 'extension' sytle=\"display:none\"></div>"
                $('html').append(div);
            }

            mychart.on('mouseover', function(params) {
                if(params.componentType == "xAxis") {
                    $('#extension').css({
                        "position": "absolute",
                        "color": "black",
                        //"border":"solid 2px white",
                        "font-family": "Arial",
                        "font-size": "10px",
                        "padding": "5px",
                        "display": "inline"
                    }).text(params.value);

                    $("html").mousemove(function(event) {
                        var xx = event.pageX - 30;
                        var yy = event.pageY + 20;
                        $('#extension').css('top', yy).css('left', xx);
                    });
                }
            });

            mychart.on('mouseout', function(params) {
                if(params.componentType == "xAxis") {
                    $('#extension').css('display', 'none');
                }
            });

        };
        if (!showFlag){
            myChart.setOption(option ,false);
            $("#"+id).css("background","url(../images/no_data.png) no-repeat center center");
            myChart.setOption(option,false);
        } else {
            $("#"+id).css("background","");
        }

        myChart.off('click');//防止事件重复注册，弹出多个frame框
        myChart.on('click', function (params) {

            var type= "";
            if(params.seriesName=="项目成交金额"){
                type="2"
            }
            if(params.seriesName=="商家成交金额"){
                type="4"
            }
            //类型名称
            var typeName =params.name;

            var detailData = {
                type: type,
                cityId: $(".cityId").val(),
                year: $(".yyear").val(),
                typeName: typeName
            }
            deTail("html/mainAdd.html", "排行详细", detailData);
        });

    }

});