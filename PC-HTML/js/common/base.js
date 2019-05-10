UrlParm = (function() {
  var b, a
  ;(function c() {
    b = []
    a = {}
    var e = window.location.search.substr(1)
    if (e != '') {
      e = e.replace('*', '&')
      var g = decodeURIComponent(e).split('&')
      for (var f = 0, d = g.length; f < d; f++) {
        if (g[f] != '') {
          var h = g[f].split('=')
          if (h.length == 1 || (h.length == 2 && h[1] == '')) {
            b.push([''])
            a[h[0]] = b.length - 1
          } else {
            if (typeof h[0] == 'undefined' || h[0] == '') {
              b[0] = [h[1]]
            } else {
              if (typeof a[h[0]] == 'undefined') {
                b.push([h[1]])
                a[h[0]] = b.length - 1
              } else {
                b[a[h[0]]].push(h[1])
              }
            }
          }
        }
      }
    }
  })()
  return {
    parm: function(f) {
      try {
        return typeof f == 'number' ? b[f][0] : b[a[f]][0]
      } catch (d) {}
    },
    parmValues: function(f) {
      try {
        return typeof f == 'number' ? b[f] : b[a[f]]
      } catch (d) {}
    },
    hasParm: function(d) {
      return typeof d == 'string' ? typeof a[d] != 'undefined' : false
    },
    parmMap: function() {
      var g = {}
      try {
        for (var f in a) {
          g[f] = b[a[f]]
        }
      } catch (d) {}
      return g
    }
  }
})()
!(function(a) {
  function b(a, b) {
    var c = (65535 & a) + (65535 & b),
      d = (a >> 16) + (b >> 16) + (c >> 16)
    return (d << 16) | (65535 & c)
  }

  function c(a, b) {
    return (a << b) | (a >>> (32 - b))
  }

  function d(a, d, e, f, g, h) {
    return b(c(b(b(d, a), b(f, h)), g), e)
  }

  function e(a, b, c, e, f, g, h) {
    return d((b & c) | (~b & e), a, b, f, g, h)
  }

  function f(a, b, c, e, f, g, h) {
    return d((b & e) | (c & ~e), a, b, f, g, h)
  }

  function g(a, b, c, e, f, g, h) {
    return d(b ^ c ^ e, a, b, f, g, h)
  }

  function h(a, b, c, e, f, g, h) {
    return d(c ^ (b | ~e), a, b, f, g, h)
  }

  function i(a, c) {
    ;(a[c >> 5] |= 128 << c % 32), (a[(((c + 64) >>> 9) << 4) + 14] = c)
    var d,
      i,
      j,
      k,
      l,
      m = 1732584193,
      n = -271733879,
      o = -1732584194,
      p = 271733878
    for (d = 0; d < a.length; d += 16) {
      ;(i = m),
        (j = n),
        (k = o),
        (l = p),
        (m = e(m, n, o, p, a[d], 7, -680876936)),
        (p = e(p, m, n, o, a[d + 1], 12, -389564586)),
        (o = e(o, p, m, n, a[d + 2], 17, 606105819)),
        (n = e(n, o, p, m, a[d + 3], 22, -1044525330)),
        (m = e(m, n, o, p, a[d + 4], 7, -176418897)),
        (p = e(p, m, n, o, a[d + 5], 12, 1200080426)),
        (o = e(o, p, m, n, a[d + 6], 17, -1473231341)),
        (n = e(n, o, p, m, a[d + 7], 22, -45705983)),
        (m = e(m, n, o, p, a[d + 8], 7, 1770035416)),
        (p = e(p, m, n, o, a[d + 9], 12, -1958414417)),
        (o = e(o, p, m, n, a[d + 10], 17, -42063)),
        (n = e(n, o, p, m, a[d + 11], 22, -1990404162)),
        (m = e(m, n, o, p, a[d + 12], 7, 1804603682)),
        (p = e(p, m, n, o, a[d + 13], 12, -40341101)),
        (o = e(o, p, m, n, a[d + 14], 17, -1502002290)),
        (n = e(n, o, p, m, a[d + 15], 22, 1236535329)),
        (m = f(m, n, o, p, a[d + 1], 5, -165796510)),
        (p = f(p, m, n, o, a[d + 6], 9, -1069501632)),
        (o = f(o, p, m, n, a[d + 11], 14, 643717713)),
        (n = f(n, o, p, m, a[d], 20, -373897302)),
        (m = f(m, n, o, p, a[d + 5], 5, -701558691)),
        (p = f(p, m, n, o, a[d + 10], 9, 38016083)),
        (o = f(o, p, m, n, a[d + 15], 14, -660478335)),
        (n = f(n, o, p, m, a[d + 4], 20, -405537848)),
        (m = f(m, n, o, p, a[d + 9], 5, 568446438)),
        (p = f(p, m, n, o, a[d + 14], 9, -1019803690)),
        (o = f(o, p, m, n, a[d + 3], 14, -187363961)),
        (n = f(n, o, p, m, a[d + 8], 20, 1163531501)),
        (m = f(m, n, o, p, a[d + 13], 5, -1444681467)),
        (p = f(p, m, n, o, a[d + 2], 9, -51403784)),
        (o = f(o, p, m, n, a[d + 7], 14, 1735328473)),
        (n = f(n, o, p, m, a[d + 12], 20, -1926607734)),
        (m = g(m, n, o, p, a[d + 5], 4, -378558)),
        (p = g(p, m, n, o, a[d + 8], 11, -2022574463)),
        (o = g(o, p, m, n, a[d + 11], 16, 1839030562)),
        (n = g(n, o, p, m, a[d + 14], 23, -35309556)),
        (m = g(m, n, o, p, a[d + 1], 4, -1530992060)),
        (p = g(p, m, n, o, a[d + 4], 11, 1272893353)),
        (o = g(o, p, m, n, a[d + 7], 16, -155497632)),
        (n = g(n, o, p, m, a[d + 10], 23, -1094730640)),
        (m = g(m, n, o, p, a[d + 13], 4, 681279174)),
        (p = g(p, m, n, o, a[d], 11, -358537222)),
        (o = g(o, p, m, n, a[d + 3], 16, -722521979)),
        (n = g(n, o, p, m, a[d + 6], 23, 76029189)),
        (m = g(m, n, o, p, a[d + 9], 4, -640364487)),
        (p = g(p, m, n, o, a[d + 12], 11, -421815835)),
        (o = g(o, p, m, n, a[d + 15], 16, 530742520)),
        (n = g(n, o, p, m, a[d + 2], 23, -995338651)),
        (m = h(m, n, o, p, a[d], 6, -198630844)),
        (p = h(p, m, n, o, a[d + 7], 10, 1126891415)),
        (o = h(o, p, m, n, a[d + 14], 15, -1416354905)),
        (n = h(n, o, p, m, a[d + 5], 21, -57434055)),
        (m = h(m, n, o, p, a[d + 12], 6, 1700485571)),
        (p = h(p, m, n, o, a[d + 3], 10, -1894986606)),
        (o = h(o, p, m, n, a[d + 10], 15, -1051523)),
        (n = h(n, o, p, m, a[d + 1], 21, -2054922799)),
        (m = h(m, n, o, p, a[d + 8], 6, 1873313359)),
        (p = h(p, m, n, o, a[d + 15], 10, -30611744)),
        (o = h(o, p, m, n, a[d + 6], 15, -1560198380)),
        (n = h(n, o, p, m, a[d + 13], 21, 1309151649)),
        (m = h(m, n, o, p, a[d + 4], 6, -145523070)),
        (p = h(p, m, n, o, a[d + 11], 10, -1120210379)),
        (o = h(o, p, m, n, a[d + 2], 15, 718787259)),
        (n = h(n, o, p, m, a[d + 9], 21, -343485551)),
        (m = b(m, i)),
        (n = b(n, j)),
        (o = b(o, k)),
        (p = b(p, l))
    }
    return [m, n, o, p]
  }

  function j(a) {
    var b,
      c = ''
    for (b = 0; b < 32 * a.length; b += 8) {
      c += String.fromCharCode((a[b >> 5] >>> b % 32) & 255)
    }
    return c
  }

  function k(a) {
    var b,
      c = []
    for (c[(a.length >> 2) - 1] = void 0, b = 0; b < c.length; b += 1) {
      c[b] = 0
    }
    for (b = 0; b < 8 * a.length; b += 8) {
      c[b >> 5] |= (255 & a.charCodeAt(b / 8)) << b % 32
    }
    return c
  }

  function l(a) {
    return j(i(k(a), 8 * a.length))
  }

  function m(a, b) {
    var c,
      d,
      e = k(a),
      f = [],
      g = []
    for (
      f[15] = g[15] = void 0, e.length > 16 && (e = i(e, 8 * a.length)), c = 0;
      16 > c;
      c += 1
    ) {
      ;(f[c] = 909522486 ^ e[c]), (g[c] = 1549556828 ^ e[c])
    }
    return (d = i(f.concat(k(b)), 512 + 8 * b.length)), j(i(g.concat(d), 640))
  }

  function n(a) {
    var b,
      c,
      d = '0123456789abcdef',
      e = ''
    for (c = 0; c < a.length; c += 1) {
      ;(b = a.charCodeAt(c)), (e += d.charAt((b >>> 4) & 15) + d.charAt(15 & b))
    }
    return e
  }

  function o(a) {
    return unescape(encodeURIComponent(a))
  }

  function p(a) {
    return l(o(a))
  }

  function q(a) {
    return n(p(a))
  }

  function r(a, b) {
    return m(o(a), o(b))
  }

  function s(a, b) {
    return n(r(a, b))
  }

  function t(a, b, c) {
    return b ? (c ? r(b, a) : s(b, a)) : c ? p(a) : q(a)
  }

  'function' == typeof define && define.amd
    ? define(function() {
        return t
      })
    : (a.md5 = t)
})(this)

