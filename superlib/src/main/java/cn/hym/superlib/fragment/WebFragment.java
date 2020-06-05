package cn.hym.superlib.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.just.library.AgentWeb;
import com.just.library.AgentWebSettings;
import com.just.library.ChromeClientCallbackManager;
import com.just.library.DownLoadResultListener;
import com.just.library.LogUtils;
import com.just.library.WebDefaultSettingsManager;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.R;
import cn.hym.superlib.fragment.base.BaseKitFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by 胡彦明 on 2017/8/24.
 * <p>
 * Description WebFragment
 * <p>
 * otherTips
 */

public class WebFragment extends BaseKitFragment {
    String loadurl;
    String title = "";
    protected AgentWeb mAgentWeb;
    LinearLayout llContainer;
    public static WebFragment newInstance(Bundle args) {
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);
        llContainer= (LinearLayout) view.findViewById(R.id.ll_container);
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_web;
    }

    @Override
    public void doLogic() {
        title = getArguments().getString("title", "");
        loadurl = getArguments().getString("url", "www.baidu.com");
        Logger.d("url="+loadurl);
        showBackButton();
        setTitle(title);
        mAgentWeb =
                AgentWeb.with(this)//
                .setAgentWebParent(llContainer, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))//
                .setIndicatorColorWithHeight(-1, 2)//
                .setAgentWebWebSettings(getSettings())//
                .setWebViewClient(mWebViewClient)
                .setWebChromeClient(mWebChromeClient)
                .setReceivedTitleCallback(mCallback)
                .setSecurityType(AgentWeb.SecurityType.strict)
                .addDownLoadResultListener(mDownLoadResultListener)
                .createAgentWeb()//
                .ready()//
                .go(loadurl);

//        mAgentWeb = AgentWeb.with(this)//
//                .setAgentWebParent(llContainer,
//                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//                .setIndicatorColorWithHeight(-1, 2)//
//                .setAgentWebWebSettings(getSettings())//
//                .setWebViewClient(mWebViewClient)
//                .setWebChromeClient(mWebChromeClient)
//                .setReceivedTitleCallback(mCallback)
//                .setSecurityType(AgentWeb.SecurityType.strict)
//                .addDownLoadResultListener(mDownLoadResultListener)
//                .createAgentWeb()//
//                .ready()//
//                .go(loadurl);

         // mAgentWeb.getAgentWebSettings().getWebSettings().setAllowUniversalAccessFromFileURLs(true);
         // mAgentWeb.getAgentWebSettings().getWebSettings().setAllowFileAccessFromFileURLs(true);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    public AgentWebSettings getSettings() {
        return WebDefaultSettingsManager.getInstance();
    }

    protected ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
        @Override
        public void onReceivedTitle(WebView view, String title) {


        }
    };
    protected WebChromeClient mWebChromeClient = new WebChromeClient() {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            //  super.onProgressChanged(view, newProgress);
            //Log.i(TAG,"onProgressChanged:"+newProgress+"  view:"+view);
        }
    };

    protected WebViewClient mWebViewClient = new WebViewClient() {

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            // return shouldOverrideUrlLoading(view, request.getUrl() + "");
            return super.shouldOverrideUrlLoading(view, request);
        }


        //
        @Override
        public boolean shouldOverrideUrlLoading(final WebView view, String url) {
            LogUtils.i(TAG, "mWebViewClient shouldOverrideUrlLoading:" + url);
            //intent:// scheme的处理 如果返回false ， 则交给 DefaultWebClient 处理 ， 默认会打开该Activity  ， 如果Activity不存在则跳到应用市场上去.  true 表示拦截
            //例如优酷视频播放 ，intent://play?...package=com.youku.phone;end;
            //优酷想唤起自己应用播放该视频 ， 下面拦截地址返回 true  则会在应用内 H5 播放 ，禁止优酷唤起播放该视频， 如果返回 false ， DefaultWebClient  会根据intent 协议处理 该地址 ， 首先匹配该应用存不存在 ，如果存在 ， 唤起该应用播放 ， 如果不存在 ， 则跳到应用市场下载该应用 .
            if (url.startsWith("intent://") && url.contains("com.youku.phone"))
                return true;
            /*else if (isAlipay(view, url))   //1.2.5开始不用调用该方法了 ，只要引入支付宝sdk即可 ， DefaultWebClient 默认会处理相应url调起支付宝
                return true;*/

            return false;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            Log.i(TAG, "url:" + url + " onPageStarted  target:" + loadurl);


        }
    };
    protected DownLoadResultListener mDownLoadResultListener = new DownLoadResultListener() {
        @Override
        public void success(String path) {
            //do you work
        }

        @Override
        public void error(String path, String resUrl, String cause, Throwable e) {
            //do you work
        }
    };

    @Override
    public void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        //mAgentWeb.clearWebCache();
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroyView();
        //  mAgentWeb.destroy();

    }

    @Override
    public boolean onBackPressedSupport() {
        if( mAgentWeb.back()){
            return true;
        }else {
           return super.onBackPressedSupport();
        }

    }


}
