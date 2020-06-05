package com.hym.eshoplib.bean.invoice;

import java.io.Serializable;

/**
 * Created by 胡彦明 on 2018/4/11.
 * <p>
 * Description 选择发票
 * <p>
 * otherTips
 */

public class SelectInvoiceBean implements Serializable{
    String invoice;//发票类型（1：普票，2：增票）
    String invoice_type;//普票类型（1：个人，2：公司）
    String invoice_taxpayer;//纳税人识别号
    String invoice_company;//公司名称
    String memo;//备注
    String store_id;//店铺ID

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoice_type) {
        this.invoice_type = invoice_type;
    }

    public String getInvoice_taxpayer() {
        return invoice_taxpayer;
    }

    public void setInvoice_taxpayer(String invoice_taxpayer) {
        this.invoice_taxpayer = invoice_taxpayer;
    }

    public String getInvoice_company() {
        return invoice_company;
    }

    public void setInvoice_company(String invoice_company) {
        this.invoice_company = invoice_company;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    @Override
    public String toString() {
        return "SelectInvoiceBean{" +
                "invoice='" + invoice + '\'' +
                ", invoice_type='" + invoice_type + '\'' +
                ", invoice_taxpayer='" + invoice_taxpayer + '\'' +
                ", invoice_company='" + invoice_company + '\'' +
                ", memo='" + memo + '\'' +
                ", store_id='" + store_id + '\'' +
                '}';
    }
}


