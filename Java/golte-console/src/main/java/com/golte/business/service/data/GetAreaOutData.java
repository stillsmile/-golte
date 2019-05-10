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
public class GetAreaOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    //区域Id
    private Long id;

    //区域编号
    private String areaNo;

    //区域名称
    private String name;

    //父区域编号
    private String fNo;

}
