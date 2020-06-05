package com.hym.eshoplib.bean.goods;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/21.
 * <p>
 * Description 商品详情
 * <p>
 * otherTips
 */

public class GoodsDetailBean implements Serializable{

    /**
     * data : {"specification_id":"269","content_id":"231","property_related":null,"an":"P15085833665336","image":null,"weight":null,"length":null,"wide":null,"height":null,"price":"15800.00","price_market":"15800.00","price_cost":"0.00","stock":"10","sort_order":"0","is_shelf":"1","is_set_price":"0","min_price":"0.00","max_price":"0.00","is_del":"0","is_index":"0","sale_vloum":"0","bean":"0.00","name":"乔氏台球桌 中式8球台球桌 Q3家用全套配置免费上门安装包邮(木库) ","image_default":"http://jzshop.liandao.mobi/uploads/5b/79/c6/3a/57/88a16502393f49402e3f65.jpg","bn":null,"store_id":"5","unit_id":"11","sales_volume":"0","sales_amount":"0.00","freight":"0.00","min_count":"0","specdetail":"","propertylist":[{"ptitle":"默认规格","is_checkd":"1","specification_id":"269"}],"is_favorite":0,"unit_name":"张","content_attachment":[{"filepath":"http://jzshop.liandao.mobi/uploads/5b/79/c6/3a/57/88a16502393f49402e3f65.jpg"}],"body":null,"detail_url":"http://jzshop.liandao.mobi/goods/index/detail1?content_id=231","share_url":"http://jzshop.liandao.mobi/wechat/productshow.html?id=269","store_name":"老赔台球商贸公司","store_goods_count":223,"store_image_default":""}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * specification_id : 269
         * content_id : 231
         * property_related : null
         * an : P15085833665336
         * image : null
         * weight : null
         * length : null
         * wide : null
         * height : null
         * price : 15800.00
         * price_market : 15800.00
         * price_cost : 0.00
         * stock : 10
         * sort_order : 0
         * is_shelf : 1
         * is_set_price : 0
         * min_price : 0.00
         * max_price : 0.00
         * is_del : 0
         * is_index : 0
         * sale_vloum : 0
         * bean : 0.00
         * name : 乔氏台球桌 中式8球台球桌 Q3家用全套配置免费上门安装包邮(木库)
         * image_default : http://jzshop.liandao.mobi/uploads/5b/79/c6/3a/57/88a16502393f49402e3f65.jpg
         * bn : null
         * store_id : 5
         * unit_id : 11
         * sales_volume : 0
         * sales_amount : 0.00
         * freight : 0.00
         * min_count : 0
         * specdetail :
         * propertylist : [{"ptitle":"默认规格","is_checkd":"1","specification_id":"269"}]
         * is_favorite : 0
         * unit_name : 张
         * content_attachment : [{"filepath":"http://jzshop.liandao.mobi/uploads/5b/79/c6/3a/57/88a16502393f49402e3f65.jpg"}]
         * body : null
         * detail_url : http://jzshop.liandao.mobi/goods/index/detail1?content_id=231
         * share_url : http://jzshop.liandao.mobi/wechat/productshow.html?id=269
         * store_name : 老赔台球商贸公司
         * store_goods_count : 223
         * store_image_default :
         */

        private String specification_id;
        private String content_id;
        private Object property_related;
        private String an;
        private Object image;
        private Object weight;
        private Object length;
        private Object wide;
        private Object height;
        private String price;
        private String price_market;
        private String price_cost;
        private String stock;
        private String sort_order;
        private String is_shelf;
        private String is_set_price;
        private String min_price;
        private String max_price;
        private String is_del;
        private String is_index;
        private String sale_vloum;
        private String bean;
        private String name;
        private String image_default;
        private Object bn;
        private String store_id;
        private String unit_id;
        private String sales_volume;
        private String sales_amount;
        private String freight;
        private String min_count;
        private String specdetail;
        private int is_favorite;
        private String unit_name;
        private Object body;
        private String detail_url;
        private String share_url;
        private String store_name;
        private int store_goods_count;
        private String store_image_default;
        private List<PropertylistBean> propertylist;
        private List<ContentAttachmentBean> content_attachment;

        public String getSpecification_id() {
            return specification_id;
        }

        public void setSpecification_id(String specification_id) {
            this.specification_id = specification_id;
        }

        public String getContent_id() {
            return content_id;
        }

        public void setContent_id(String content_id) {
            this.content_id = content_id;
        }

        public Object getProperty_related() {
            return property_related;
        }

        public void setProperty_related(Object property_related) {
            this.property_related = property_related;
        }

        public String getAn() {
            return an;
        }

