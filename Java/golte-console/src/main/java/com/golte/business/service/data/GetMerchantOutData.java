package com.golte.business.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 操作商家入参
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMerchantOutData implements Serializable {
    private static final long serialVersionUID = 1L;


    //商家Id
    private Long id;

    //商家编号
    private String code;

    //商家名称
    private String name;

    //商家等级（1全国 2城市 3项目）
    private String level;

    //联系人
    private String contact;

    //联系方式
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
    private String cityId;

    //公司地址（省 市 区）
    private String conpanyAddress;

    //详细地址
    private String detailedAddress;

    //是否合格供应商（0不合格 1合格）
    private String supplierType;

    //备注
    private String remarks;

    //合同数量
    private int heTongNum;

    //合同总额（元）
    private BigDecimal amount;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

    //全部城市权限
    private String allCity;//1-有   0-没有

    //可以编辑城市下的商家
    private String editCity;//1-可以

}
