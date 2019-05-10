package com.golte.system.service.data;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: 图片验证码返回结果
 * @Author: Mr.WJ
 * @Date: 2019/4/22 18:08
 */
@Data
public class GetVerifyOutData implements Serializable{
    private static final long serialVersionUID = 1190175735520089257L;

    //验证码
    private String code;

    //验证码base64格式
    private String base64;
}