/**
 * 本地联调地址
 * @type {string}
 */
var interfaceUrl = 'http://172.16.0.68:9203/'; //俞强

/**
 * 测试环境
 * @type {string}
 */
// var interfaceUrl = 'http://221.224.53.27:10022/';

var logToken = sessionStorage.getItem('logToken')

var ajaxJS = function(param, data, callback) {
  var $ = param.jquery
  var layer = param.layer
  var url = param.url
  var type = param.type
  data = data == null || data == '' || typeof data == 'undefined' ? {} : data

  $.ajaxSetup({
    statusCode: {
      404: function() {
        parent.location.href = 'error.html'
      },
      500: function() {
        parent.location.href = 'error.html'
      }
    }
  })
  $.ajax({
    url: interfaceUrl + url,
    type: type,
    data: JSON.stringify(data),
    contentType: 'application/json',
    dataType: 'json',
    async: false,
    crossDomain: true == !(document.all),
    beforeSend: function(request) {
      request.setRequestHeader('X-Token', logToken)
    },
    success: function(d) {
      if (d.code === 200) {
        callback(d)
      } else if (d.code === 404) {
        layer.msg("您还没登录，请先登录！")
        setTimeout(function() {
          if (parent.goLogin) {
            parent.goLogin()
          } else {
            if (parent.parent.goLogin) {
              parent.parent.goLogin()
            }
          }
        }, 500)
      } else {
          layer.msg(d.message)
      }
    },
    error:function(){
      if(window.navigator.onLine==true) {　

        　　　　
        
      }else{
        layer.msg("网络中断，请重新联网")
      }
      
    }
  })
}

