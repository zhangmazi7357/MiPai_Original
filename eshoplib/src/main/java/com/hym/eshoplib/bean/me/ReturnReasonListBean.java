package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/5/1.
 * <p>
 * Description 退货原因列表
 * <p>
 * otherTips
 */

public class ReturnReasonListBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * reason_id : 1
         * memo : 拍错/不喜欢/效果不好
         */

        private String reason_id;
        private String memo;

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
    }
}
