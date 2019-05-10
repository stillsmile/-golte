package com.golte.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.golte.common.data.JsonResult;
import com.golte.common.data.PageInfo;
import com.golte.common.util.Util;
import com.golte.common.util.UtilConst;
import com.golte.common.util.UtilMessage;
import com.golte.mapper.*;
import com.golte.mapper.entity.GConfigure;
import com.golte.system.service.ConfigService;
import com.golte.system.service.data.GetConfigInData;
import com.golte.system.service.data.GetConfigOutData;
import com.golte.system.service.data.GetMainInData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;


@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private GConfigureMapper configureMapper;

    @Override
    public JsonResult selectConfigInfoList(GetMainInData inData) throws Exception {

        PageInfo<GetConfigOutData> pageInfo;
        PageHelper.startPage(inData.getPageNum(), inData.getPageSize());

        Example example = new Example(GConfigure.class);
        example.createCriteria().andEqualTo("cogKey", "TYPE");
        List<GetConfigOutData> out = new ArrayList<>();

        List<GConfigure> gConfigures = configureMapper.selectByExample(example);

        GetConfigOutData configureInfo = null;
        for (GConfigure  configure :gConfigures){
            String[] percent = configure.getCogExtendOne().split(",");
            String[] split = configure.getCogName().split(",");
            int count = 0;
            for(String oneMember: split){
                example = new Example(GConfigure.class);
                example.createCriteria().andEqualTo("cogKey", "ROLENAME")
                        .andEqualTo("cogValue", oneMember);
                List<GConfigure> gConfiguresMembers = configureMapper.selectByExample(example);
                GConfigure configure1 = null;
                if(gConfiguresMembers.size()>0){
                    configure1 = gConfiguresMembers.get(0);
                }else{
                    continue;
                }
                configureInfo = new GetConfigOutData();
                configureInfo.setTypeValue(configure.getId()+"");
                configureInfo.setType(configure.getCogExtendTwo());
                configureInfo.setTypeId(configure.getId()+"");
                configureInfo.setTypeValue(configure.getCogValue());
                configureInfo.setRoleNameId(configure1.getCogValue());
                configureInfo.setRoleName(configure1.getCogName());
                configureInfo.setPerCent(percent[count]);
                count ++;
                out.add(configureInfo);
            }

        }
        if (Util.isEmptyList(out)) {
            pageInfo = new PageInfo<>(new ArrayList<GetConfigOutData>());
        } else {
            pageInfo = new PageInfo<>(out);
        }
        return JsonResult.success(pageInfo, UtilMessage.GET_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult saveRoleConfig(GetConfigInData inData) throws Exception {

        //查询是否存在对应的激励角色
        Example example = new Example(GConfigure.class);
        example.createCriteria().andEqualTo("cogKey", "ROLENAME");
        List<GConfigure> roleConfigs = configureMapper.selectByExample(example);
        GConfigure configureInfo = new GConfigure();
        String roleId = "";
        if(roleConfigs.size()>0){
            int count = 0;
            //若存在，且存在相同的角色名称。通过名称。定位其对应的角色Id
            for (GConfigure configure : roleConfigs) {
                if (configure.getCogName().equals(inData.getRoleName())) {
                    count ++;
                    roleId = configure.getCogValue();
                    configureInfo = configure;
                    break;
                }
            }
            //若不存在相同的角色名称，则添加
            if (count==0){
                roleId = (roleConfigs.size()+1)+"";
                configureInfo.setCogKey("ROLENAME");
                configureInfo.setCogValue((roleConfigs.size()+1)+"");//设置roleId
                configureInfo.setCogName(inData.getRoleName());
                configureMapper.insert(configureInfo);
            }

        }else{
            //若不存在激励角色信息，则创建第一个激励角色
            roleId = "1";
            configureInfo.setCogKey("ROLENAME");
            configureInfo.setCogValue("1");//设置roleId
            configureInfo.setCogName(inData.getRoleName());
            configureMapper.insert(configureInfo);
        }

        example = new Example(GConfigure.class);
        example.createCriteria().andEqualTo("cogKey", "TYPE").andEqualTo("cogValue", inData.getTypeValue());
        //查询对应的激励类型
        List<GConfigure> gConfigures = configureMapper.selectByExample(example);
        if(gConfigures.size()>0){
            //存在对应的激励类型
            GConfigure configureInfoType = new GConfigure();
            GConfigure configure = gConfigures.get(0);
            //获取存储的对应的激励角色及其对应的百分比
            String[] splitCogNames = configure.getCogName().split(",");
            String[] splitCogExtendOnes = configure.getCogExtendOne().split(",");
            //若改激励类型存在相同的激励角色。则不在添加
            for(String cogName:splitCogNames){
                if(cogName.equals(roleId)){
                    return JsonResult.fail(UtilConst.CODE_403,"该角色配置已存在");
                }
            }
            //添加该类型对应的激励角色
            String cogName = configure.getCogName();
            if(cogName.equals("")){
                cogName  = roleId;
            }else {
                cogName = cogName + "," + roleId;
            }
            //添加该类型对应的激励角色百分比
            String cogExtendOne = configure.getCogExtendOne();
            if(cogExtendOne.equals("")){
                cogExtendOne  = inData.getPerCent();
            }else {
                cogExtendOne = cogExtendOne + "," + inData.getPerCent();
            }
            configure.setCogName(cogName);
            configure.setCogExtendOne(cogExtendOne);
            configureMapper.updateByPrimaryKeySelective(configure);
        }else {
            /*GConfigure configureInfoType1 = new GConfigure();
            configureInfoType1.setCogKey("TYPE");
            configureInfoType1.setCogValue(inData.getTypeValue());
            configureInfoType1.setCogName(inData.getTypeValue());
            configureInfoType1.setCogExtendOne(inData.getPerCent());
            configureMapper.insert(configureInfoType1);*/
            return JsonResult.fail(UtilConst.CODE_403,"不存在对应的激励类型");
        }

        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult updateRoleConfig(GetConfigInData inData) throws Exception {
        //查询改激励类型的存储信息
        GConfigure configure = configureMapper.selectByPrimaryKey(Long.parseLong(inData.getTypeId()));
        String[] splitCogNames = configure.getCogName().split(",");
        String[] splitCogExtendOnes = configure.getCogExtendOne().split(",");
        int countName = 0;
        int countPer = 0;
        //定位到要更新的角色
        for(String cogName:splitCogNames){
            countName ++;
            if(cogName.equals(inData.getRoleNameId())){
                break;
            }
        }
        //定位到要更新的百分比
        String cogExtendOne = "";
        for(String cogExtendOnes:splitCogExtendOnes){
            countPer ++;
            if(countPer==countName){
                cogExtendOnes = inData.getPerCent();
            }
            if(cogExtendOne.equals("")){
                cogExtendOne  = cogExtendOnes;
            }else {
                cogExtendOne = cogExtendOne + "," + cogExtendOnes;
            }
        }
        configure.setCogExtendOne(cogExtendOne);
        configureMapper.updateByPrimaryKeySelective(configure);

        return JsonResult.success("", UtilMessage.SAVE_MESSAGE_SUCCESS);
    }

    @Override
    @Transactional
    public JsonResult deleteRoleConfig(GetConfigInData inData) throws Exception {
        List<Long> typeIds = inData.getTypeIds();//对应的类型的Id集合
        List<String> ids = inData.getIds();//对应的删除角色的Id集合
        for (int i=0;i<typeIds.size();i++){
            Long typeId = typeIds.get(i);
            String roleId = ids.get(i);
            //查找配置类存储的对应的角色及比例
            GConfigure configure = configureMapper.selectByPrimaryKey(typeId);
            String[] splitCogNames = configure.getCogName().split(",");
            String[] splitCogExtendOnes = configure.getCogExtendOne().split(",");

            int countName = -1;
            String cogNames = "";
            for(int k=0;k<splitCogNames.length;k++){
                String cogName = splitCogNames[k];
                //若相同则不在添加此角色ID
                if(cogName.equals(roleId)){
                    countName  = k;
                    continue;
                }
                if(cogNames.equals("")){
                    cogNames  = cogName;
                }else {
                    cogNames = cogNames + "," + cogName;
                }
            }

            String cogExtendOne = "";
            for (int k=0;k< splitCogExtendOnes.length;k++) {
                String countPer = splitCogExtendOnes[k];
                //相同则说明是角色删除
                if(countName==k){
                    continue;
                }
                if(cogExtendOne.equals("")){
                    cogExtendOne  = countPer;
                }else {
                    cogExtendOne = cogExtendOne + "," + countPer;
                }
            }
            configure.setCogExtendOne(cogExtendOne);
            configure.setCogName(cogNames);
            configureMapper.updateByPrimaryKeySelective(configure);
        }

        return JsonResult.success("", UtilMessage.DEL_MESSAGE_SUCCESS);
    }
}
