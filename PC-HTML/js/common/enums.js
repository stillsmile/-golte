var enableEnum = {
    VALID: 1,
    INVALID: 0,
    properties: {
        1: {name: "有效"},
        0: {name: "无效"}
    }
}
var paybackTypeEnum = {
    UNPAYBACK: 1,
    PAYBACKING: 2,
    PAYBACK: 3,
    properties: {
        1: {name: "未回款"},
        2: {name: "部分回款"},
        3: {name: "已回款"}
    }
}
var contractStatusEnum = {
    UNBEGIN: 1,
    BEGINING: 2,
    CHANGING: 3,
    APRROBED: 4,
    FINISHED: 5,
    TERMINATED: 6,
    properties: {
        1: {name: "未开始"},
        2: {name: "进行中"},
        3: {name: "变更审批中"},
        4: {name: "终止审批中"},
        5: {name: "已结束"},
        6: {name: "已终止"}
    }
}
var heGeEnum = {
    QUALIFIED: 1,
    UNQUALIFIED: 0,
    properties: {
        1: {name: "是"},
        0: {name: "否"}
    }
}
var EXCITATIONTYPE = {
    MONTH: 1,
    YEAR: 2,
    properties: {
        1: {name: "月度"},
        2: {name: "年度"}
    }
}

var CONTRACTTYPE = {
    ADVERTISEMENT: 1,
    FIELD: 2,
    TEMP: 3,
    SERVICE: 4,
    OTHER: 5,
    properties: {
        1: {name: "广告资源"},
        2: {name: "场地资源"},
        3: {name: "临时摆展"},
        4: {name: "服务产品"},
        5: {name: "其他"},
        '2,5': {name: "管理收入"}
    }
}

var levelEnum = {
    COUNTRY: 1,
    CITY: 2,
    PROJECT: 3,
    properties: {
        1: {name: "全国"},
        2: {name: "城市"},
        3: {name: "项目"}
    }
}
