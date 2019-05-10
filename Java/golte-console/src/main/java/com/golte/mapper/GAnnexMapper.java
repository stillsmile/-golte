package com.golte.mapper;

import com.golte.business.service.data.GetAreaInData;
import com.golte.business.service.data.GetFileInData;
import com.golte.business.service.data.GetFileOutData;
import com.golte.common.util.BaseMapper;
import com.golte.mapper.entity.GAnnex;
import com.golte.mapper.entity.GArea;

import java.util.List;

public interface GAnnexMapper extends BaseMapper<GAnnex> {
    /**
     * 获取上传文件列表
     * @param inData
     * @return
     * @throws Exception
     */
    List<GetFileOutData> selectUploadFileList(GetFileInData inData) throws Exception;


}