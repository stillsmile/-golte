package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 获取未读消息返回结果
 *
 * @author ws
 * @date 2019-04-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessagePushOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    //消息Title
    private String mesTitle;

    //消息内容
    private String mesContent;

    //创建时间
    private String createTime;

    //信息状态 0未读 1已读
    private String mesStatus;
}
