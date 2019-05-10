package com.golte.business.service.data;

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
@NoArgsConstructor
public class GetMerchantExportOutData implements Serializable {

    //商家等级（1全国 2城市 3项目）
    private String level;

    //商家名称
    private String name;

    //联系人1
    private String contact;

    //联系方式1
    private String contactPhone;

    //是否合格供应商（0不合格 1合格）
    private String supplierType;

    //合同数量
    private int heTongNum;

    //合同总额（元）
    private BigDecimal amount;





}