function addMd5(data, serviceDate, nonce) {
  var key = '_TIANSHI#9q6w3e#!'
  return md5(md5(JSON.stringify(data) + serviceDate + nonce) + key)
}

var chars = [
  '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
]

function generateMixed(n) {
  var res = ''
  for (var i = 0; i < n; i++) {
    var id = Math.ceil(Math.random() * 35)
    res += chars[id]
  }
  return res
}

//封装全局数据表格渲染
var tableIns
var tableInit = function(table, url, colArr, where, callback) {
  tableIns = table.render({
    elem: '#tableList',
    url: interfaceUrl + url,
    method: 'post',
    contentType: 'application/json',
    page: {
      layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'], //自定义分页布局
      curr: 1, //设定初始在第 5 页
      groups: 10 //只显示 1 个连续页码
    },
    limit: 10,
    limits: [10, 50, 100, 200],
    id: 'tableList',
    cols: colArr,
    request: {
      pageName: 'pageNum', //页码的参数名称，默认：page
      limitName: 'pageSize' //每页数据量的参数名，默认：limit
    },
    response: {
      countName: 'total',
      dataName: 'list'
    },
    where: where,
    done: function(res) {
      if (res) {
        if (callback) {
          callback()
        }
      }
        //得到当前页码
        layui.jquery('.layui-laypage-skip input').bind('input propertyChange', function () {
            if (layui.jquery(this).val() === '0') {
                layui.jquery(this).val(1)
            }
        })
        //得到数据总量
    }
  })
}

//封装搜索方法
var isDown

function search($, table, form, whereList) {
  table.reload('tableList', {
    page: {
      curr: 1 //重新从第 1 页开始
    },
    where: whereList
  })
  $('.screen-wrapper')
    .stop()
    .slideUp()
  isDown = true
  $('.screen span').html("筛选")
}

