package com.golte.resource.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 操作员工返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectDetailOutData implements Serializable {
    private static final long serialVersionUID = -4510879035572396712L;

    //主键ID
    private Long id;

    //城市名称
    private String cityName;

    //项目名称
    private String projectName;

    //项目Id
    private Long projectId;

    //三级节点Id查一级节点Id
    private Long firstPointId;

    //三级节点Id查一级节点Id
    private Long towPointId;

    //一级点位名称
    private String firstPointName;

    //二级点位名称
    private String towPointName;

    //三级点位名称
    private String threePointName;

    //三级点位ID
    private Long pointId;

    //资产点位名称
    private String zcName;

    //资源编号
    private String assetsCode;

    //资源名称
    private String assetsName;

    //资源规格/平方
    private String assetsUnit;

    //合作商名称
    private String merchantName;

    //合同名称
    private String contractName;

    //合同单价
    private BigDecimal amount;


}