        public void setAn(String an) {
            this.an = an;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public Object getWeight() {
            return weight;
        }

        public void setWeight(Object weight) {
            this.weight = weight;
        }

        public Object getLength() {
            return length;
        }

        public void setLength(Object length) {
            this.length = length;
        }

        public Object getWide() {
            return wide;
        }

        public void setWide(Object wide) {
            this.wide = wide;
        }

        public Object getHeight() {
            return height;
        }

        public void setHeight(Object height) {
            this.height = height;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPrice_market() {
            return price_market;
        }

        public void setPrice_market(String price_market) {
            this.price_market = price_market;
        }

        public String getPrice_cost() {
            return price_cost;
        }

        public void setPrice_cost(String price_cost) {
            this.price_cost = price_cost;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSort_order() {
            return sort_order;
        }

        public void setSort_order(String sort_order) {
            this.sort_order = sort_order;
        }

        public String getIs_shelf() {
            return is_shelf;
        }

        public void setIs_shelf(String is_shelf) {
            this.is_shelf = is_shelf;
        }

        public String getIs_set_price() {
            return is_set_price;
        }

        public void setIs_set_price(String is_set_price) {
            this.is_set_price = is_set_price;
        }

        public String getMin_price() {
            return min_price;
        }

        public void setMin_price(String min_price) {
            this.min_price = min_price;
        }

        public String getMax_price() {
            return max_price;
        }

        public void setMax_price(String max_price) {
            this.max_price = max_price;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getIs_index() {
            return is_index;
        }

        public void setIs_index(String is_index) {
            this.is_index = is_index;
        }

        public String getSale_vloum() {
            return sale_vloum;
        }

        public void setSale_vloum(String sale_vloum) {
            this.sale_vloum = sale_vloum;
        }

        public String getBean() {
            return bean;
        }

        public void setBean(String bean) {
            this.bean = bean;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImage_default() {
            return image_default;
        }

        public void setImage_default(String image_default) {
            this.image_default = image_default;
        }

        public Object getBn() {
            return bn;
        }

        public void setBn(Object bn) {
            this.bn = bn;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(String unit_id) {
            this.unit_id = unit_id;
        }

        public String getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(String sales_volume) {
            this.sales_volume = sales_volume;
        }

        public String getSales_amount() {
            return sales_amount;
        }

        public void setSales_amount(String sales_amount) {
            this.sales_amount = sales_amount;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getMin_count() {
            return min_count;
        }

        public void setMin_count(String min_count) {
            this.min_count = min_count;
        }

        public String getSpecdetail() {
            return specdetail;
        }

        public void setSpecdetail(String specdetail) {
            this.specdetail = specdetail;
        }

        public int getIs_favorite() {
            return is_favorite;
        }

        public void setIs_favorite(int is_favorite) {
            this.is_favorite = is_favorite;
        }

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public Object getBody() {
            return body;
        }

        public void setBody(Object body) {
            this.body = body;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public int getStore_goods_count() {
            return store_goods_count;
        }

        public void setStore_goods_count(int store_goods_count) {
            this.store_goods_count = store_goods_count;
        }

        public String getStore_image_default() {
            return store_image_default;
        }

        public void setStore_image_default(String store_image_default) {
            this.store_image_default = store_image_default;
        }

        public List<PropertylistBean> getPropertylist() {
            return propertylist;
        }

        public void setPropertylist(List<PropertylistBean> propertylist) {
            this.propertylist = propertylist;
        }

        public List<ContentAttachmentBean> getContent_attachment() {
            return content_attachment;
        }

        public void setContent_attachment(List<ContentAttachmentBean> content_attachment) {
            this.content_attachment = content_attachment;
        }

        public static class PropertylistBean implements Serializable{
            /**
             * ptitle : 默认规格
             * is_checkd : 1
             * specification_id : 269
             */

            private String ptitle;
            private String is_checkd;
            private String specification_id;

            public String getPtitle() {
                return ptitle;
            }

            public void setPtitle(String ptitle) {
                this.ptitle = ptitle;
            }

            public String getIs_checkd() {
                return is_checkd;
            }

            public void setIs_checkd(String is_checkd) {
                this.is_checkd = is_checkd;
            }

            public String getSpecification_id() {
                return specification_id;
            }

            public void setSpecification_id(String specification_id) {
                this.specification_id = specification_id;
            }
        }

        public static class ContentAttachmentBean implements Serializable{
            /**
             * filepath : http://jzshop.liandao.mobi/uploads/5b/79/c6/3a/57/88a16502393f49402e3f65.jpg
             */

            private String filepath;

            public String getFilepath() {
                return filepath;
            }

            public void setFilepath(String filepath) {
                this.filepath = filepath;
            }
        }
    }
}
