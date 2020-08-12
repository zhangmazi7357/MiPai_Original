package cn.hym.superlib.utils.http;

import android.text.TextUtils;

import com.hym.httplib.HttpConstans;
import com.hym.httplib.model.HttpRequest;

/**
 * Created by 胡彦明 on 2017/7/4.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public class HttpUtil {


    //    public static final String SERVER_IP = HttpConstans.server_ip + "/api";
    public static final String SERVER_IP = "http://121.40.80.45/api";


    //public static final String SERVER_IP= "http://wenjie.youwoapp.cn/api";


/*
    public static BaseHttpRequest getTestRequest(){
        BaseHttpRequest request=new BaseHttpRequest();
        request.setUrl(TEST_SERVER_IP);
        return request;
    }
*/


    public static BaseHttpRequest getRequest() {
        BaseHttpRequest request = new BaseHttpRequest();
        request.setUrl(SERVER_IP);
        return request;
    }

    public static class BaseHttpRequest extends HttpRequest<String, String> {
        public static final int defaultMark = 0x01;

        public BaseHttpRequest() {
            setMark(defaultMark);
        }

        //接口名
        public void setApp(String app) {
            AddParems("app", app);
        }

        //方法名
        public void setClassName(String className) {
            AddParems("class", className);
        }

        //        public BaseHttpRequest addParams(String key,String value){
//            AddParems(key,value);
//            return this;
//
//        }
        public BaseHttpRequest addParamsNotEmpty(String key, String value) {
            if (!TextUtils.isEmpty(value)) {
                AddParems(key, value);
            }
            return this;
        }

        public BaseHttpRequest addParamsNotNull(String key, String value) {
            if (value != null) {
                AddParems(key, value);
            }
            return this;
        }

    }

}
