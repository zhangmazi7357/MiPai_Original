package cn.hym.superlib.manager;

import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * 分享内容 封装
 */
public class ShareBean {
    private String url;
    private String title;
    private String description;
    private String imgUrl;


    public ShareBean(String url, String title, String description, String imgUrl) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
