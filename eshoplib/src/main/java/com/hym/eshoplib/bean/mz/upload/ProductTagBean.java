package com.hym.eshoplib.bean.mz.upload;

public class ProductTagBean {
    private String tag;
    private boolean checked;


    public ProductTagBean(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
