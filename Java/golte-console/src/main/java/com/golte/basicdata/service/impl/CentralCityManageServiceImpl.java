package com.golte.basicdata.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.basicdata.service.CentralCityManageService;
import com.golte.basicdata.service.data.GetCentralCityInData;
import com.golte.basicdata.service.data.GetCentralCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.GCentralCityMapper;
import com.golte.mapper.entity.GCentralCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CentralCityManageServiceImpl implements CentralCityManageService {

    @Autowired
    private GCentralCityMapper centralCityMapper;

    @Override
    public JsonResult getCentraLeaderList(GetCentralCityInData inData) throws Exception {

        List<GetCentralCityOutData> getCentralCityOutData = centralCityMapper.selectCentralLeaderListByCondition(inData);
        return JsonResult.success(getCentralCityOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectCentralCity(GetCentralCityInData inData) throws Exception {

        PageInfo<GetCentralCityOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetCentralCityOutData> getCentralCityOutData = centralCityMapper.selectListByCondition(inData);
        if (Util.isEmptyList(getCentralCityOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetCentralCityOutData>());
        } else {
            pageInfo = new PageInfo<>(getCentralCityOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectCentralCityByCitys(GetCentralCityInData inData) throws Exception {
        List<GetCentralCityOutData> outData = centralCityMapper.selectCentralCityByCitys(inData);
        return JsonResult.success(outData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveCentralCity(GetCentralCityInData inData) throws Exception {
        //先判断是否存在此城市信息
        Example example = new Example(GCentralCity.class);
        example.createCriteria().andEqualTo("centralCityName", inData.getCentralCityName())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        int count  = centralCityMapper.selectCountByExample(example);
        if(count>0){
            return JsonResult.fail(UtilConst.CODE_500, UtilMessage.CENTRAL_CITY_NAME_REPEAT);
        }

        //组装城市信息
        GCentralCity centralCity = new GCentralCity();
        centralCity.setCentralCityName(inData.getCentralCityName());
        centralCity.setEmpId(inData.getEmpId());
        centralCity.setDelStatus(UtilConst.STR_NUMBER_1);
        centralCity.setCreateName(inData.getLoginAccount());
        centralCity.setCreateTime(new Date());
        centralCityMapper.insert(centralCity);

        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteCentralCity(GetCentralCityInData inData) throws Exception {
        //删除中心城市信息
        Example example = new Example(GCentralCity.class);
        example.createCriteria().andIn("id", inData.getIds());
        GCentralCity entity = new GCentralCity();
        entity.setDelStatus(UtilConst.STR_NUMBER_0);
        centralCityMapper.updateByExampleSelective(entity,example);
        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateCentralCity(GetCentralCityInData inData) throws Exception {
        //先判断是否存在此城市信息
        Example example = new Example(GCentralCity.class);
        example.createCriteria().andEqualTo("centralCityName", inData.getCentralCityName())
                .andEqualTo("delStatus", UtilConst.STR_NUMBER_1);
        List<GCentralCity> gCentralCities = centralCityMapper.selectByExample(example);
        if(gCentralCities.size()>0){
            GCentralCity gCentralCity = gCentralCities.get(0);
            //如果Id相同，则为同一个，不同则说明重复
            if(!gCentralCity.getId().equals(inData.getId())){
                return JsonResult.fail(UtilConst.CODE_500, UtilMessage.CENTRAL_CITY_NAME_REPEAT);
            }
        }

        GCentralCity centralCity = centralCityMapper.selectByPrimaryKey(inData.getId());
        centralCity.setCentralCityName(inData.getCentralCityName());
        centralCity.setEmpId(inData.getEmpId());
        centralCity.setUpdateName(inData.getLoginAccount());
        centralCity.setUpdateTime(new Date());
        centralCityMapper.updateByPrimaryKeySelective(centralCity);
        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }
}
