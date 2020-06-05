package com.hym.eshoplib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hym.eshoplib.fragment.address.AddressListFragment;
import com.hym.eshoplib.fragment.goods.CustomServiceFragment;
import com.hym.eshoplib.fragment.goods.GoodsCommentsListFragment;
import com.hym.eshoplib.fragment.goods.SpecListFragment;
import com.hym.eshoplib.fragment.home.GoodsClassicFragment;
import com.hym.eshoplib.fragment.home.GoodsOrderByFragment;
import com.hym.eshoplib.fragment.home.SearchGoodsFragment;
import com.hym.eshoplib.fragment.order.ConfirmOrderFragment;
import com.hym.eshoplib.fragment.order.LogicalsFragment;
import com.hym.eshoplib.fragment.order.MyOrderMainFragment;
import com.hym.eshoplib.fragment.order.SelectPaymentFragment;
import com.hym.eshoplib.fragment.order.mipai.AddCommentsFragment;
import com.hym.eshoplib.fragment.order.mipai.CommentDetailFragment;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.fragment.shoppingcart.ShoppingcartFragment;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.interfaces.action.IFragmentAction;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/12/16.
 * <p>
 * Description 商城lib 导航activity
 * <p>
 * otherTips
 */

public class EshopActionActivity extends BaseActionActivity implements IFragmentAction {
    public static final int ModelType_Eshop = 0x01;//商城模块
    public static final int Action_eshop_classic = 0x11;//分类
    public static final int Action_eshop_orderby = 0x12;//排序
    public static final int Action_eshop_spec = 0x13;//选择分类
    public static final int Action_eshop_comments = 0x14;//商品评论
    public static final int Action_eshop_service = 0x15;//客服咨询
    public static final int Action_eshop_search = 0x16;//商品搜索


    public static final int ModelType_Address = 0x02;//收货地址模块
    public static final int Action_address_addresslist = 0x21;//收货地址列表

    public static final int ModelType_Shoppingcart = 0x03;//购物车模块
    public static final int Action_shoppingcart_main = 0x31;//购物车主界面

    public static final int ModelType_Order = 0x04;//订单模块
    public static final int Action_order_confirm_order = 0x41;//确认订单
    public static final int Action_order_list = 0x42;//订单列表
    public static final int Action_order_order_detail = 0x43;//订单详情
    public static final int Action_order_order_pay = 0x44;//订单支付
    public static final int Action_order_order_logitics = 0x45;//物流
    public static final int Action_order_add_comment = 0x46;//添加评论
    public static final int Action_order_comment_detail = 0x47;//评论详情





    public static void start(Activity from, Bundle args) {
        Intent intent = new Intent(from, EshopActionActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivity(intent);
    }
    public static void startFroresult(Activity from, Bundle args,int reqCode) {
        Intent intent = new Intent(from, EshopActionActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent,reqCode);
    }

    @Override
    public IFragmentAction getAction() {
        return this;
    }

    @Override
    public SupportFragment getTargetFragment(int model_type, int action) {
        SupportFragment fragment = null;
        if (model_type == ModelType_Eshop) {
            switch (action) {
                case Action_eshop_classic:
                    //商品分类
                    fragment= GoodsClassicFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_eshop_orderby:
                    fragment= GoodsOrderByFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_eshop_spec:
                    fragment= SpecListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_eshop_comments:
                    fragment= GoodsCommentsListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_eshop_service:
                    fragment= CustomServiceFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_eshop_search:
                    fragment= SearchGoodsFragment.newInstance(getIntent().getExtras());
                    break;

            }
        }else if(model_type==ModelType_Address){
            switch (action) {
                case Action_address_addresslist:
                    //收货地址列表
                    fragment= AddressListFragment.newInstance(getIntent().getExtras());
                    break;

            }
        }else if(model_type==ModelType_Shoppingcart){
            switch (action) {
                case Action_shoppingcart_main:
                    //购物车
                    fragment= ShoppingcartFragment.newInstance(getIntent().getExtras());
                    break;

            }
        }else if(model_type==ModelType_Order){
            switch (action) {
                case Action_order_confirm_order:
                    //确认订单
                    fragment= ConfirmOrderFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_list:
                    //订单列表
                    fragment= MyOrderMainFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_order_detail:
                    //订单详情
                    fragment= MipaiOrderDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_order_pay:
                    //订单支付，从订单列表中进入
                    fragment= SelectPaymentFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_order_logitics:
                    //物流
                    fragment= LogicalsFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_add_comment:
                    fragment= AddCommentsFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_order_comment_detail:
                    fragment= CommentDetailFragment.newInstance(getIntent().getExtras());
                    break;

            }
        }
        return fragment;
    }
}
