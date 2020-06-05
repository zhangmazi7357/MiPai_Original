package com.hym.eshoplib.bean.order;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/9/20.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class CommentLableListBean {

    /**
     * data : {"logo":"http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg","label_list":[{"label_id":"74","label_name":"服务超好"},{"label_id":"72","label_name":"店主超赞"},{"label_id":"73","label_name":"品质超高"}]}
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
         * logo : http://mpai.liandao.mobi/uploads/f9/e4/78/6b/71/224d8f311e58ce6b19c125.jpeg
         * label_list : [{"label_id":"74","label_name":"服务超好"},{"label_id":"72","label_name":"店主超赞"},{"label_id":"73","label_name":"品质超高"}]
         */

        private String logo;
        private List<LabelListBean> label_list;

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public List<LabelListBean> getLabel_list() {
            return label_list;
        }

        public void setLabel_list(List<LabelListBean> label_list) {
            this.label_list = label_list;
        }

        public static class LabelListBean {
            /**
             * label_id : 74
             * label_name : 服务超好
             */

            private String label_id;
            private String label_name;
            private boolean isSelect=false;

            public boolean isSelect() {
                return isSelect;
            }

            public void setSelect(boolean select) {
                isSelect = select;
            }

            public String getLabel_id() {
                return label_id;
            }

            public void setLabel_id(String label_id) {
                this.label_id = label_id;
            }

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }
        }
    }
}
