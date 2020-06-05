package com.hym.loginmodule.bean;

/**
 * Created by 胡彦明 on 2018/1/13.
 * <p>
 * Description 登录回执
 * <p>
 * otherTips
 */

public class LoginBean {

    /**
     * data : {"token":"569cc81978db31265a138f539594f4ba","perfected":0,"region_name":"北京"}
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
         * token : 569cc81978db31265a138f539594f4ba
         * perfected : 0
         * region_name : 北京
         */

        private String token;
        private int perfected;
        private String region_name;
        private String rongcloud_token;
        private String avatar;
        private String userid;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        private String nickname;

        public String getRongcloud_token() {
            return rongcloud_token;
        }

        public void setRongcloud_token(String rongcloud_token) {
            this.rongcloud_token = rongcloud_token;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getPerfected() {
            return perfected;
        }

        public void setPerfected(int perfected) {
            this.perfected = perfected;
        }

        public String getRegion_name() {
            return region_name;
        }

        public void setRegion_name(String region_name) {
            this.region_name = region_name;
        }
    }
}
