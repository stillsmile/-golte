package com.golte.resource.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
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
public class GetProjectOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键ID
    private Long id;

    //城市Id
    private Long cityId;

    //城市名称
    private String cityName;

    //中心城市Id
    private Long centralCityId;

    //中心城市名称
    private String centralCityName;

    //项目名称
    private String projectName;

    //创建人
    private String createName;

    //创建时间
    private String createTime;

}
