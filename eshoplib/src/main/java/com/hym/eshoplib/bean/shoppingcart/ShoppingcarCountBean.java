package com.hym.eshoplib.bean.shoppingcart;

/**
 * Created by 胡彦明 on 2018/3/19.
 * <p>
 * Description 购物车数量
 * <p>
 * otherTips
 */

public class ShoppingcarCountBean {

    /**
     * data : {"quantity":"2"}
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
         * quantity : 2
         */

        private String quantity;

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
