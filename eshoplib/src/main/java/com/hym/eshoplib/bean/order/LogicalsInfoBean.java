package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/4/14.
 * <p>
 * Description 物流信息正常订单
 * <p>
 * otherTips
 */

public class LogicalsInfoBean {

    /**
     * data : {"express_name":"韵达快递","express_num":"3886230018973","consignee_name":"空军建军节","consignee_region_name":null,"consignee_address":"你要相信我摸摸哦哦哦浏览量","consignee_mobile":"18888888888","express_tel":"123456","status":"待收货","express_list":[{"time":"2018-04-12 10:25:08","ftime":"2018-04-12 10:25:08","context":"[辽宁沈阳白塔堡公司]快件已被 已签收 签收"},{"time":"2018-04-12 07:47:11","ftime":"2018-04-12 07:47:11","context":"[辽宁沈阳白塔堡公司]进行派件扫描；派送业务员：王延刚；联系电话：15002454245"},{"time":"2018-04-12 00:55:56","ftime":"2018-04-12 00:55:56","context":"[辽宁沈阳分拨中心]从站点发出，本次转运目的地：辽宁沈阳白塔堡公司"},{"time":"2018-04-12 00:53:13","ftime":"2018-04-12 00:53:13","context":"[辽宁沈阳分拨中心]快件进入分拨中心进行分拨"},{"time":"2018-04-11 23:14:41","ftime":"2018-04-11 23:14:41","context":"[辽宁沈阳分拨中心]快件进入分拨中心进行分拨"},{"time":"2018-04-10 04:53:53","ftime":"2018-04-10 04:53:53","context":"[苏州航空部]从站点发出，本次转运目的地：苏州航空部宁角货代分部"},{"time":"2018-04-10 04:53:40","ftime":"2018-04-10 04:53:40","context":"[苏州航空部]从站点发出，本次转运目的地：沈阳航空部"},{"time":"2018-04-10 02:52:12","ftime":"2018-04-10 02:52:12","context":"[江苏苏州分拨中心]进行快件扫描"},{"time":"2018-04-10 02:48:30","ftime":"2018-04-10 02:48:30","context":"[江苏苏州分拨中心]在分拨中心进行称重扫描"},{"time":"2018-04-09 20:43:20","ftime":"2018-04-09 20:43:20","context":"[江苏海门市公司]进行下级地点扫描，将发往：辽宁沈阳网点包"},{"time":"2018-04-09 20:04:31","ftime":"2018-04-09 20:04:31","context":"[江苏海门市公司]进行揽件扫描"}]}
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
         * express_name : 韵达快递
         * express_num : 3886230018973
         * consignee_name : 空军建军节
         * consignee_region_name : null
         * consignee_address : 你要相信我摸摸哦哦哦浏览量
         * consignee_mobile : 18888888888
         * express_tel : 123456
         * status : 待收货
         * express_list : [{"time":"2018-04-12 10:25:08","ftime":"2018-04-12 10:25:08","context":"[辽宁沈阳白塔堡公司]快件已被 已签收 签收"},{"time":"2018-04-12 07:47:11","ftime":"2018-04-12 07:47:11","context":"[辽宁沈阳白塔堡公司]进行派件扫描；派送业务员：王延刚；联系电话：15002454245"},{"time":"2018-04-12 00:55:56","ftime":"2018-04-12 00:55:56","context":"[辽宁沈阳分拨中心]从站点发出，本次转运目的地：辽宁沈阳白塔堡公司"},{"time":"2018-04-12 00:53:13","ftime":"2018-04-12 00:53:13","context":"[辽宁沈阳分拨中心]快件进入分拨中心进行分拨"},{"time":"2018-04-11 23:14:41","ftime":"2018-04-11 23:14:41","context":"[辽宁沈阳分拨中心]快件进入分拨中心进行分拨"},{"time":"2018-04-10 04:53:53","ftime":"2018-04-10 04:53:53","context":"[苏州航空部]从站点发出，本次转运目的地：苏州航空部宁角货代分部"},{"time":"2018-04-10 04:53:40","ftime":"2018-04-10 04:53:40","context":"[苏州航空部]从站点发出，本次转运目的地：沈阳航空部"},{"time":"2018-04-10 02:52:12","ftime":"2018-04-10 02:52:12","context":"[江苏苏州分拨中心]进行快件扫描"},{"time":"2018-04-10 02:48:30","ftime":"2018-04-10 02:48:30","context":"[江苏苏州分拨中心]在分拨中心进行称重扫描"},{"time":"2018-04-09 20:43:20","ftime":"2018-04-09 20:43:20","context":"[江苏海门市公司]进行下级地点扫描，将发往：辽宁沈阳网点包"},{"time":"2018-04-09 20:04:31","ftime":"2018-04-09 20:04:31","context":"[江苏海门市公司]进行揽件扫描"}]
         */

        private String express_name;
        private String express_num;
        private String consignee_name;
        private Object consignee_region_name;
        private String consignee_address;
        private String consignee_mobile;
        private String express_tel;
        private String status;
        private List<ExpressListBean> express_list;

        public String getExpress_name() {
            return express_name;
        }

        public void setExpress_name(String express_name) {
            this.express_name = express_name;
        }

        public String getExpress_num() {
            return express_num;
        }

        public void setExpress_num(String express_num) {
            this.express_num = express_num;
        }

        public String getConsignee_name() {
            return consignee_name;
        }

        public void setConsignee_name(String consignee_name) {
            this.consignee_name = consignee_name;
        }

        public Object getConsignee_region_name() {
            return consignee_region_name;
        }

        public void setConsignee_region_name(Object consignee_region_name) {
            this.consignee_region_name = consignee_region_name;
        }

        public String getConsignee_address() {
            return consignee_address;
        }

        public void setConsignee_address(String consignee_address) {
            this.consignee_address = consignee_address;
        }

        public String getConsignee_mobile() {
            return consignee_mobile;
        }

        public void setConsignee_mobile(String consignee_mobile) {
            this.consignee_mobile = consignee_mobile;
        }

        public String getExpress_tel() {
            return express_tel;
        }

        public void setExpress_tel(String express_tel) {
            this.express_tel = express_tel;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<ExpressListBean> getExpress_list() {
            return express_list;
        }

        public void setExpress_list(List<ExpressListBean> express_list) {
            this.express_list = express_list;
        }

        public static class ExpressListBean {
            /**
             * time : 2018-04-12 10:25:08
             * ftime : 2018-04-12 10:25:08
             * context : [辽宁沈阳白塔堡公司]快件已被 已签收 签收
             */

            private String time;
            private String ftime;
            private String context;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }
        }
    }
}
