package com.golte.business.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 操作商家入参
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMerchantInData implements Serializable {
    private static final long serialVersionUID = 1L;


    //商家Id
    private Long id;

    //评估表Id
    private Long evaluationId;

    //商家编号
    private String code;

    //商家名称
    private String name;

    //商家等级（1全国 2城市 3项目）
    private String level;

    //联系人1
    private String contact;

    //联系方式1
    private String contactPhone;

    //联系人2
    private String contact2;

    //联系方式2
    private String contactPhone2;

    //城市（省 市）
    private String city;

    //中心城市Id
    private Long centralCityId;

    //城市Id
    private Long cityId;

    //公司地址（省 市 区）
    private String conpanyAddress;

    //详细地址
    private String detailedAddress;

    //是否合格供应商（0不合格 1合格）
    private String supplierType;

    //备注
    private String remarks;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    // 合作评估
    private String cooperationEvaluation;;

    //删除商家ID集合
    private List<Long> ids;

    //上传文件列表
    private List<GetFileInData> fileList;

    /**
     *token
     */
    private String token;

    /**
     * 有权限的城市ID集合
     */
    private List<Long> cityIds;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

}
