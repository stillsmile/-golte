package com.golte.basicdata.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 操作员工返回结果
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCentralCityOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    // 中心城市id
    private Long id;

    // 中心城市名称
    private String centralCityName;

    //中心城市负责人
    private Long empId;

    //中心城市负责人名称
    private String empName;

    //创建人
    private String createName;

    //创建时间
    private String createTime;

}
