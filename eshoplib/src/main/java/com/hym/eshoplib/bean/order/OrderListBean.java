package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/14.
 * <p>
 * Description 我的订单列表
 * <p>
 * otherTips
 */

public class OrderListBean {


    /**
     * data : {"totalpage":3,"totalnum":"29","currentpage":1,"info":[{"child_order_id":"408","child_order_number":"D-20180413-13562254-999952","userid":"891","store_id":"5","status":"1","ctime":"2018-04-13 13:56:22","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"414","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"397","child_order_number":"D-20180412-22215248-535410","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:21:52","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"1","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"403","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"396","child_order_number":"D-20180412-22194448-974898","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:19:44","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"402","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"395","child_order_number":"D-20180412-22181998-531019","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:18:19","total_amount":"710.00","payment_amount":"715.00","pay_status":"1","order_type":"0","freight_amount":"5.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"400","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""},{"item_id":"401","content_id":"411","goods_name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","buy_price":"690.00","buy_num":"1","payment_amount":"690.00","property_relate":""}],"goods_num":2,"status_name":"等待卖家发货"},{"child_order_id":"394","child_order_number":"D-20180412-22125897-100525","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:12:58","total_amount":"710.00","payment_amount":"715.00","pay_status":"1","order_type":"0","freight_amount":"5.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"398","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""},{"item_id":"399","content_id":"411","goods_name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","buy_price":"690.00","buy_num":"1","payment_amount":"690.00","property_relate":""}],"goods_num":2,"status_name":"等待卖家发货"},{"child_order_id":"393","child_order_number":"D-20180412-22120856-505257","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:12:08","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"1","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"397","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"392","child_order_number":"D-20180412-22120250-995348","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:12:02","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"0","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"396","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"待付款"},{"child_order_id":"391","child_order_number":"D-20180412-22032499-975149","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:03:24","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"395","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"390","child_order_number":"D-20180412-22025299-569898","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:02:52","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"394","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"389","child_order_number":"D-20180412-22002254-515298","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:00:22","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"393","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"}]}
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
         * totalpage : 3
         * totalnum : 29
         * currentpage : 1
         * info : [{"child_order_id":"408","child_order_number":"D-20180413-13562254-999952","userid":"891","store_id":"5","status":"1","ctime":"2018-04-13 13:56:22","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"414","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"397","child_order_number":"D-20180412-22215248-535410","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:21:52","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"1","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"403","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"396","child_order_number":"D-20180412-22194448-974898","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:19:44","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"402","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"395","child_order_number":"D-20180412-22181998-531019","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:18:19","total_amount":"710.00","payment_amount":"715.00","pay_status":"1","order_type":"0","freight_amount":"5.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"400","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""},{"item_id":"401","content_id":"411","goods_name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","buy_price":"690.00","buy_num":"1","payment_amount":"690.00","property_relate":""}],"goods_num":2,"status_name":"等待卖家发货"},{"child_order_id":"394","child_order_number":"D-20180412-22125897-100525","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:12:58","total_amount":"710.00","payment_amount":"715.00","pay_status":"1","order_type":"0","freight_amount":"5.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"398","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""},{"item_id":"399","content_id":"411","goods_name":"台球桌自动集球器 黑八球桌改装 健英阿力孟滑道（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/b1/e5/ca/23/f4/b9865d4c7fd500246407ee.jpeg","buy_price":"690.00","buy_num":"1","payment_amount":"690.00","property_relate":""}],"goods_num":2,"status_name":"等待卖家发货"},{"child_order_id":"393","child_order_number":"D-20180412-22120856-505257","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:12:08","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"1","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"397","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"392","child_order_number":"D-20180412-22120250-995348","userid":"891","store_id":"228","status":"1","ctime":"2018-04-12 22:12:02","total_amount":"123210.00","payment_amount":"123210.00","pay_status":"0","order_type":"0","freight_amount":null,"store_name":"测试店铺","store_logo":"http://jzshop.liandao.mobi/uploads/10/f9/41/76/9f/bfcc579f9a4a7732064030.png","items":[{"item_id":"396","content_id":"447","goods_name":"测试商品","goods_image":"http://jzshop.liandao.mobi/uploads/1d/b1/80/b3/16/d2234a96f4bb54ac07cdda.jpeg","buy_price":"123210.00","buy_num":"1","payment_amount":"123210.00","property_relate":"颜色:蓝色"}],"goods_num":1,"status_name":"待付款"},{"child_order_id":"391","child_order_number":"D-20180412-22032499-975149","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:03:24","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"395","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"390","child_order_number":"D-20180412-22025299-569898","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:02:52","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"394","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"},{"child_order_id":"389","child_order_number":"D-20180412-22002254-515298","userid":"891","store_id":"5","status":"1","ctime":"2018-04-12 22:00:22","total_amount":"20.00","payment_amount":"30.00","pay_status":"1","order_type":"0","freight_amount":"10.00","store_name":"老赔台球商贸公司","store_logo":"","items":[{"item_id":"393","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}],"goods_num":1,"status_name":"等待卖家发货"}]
         */