layui.use(['table', 'jquery', 'layer'], function() {
  var $ = layui.jquery,
    table = layui.table,
    layer = parent.layer === undefined ? layui.layer : top.layer

  isDown = true
  $('.screen').click(function() {
    if (isDown) {
      $('.screen-wrapper')
        .stop()
        .slideDown()
      isDown = !isDown
      $('.screen span').html("收起")
    } else {
      $('.screen-wrapper')
        .stop()
        .slideUp()
      isDown = !isDown
      $('.screen span').html("筛选")
    }
  })

  //重置
  $('.reset').click(function() {
    table.reload('tableList', {
      page: {
        curr: 1 //重新从第 1 页开始
      },
      where: {}
    })
    $('.screen-wrapper')
      .stop()
      .slideUp()
    isDown = true
    $('.screen span').html("筛选")
  })

  /*图片预览*/
  $(document).click(function(e) {
    if (e.target.className === 'preview') {
      if (parent.showImg) {
        parent.showImg(e.target.src)
      } else {
        if (parent.parent.showImg) {
          parent.parent.showImg(e.target.src)
        }
      }
    }
  })

  $('.layui-tab-title li').click(function() {
    $('.layui-layer-content').remove()
  })

  $('.closeImg').click(function(e) {
    var e = e || event
    e.cancelBubble = true
    e.stopPropagation()

    $(this).parent().hide().find('img').attr('src', '');
    $(this).parent().parent().find('.upload-wrapper').show();
    $(this).hide().find('img').attr('src', '');
    $(this).find('img').attr('smallpic', '');
    $(this).find('img').attr('video', '');
    $(this).find('video').attr('smallpic', '');
    $(this).find('video').attr('src', '');
    $(this).find('video').attr('bigpic', '');
    $(this).parent().find('img').attr('flag', '')

    // $('.upload-wrapper').show();
    // $('.showImg').hide().find('img').attr('src', '');
    // $('.showImg').find('img').attr('smallpic', '');
    // $('.showImg').find('img').attr('video', '');
    // $('.showImg').find('video').attr('smallpic', '');
    // $('.showImg').find('video').attr('src', '');
    // $('.showImg').find('video').attr('bigpic', '');
    // $('.showImg img').attr('flag','')
  })
  $('.name').html("名称")
  $('.color').html("颜色")

  /*添加标签*/
  $('.memAdd').click(function() {
    var index = layer.open({
      title: "添加",
      type: 2,
      area: ['380px', '350px'],
      content: 'html/news_setup/newsMessage/tag.html',
      resize: false,
      btn: "提交",
      yes: function(index, layero) {
        var body = layer.getChildFrame('body', index)
        var name = body.find('.tagName').val()
        var color = body.find('.tagColor').val()
        if (color == '') {
          color = '#ec101a'
        }
        if (name == '') {
          layer.msg("标签名称为空，请输入")
          return
        }
        if ($('.tag').length > 5) {
          layer.msg("标签不可过多")
          return
        }
        for (var i = 0; i < $('.tag').length; i++) {
          if (
            $('.tag')
              .eq(i)
              .find('.tagText')
              .html() === name &&
            $('.tag').length >= 1
          ) {
            layer.msg("该标签已存在")
            return
          }
        }
        addTag(name, color)
        layer.close(index)
      }
    })
  })

  function addTag(name, color) {
    var str =
      ' <li class="tag" style="background-color: ' +
      color +
      ';">' +
      '       <img src="../../../images/tag-cover.png">' +
      '       <span class="tagText">' +
      name +
      '</span>' +
      '       <i class="delTag">×</i>' +
      '       </li>'
    $('.tagList').append(str)
    $('.delTag').each(function() {
      $(this).click(function() {
        $(this)
          .parents('.tag')
          .remove()
      })
    })
  }

  /*生成随机数*/
  $('.num-hook').each(function() {
    $(this).click(function() {
      $(this).val((Math.random() * 10000) | 0)
    })
  })
})

/**
 * 验证手机号
 */
function checkPhone(phone) {
    if (!phone) {
      return false;
    }
    if (phone.length !== 10) {
      return false;
    }
    return true;
}

function getDay(day){
  var today = new Date();
  var targetday_milliseconds=today.getTime() + 1000*60*60*24*day;
  today.setTime(targetday_milliseconds); //注意，这行是关键代码
  var tYear = today.getFullYear();
  var tMonth = today.getMonth();
  var tDate = today.getDate();
  tMonth = doHandleMonth(tMonth + 1);
  tDate = doHandleMonth(tDate);
  return tYear+"-"+tMonth+"-"+tDate;
}

function doHandleMonth(month){
  var m = month;
  if(month.toString().length == 1){
    m = "0" + month;
  }
  return m;
}
