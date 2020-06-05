package com.hym.eshoplib.bean.me;

/**
 * Created by 胡彦明 on 2018/11/21.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class ChangeAvantarBean {

    /**
     * data : {"avatar":"http://mpai.liandao.mobi/uploads/ad/83/3f/cc/aa/651969fd4fa92874b57a45.jpg"}
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
         * avatar : http://mpai.liandao.mobi/uploads/ad/83/3f/cc/aa/651969fd4fa92874b57a45.jpg
         */

        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
