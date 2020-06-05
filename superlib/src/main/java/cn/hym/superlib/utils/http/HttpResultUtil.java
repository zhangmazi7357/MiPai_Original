package cn.hym.superlib.utils.http;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * Created by 胡彦明 on 2017/6/28.
 * <p>
 * Description 处理返回数据
 * <p>
 * otherTips
 */

public class HttpResultUtil {
    //刷新数据成功
    public static int onRefreshSuccess(int totalPage, int currentPage, List data, BaseQuickAdapter adapter) {
        //刷新数据
        adapter.setNewData(data);
        if (currentPage < totalPage) {
            currentPage += 1;
        } else {
            //全部数据
            adapter.loadMoreEnd(false);//已经是全部数据了
        }
        return currentPage;
    }
    public static int onRefreshSuccess(int totalPage, int currentPage, List data, BaseQuickAdapter adapter,boolean hide) {
        //刷新数据
        adapter.setNewData(data);
        if (currentPage < totalPage) {
            currentPage += 1;
        } else {
            //全部数据
            adapter.loadMoreEnd(hide);//已经是全部数据了
        }
        return currentPage;
    }

    //加载更多成功
    public  static int onLoardMoreSuccess(int totalPage, int currentPage, List data, BaseQuickAdapter adapter) {
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (currentPage >= totalPage) {
            adapter.loadMoreEnd(false);//全部数据
        } else {
            //添加数据 页码+1
            currentPage += 1;
        }
        return currentPage;
    }
    //加载更多成功
    public  static int onLoardMoreSuccess(int totalPage, int currentPage, List data, BaseQuickAdapter adapter,boolean hide) {
        adapter.addData(data);
        adapter.loadMoreComplete();
        if (currentPage >= totalPage) {
            //是否关闭已经加载更多
            adapter.loadMoreEnd(hide);//全部数据
        } else {
            //添加数据 页码+1
            currentPage += 1;
        }
        return currentPage;
    }

    //加载数据失败adapter 显示加载失败可以重试
    public static void onLoardDataFailer(BaseQuickAdapter adapter) {
        if (adapter != null) {
            adapter.loadMoreFail();
        }
    }
}
