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
@NoArgsConstructor
public class GetMerchantExportInData implements Serializable {

    //商家编号
    private String code;

    //商家名称
    private String name;

    //联系人1
    private String contact;

    //联系方式1
    private String contactPhone;

    //联系人2
    private String contact2;

    //联系方式2
    private String contactPhone2;

    //公司地址（省 市 区）
    private String conpanyAddress;

    //商家等级（1全国 2城市 3项目）
    private String level;

    //是否合格供应商（0不合格 1合格）
    private String supplierType;

    //城市（省 市）
    private String city;

    //备注
    private String remarks;

    /**
     * 序号
     */
    private String index;
}
