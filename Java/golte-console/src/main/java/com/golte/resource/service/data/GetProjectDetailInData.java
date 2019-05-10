package com.golte.resource.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 操作员工返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectDetailInData implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键ID
    private Long id;

    //点位父ID
    private Long parentId;

    //资源点位ID
    private Long resourceId;

    //资源三级点位ID
    private Long pointId;

    //资源点位等级1-一级点位 2-二级 3-三级
    private String level;

    //中心城市Id
    private Long centralCityId;

    //城市Id
    private Long cityId;

    //城市名称
    private String cityName;

    //项目名称
    private String projectName;

    //项目Id
    private Long projectId;

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

    //是否签约
    private String isSigned;

    //合同单价
    private BigDecimal amount;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    /**
     *token
     */
    private String token;

    /**
     * 有权限的城市ID集合
     */
    private List<Long> cityIds;

    //删除项目ID集合
    private List<Long> ids;


    //资源项目详细列表
    private List<GetProjectDetailInData> rProjectDetailList;


}