        private int totalpage;
        private String totalnum;
        private int currentpage;
        private List<InfoBean> info;

        public int getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(int totalpage) {
            this.totalpage = totalpage;
        }

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

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * child_order_id : 408
             * child_order_number : D-20180413-13562254-999952
             * userid : 891
             * store_id : 5
             * status : 1
             * ctime : 2018-04-13 13:56:22
             * total_amount : 20.00
             * payment_amount : 30.00
             * pay_status : 1
             * order_type : 0
             * freight_amount : 10.00
             * store_name : 老赔台球商贸公司
             * store_logo :
             * items : [{"item_id":"414","content_id":"414","goods_name":"野豹台球杆职业y5皮头（运费到付）","goods_image":"http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg","buy_price":"20.00","buy_num":"1","payment_amount":"20.00","property_relate":""}]
             * goods_num : 1
             * status_name : 等待卖家发货
             */

            private String child_order_id;
            private String child_order_number;
            private String userid;
            private String store_id;
            private String status;
            private String ctime;
            private String total_amount;
            private String payment_amount;
            private String pay_status;
            private String order_type;
            private String freight_amount;
            private String store_name;
            private String store_logo;
            private int goods_num;
            private String status_name;
            private List<ItemsBean> items;

            public String getChild_order_id() {
                return child_order_id;
            }

            public void setChild_order_id(String child_order_id) {
                this.child_order_id = child_order_id;
            }

            public String getChild_order_number() {
                return child_order_number;
            }

            public void setChild_order_number(String child_order_number) {
                this.child_order_number = child_order_number;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getStore_id() {
                return store_id;
            }

            public void setStore_id(String store_id) {
                this.store_id = store_id;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getPayment_amount() {
                return payment_amount;
            }

            public void setPayment_amount(String payment_amount) {
                this.payment_amount = payment_amount;
            }

            public String getPay_status() {
                return pay_status;
            }

            public void setPay_status(String pay_status) {
                this.pay_status = pay_status;
            }

            public String getOrder_type() {
                return order_type;
            }

            public void setOrder_type(String order_type) {
                this.order_type = order_type;
            }

            public String getFreight_amount() {
                return freight_amount;
            }

            public void setFreight_amount(String freight_amount) {
                this.freight_amount = freight_amount;
            }

            public String getStore_name() {
                return store_name;
            }

            public void setStore_name(String store_name) {
                this.store_name = store_name;
            }

            public String getStore_logo() {
                return store_logo;
            }

            public void setStore_logo(String store_logo) {
                this.store_logo = store_logo;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }

            public List<ItemsBean> getItems() {
                return items;
            }

            public void setItems(List<ItemsBean> items) {
                this.items = items;
            }

            public static class ItemsBean {
                /**
                 * item_id : 414
                 * content_id : 414
                 * goods_name : 野豹台球杆职业y5皮头（运费到付）
                 * goods_image : http://jzshop.liandao.mobi/uploads/03/a1/10/6f/c2/8c32eb6c84919ba4358346.jpeg
                 * buy_price : 20.00
                 * buy_num : 1
                 * payment_amount : 20.00
                 * property_relate :
                 */

                private String item_id;
                private String content_id;
                private String goods_name;
                private String goods_image;
                private String buy_price;
                private String buy_num;
                private String payment_amount;
                private String property_relate;

                public String getItem_id() {
                    return item_id;
                }

                public void setItem_id(String item_id) {
                    this.item_id = item_id;
                }

                public String getContent_id() {
                    return content_id;
                }

                public void setContent_id(String content_id) {
                    this.content_id = content_id;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_image() {
                    return goods_image;
                }

                public void setGoods_image(String goods_image) {
                    this.goods_image = goods_image;
                }

                public String getBuy_price() {
                    return buy_price;
                }

                public void setBuy_price(String buy_price) {
                    this.buy_price = buy_price;
                }

                public String getBuy_num() {
                    return buy_num;
                }

                public void setBuy_num(String buy_num) {
                    this.buy_num = buy_num;
                }

                public String getPayment_amount() {
                    return payment_amount;
                }

                public void setPayment_amount(String payment_amount) {
                    this.payment_amount = payment_amount;
                }

                public String getProperty_relate() {
                    return property_relate;
                }

                public void setProperty_relate(String property_relate) {
                    this.property_relate = property_relate;
                }
            }
        }
    }
}
