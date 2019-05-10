package com.golte.system.service.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Description:
 * User: wangjuny
 * Date: 2018-08-07
 */
@Data
public class GetPieEchartsOutData {
    private Title title;
    private String type;
    private String xAxis;
    private Legend legend;
    private List<Series> series;
    private List values;
    @Data
    @AllArgsConstructor
    public static class Title{
        private String  text;
    }

    @Data
    public static  class Legend{
        private String[] data;
    }
    @Data
    public static class Series{
        private String type;
        private List<DataItem> data;
    }
    @Data
    @AllArgsConstructor
    public static class DataItem{
        private String value;
        private String name;
    }
}


