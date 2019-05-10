package com.golte.resource.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPointTableOutData implements Serializable{
	private static final long serialVersionUID = 1L;

    //三级点位ID
    private Long id;

    //三级点位名称
    private String name;

    //二级点位ID
    private Long secondId;

    //二级点位名称
    private String secondName;

    //一级点位ID
    private Long firstId;

    //一级点位名称
    private String firstName;

    //删除状态（0 删除 1未删除）
    private String delStatus;

    //资源等级
    private Integer level;

}
