package com.golte.resource.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作员工返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@NoArgsConstructor
public class GetPointExportInData {

    //城市名称
    private String cityName;

    //项目名称
    private String projectName;

    //一级点位名称
    private String firstPointName;

    //二级点位名称
    private String secondPointName;

    //三级点位名称
    private String thirdPointName;

    //资源名称
    private String assetsName;

    //资源编号
    private String assetsCode;

    //资源规格/平方
    private String assetsUnit;

    // 序号
    private String index;
}
