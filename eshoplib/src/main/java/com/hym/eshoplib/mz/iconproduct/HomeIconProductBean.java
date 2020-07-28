package com.hym.eshoplib.mz.iconproduct;

import java.util.List;

/**
 * 首页 icon 跳转
 */
class HomeIconProductBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * case_id : 2798
         * title : 嗯嗯呐
         * image_default : /a7/fb/a3/44/9d/cc82b250252a4bfc9a8c2b.jpeg
         * address :
         * lon :
         * lat :
         * present_price : 1
         * original_price : 0
         * tags : null
         * store_id : 1258
         * weight : 2
         */

        private String case_id;
        private String title;
        private String image_default;
        private String address;
        private String lon;
        private String lat;
        private String present_price;
        private String original_price;
        private String tags;
        private String store_id;
        private int weight;

        public String getCase_id() {
            return case_id;
        }

        public void setCase_id(String case_id) {
            this.case_id = case_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage_default() {
            return image_default;
        }

        public void setImage_default(String image_default) {
            this.image_default = image_default;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getPresent_price() {
            return present_price;
        }

        public void setPresent_price(String present_price) {
            this.present_price = present_price;
        }

        public String getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(String original_price) {
            this.original_price = original_price;
        }

        public Object getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }
}
