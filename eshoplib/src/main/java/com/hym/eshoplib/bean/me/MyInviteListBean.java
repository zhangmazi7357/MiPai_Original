package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/10/6.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class MyInviteListBean {

    /**
     * data : {"is_set":0,"invitecode":"AA2A4F","list":[{"ctime":"2018-09-15 11:47:50","nickname":"13911408115","phone":"13911408115"}]}
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
         * is_set : 0
         * invitecode : AA2A4F
         * list : [{"ctime":"2018-09-15 11:47:50","nickname":"13911408115","phone":"13911408115"}]
         */

        private int is_set;
        private String invitecode;
        private List<ListBean> list;


        public int getIs_set() {
            return is_set;
        }

        public void setIs_set(int is_set) {
            this.is_set = is_set;
        }

        public String getInvitecode() {
            return invitecode;
        }

        public void setInvitecode(String invitecode) {
            this.invitecode = invitecode;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * ctime : 2018-09-15 11:47:50
             * nickname : 13911408115
             * phone : 13911408115
             */

            private String ctime;
            private String nickname;
            private String phone;
            private String avatar;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }
    }
}
