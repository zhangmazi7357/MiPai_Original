package com.hym.eshoplib.bean.home;

import java.util.List;

public class CommentForMeBean {
    /**
     * data : {"history":["小兵","宣传片","今","创意","徽","汽车"],"hotword":["清新"],"seek":[],"shot":["优雅","清新"]}
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
        private List<?> seek;
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

        public List<?> getSeek() {
            return seek;
        }

        public void setSeek(List<?> seek) {
            this.seek = seek;
        }

        public List<String> getShot() {
            return shot;
        }

        public void setShot(List<String> shot) {
            this.shot = shot;
        }
    }
}
