package com.hym.eshoplib.bean.home;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/20.
 * <p>
 * Description 商品列表
 * <p>
 * otherTips
 */

public class GoodsListBean {

    /**
     * data : {"totalnum":209,"currentpage":1,"totalpage":21,"cart_quantity":0,"info":[{"price":"396.00","name":"璟点台尼双红线品质（俱乐部专供）大面+帮-运费到付","image_default":"http://jzshop.liandao.mobi/uploads/50/27/fa/c1/d8/123348f83a9d16e8989b35.jpg","content_id":"317","specification_id":"387"},{"price":"7.00","name":"黑色 十六彩中八托盘 台球托盘（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/54/52/1e/c5/88/f282bd076c50e3f7072b37.png","content_id":"239","specification_id":"276"},{"price":"14900.00","name":"璟点台球桌X7（金腿）（搬运自付，包安装）","image_default":"http://jzshop.liandao.mobi/uploads/af/c0/83/19/fe/f2b4b614d079ab14a3c61e.jpg","content_id":"324","specification_id":"394"},{"price":"25.00","name":"球杆固定器 修杆固定器（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/c8/31/7e/18/c1/369c8f11dde4d8388f003a.jpg","content_id":"242","specification_id":"279"},{"price":"40.00","name":"斯诺克黑八球台普通台罩（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/85/c6/89/93/08/48c9ca230dc60d116fc1ef.jpg","content_id":"326","specification_id":"396"},{"price":"16.00","name":"皮头针刺 （运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/2d/0f/5c/5d/41/6899f66a9b12a19222fa6d.jpg","content_id":"245","specification_id":"282"},{"price":"7.00","name":"小刀刷8寸台尼刷清洁刷（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/ed/32/f8/e0/25/314164bf350ff40e171318.jpg","content_id":"329","specification_id":"401"},{"price":"1035.00","name":"台尼熨斗 大号（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/7d/81/6e/c4/47/3c5052e779fe760298c604.jpg","content_id":"247","specification_id":"284"},{"price":"12.00","name":"小毛刷9寸台尼刷清洁刷（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/ee/6d/91/3a/f1/e8d9c70f0e3e3a9035d7da.jpg","content_id":"331","specification_id":"403"},{"price":"360.00","name":"台尼熨斗 小号（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/55/b8/ea/c2/2b/fab534ec6288aec63dceb2.jpg","content_id":"249","specification_id":"286"}]}
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
         * totalnum : 209
         * currentpage : 1
         * totalpage : 21
         * cart_quantity : 0
         * info : [{"price":"396.00","name":"璟点台尼双红线品质（俱乐部专供）大面+帮-运费到付","image_default":"http://jzshop.liandao.mobi/uploads/50/27/fa/c1/d8/123348f83a9d16e8989b35.jpg","content_id":"317","specification_id":"387"},{"price":"7.00","name":"黑色 十六彩中八托盘 台球托盘（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/54/52/1e/c5/88/f282bd076c50e3f7072b37.png","content_id":"239","specification_id":"276"},{"price":"14900.00","name":"璟点台球桌X7（金腿）（搬运自付，包安装）","image_default":"http://jzshop.liandao.mobi/uploads/af/c0/83/19/fe/f2b4b614d079ab14a3c61e.jpg","content_id":"324","specification_id":"394"},{"price":"25.00","name":"球杆固定器 修杆固定器（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/c8/31/7e/18/c1/369c8f11dde4d8388f003a.jpg","content_id":"242","specification_id":"279"},{"price":"40.00","name":"斯诺克黑八球台普通台罩（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/85/c6/89/93/08/48c9ca230dc60d116fc1ef.jpg","content_id":"326","specification_id":"396"},{"price":"16.00","name":"皮头针刺 （运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/2d/0f/5c/5d/41/6899f66a9b12a19222fa6d.jpg","content_id":"245","specification_id":"282"},{"price":"7.00","name":"小刀刷8寸台尼刷清洁刷（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/ed/32/f8/e0/25/314164bf350ff40e171318.jpg","content_id":"329","specification_id":"401"},{"price":"1035.00","name":"台尼熨斗 大号（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/7d/81/6e/c4/47/3c5052e779fe760298c604.jpg","content_id":"247","specification_id":"284"},{"price":"12.00","name":"小毛刷9寸台尼刷清洁刷（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/ee/6d/91/3a/f1/e8d9c70f0e3e3a9035d7da.jpg","content_id":"331","specification_id":"403"},{"price":"360.00","name":"台尼熨斗 小号（运费到付）","image_default":"http://jzshop.liandao.mobi/uploads/55/b8/ea/c2/2b/fab534ec6288aec63dceb2.jpg","content_id":"249","specification_id":"286"}]
         */

        private int totalnum;
        private int currentpage;
        private int totalpage;
        private int cart_quantity;
        private List<InfoBean> info;

        public int getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(int totalnum) {
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

        public int getCart_quantity() {
            return cart_quantity;
        }

        public void setCart_quantity(int cart_quantity) {
            this.cart_quantity = cart_quantity;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * price : 396.00
             * name : 璟点台尼双红线品质（俱乐部专供）大面+帮-运费到付
             * image_default : http://jzshop.liandao.mobi/uploads/50/27/fa/c1/d8/123348f83a9d16e8989b35.jpg
             * content_id : 317
             * specification_id : 387
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
