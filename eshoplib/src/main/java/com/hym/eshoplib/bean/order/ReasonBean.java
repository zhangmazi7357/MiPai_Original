package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/19.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ReasonBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reason_id : 2
         * memo : 材质与商品描述不符
         * en_memo : Material is not in conformity with the description of the goods
         * japan_memo : 素材は商品に合わない
         * type : 1
         */

        private String reason_id;
        private String memo;
        private String en_memo;
        private String japan_memo;
        private String type;

        public String getReason_id() {
            return reason_id;
        }

        public void setReason_id(String reason_id) {
            this.reason_id = reason_id;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getEn_memo() {
            return en_memo;
        }

        public void setEn_memo(String en_memo) {
            this.en_memo = en_memo;
        }

        public String getJapan_memo() {
            return japan_memo;
        }

        public void setJapan_memo(String japan_memo) {
            this.japan_memo = japan_memo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
