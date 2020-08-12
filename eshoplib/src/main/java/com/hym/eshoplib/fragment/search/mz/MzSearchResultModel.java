package com.hym.eshoplib.fragment.search.mz;

import java.util.List;

/**
 * 搜索结果
 */
public class MzSearchResultModel {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        @Override
        public String toString() {
            return "DataBean{" +
                    "case_id='" + case_id + '\'' +
                    ", title='" + title + '\'' +
                    ", image_default='" + image_default + '\'' +
                    ", address='" + address + '\'' +
                    ", lon='" + lon + '\'' +
                    ", lat='" + lat + '\'' +
                    ", present_price='" + present_price + '\'' +
                    ", original_price='" + original_price + '\'' +
                    ", tags='" + tags + '\'' +
                    ", store_id='" + store_id + '\'' +
                    ", content_id='" + content_id + '\'' +
                    ", views='" + views + '\'' +
                    ", length='" + length + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }

        /**
         * case_id : 2809
         * title : 韩国
         * image_default : /21/49/c8/f5/3b/1e34ab8a540b0af1649820.
         * address : 格格货栈
         * lon : 116.329340
         * lat : 39.773749
         * present_price : 45
         * original_price : 0
         * tags : 分享送底片
         * store_id : 1258
         * content_id : 0
         * views : 3
         * length :
         * type : 2
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
        private String content_id;
        private String views;
        private String length;
        private String type;

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

        public String getTags() {
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

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    @Override
    public String toString() {
        return "MzSearchResultModel{" +
                "data=" + data +
                '}';
    }
}
