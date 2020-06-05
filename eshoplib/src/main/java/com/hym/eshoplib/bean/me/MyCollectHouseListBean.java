package com.hym.eshoplib.bean.me;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/13.
 * <p>
 * Description 我的收藏房源列表
 * <p>
 * otherTips
 */

public class MyCollectHouseListBean {

    /**
     * data : {"totalnum":"1","currentpage":"1","totalpage":"1","info":[{"favorite_id":"65","type":"house","content":"1","userid":"1710","specification_id":null,"description":null,"ctime":"2018-01-04 10:42:37","title":"三里屯豪华两居，2卧室+1书房+1保姆间+1卫生间\n，配套齐全","price":"99.99","house_id":"1","image_default":"","label_list":[{"value_name":"100","label_name":"学校"}],"cat_reg":{"category_name":"个人","region_name":"海淀区"},"layout_name":"两居"}]}
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
         * totalnum : 1
         * currentpage : 1
         * totalpage : 1
         * info : [{"favorite_id":"65","type":"house","content":"1","userid":"1710","specification_id":null,"description":null,"ctime":"2018-01-04 10:42:37","title":"三里屯豪华两居，2卧室+1书房+1保姆间+1卫生间\n，配套齐全","price":"99.99","house_id":"1","image_default":"","label_list":[{"value_name":"100","label_name":"学校"}],"cat_reg":{"category_name":"个人","region_name":"海淀区"},"layout_name":"两居"}]
         */

        private String totalnum;
        private String currentpage;
        private String totalpage;
        private List<InfoBean> info;

        public String getTotalnum() {
            return totalnum;
        }

        public void setTotalnum(String totalnum) {
            this.totalnum = totalnum;
        }

        public String getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(String currentpage) {
            this.currentpage = currentpage;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * favorite_id : 65
             * type : house
             * content : 1
             * userid : 1710
             * specification_id : null
             * description : null
             * ctime : 2018-01-04 10:42:37
             * title : 三里屯豪华两居，2卧室+1书房+1保姆间+1卫生间
             ，配套齐全
             * price : 99.99
             * house_id : 1
             * image_default :
             * label_list : [{"value_name":"100","label_name":"学校"}]
             * cat_reg : {"category_name":"个人","region_name":"海淀区"}
             * layout_name : 两居
             */

            private String favorite_id;
            private String type;
            private String content;
            private String userid;
            private Object specification_id;
            private Object description;
            private String ctime;
            private String title;
            private String price;
            private String house_id;
            private String image_default;
            private CatRegBean cat_reg;
            private String layout_name;
            private List<LabelListBean> label_list;

            public String getFavorite_id() {
                return favorite_id;
            }

            public void setFavorite_id(String favorite_id) {
                this.favorite_id = favorite_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public Object getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(Object specification_id) {
                this.specification_id = specification_id;
            }

            public Object getDescription() {
                return description;
            }

            public void setDescription(Object description) {
                this.description = description;
            }

            public String getCtime() {
                return ctime;
            }

            public void setCtime(String ctime) {
                this.ctime = ctime;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getHouse_id() {
                return house_id;
            }

            public void setHouse_id(String house_id) {
                this.house_id = house_id;
            }

            public String getImage_default() {
                return image_default;
            }

            public void setImage_default(String image_default) {
                this.image_default = image_default;
            }

            public CatRegBean getCat_reg() {
                return cat_reg;
            }

            public void setCat_reg(CatRegBean cat_reg) {
                this.cat_reg = cat_reg;
            }

            public String getLayout_name() {
                return layout_name;
            }

            public void setLayout_name(String layout_name) {
                this.layout_name = layout_name;
            }

            public List<LabelListBean> getLabel_list() {
                return label_list;
            }

            public void setLabel_list(List<LabelListBean> label_list) {
                this.label_list = label_list;
            }

            public static class CatRegBean {
                /**
                 * category_name : 个人
                 * region_name : 海淀区
                 */

                private String category_name;
                private String region_name;

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getRegion_name() {
                    return region_name;
                }

                public void setRegion_name(String region_name) {
                    this.region_name = region_name;
                }
            }

            public static class LabelListBean {
                /**
                 * value_name : 100
                 * label_name : 学校
                 */

                private String value_name;
                private String label_name;

                public String getValue_name() {
                    return value_name;
                }

                public void setValue_name(String value_name) {
                    this.value_name = value_name;
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
}
