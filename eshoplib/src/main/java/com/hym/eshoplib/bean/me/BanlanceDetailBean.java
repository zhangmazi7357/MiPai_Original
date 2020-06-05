package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/19.
 * <p>
 * Description 余额流水列表
 * <p>
 * otherTips
 */

public class BanlanceDetailBean {

    /**
     * data : {"totalnum":"6","currentpage":"1","totalpage":"1","info":[{"log_id":"3240","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11232510-097531","ctime":"2018-04-19 11:23:27","type":"2"},{"log_id":"3239","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:20:46","type":"2"},{"log_id":"3238","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:17:54","type":"2"},{"log_id":"3237","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:16:26","type":"2"},{"log_id":"3236","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:03:32","type":"2"},{"log_id":"239","value":"0","cause":"后台管理员修改","ctime":"2017-10-19 19:28:40","type":"2"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalnum : 6
         * currentpage : 1
         * totalpage : 1
         * info : [{"log_id":"3240","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11232510-097531","ctime":"2018-04-19 11:23:27","type":"2"},{"log_id":"3239","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:20:46","type":"2"},{"log_id":"3238","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:17:54","type":"2"},{"log_id":"3237","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:16:26","type":"2"},{"log_id":"3236","value":"12","cause":"购买商城商品,相关订单号:D-20180419-11033050-539710","ctime":"2018-04-19 11:03:32","type":"2"},{"log_id":"239","value":"0","cause":"后台管理员修改","ctime":"2017-10-19 19:28:40","type":"2"}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
        private List<InfoBean> info;

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * log_id : 3240
             * value : 12
             * cause : 购买商城商品,相关订单号:D-20180419-11232510-097531
             * ctime : 2018-04-19 11:23:27
             * type : 2
             */

            private String log_id;
            private String value;
            private String cause;
            private String ctime;
            private String type;

            public String getLog_id() {
                return log_id;
            }

            public void setLog_id(String log_id) {
                this.log_id = log_id;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getCause() {
                return cause;
            }

            public void setCause(String cause) {
                this.cause = cause;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
