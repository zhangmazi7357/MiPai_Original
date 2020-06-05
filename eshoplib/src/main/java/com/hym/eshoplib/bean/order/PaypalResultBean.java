package com.hym.eshoplib.bean.order;

/**
 * Created by 胡彦明 on 2018/5/12.
 * <p>
 * Description paypal回执，用于回调服务器
 * <p>
 * otherTips
 */

public class PaypalResultBean {
    @Override
    public String toString() {
        return "PaypalResultBean{" +
                "client=" + client +
                ", response=" + response +
                ", response_type='" + response_type + '\'' +
                '}';
    }

    /**
     * client : {"environment":"sandbox","paypal_sdk_version":"2.15.3","platform":"Android","product_name":"PayPal-Android-SDK"}
     * response : {"create_time":"2018-05-12T10:35:51Z","id":"PAY-9L5060528R739745RLL3MG6A","intent":"sale","state":"approved"}
     * response_type : payment
     */

    private ClientBean client;
    private ResponseBean response;
    private String response_type;

    public ClientBean getClient() {
        return client;
    }

    public void setClient(ClientBean client) {
        this.client = client;
    }

    public ResponseBean getResponse() {
        return response;
    }

    public void setResponse(ResponseBean response) {
        this.response = response;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public static class ClientBean {
        @Override
        public String toString() {
            return "ClientBean{" +
                    "environment='" + environment + '\'' +
                    ", paypal_sdk_version='" + paypal_sdk_version + '\'' +
                    ", platform='" + platform + '\'' +
                    ", product_name='" + product_name + '\'' +
                    '}';
        }

        /**
         * environment : sandbox
         * paypal_sdk_version : 2.15.3
         * platform : Android
         * product_name : PayPal-Android-SDK
         */

        private String environment;
        private String paypal_sdk_version;
        private String platform;
        private String product_name;

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }

        public String getPaypal_sdk_version() {
            return paypal_sdk_version;
        }

        public void setPaypal_sdk_version(String paypal_sdk_version) {
            this.paypal_sdk_version = paypal_sdk_version;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }
    }

    public static class ResponseBean {
        @Override
        public String toString() {
            return "ResponseBean{" +
                    "create_time='" + create_time + '\'' +
                    ", id='" + id + '\'' +
                    ", intent='" + intent + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }

        /**
         * create_time : 2018-05-12T10:35:51Z
         * id : PAY-9L5060528R739745RLL3MG6A
         * intent : sale
         * state : approved
         */

        private String create_time;
        private String id;
        private String intent;
        private String state;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
