package com.golte.resource.service.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@NoArgsConstructor
public class GetPointExportOutData implements Serializable {

    //城市名称
    private String cityName;

    //项目名称
    private String projectName;

    //资产点位名称
    private String zcName;

    //资源编号
    private String assetsCode;

    //资源名称
    private String assetsName;

    //合作商名称
    private String merchantName;

    //合同名称
    private String contractName;

    //合同单价
    private BigDecimal amount;

}
