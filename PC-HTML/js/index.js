var tab;
var addTab;
layui.config({
    base: "js/common/" //存放拓展模块的根目录
}).use(['bodyTab', 'form', 'element', 'layer', 'jquery'], function () {
    var form = layui.form, layer = layui.layer, $ = layui.jquery, element = layui.element;

    //全局的ajax请求参数
    var param = {
        jquery: $, layer: layer, url: 'pc/logout', type: 'post',
    };
    $('body').attr('style',"display:!important")
    var loginName = sessionStorage.getItem("loginName");
    $(".userName").html(loginName || "用户名");

    // 判断是否有未读消息
    $(".messageNum").css("display","block");
    $(".messageNum").html("1");

    var mySwiper = new Swiper('.swiper-container', {
        autoplay: true,
        direction: 'vertical',
    })


    //点击退出
    $(".signOut").click(function () {
        ajaxJS(param, '', function (d) {
            sessionStorage.clear();
            window.location.href = "login.html";
        });

    });

    tab = layui.bodyTab({
        openTabNum: "50",  //最大可打开窗口数量
        url: "pc/getResByUser"
    });

    //隐藏左侧导航
    $(".hideMenu").click(function () {
        $(".layui-layout-admin").toggleClass("showMenu");
        //渲染顶部窗口
        tab.tabMove();
    });

    //渲染左侧菜单
    tab.render(0);

    // 添加新窗口
    $("body").on("click", ".layui-nav .layui-nav-item a", function () {
        //如果不存在子级
        if ($(this).siblings().length == 0) {
            addTab($(this));
        }
        $(this).parent("li").siblings().removeClass("layui-nav-itemed");
    });


    //刷新当前
    $(".refresh").on("click", function () {  //此处添加禁止连续点击刷新一是为了降低服务器压力，另外一个就是为了防止超快点击造成chrome本身的一些js文件的报错(不过貌似这个问题还是存在，不过概率小了很多)
        if ($(this).hasClass("refreshThis")) {
            $(this).removeClass("refreshThis");
            $(".clildFrame .layui-tab-item.layui-show").find("iframe")[0].contentWindow.location.reload(true);
            setTimeout(function () {
                $(".refresh").addClass("refreshThis");
            }, 2000)
        } else {
            layer.msg("您点击的速度超过了服务器的响应速度，还是等两秒再刷新吧！");
        }
    })

    //关闭其他
    $(".closePageOther").on("click", function () {
        if ($("#top_tabs li").length > 2 && $("#top_tabs li.layui-this cite").text() != "后台首页") {
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
        } else if ($("#top_tabs li.layui-this cite").text() == "后台首页" && $("#top_tabs li").length > 1) {
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '' && !$(this).hasClass("layui-this")) {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
        } else {
            layer.msg("没有可以关闭的窗口了");
        }
        //渲染顶部窗口
        tab.tabMove();
    });

    //关闭全部
    $(".closePageAll").on("click", function () {
        if ($("#top_tabs li").length > 1) {
            $("#top_tabs li").each(function () {
                if ($(this).attr("lay-id") != '') {
                    element.tabDelete("bodyTab", $(this).attr("lay-id")).init();
                }
            })
            document.location.reload();
        } else {
            layer.msg("没有可以关闭的窗口了");
        }
        //渲染顶部窗口
        tab.tabMove();
    })
})

//打开新窗口
addTab = function (_this) {
    tab.tabAdd(_this);
}

function navBar(d) {
    var data = d.data.top;
    var number = d.data.number;
    var numHtml =  "";
    if(number !='0'){
        numHtml =  '<span class="layui-badge messageNum">'+  number +'</span>';
    }
    var ulHtml = '<ul class="layui-nav layui-nav-tree">' +
        '<li class="layui-nav-item">' +
        '<a href="javascript:;" data-url="html/main.html">' +
        '<i class="iconfont"></i>' +
        '<cite>后台首页</cite>' +
        '</a>' +
        '</li>' +
        '<li class="layui-nav-item">' +
        '<a href="javascript:;" data-url="html/message.html">' +
        '<i class="iconfont"></i>' +
        '<cite>我的消息</cite>' +
         numHtml +
        '</a>' +
        '</li>';

    for (var i = 0; i < data.length; i++) {
        ulHtml += '<li class="layui-nav-item">';
        if (data[i].nodes != undefined && data[i].nodes.length > 0) {
            ulHtml += '<a href="javascript:;">';
            if (data[i].icon != undefined && data[i].icon != '') {
                if (data[i].icon.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + data[i].icon + '" data-icon="' + data[i].icon + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].name + '</cite>';
            ulHtml += '<span class="layui-nav-more"></span>';
            ulHtml += '</a>';
            ulHtml += '<dl class="layui-nav-child">';
            for (var j = 0; j < data[i].nodes.length; j++) {
                if (data[i].nodes[j].target == "_blank") {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + data[i].nodes[j].url + '" target="' + data[i].nodes[j].target + '">';
                } else {
                    ulHtml += '<dd><a href="javascript:;" data-url="' + data[i].nodes[j].url + '">';
                }
                if (data[i].nodes[j].icon != undefined && data[i].nodes[j].icon != '') {
                    if (data[i].nodes[j].icon.indexOf("icon-") != -1) {
                        ulHtml += '<i class="iconfont ' + data[i].nodes[j].icon + '" data-icon="' + data[i].nodes[j].icon + '"></i>';
                    } else {
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].nodes[j].icon + '">' + data[i].nodes[j].icon + '</i>';
                    }
                }
                ulHtml += '<cite>' + data[i].nodes[j].name + '</cite></a></dd>';
            }
            ulHtml += "</dl>";
        } else {
            if (data[i].target == "_blank") {
                ulHtml += '<a href="javascript:;" data-url="' + data[i].url + '" target="' + data[i].target + '">';
            } else {
                ulHtml += '<a href="javascript:;" data-url="' + data[i].url + '">';
            }
            if (data[i].icon != undefined && data[i].icon != '') {
                if (data[i].icon.indexOf("icon-") != -1) {
                    ulHtml += '<i class="iconfont ' + data[i].icon + '" data-icon="' + data[i].icon + '"></i>';
                } else {
                    ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                }
            }
            ulHtml += '<cite>' + data[i].name + '</cite></a>';
        }
        ulHtml += '</li>';
    }
    ulHtml += '</ul>';
    return ulHtml;
}

function showImg(url) {
    $('#layerPic img').attr('src', url);
    setTimeout(function () {
        $('#layerPic').fadeIn()
    }, 20)
}

$('.layer-bg').click(function () {
    $('#layerPic').fadeOut()
});

function goLogin() {
    if (sessionStorage.getItem('loginUrl') == null || sessionStorage.getItem('loginUrl') == '') {
        // parent.location.href = '/login.html';
        parent.location.href = '/golte/login.html';//线上
    } else {
        parent.location.href = sessionStorage.getItem('loginUrl');
    }
}

function mainJump(_this) {
    addTab(_this)
}