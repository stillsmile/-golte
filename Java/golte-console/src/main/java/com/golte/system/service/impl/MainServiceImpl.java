package com.golte.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.basicdata.service.data.GetCentralCityOutData;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.GCityYearTarget;
import com.golte.mapper.entity.GContract;
import com.golte.system.service.MainService;
import com.golte.system.service.data.GetMainInData;
import com.golte.system.service.data.GetMainOutData;
import com.golte.system.service.data.GetMainTopOutData;
import com.golte.system.service.data.GetPieEchartsOutData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private GCityMapper cityMapper;

    @Autowired
    private GResourceProjectDetailMapper resourceProjectDetailMapper;

    @Autowired
    private GPaybackRecordMapper paybackRecordMapper;

    @Autowired
    private GContractMapper contractMapper;

    @Autowired
    private GCityYearTargetMapper cityYearTargetMapper;


    @Override
    public JsonResult selectYearTargetList(GetMainInData inData) throws Exception {

        GetMainOutData outData = new GetMainOutData();
        outData.setYearTarget(new BigDecimal("0"));//初始化值，否则计算出错
        Example example = new Example(GCityYearTarget.class);
        example.createCriteria().andEqualTo("cityId", inData.getCityId()).andEqualTo("year", inData.getYear());
        List<GCityYearTarget> gCityYearTargets = cityYearTargetMapper.selectByExample(example);
        if(gCityYearTargets.size()> 0){
            //组装年度指标
            for(GCityYearTarget targets : gCityYearTargets){
                BigDecimal yearTarget = targets.getYearTarget();
                if(yearTarget==null){
                    yearTarget = new BigDecimal("0");
                }
                BigDecimal target = outData.getYearTarget();
                BigDecimal add = target.add(yearTarget.multiply(new BigDecimal(10000)));
                outData.setYearTarget(add);
            }
        }else{
            outData.setYearTarget(new BigDecimal("0"));
        }
        //组装签约金额
        GetMainOutData mainOutData = cityMapper.selectYearTargetByCondition(inData);
        BigDecimal amount = null;
        if(mainOutData!=null){
             amount = mainOutData.getAmount();
            outData.setAmount(amount);
        }else{
             amount = new BigDecimal("0");
            outData.setAmount(amount);
        }

        if(amount.compareTo(BigDecimal.ZERO)==0||outData.getYearTarget().compareTo(BigDecimal.ZERO)==0){
            outData.setRate(new BigDecimal("0"));
        }else{
            BigDecimal divide = amount.divide(outData.getYearTarget(),10,BigDecimal.ROUND_HALF_UP);
            outData.setRate(divide.multiply(new BigDecimal("100")).setScale(2,BigDecimal.ROUND_HALF_UP));
        }
        return JsonResult.success(outData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectResourceTypeListDetail(GetMainInData inData) throws Exception {
        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetMainTopOutData> getMainTopSixOutData = resourceProjectDetailMapper.selectResourceTypeListDetail(inData);

        if (Util.isEmptyList(getMainTopSixOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMainTopOutData>());
        } else {
            pageInfo = new PageInfo<>(getMainTopSixOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectResourceTypeList(GetMainInData inData) throws Exception {

        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(1, 6);

        List<GetMainTopOutData> getMainTopSixOutData = resourceProjectDetailMapper.selectResourceTypeList(inData);

        pageInfo = new PageInfo<>(getMainTopSixOutData);


        GetPieEchartsOutData getPieEchartsOutData = new GetPieEchartsOutData();
        if(pageInfo.getList().size()>0){
            GetPieEchartsOutData.Legend legend = new GetPieEchartsOutData.Legend();
            GetPieEchartsOutData.Series series = new GetPieEchartsOutData.Series();
            List<GetPieEchartsOutData.DataItem> serieData = new ArrayList<>();
            series.setData(serieData);
            String[] strs = new String[pageInfo.getList().size()];
            List<GetPieEchartsOutData.Series> data =  new ArrayList<>();
            int count =0;
            for (GetMainTopOutData mainTopSixOutData : pageInfo.getList()){
                GetPieEchartsOutData.DataItem dataItem = new GetPieEchartsOutData.DataItem(mainTopSixOutData.getValue(),mainTopSixOutData.getName());
                series.getData().add(dataItem);
                strs[count] = mainTopSixOutData.getName();
                count++;
            }
            data.add(series);
            legend.setData(strs);
            getPieEchartsOutData.setTitle(new GetPieEchartsOutData.Title("资源"));
            getPieEchartsOutData.setLegend(legend);
            getPieEchartsOutData.setSeries(data);
        }
        return JsonResult.success(getPieEchartsOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectPaybackRecordList(GetMainInData inData) throws Exception {

        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(1, 10);
        List<GetMainTopOutData> getMainTopOutData = paybackRecordMapper.selectPaybackRecordTop10(inData);
        pageInfo = new PageInfo<>(getMainTopOutData);
        GetPieEchartsOutData getPieEchartsOutData = new GetPieEchartsOutData();
        if (pageInfo.getList().size()>0){
            String[] strs = new String[pageInfo.getList().size()];
            GetPieEchartsOutData.Legend legend = new GetPieEchartsOutData.Legend();
            List val = new ArrayList();
            int count =0;
            for (GetMainTopOutData mainTopOutData : pageInfo.getList()){
                val.add(mainTopOutData.getPayValue());
                strs[count] = mainTopOutData.getName();
                count++;
            }
            getPieEchartsOutData.setValues(val);
            legend.setData(strs);
            getPieEchartsOutData.setLegend(legend);
            getPieEchartsOutData.setType("2");
            getPieEchartsOutData.setXAxis("项目");
            getPieEchartsOutData.setTitle(new GetPieEchartsOutData.Title("项目成交金额"));
        }
        return JsonResult.success(getPieEchartsOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectPaybackRecordListDetail(GetMainInData inData) throws Exception {
        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetMainTopOutData> getMainTopOutData = paybackRecordMapper.selectPaybackRecordTop10Detail(inData);

        if (Util.isEmptyList(getMainTopOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMainTopOutData>());
        } else {
            pageInfo = new PageInfo<>(getMainTopOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectMerchantTop10(GetMainInData inData) throws Exception {

        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(1, 10);
        List<GetMainTopOutData> getMainTopOutData = paybackRecordMapper.selectMerchantTop10(inData);
        pageInfo = new PageInfo<>(getMainTopOutData);

        GetPieEchartsOutData getPieEchartsOutData = new GetPieEchartsOutData();
        if (pageInfo.getList().size()>0){
            String[] strs = new String[pageInfo.getList().size()];
            GetPieEchartsOutData.Legend legend = new GetPieEchartsOutData.Legend();
            List val = new ArrayList();
            int count =0;
            for (GetMainTopOutData mainTopOutData : pageInfo.getList()){
                val.add(mainTopOutData.getPayValue());
                strs[count] = mainTopOutData.getName();
                count++;
            }
            getPieEchartsOutData.setValues(val);
            legend.setData(strs);
            getPieEchartsOutData.setLegend(legend);
            getPieEchartsOutData.setType("4");
            getPieEchartsOutData.setXAxis("商家");
            getPieEchartsOutData.setTitle(new GetPieEchartsOutData.Title("商家成交金额"));
        }
        return JsonResult.success(getPieEchartsOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectMerchantTop10Detail(GetMainInData inData) throws Exception {
        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());
        List<GetMainTopOutData> getMainTopOutData = paybackRecordMapper.selectMerchantTop10Detail(inData);

        if (Util.isEmptyList(getMainTopOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMainTopOutData>());
        } else {
            pageInfo = new PageInfo<>(getMainTopOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectListByContractType(GetMainInData inData) throws Exception {
        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(1, 6);
        List<GetMainTopOutData> getMainTopOutData = contractMapper.selectListByContractType(inData);
        pageInfo = new PageInfo<>(getMainTopOutData);
        GetPieEchartsOutData getPieEchartsOutData = new GetPieEchartsOutData();
        if(pageInfo.getList().size()>0){
            GetPieEchartsOutData.Legend legend = new GetPieEchartsOutData.Legend();
            GetPieEchartsOutData.Series series = new GetPieEchartsOutData.Series();
            List<GetPieEchartsOutData.DataItem> serieData = new ArrayList<>();
            series.setData(serieData);
            String[] strs = new String[pageInfo.getList().size()];
            List<GetPieEchartsOutData.Series> data =  new ArrayList<>();
            int count =0;
            for (GetMainTopOutData mainTopSixOutData : pageInfo.getList()){
                String name = mainTopSixOutData.getName();
                if("1".equals(name)){ name = "广告资源" ; }
                if("2".equals(name)){ name = "场地资源" ; }
                if("3".equals(name)){ name = "临时摆展" ; }
                if("4".equals(name)){ name = "服务产品" ; }
                if("5".equals(name)){ name = "其他" ; }
                if("2,5".equals(name)){ name = "管理收入" ; }
                GetPieEchartsOutData.DataItem dataItem = new GetPieEchartsOutData.DataItem(mainTopSixOutData.getValue(),name);
                series.getData().add(dataItem);
                strs[count] = name;
                count++;
            }
            data.add(series);
            legend.setData(strs);
            getPieEchartsOutData.setTitle(new GetPieEchartsOutData.Title("类别"));
            getPieEchartsOutData.setLegend(legend);
            getPieEchartsOutData.setSeries(data);
        }
        return JsonResult.success(getPieEchartsOutData, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    public JsonResult selectListByContractTypeDetail(GetMainInData inData) throws Exception {
        String typeName = inData.getTypeName();
        if("广告资源".equals(typeName)){ inData.setTypeName("1");}
        if("场地资源".equals(typeName)){ inData.setTypeName("2");}
        if("临时摆展".equals(typeName)){ inData.setTypeName("3"); }
        if("服务产品".equals(typeName)){ inData.setTypeName("4"); }
        if("其他".equals(typeName)){ inData.setTypeName("5"); }
        if("管理收入".equals(typeName)){ inData.setTypeName("2,5"); }

        PageInfo<GetMainTopOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        List<GetMainTopOutData> getMainTopOutData = contractMapper.selectListByContractTypeDetail(inData);
        for (GetMainTopOutData mainTopOutData : getMainTopOutData) {
            String name = mainTopOutData.getName();
            if("1".equals(name)){ mainTopOutData.setName("广告资源");}
            if("2".equals(name)){ mainTopOutData.setName("场地资源"); }
            if("3".equals(name)){ mainTopOutData.setName("临时摆展"); }
            if("4".equals(name)){ mainTopOutData.setName("服务产品"); }
            if("5".equals(name)){ mainTopOutData.setName("其他"); }
            if("2,5".equals(name)){ mainTopOutData.setName("管理收入"); }
        }
        if (Util.isEmptyList(getMainTopOutData)) {
            pageInfo = new PageInfo<>(new ArrayList<GetMainTopOutData>());
        } else {
            pageInfo = new PageInfo<>(getMainTopOutData);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

}
