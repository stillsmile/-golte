package com.golte.mapper;

import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GResource;

import java.util.List;

public interface GResourceMapper extends BaseMapper<GResource> {

    /**
     * 查询用户的资源
     * @author  wangzy
     * @date 2019年3月20日
     * @Description:
     * @param id
     * @return
     */
    List<GResource> getListByEmp(Long id);
}