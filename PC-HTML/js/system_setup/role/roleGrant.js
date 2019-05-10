layui.use(['layer', 'jquery'], function () {
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : top.layer;

    var roleId = UrlParm.parm("roleId");//从链接地址获取roleId

    //全局设置ajax请求参数
    var param = {
        jquery: $, layer: layer, url: '', type: 'post',
    };

    var setting = {
        async: {
            enable: true,
            type: "post",
            contentType: "application/json",
            url: interfaceUrl + "pc/resource/role/select?roleId=" + roleId,
            dataType: 'json'
        },
        check: {
            enable: true
        }
    };
    $.fn.zTree.init($("#tree"), setting);
    var treeObj = $.fn.zTree.getZTreeObj("tree");

    //全选
    var isAll = false;
    $('#allChoose').click(function () {
        treeObj.checkAllNodes(true);
        isAll = true;
    });
    //取消全选
    $('#cancelChoose').click(function () {
        treeObj.checkAllNodes(false);
    });

    //反选
    $('#backChoose').click(function () {
        function filter(node) {
            return (node.checked == false);
        }
        var treeArr = treeObj.getNodesByFilter(filter);
        if(treeArr.length == 0){

            treeObj.checkAllNodes(false);
        }else{
            var nodes = treeObj.getCheckedNodes(true);
            treeObj.checkAllNodes(true);
            for (var i = 0; i < nodes.length; i++) {


                
                    treeObj.checkNode(nodes[i], false);
                    
                
            }
            var nodes2=treeObj.getCheckedNodes(false)
            console.log(nodes2)
            var k1,k2,count2=0,count3=0
            for(var j=0;j<nodes2.length;j++){
                if(nodes2[j].level=='0'){
                    k1=nodes2[j].nodes.length
                }
                if(nodes2[j].level=='1'){
                    k2=nodes2[j].nodes.length
                    count2++
                }
                if(nodes2[j].level=='2'){
                    count3++
                }
            }
            console.log(k1,count2)
            if(k1>count2){
                for(var z=0;z<nodes2.length;z++){
                    if(nodes2[z].level=='0'){
                        treeObj.checkNode(nodes2[z], true);
                    }
                }
            }

            if(k2>count3){
                for(var j1=0;j1<nodes2.length;j1++){
                    if(nodes2[j1].level=='1'){
                        treeObj.checkNode(nodes2[j1], true);
                    }
                }
            }
        }
        isAll = false;
    });

    $("#grantBtn").click(function () {
        var nodes = treeObj.getCheckedNodes(true);
        var values = [];
        for (var i = 0; i < nodes.length; i++) {
            values.push(nodes[i].id);
        }
        param.url = 'pc/role/authorization';
        ajaxJS(param, {id: roleId, resourceIds: values}, function (d) {
            if (d.code === 200) {
                top.layer.msg(d.message);
                layer.closeAll("iframe");
                //刷新父页面
                $(".layui-tab-item.layui-show", parent.document).find("iframe")[0].contentWindow.location.reload();
            } else {
                layer.msg(d.message)
            }
        })
    });

    $("#cancel").click(function () {
        layer.closeAll("iframe");
    });
});

function goLogin() {
    parent.goLogin()
}

