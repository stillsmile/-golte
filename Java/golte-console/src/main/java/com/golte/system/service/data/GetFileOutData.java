package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetFileOutData implements Serializable {
    private static final long serialVersionUID = 1L;

    //文件名
    private String fileName;

    //文件名存储路径
    private String path;
}
