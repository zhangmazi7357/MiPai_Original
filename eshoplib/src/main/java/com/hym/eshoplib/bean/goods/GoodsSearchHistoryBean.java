package com.hym.eshoplib.bean.goods;

import java.util.List;

/**
 * Created by 胡彦明 on 2018/2/24.
 * <p>
 * Description 商品搜索历史
 * <p>
 * otherTips
 */

public class GoodsSearchHistoryBean {

    /**
     * data : {"hotword":["巧粉","皮头","水晶球","毒药","台尼","入门级打球杆","公杆","集球器","野豹","璟点","美洲豹","台球桌","台球杆"],"history":["什么"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> hotword;
        private List<String> history;

        public List<String> getHotword() {
            return hotword;
        }

        public void setHotword(List<String> hotword) {
            this.hotword = hotword;
        }

        public List<String> getHistory() {
            return history;
        }

        public void setHistory(List<String> history) {
            this.history = history;
        }
    }
}
