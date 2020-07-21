package com.hym.eshoplib.bean.mz.upload;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * 产品分类 ;
 */
public class ProductSortBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * create_time : 1594968563
         * onetype_id : 1
         * title : 照片类
         */

        private String create_time;
        private String onetype_id;
        private String title;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.create_time);
            dest.writeString(this.onetype_id);
            dest.writeString(this.title);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.create_time = in.readString();
            this.onetype_id = in.readString();
            this.title = in.readString();
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
}
