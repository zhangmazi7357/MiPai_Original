package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/18.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class EshopHomeDataBean {

    /**
     * data : {"totalnum":50,"currentpage":"1","totalpage":5,"banner":[{"board_id":"1","page_id":"7","title":"双旦盛典","content":"","attachmentid":"3132","link_url":"","go_type":"1","specification_id":"424","filepath":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"}],"category":[{"category_id":"1","store_id":"5","category_name":"数码","filepath":"http://jzshop.liandao.mobi/uploads/54/aa/6e/cc/b0/062b5291b7a1f2606dc1b0.jpeg"},{"category_id":"2","store_id":"5","category_name":"旅行","filepath":"http://jzshop.liandao.mobi/uploads/9a/11/66/11/e1/16f1f52fa45402bb0786c6.jpeg"},{"category_id":"3","store_id":"5","category_name":"母婴","filepath":"http://jzshop.liandao.mobi/uploads/4c/d8/b0/b6/07/65ff4be32bab592b44c546.jpeg"},{"category_id":"4","store_id":"5","category_name":"品牌","filepath":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"},{"category_id":"5","store_id":"5","category_name":"箱包","filepath":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg"},{"category_id":"6","store_id":"5","category_name":"服饰","filepath":"http://jzshop.liandao.mobi/uploads/54/aa/6e/cc/b0/062b5291b7a1f2606dc1b0.jpeg"},{"category_id":"8","store_id":"5","category_name":"周边","filepath":"http://jzshop.liandao.mobi/uploads/4c/d8/b0/b6/07/65ff4be32bab592b44c546.jpeg"}],"info":[{"price":"0.00","name":"凯拉图J系列J3黄色球杆","image_default":"http://jzshop.liandao.mobi/uploads/63/d5/01/cc/c2/6e8325f981852ff0cd01e4.jpg","content_id":"364","specification_id":"440"},{"price":"239.00","name":"凯拉图J系列J2红色球杆","image_default":"http://jzshop.liandao.mobi/uploads/e4/31/e3/fd/2b/f306ca77208d0179692a80.jpg","content_id":"363","specification_id":"439"},{"price":"0.00","name":"凯拉图J系列J1黑色球杆","image_default":"http://jzshop.liandao.mobi/uploads/79/69/e9/38/a9/6019d8f28f66f352a20cb9.jpg","content_id":"362","specification_id":"438"},{"price":"123210.00","name":"测试商品","image_default":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","content_id":"367","specification_id":"447"},{"price":"0.00","name":"凯拉图J系列红J4棕色球杆","image_default":"http://jzshop.liandao.mobi/uploads/88/43/63/4c/ad/ad38b74b18122efb92fe9f.jpg","content_id":"365","specification_id":"441"},{"price":"20.00","name":"野豹台球杆职业y5皮头（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","content_id":"342","specification_id":"414"},{"price":"730.00","name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","content_id":"339","specification_id":"411"},{"price":"175.00","name":"意大利龙古尼LONGONI 皮头 夹层S单粒（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/58/49/8c/36/5e/4c483681bb815ab0ac39d6.jpg","content_id":"338","specification_id":"410"},{"price":"100.00","name":"意大利龙古尼LONGONI 皮头 蓝色S单粒（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/70/fd/48/e1/f7/325978f31d934d986e9148.jpg","content_id":"335","specification_id":"407"},{"price":"69.00","name":"黑色魔力moori置放器 便携靠杆器（包邮）","image_default":"http://jzshop.liandao.mobi/uploads/4f/06/ac/07/80/1c22a54a5a92a165d217bc.jpg","content_id":"334","specification_id":"406"}]}
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
         * totalnum : 50
         * currentpage : 1
         * totalpage : 5
         * banner : [{"board_id":"1","page_id":"7","title":"双旦盛典","content":"","attachmentid":"3132","link_url":"","go_type":"1","specification_id":"424","filepath":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"}]
         * category : [{"category_id":"1","store_id":"5","category_name":"数码","filepath":"http://jzshop.liandao.mobi/uploads/54/aa/6e/cc/b0/062b5291b7a1f2606dc1b0.jpeg"},{"category_id":"2","store_id":"5","category_name":"旅行","filepath":"http://jzshop.liandao.mobi/uploads/9a/11/66/11/e1/16f1f52fa45402bb0786c6.jpeg"},{"category_id":"3","store_id":"5","category_name":"母婴","filepath":"http://jzshop.liandao.mobi/uploads/4c/d8/b0/b6/07/65ff4be32bab592b44c546.jpeg"},{"category_id":"4","store_id":"5","category_name":"品牌","filepath":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg"},{"category_id":"5","store_id":"5","category_name":"箱包","filepath":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg"},{"category_id":"6","store_id":"5","category_name":"服饰","filepath":"http://jzshop.liandao.mobi/uploads/54/aa/6e/cc/b0/062b5291b7a1f2606dc1b0.jpeg"},{"category_id":"8","store_id":"5","category_name":"周边","filepath":"http://jzshop.liandao.mobi/uploads/4c/d8/b0/b6/07/65ff4be32bab592b44c546.jpeg"}]
         * info : [{"price":"0.00","name":"凯拉图J系列J3黄色球杆","image_default":"http://jzshop.liandao.mobi/uploads/63/d5/01/cc/c2/6e8325f981852ff0cd01e4.jpg","content_id":"364","specification_id":"440"},{"price":"239.00","name":"凯拉图J系列J2红色球杆","image_default":"http://jzshop.liandao.mobi/uploads/e4/31/e3/fd/2b/f306ca77208d0179692a80.jpg","content_id":"363","specification_id":"439"},{"price":"0.00","name":"凯拉图J系列J1黑色球杆","image_default":"http://jzshop.liandao.mobi/uploads/79/69/e9/38/a9/6019d8f28f66f352a20cb9.jpg","content_id":"362","specification_id":"438"},{"price":"123210.00","name":"测试商品","image_default":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","content_id":"367","specification_id":"447"},{"price":"0.00","name":"凯拉图J系列红J4棕色球杆","image_default":"http://jzshop.liandao.mobi/uploads/88/43/63/4c/ad/ad38b74b18122efb92fe9f.jpg","content_id":"365","specification_id":"441"},{"price":"20.00","name":"野豹台球杆职业y5皮头（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","content_id":"342","specification_id":"414"},{"price":"730.00","name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","content_id":"339","specification_id":"411"},{"price":"175.00","name":"意大利龙古尼LONGONI 皮头 夹层S单粒（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/58/49/8c/36/5e/4c483681bb815ab0ac39d6.jpg","content_id":"338","specification_id":"410"},{"price":"100.00","name":"意大利龙古尼LONGONI 皮头 蓝色S单粒（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/70/fd/48/e1/f7/325978f31d934d986e9148.jpg","content_id":"335","specification_id":"407"},{"price":"69.00","name":"黑色魔力moori置放器 便携靠杆器（包邮）","image_default":"http://jzshop.liandao.mobi/uploads/4f/06/ac/07/80/1c22a54a5a92a165d217bc.jpg","content_id":"334","specification_id":"406"}]
         */

        private int totalnum;
        private String currentpage;
        private int totalpage;
        private List<BannerBean> banner;
        private List<CategoryBean> category;
        private List<InfoBean> info;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
            this.totalnum = totalnum;
        }

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<CategoryBean> getCategory() {
            return category;
        }

        public void setCategory(List<CategoryBean> category) {
            this.category = category;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class BannerBean {
            /**
             * board_id : 1
             * page_id : 7
             * title : 双旦盛典
             * content :
             * attachmentid : 3132
             * link_url :
             * go_type : 1
             * specification_id : 424
             * filepath : http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg
             */

            private String board_id;
            private String page_id;
            private String title;
            private String content;
            private String attachmentid;
            private String link_url;
            private String go_type;
            private String specification_id;
            private String filepath;

            public String getBoard_id() {
                return board_id;
            }

            public void setBoard_id(String board_id) {
                this.board_id = board_id;
            }

            public String getPage_id() {
                return page_id;
            }

            public void setPage_id(String page_id) {
                this.page_id = page_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAttachmentid() {
                return attachmentid;
            }

            public void setAttachmentid(String attachmentid) {
                this.attachmentid = attachmentid;
            }

            public String getLink_url() {
                return link_url;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getGo_type() {
                return go_type;
            }

            public void setGo_type(String go_type) {
                this.go_type = go_type;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }
        }

        public static class CategoryBean {
            /**
             * category_id : 1
             * store_id : 5
             * category_name : 数码
             * filepath : http://jzshop.liandao.mobi/uploads/54/aa/6e/cc/b0/062b5291b7a1f2606dc1b0.jpeg
             */

            private String category_id;
            private String store_id;
            private String category_name;
            private String filepath;

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }
        }

        public static class InfoBean {
            /**
             * price : 0.00
             * name : 凯拉图J系列J3黄色球杆
             * image_default : http://jzshop.liandao.mobi/uploads/63/d5/01/cc/c2/6e8325f981852ff0cd01e4.jpg
             * content_id : 364
             * specification_id : 440
             */

            private String price;
            private String name;
            private String image_default;
            private String content_id;
            private String specification_id;

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public String getContent_id() {
                return content_id;
            }

            public void setContent_id(String content_id) {
                this.content_id = content_id;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
            }
        }
    }
}
