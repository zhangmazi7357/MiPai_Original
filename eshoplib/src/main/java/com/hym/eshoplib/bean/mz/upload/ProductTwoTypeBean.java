package com.hym.eshoplib.bean.mz.upload;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 产品二级分类
 */
public class ProductTwoTypeBean implements Parcelable {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * twotype_id : 1
         * onetype_id : 1
         * title : 职业照
         * create_time : 1594968663
         */

        private String twotype_id;
        private String onetype_id;
        private String title;
        private String create_time;
        private boolean checked;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public String getTwotype_id() {
            return twotype_id;
        }

        public void setTwotype_id(String twotype_id) {
            this.twotype_id = twotype_id;
        }

        public String getOnetype_id() {
            return onetype_id;
        }

        public void setOnetype_id(String onetype_id) {
            this.onetype_id = onetype_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.twotype_id);
            dest.writeString(this.onetype_id);
            dest.writeString(this.title);
            dest.writeString(this.create_time);
            dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.twotype_id = in.readString();
            this.onetype_id = in.readString();
            this.title = in.readString();
            this.create_time = in.readString();
            this.checked = in.readByte() != 0;
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public ProductTwoTypeBean() {
    }

    protected ProductTwoTypeBean(Parcel in) {
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<ProductTwoTypeBean> CREATOR = new Parcelable.Creator<ProductTwoTypeBean>() {
        @Override
        public ProductTwoTypeBean createFromParcel(Parcel source) {
            return new ProductTwoTypeBean(source);
        }

        @Override
        public ProductTwoTypeBean[] newArray(int size) {
            return new ProductTwoTypeBean[size];
        }
    };
}
