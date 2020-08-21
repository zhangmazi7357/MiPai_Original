package com.hym.eshoplib.fragment.search.mz.model;

import java.util.List;

/**
 * 搜索产品结果
 */
public class MzSearchAllModel {


    /**
     * data : {"totalnum":"226","currentpage":"1","totalpage":"23","info":[{"case_id":"2852","title":"写真、旅拍、街拍","image_default":"/cf/c3/ef/be/12/9a4d3dd976ac3c87fe3a19.jpeg","address":"北京","lon":"116.555840","lat":"39.915580","present_price":"499","original_price":"0","tags":"","store_id":"1544","content_id":"0","views":"0","length":"一天","type":"2"},{"case_id":"2772","title":"飞机","image_default":"/49/27/cb/3f/43/fd92b8c9db5010a9b79d04.jpg","address":"北京市沙河镇于辛庄村中心街53号","lon":"116.889252","lat":"39.833538","present_price":"0","original_price":"0","tags":"可上门,不满意免费重拍","store_id":"920","content_id":"0","views":"7","length":"","type":"2"},{"case_id":"2837","title":"拍拍","image_default":"/05/4e/50/99/a7/17d79154e89ee959e75733.jpeg","address":"朝阳","lon":"116.408625","lat":"39.918449","present_price":"100","original_price":"0","tags":"","store_id":"1530","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2836","title":"测试产品","image_default":"/7c/4d/27/53/c6/67abc4093ebcfb201b84c8.jpeg","address":"肯德基","lon":"116.28693381690978","lat":"39.826596984672975","present_price":"0","original_price":"0","tags":"","store_id":"1521","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2834","title":"牛仔","image_default":"/ca/55/2a/21/3b/ec14ecc65cfd6921f8d167.jpeg","address":"北京南站","lon":"116.3782764433493","lat":"39.86362708635704","present_price":"0","original_price":"0","tags":"","store_id":"1521","content_id":"0","views":"2","length":"","type":"2"},{"case_id":"2833","title":"giao","image_default":"/fc/b2/75/c7/19/a8d0de1006459571c6528e.jpeg","address":"朝阳","lon":"116.502409","lat":"39.915185","present_price":"1000","original_price":"0","tags":"","store_id":"1530","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2832","title":"拍","image_default":"/5b/5c/06/6e/e7/6ae19e0a33d36cd49bfa6a.jpeg","address":"朝阳区","lon":"116.502409","lat":"39.915185","present_price":"1000","original_price":"0","tags":"","store_id":"1529","content_id":"0","views":"1","length":"","type":"2"},{"case_id":"2831","title":"拍","image_default":"/05/4e/50/99/a7/17d79154e89ee959e75733.jpeg","address":"朝阳区","lon":"116.502409","lat":"39.915185","present_price":"0","original_price":"0","tags":"","store_id":"1528","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2830","title":"乖巧","image_default":"/f9/6c/28/b8/dd/41e908658da3f36c0ce63f.jpeg","address":"天安门","lon":"116.397451","lat":"39.909187","present_price":"888","original_price":"999","tags":"","store_id":"1527","content_id":"0","views":"4","length":"00:05","type":"1"},{"case_id":"2829","title":"乖巧","image_default":"/f9/6c/28/b8/dd/41e908658da3f36c0ce63f.jpeg","address":"北京","lon":"116.397036","lat":"39.917834","present_price":"888","original_price":"0","tags":"可上门,分享送底片,不满意免费重拍","store_id":"1527","content_id":"0","views":"4","length":"","type":"2"}]}
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
         * totalnum : 226
         * currentpage : 1
         * totalpage : 23
         * info : [{"case_id":"2852","title":"写真、旅拍、街拍","image_default":"/cf/c3/ef/be/12/9a4d3dd976ac3c87fe3a19.jpeg","address":"北京","lon":"116.555840","lat":"39.915580","present_price":"499","original_price":"0","tags":"","store_id":"1544","content_id":"0","views":"0","length":"一天","type":"2"},{"case_id":"2772","title":"飞机","image_default":"/49/27/cb/3f/43/fd92b8c9db5010a9b79d04.jpg","address":"北京市沙河镇于辛庄村中心街53号","lon":"116.889252","lat":"39.833538","present_price":"0","original_price":"0","tags":"可上门,不满意免费重拍","store_id":"920","content_id":"0","views":"7","length":"","type":"2"},{"case_id":"2837","title":"拍拍","image_default":"/05/4e/50/99/a7/17d79154e89ee959e75733.jpeg","address":"朝阳","lon":"116.408625","lat":"39.918449","present_price":"100","original_price":"0","tags":"","store_id":"1530","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2836","title":"测试产品","image_default":"/7c/4d/27/53/c6/67abc4093ebcfb201b84c8.jpeg","address":"肯德基","lon":"116.28693381690978","lat":"39.826596984672975","present_price":"0","original_price":"0","tags":"","store_id":"1521","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2834","title":"牛仔","image_default":"/ca/55/2a/21/3b/ec14ecc65cfd6921f8d167.jpeg","address":"北京南站","lon":"116.3782764433493","lat":"39.86362708635704","present_price":"0","original_price":"0","tags":"","store_id":"1521","content_id":"0","views":"2","length":"","type":"2"},{"case_id":"2833","title":"giao","image_default":"/fc/b2/75/c7/19/a8d0de1006459571c6528e.jpeg","address":"朝阳","lon":"116.502409","lat":"39.915185","present_price":"1000","original_price":"0","tags":"","store_id":"1530","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2832","title":"拍","image_default":"/5b/5c/06/6e/e7/6ae19e0a33d36cd49bfa6a.jpeg","address":"朝阳区","lon":"116.502409","lat":"39.915185","present_price":"1000","original_price":"0","tags":"","store_id":"1529","content_id":"0","views":"1","length":"","type":"2"},{"case_id":"2831","title":"拍","image_default":"/05/4e/50/99/a7/17d79154e89ee959e75733.jpeg","address":"朝阳区","lon":"116.502409","lat":"39.915185","present_price":"0","original_price":"0","tags":"","store_id":"1528","content_id":"0","views":"0","length":"","type":"2"},{"case_id":"2830","title":"乖巧","image_default":"/f9/6c/28/b8/dd/41e908658da3f36c0ce63f.jpeg","address":"天安门","lon":"116.397451","lat":"39.909187","present_price":"888","original_price":"999","tags":"","store_id":"1527","content_id":"0","views":"4","length":"00:05","type":"1"},{"case_id":"2829","title":"乖巧","image_default":"/f9/6c/28/b8/dd/41e908658da3f36c0ce63f.jpeg","address":"北京","lon":"116.397036","lat":"39.917834","present_price":"888","original_price":"0","tags":"可上门,分享送底片,不满意免费重拍","store_id":"1527","content_id":"0","views":"4","length":"","type":"2"}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * case_id : 2852
             * title : 写真、旅拍、街拍
             * image_default : /cf/c3/ef/be/12/9a4d3dd976ac3c87fe3a19.jpeg
             * address : 北京
             * lon : 116.555840
             * lat : 39.915580
             * present_price : 499
             * original_price : 0
             * tags :
             * store_id : 1544
             * content_id : 0
             * views : 0
             * length : 一天
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
    }
}
