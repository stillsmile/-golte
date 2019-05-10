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
public class GetProjectInData implements Serializable {
    private static final long serialVersionUID = 1L;

    //主键ID
    private Long id;

    //城市Id
    private Long cityId;

    //中心城市Id
    private Long centralCityId;

    //项目名称
    private String projectName;

    //创建人
    private String createName;

    //创建时间
    private Date createTime;

    // 操作人[默认取当前登录人]
    private String loginAccount;

    //删除项目ID集合
    private List<Long> ids;

    //项目开始时间
    private String starttime;

    //项目结束时间
    private String endtime;

    //城市ID集合
    private List<Long> cityIds;

    //页大小
    private int pageSize;

    //页码
    private int pageNum;

}
