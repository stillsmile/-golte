package com.golte.system.service.impl;

import com.golte.common.data.JsonResult;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.GResourceMapper;
import com.golte.mapper.GResourceRelationshipMapper;
import com.golte.mapper.entity.GResource;
import com.golte.mapper.entity.GResourceRelationship;
import com.golte.system.service.ResourceService;
import com.golte.system.service.data.GetResourceOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private GResourceMapper resourceMapper;

    @Autowired
    private GResourceRelationshipMapper resourceRelationshipMapper;

    @Override
    public JsonResult selectByRoleResource(String roleId) throws Exception {
        List<GetResourceOutData> outData = new ArrayList<>();
        Example example = new Example(GResource.class);
        example.setOrderByClause("RES_SEQ ASC");
        example.createCriteria().andEqualTo("resStatus", UtilConst.STR_NUMBER_1);
        List<GResource> resources = resourceMapper.selectByExample(example);
        example = new Example(GResourceRelationship.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        List<GResourceRelationship> resourceRelationships = resourceRelationshipMapper.selectByExample(example);
        Map<Long, Long> roleMap = new HashMap<Long, Long>();
        if (!Util.isEmptyList(resourceRelationships)) {
            for (GResourceRelationship resourceRelationship : resourceRelationships) {
                roleMap.put(resourceRelationship.getResourceId(), resourceRelationship.getResourceId());
            }
        }
        Map<Long, GetResourceOutData> map = new HashMap<Long, GetResourceOutData>();
        for (GResource resource : resources) {
            if (UtilConst.STR_NUMBER_0.equals(resource.getResStatus())) {
                continue;
            }
            Long id = resource.getId();
            Long pId = resource.getResPartenId();
            GetResourceOutData data = new GetResourceOutData();
            data.setId(id);
            data.setName(resource.getResName());
            data.setUrl(resource.getResUrl());
            Long value = roleMap.get(id);
            if (!Util.isEmpty(value)) {
                data.setChecked(true);
            }
            map.put(id, data);
            if (pId == 0L) {
                outData.add(data);
            }
        }
        for (GResource resource : resources) {
            if (UtilConst.STR_NUMBER_0.equals(resource.getResStatus())) {
                continue;
            }
            Long id = resource.getId();
            Long pId = resource.getResPartenId();
            GetResourceOutData idData = map.get(id);
            GetResourceOutData pIdData = map.get(pId);
            if (!Util.isEmpty(pIdData)) {
                pIdData.addChild(idData);
            }
        }
        return JsonResult.success(outData, UtilMessage.GET_MESSAGE_SUCCESS);
    }
}
