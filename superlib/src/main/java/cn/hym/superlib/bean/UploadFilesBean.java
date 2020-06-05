package cn.hym.superlib.bean;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/3/11.
 * <p>
 * Description 上传文件回执
 * <p>
 * otherTips
 */

public class UploadFilesBean {


    /**
     * data : {"token":"f7cc03a77b162cb0fb757f51adbf9da6","userid":"886","attachment_id":["2000"],"filepath":["http://localhost/uploads/06/9e/23/4b/02/3b03e3e383a780862fb31a.jpeg"]}
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
         * token : f7cc03a77b162cb0fb757f51adbf9da6
         * userid : 886
         * attachment_id : ["2000"]
         * filepath : ["http://localhost/uploads/06/9e/23/4b/02/3b03e3e383a780862fb31a.jpeg"]
         */

        private String token;
        private String userid;
        private List<String> attachment_id;
        private List<String> filepath;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public List<String> getAttachment_id() {
            return attachment_id;
        }

        public void setAttachment_id(List<String> attachment_id) {
            this.attachment_id = attachment_id;
        }

        public List<String> getFilepath() {
            return filepath;
        }

        public void setFilepath(List<String> filepath) {
            this.filepath = filepath;
        }
    }
}
