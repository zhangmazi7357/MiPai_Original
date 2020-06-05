package com.hym.eshoplib.bean.city;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/8/28.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ServerCityBean implements Serializable{

    /**
     * data : {"totalnum":"34","currentpage":1,"totalpage":2,"info":[{"region_id":"1","hid":"0:003743:000001","region_name":"北京"},{"region_id":"22","hid":"0:003743:000022","region_name":"天津"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * totalnum : 34
         * currentpage : 1
         * totalpage : 2
         * info : [{"region_id":"1","hid":"0:003743:000001","region_name":"北京"},{"region_id":"22","hid":"0:003743:000022","region_name":"天津"}]
         */

        private String totalnum;
        private int currentpage;
        private int totalpage;
        private List<InfoBean> info;

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public int getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(int currentpage) {
            this.currentpage = currentpage;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean implements Serializable{
            /**
             * region_id : 1
             * hid : 0:003743:000001
             * region_name : 北京
             */

            private String region_id;
            private String hid;
            private String region_name;

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getHid() {
                return hid;
            }

            public void setHid(String hid) {
                this.hid = hid;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }
        }
    }
}
