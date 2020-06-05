package com.hym.eshoplib.bean.me;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/13.
 * <p>
 * Description 获取我发布的租房意向
 * <p>
 * otherTips
 */

public class HouseIntentionListBean  implements Serializable{

    /**
     * data : {"totalnum":"1","currentpage":1,"totalpage":1,"info":[{"order_id":"12","userid":"891","name":"明哥","tel":"18888888888","price":"100-300RMB","region_id":"3","layout_id":"1","in_time":"2018-07-01 00:00:00","user_count":"家庭3人以上入住","is_pet":"有宠物","memo":"测试发布","email":"9966477@qq.com","favorite_id":"","ctime":"2018-02-27 20:40:37","region_name":"北京朝阳区","layout_name":"两居"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean  implements Serializable{
        /**
         * totalnum : 1
         * currentpage : 1
         * totalpage : 1
         * info : [{"order_id":"12","userid":"891","name":"明哥","tel":"18888888888","price":"100-300RMB","region_id":"3","layout_id":"1","in_time":"2018-07-01 00:00:00","user_count":"家庭3人以上入住","is_pet":"有宠物","memo":"测试发布","email":"9966477@qq.com","favorite_id":"","ctime":"2018-02-27 20:40:37","region_name":"北京朝阳区","layout_name":"两居"}]
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
             * order_id : 12
             * userid : 891
             * name : 明哥
             * tel : 18888888888
             * price : 100-300RMB
             * region_id : 3
             * layout_id : 1
             * in_time : 2018-07-01 00:00:00
             * user_count : 家庭3人以上入住
             * is_pet : 有宠物
             * memo : 测试发布
             * email : 9966477@qq.com
             * favorite_id :
             * ctime : 2018-02-27 20:40:37
             * region_name : 北京朝阳区
             * layout_name : 两居
             */
            private String house_id;
            private String order_id;
            private String userid;
            private String name;
            private String tel;
            private String price;
            private String region_id;
            private String layout_id;
            private String in_time;
            private String user_count;
            private String is_pet;
            private String memo;
            private String email;
            private String favorite_id;
            private String ctime;
            private String region_name;
            private String layout_name;

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getRegion_id() {
                return region_id;
            }

            public void setRegion_id(String region_id) {
                this.region_id = region_id;
            }

            public String getLayout_id() {
                return layout_id;
            }

            public void setLayout_id(String layout_id) {
                this.layout_id = layout_id;
            }

            public String getIn_time() {
                return in_time;
            }

            public void setIn_time(String in_time) {
                this.in_time = in_time;
            }

            public String getUser_count() {
                return user_count;
            }

            public void setUser_count(String user_count) {
                this.user_count = user_count;
            }

            public String getIs_pet() {
                return is_pet;
            }

            public void setIs_pet(String is_pet) {
                this.is_pet = is_pet;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getFavorite_id() {
                return favorite_id;
            }

            public void setFavorite_id(String favorite_id) {
                this.favorite_id = favorite_id;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getRegion_name() {
                return region_name;
            }

            public void setRegion_name(String region_name) {
                this.region_name = region_name;
            }

            public String getLayout_name() {
                return layout_name;
            }

            public void setLayout_name(String layout_name) {
                this.layout_name = layout_name;
            }
        }
    }
}
