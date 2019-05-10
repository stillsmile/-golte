package com.golte.resource.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.GResourcePointMapper;
import com.golte.mapper.GResourceProjectDetailMapper;
import com.golte.mapper.entity.GResourcePoint;
import com.golte.mapper.entity.GResourceProjectDetail;
import com.golte.resource.service.PointService;
import com.golte.resource.service.data.*;
import com.golte.system.service.data.GetResourcePointInData;
import com.golte.system.service.data.GetResourcePointOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PointServiceImpl implements PointService{

    @Autowired
    private GResourcePointMapper resourcePointMapper;

    @Autowired
    private GResourceProjectDetailMapper resourceProjectDetailMapper;


    @Override
    public JsonResult selectPointTreeList(GetPointInData inData) throws Exception {

        List<GetPointTreeOutData> getPointTreeOutData = resourcePointMapper.selectPointTreeList();
        List<GetPointTreeOutData> treeOutDataList=new ArrayList<>();
        for (GetPointTreeOutData pointTreeOutData:getPointTreeOutData){
            if(pointTreeOutData.getPid()==0L){
                treeOutDataList.add(pointTreeOutData);
            }
        }
        for (GetPointTreeOutData treeOutData: treeOutDataList){
            for (GetPointTreeOutData twoPointTreeOutData: getPointTreeOutData){
                if(treeOutData.getId().equals(twoPointTreeOutData.getPid())){
                    treeOutData.addChild(twoPointTreeOutData);
                }
            }
        }
        return JsonResult.success(treeOutDataList, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectPointTableList(GetPointInData inData) throws Exception {
        PageInfo<GetPointTableOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<GetPointTableOutData> getPointTableOutData = resourcePointMapper.selectPointTableList(inData);
        if(inData.getLevel()==null||inData.getId()==null){
            getPointTableOutData = new ArrayList<>();
        }

        if (Util.isEmptyList(getPointTableOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetPointTableOutData>());
        } else {
            pageInfo = new PageInfo<>(getPointTableOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectPointInfoByid(GetPointInData inData) throws Exception {

        List<GetPointOutData> getPointOutData = resourcePointMapper.selectPointInfoByid(inData);
        return JsonResult.success(getPointOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectPointByParentId(GetResourcePointInData inData) throws Exception {

        List<GetResourcePointOutData> getResourcePointOutData = resourcePointMapper.selectPointByParentId(inData);
        return JsonResult.success(getResourcePointOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveResourcePoint(GetPointInData inData) throws Exception {
        Example example = new Example(GResourcePoint.class);
        example.createCriteria().andEqualTo("name",inData.getName())
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                .andEqualTo("level",inData.getLevel());
        List<GResourcePoint> gResourcePoints = resourcePointMapper.selectByExample(example);
        if(gResourcePoints.size()>0){
            return JsonResult.fail(UtilConst.CODE_500, UtilMessage.POINT_NAME_ERROR);
        }
        //组装资源点位信息
        GResourcePoint gResourcePoint = new GResourcePoint();
        gResourcePoint.setName(inData.getName());
        gResourcePoint.setParentId(inData.getParentId());
        gResourcePoint.setDelStatus(UtilConst.STR_NUMBER_1);
        gResourcePoint.setLevel(inData.getLevel());
        gResourcePoint.setCreateName(inData.getLoginAccount());
        gResourcePoint.setCreateTime(new Date());
        resourcePointMapper.insert(gResourcePoint);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteResourcePointInfo(GetPointInData inData) throws Exception {
        //判断点位是否有子节点
        Example example = new Example(GResourcePoint.class);
        List<GResourcePoint> gResourcePoints =null;
        for (int i=0;i<inData.getIds().size();i++){
            example.createCriteria().andEqualTo("parentId", inData.getIds().get(i))
                    .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
            gResourcePoints = resourcePointMapper.selectByExample(example);
            if(gResourcePoints.size()>0){
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.POINT_NODE_ERROR);
            }
        }
        List<Long> ids = inData.getIds();
        for (Long id :ids){
            //判断点位是否被资源引用
            example = new Example(GResourceProjectDetail.class);
            example.createCriteria().andEqualTo("pointId", id)
                    .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);;
            List<GResourceProjectDetail> gResourceProjectDetails = resourceProjectDetailMapper.selectByExample(example);
            if (gResourceProjectDetails.size()>0) {
                return JsonResult.fail(UtilConst.CODE_403, UtilMessage.DELETE_POINT_NODE_ERROR);
            }
        }
        example = new Example(GResourcePoint.class);
        example.createCriteria().andIn("id", inData.getIds());
        GResourcePoint gResourcePoint = new GResourcePoint();
        gResourcePoint.setDelStatus(UtilConst.STR_NUMBER_0);
        resourcePointMapper.updateByExampleSelective(gResourcePoint, example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateResourcePointDetail(GetPointInData inData) throws Exception {
        //更新前判断数据重复性
        Example example = new Example(GResourcePoint.class);
        example.createCriteria().andEqualTo("name",inData.getName())
                .andEqualTo("delStatus",UtilConst.STR_NUMBER_1)
                .andEqualTo("level",inData.getLevel());
        List<GResourcePoint> gResourcePoints = resourcePointMapper.selectByExample(example);
        if(gResourcePoints.size()>0){
            GResourcePoint gResourcePoint = gResourcePoints.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!gResourcePoint.getId().equals(inData.getId())){
                return JsonResult.fail(UtilConst.CODE_500, UtilMessage.POINT_NAME_ERROR);
            }
        }
        GResourcePoint gResourcePoint = resourcePointMapper.selectByPrimaryKey(inData.getId());
        //更新资源点位
        gResourcePoint.setName(inData.getName());
        gResourcePoint.setUpdateName(inData.getLoginAccount());
        gResourcePoint.setUpdateTime(new Date());
        resourcePointMapper.updateByPrimaryKeySelective(gResourcePoint);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

}
