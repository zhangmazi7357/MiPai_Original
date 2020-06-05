package com.hym.eshoplib.bean.home;

/**
 * Created by 胡彦明 on 2018/10/16.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class UnReadMessageBean {

    /**
     * data : {"system":"2","order":"165","comment":"2"}
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
         * system : 2
         * order : 165
         * comment : 2
         */

        private String system;
        private String order;
        private String comment;

        public String getSystem() {
            return system;
        }

        public void setSystem(String system) {
            this.system = system;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
