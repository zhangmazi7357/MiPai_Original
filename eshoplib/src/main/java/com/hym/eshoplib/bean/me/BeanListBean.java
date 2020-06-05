package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/6.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class BeanListBean {

    /**
     * data : {"totalnum":"1","currentpage":"1","totalpage":"1","url":"http://localhost:8883/bts/bean/index","info":[{"log_id":"5","value":"100.00","cause":"用户充值","ctime":"2018-07-27 14:49:49","type":"1","after_value":"100"}]}
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
         * totalnum : 1
         * currentpage : 1
         * totalpage : 1
         * url : http://localhost:8883/bts/bean/index
         * info : [{"log_id":"5","value":"100.00","cause":"用户充值","ctime":"2018-07-27 14:49:49","type":"1","after_value":"100"}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
        private String url;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * log_id : 5
             * value : 100.00
             * cause : 用户充值
             * ctime : 2018-07-27 14:49:49
             * type : 1
             * after_value : 100
             */

            private String log_id;
            private String value;
            private String cause;
            private String ctime;
            private String type;
            private String after_value;

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

            public String getAfter_value() {
                return after_value;
            }

            public void setAfter_value(String after_value) {
                this.after_value = after_value;
            }
        }
    }
}
