package com.golte.business.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 操作商家入参
 *
 * @author ws
 * @date 2019/3/22 15:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFileOutData implements Serializable {
    private static final long serialVersionUID = 1L;


    //关联ID
    private Long associationId;

    //区域名称
    private String name;

    //父区域编号
    private String url;

    //1合同附件 2商家附件 3商家评估附件 4合同终止附件
    private String type;
}
