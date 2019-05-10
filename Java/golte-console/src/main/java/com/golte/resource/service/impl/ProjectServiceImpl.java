package com.golte.resource.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.GResourceProject;
import com.golte.mapper.entity.GResourceProjectDetail;
import com.golte.resource.service.ProjectService;
import com.golte.resource.service.data.GetProjectInData;
import com.golte.resource.service.data.GetProjectOutData;
import com.golte.system.service.data.GetResourceProjectInData;
import com.golte.system.service.data.GetResourceProjectOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private GResourceProjectMapper resourceProjectMapper;

    @Autowired
    private GResourceProjectDetailMapper resourceProjectDetailMapper;

    @Autowired
    private GContractMapper contractMapper;

    @Override
    public JsonResult selectListByCity(GetResourceProjectInData inData) throws Exception {
        PageInfo<GetResourceProjectOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<GetResourceProjectOutData> getResourceProjectOutData = resourceProjectMapper.selectListByCity(inData);
        if (Util.isEmptyList(getResourceProjectOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetResourceProjectOutData>());
        } else {
            pageInfo = new PageInfo<>(getResourceProjectOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectResourceProjectList(GetProjectInData inData) throws Exception {
        PageInfo<GetProjectOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<GetProjectOutData> getContractOutData = resourceProjectMapper.selectProjectListByCondition(inData);
        if (Util.isEmptyList(getContractOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetProjectOutData>());
        } else {
            pageInfo = new PageInfo<>(getContractOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveResourceProject(GetProjectInData inData) throws Exception {
        Example example = new Example(GResourceProject.class);
        example.createCriteria().andEqualTo("cityId", inData.getCityId())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1)
        .andEqualTo("projectName", inData.getProjectName());
        int count  = resourceProjectMapper.selectCountByExample(example);
        if(count>0){
            return JsonResult.fail(UtilConst.CODE_500, UtilMessage.PROJECT_NAME_ERROR);
        }

        GResourceProject resourceProject = new GResourceProject();
        resourceProject.setCityId(inData.getCityId());
        resourceProject.setCentralCityId(inData.getCentralCityId());
        resourceProject.setProjectName(inData.getProjectName());
        resourceProject.setCreateName(inData.getLoginAccount());
        resourceProject.setCreateTime(new Date());
        resourceProject.setDelStatus(UtilConst.STR_NUMBER_1);
        resourceProjectMapper.insert(resourceProject);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteResourceProject(GetProjectInData inData) throws Exception {
        //判断数据有效性，是否使用中
        for (Long id :inData.getIds()){
            List<GetProjectOutData> getProjectOutData = resourceProjectMapper.selectExistContract(id);
            if(getProjectOutData.size()>0){
                GetProjectOutData projectOutData = getProjectOutData.get(0);
                String exitMes = "提示：存在与合同关联的项目:"+ projectOutData.getProjectName() +"，不可删除";
                return JsonResult.fail(UtilConst.CODE_500, exitMes);
            }

            //判断项目与资源关联，否则不可删除
            Example example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("projectId", id).andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            List<GResourceProjectDetail> resourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if(resourceProjectDetails.size()>0){
                GResourceProjectDetail gResourceProjectDetail = resourceProjectDetails.get(0);
                String exitMes = "提示：存在与资源关联的项目，不可删除";
                return JsonResult.fail(UtilConst.CODE_500, exitMes);
            }

        }
        Example example = new Example(GResourceProject.class);

        example.createCriteria().andIn("id", inData.getIds());
        GResourceProject resourceProject = new GResourceProject();
        resourceProject.setDelStatus(UtilConst.STR_NUMBER_0);
        resourceProjectMapper.updateByExampleSelective(resourceProject, example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateResourceProject(GetProjectInData inData) throws Exception {
        GResourceProject resourceProject = resourceProjectMapper.selectByPrimaryKey(inData.getId());
        Example example = new Example(GResourceProject.class);
        example.createCriteria().andEqualTo("cityId", inData.getCityId())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1)
                .andEqualTo("projectName", inData.getProjectName());
        List<GResourceProject> gResourceProjects = resourceProjectMapper.selectByExample(example);
        if(gResourceProjects.size()>0){
            GResourceProject resourceProject1 = gResourceProjects.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!resourceProject1.getId().equals(inData.getId())){
                return JsonResult.fail(UtilConst.CODE_500, UtilMessage.REPEAT_NAME_ERROR);
            }
        }
        //更新资源项目
        resourceProject.setCityId(inData.getCityId());
        resourceProject.setCentralCityId(inData.getCentralCityId());
        resourceProject.setProjectName(inData.getProjectName());
        resourceProject.setUpdateName(inData.getLoginAccount());
        resourceProject.setUpdateTime(new Date());
        resourceProjectMapper.updateByPrimaryKeySelective(resourceProject);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

}
