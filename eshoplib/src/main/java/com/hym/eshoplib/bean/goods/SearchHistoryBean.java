package com.hym.eshoplib.bean.goods;

import java.util.List;

/**
 * Created by 胡彦明 on 2017/8/15.
 * <p>
 * Description 搜索结果和热词
 * <p>
 * otherTips
 */

public class SearchHistoryBean {

    /**
     * data : {"history":["你好","你好","酒"],"hotword":[]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> history;
        private List<String> hotword;

        public List<String> getSeek() {
            return seek;
        }

        public void setSeek(List<String> seek) {
            this.seek = seek;
        }

        public List<String> getShot() {
            return shot;
        }

        public void setShot(List<String> shot) {
            this.shot = shot;
        }

        private List<String> seek;
        private List<String> shot;

        public List<String> getHistory() {
            return history;
        }

        public void setHistory(List<String> history) {
            this.history = history;
        }

        public List<String> getHotword() {
            return hotword;
        }

        public void setHotword(List<String> hotword) {
            this.hotword = hotword;
        }
    }
}
