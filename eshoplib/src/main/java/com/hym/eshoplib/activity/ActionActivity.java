package com.hym.eshoplib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

import com.hym.eshoplib.fragment.city.SelectCityFragment;
import com.hym.eshoplib.fragment.goods.CertificateListFragment;
import com.hym.eshoplib.fragment.goods.GoodsDetaiFragment;
import com.hym.eshoplib.fragment.goods.ImageDetailFragment;
import com.hym.eshoplib.fragment.goods.VadieoDetailFragment;
import com.hym.eshoplib.fragment.guide.GuideFragment_step_1;
import com.hym.eshoplib.fragment.home.MoreVadieoListFragment;
import com.hym.eshoplib.fragment.home.NeedSearchFragment;
import com.hym.eshoplib.fragment.home.ShopDetailsImageFragment;
import com.hym.eshoplib.fragment.home.ShopDetailsVideoFragment;
import com.hym.eshoplib.fragment.me.AuthFragment;
import com.hym.eshoplib.fragment.me.ChangePhoneFragment;
import com.hym.eshoplib.fragment.me.MeDetailFragment;
import com.hym.eshoplib.fragment.me.MyCollectionCodeFragment;
import com.hym.eshoplib.fragment.me.MyInviteCodeFragment;
import com.hym.eshoplib.fragment.me.Openshop.ActorInfoFragment;
import com.hym.eshoplib.fragment.me.Openshop.CommonSelectFragment;
import com.hym.eshoplib.fragment.me.Openshop.OpenShopStep1;
import com.hym.eshoplib.fragment.me.Openshop.SelectIndustryFragment;
import com.hym.eshoplib.fragment.me.Openshop.SelectProductRegionFragment;
import com.hym.eshoplib.fragment.me.SetPayPwdFragmentStep1;
import com.hym.eshoplib.fragment.me.SettingFragment;
import com.hym.eshoplib.fragment.me.collect.MyCollctMainFragment;
import com.hym.eshoplib.fragment.me.editshop.EditshopBaseInfo;
import com.hym.eshoplib.fragment.me.pocket.LogDetailFragment;
import com.hym.eshoplib.fragment.me.pocket.MyDetailMainFragment;
import com.hym.eshoplib.fragment.me.pocket.MyPocketFragment;
import com.hym.eshoplib.fragment.me.pocket.ResetPayPwdFragment;
import com.hym.eshoplib.fragment.message.SystemMessageDetailFragment;
import com.hym.eshoplib.fragment.news.NewsDetailFragment;
import com.hym.eshoplib.fragment.news.NewsListFragment;
import com.hym.eshoplib.fragment.order.mipai.MipaiOrderDetailFragment;
import com.hym.eshoplib.fragment.search.SearchFragment;
import com.hym.eshoplib.fragment.search.SearchResultFragment;
import com.hym.eshoplib.fragment.shop.BusinessAuthFragment;
import com.hym.eshoplib.fragment.shop.EditImageFragment;
import com.hym.eshoplib.fragment.shop.EditVideoFragment;
import com.hym.eshoplib.fragment.shop.EdityServicePriceFragment;
import com.hym.eshoplib.fragment.shop.MoreShopListFragment;
import com.hym.eshoplib.fragment.shop.PersonalAuthFragment;
import com.hym.eshoplib.fragment.shop.ShopAwardFragment;
import com.hym.eshoplib.fragment.shop.ShopCardInfoFragment;
import com.hym.eshoplib.fragment.shop.ShopCircleFragment;
import com.hym.eshoplib.fragment.shop.ShopInfoPagerFragment;
import com.hym.eshoplib.fragment.shop.ShopListFragment;
import com.hym.eshoplib.fragment.shop.ShopStudyFragment;
import com.hym.eshoplib.fragment.shop.ShopWorkInfoFragment;
import com.hym.eshoplib.fragment.shop.UpLoadImageFragment;
import com.hym.eshoplib.fragment.shop.UpLoadVideoFragment;

import cn.hym.superlib.activity.base.BaseActionActivity;
import cn.hym.superlib.fragment.WebFragment;
import cn.hym.superlib.interfaces.action.IFragmentAction;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/12/15.
 * <p>
 * Description 通用载体activity
 * <p>
 * otherTips
 */

public class ActionActivity extends BaseActionActivity implements IFragmentAction {
    public static final int ModelType_Home = 0x02;//首页
    public static final int Action_city = 0x21;//切换城市
    public static final int Action_search = 0x22;//搜索
    public static final int Action_search_result = 0x221;//搜索结果
    public static final int Action_vadieo_detail = 0x23;//视频详情
    public static final int Action_image_detail = 0x24;//图片详情
    public static final int Action_news = 0x25;//新闻列表
    public static final int Action_news_detail = 0x26;//新闻详情
    public static final int Action_system_messgae_detail = 0x27;//系统消息详情
    public static final int Action_more_vadieo = 0x28;//更多视频
    public static final int Action_web = 0x29;//web
    public static final int Action_guide = 0x291;//引导页

    public static final int ModelType_Shop = 0x03;//工作室
    public static final int Action_ShopList = 0x31;//工作室列表
    public static final int Action_ShopDetail = 0x32;//工作室详情
    public static final int Action_OrderDetail = 0x33;//订单详情
    public static final int Action_Certificate = 0x34;//证书详情
    public static final int Action_OpenShop = 0x35;//开工作室
    public static final int Action_SelectIndustry = 0x36;//选择行业类型
    public static final int Action_SelectRegion = 0x37;//选择产品所在地区
    public static final int Action_Shop_info = 0x38;//工作室信息
    public static final int Action_Shop_uploadImage = 0x391;//上传图片
    public static final int Action_Shop_uploadVideo = 0x392;//上传视频
    public static final int Action_Shop_chage_price = 0x393;//改变价格
    public static final int Action_Shop_commonselect = 0x394;//选择退款情况学历发票
    public static final int Action_Shop_editBaseInfo = 0x395;//基本信息
    public static final int Action_Shop_editCardInfo = 0x396;//身份信息
    public static final int Action_Shop_editWorkInfo = 0x397;//从业信息
    public static final int Action_Shop_editStudyInfo = 0x398;//学历
    public static final int Action_Shop_editAwardInfo = 0x399;//获奖
    public static final int Action_Shop_editCircleInfo = 0x39;//从业经历
    public static final int Action_Shop_actor_info = 0x3991;//演员基本信息


    public static final int ModelType_me = 0x04;//设个人中心
    public static final int Action_Setting = 0x41;//设置主页
    public static final int Action_ChangePhone = 0x42;//更换手机号
    public static final int Action_SetPayPwd = 0x43;//设置支付密码
    public static final int Action_ResetSetPayPwd = 0x431;//修改支付密码
    public static final int Action_MeDetail = 0x44;//个人详情
    public static final int Action_MyCollect = 0x45;//我的收藏
    public static final int Action_MyPocket = 0x46;//我的钱包
    public static final int Action_MyLogDetail = 0x47;//钱包流水
    public static final int Action_MyInviteCode = 0x48;//钱包流水
    public static final int Action_LogDetail = 0x49;//流水详情
    public static final int Action_Edit_vadieo = 0x491;//编辑视频
    public static final int Action_Edit_image = 0x492;//编辑图片
    public static final int Action_Auth = 0x493;//认证
    public static final int Action_Auth_Personal = 0x494;//个人认证
    public static final int Action_Auth_Business = 0x495;//企业认证
    public static final int ShopDetail = 0x496;//商品图片详情页
    public static final int ShopVideoDetail = 0x499;//商品视频详情页
    public static final int Action_Need_search = 0x497; //需求搜索页
    public static final int moreshop = 0x498;//更多列表页


    public static void start(Activity from, Bundle args) {
        Intent intent = new Intent(from, ActionActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivity(intent);
    }

    public static void startForResult(Activity from, Bundle args, int reqCode) {
        Intent intent = new Intent(from, ActionActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent, reqCode);
    }

    public static void startForResult(Fragment from, Bundle args, int reqCode) {
        Intent intent = new Intent(from.getActivity(), ActionActivity.class);
        if (args != null) {
            intent.putExtras(args);
        }
        from.startActivityForResult(intent, reqCode);
    }

    @Override
    public IFragmentAction getAction() {
        return this;
    }

    @Override
    public SupportFragment getTargetFragment(int model_type, int action) {
        //bundle 可传可不传，ActionActivity 会重新设置fragment,setArguments()方法
        SupportFragment fragment = null;
        if (model_type == ModelType_Home) {
            switch (action) {
                case Action_city:
                    //当前开通城市
                    fragment = SelectCityFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_search:
                    //当前开通城市
                    fragment = SearchFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_vadieo_detail:
                    fragment = VadieoDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_image_detail:
                    fragment = ImageDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_news:
                    fragment = NewsListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_news_detail:
                    fragment = NewsDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_search_result:
                    fragment = SearchResultFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_system_messgae_detail:
                    fragment = SystemMessageDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_more_vadieo:
                    fragment = MoreVadieoListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_web:
                    fragment = WebFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_guide:
                    fragment = GuideFragment_step_1.newInstance(getIntent().getExtras());
                    break;
                case ShopDetail:    // 图片商品详情 ;
                    fragment = ShopDetailsImageFragment.newInstance(getIntent().getExtras());
                    break;
                case ShopVideoDetail:  // 视频商品详情;
                    fragment = ShopDetailsVideoFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Need_search:
                    fragment = NeedSearchFragment.newInstance(getIntent().getExtras());
                    break;
                case moreshop:
                    fragment = MoreShopListFragment.newInstance(getIntent().getExtras());
                    break;
            }
        } else if (model_type == ModelType_Shop) {
            switch (action) {
                case Action_ShopList:
                    fragment = ShopListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_ShopDetail:   // 跳到工作室
                    fragment = GoodsDetaiFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_OrderDetail:
                    fragment = MipaiOrderDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Certificate:
                    fragment = CertificateListFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_OpenShop:
                    fragment = OpenShopStep1.newInstance(getIntent().getExtras());
                    break;
                case Action_SelectIndustry:
                    fragment = SelectIndustryFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_SelectRegion:
                    fragment = SelectProductRegionFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_info:          // 工作室信息，跳转到设置工作室信息
                    fragment = ShopInfoPagerFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_uploadImage:               // 工作室 - 产品 - 上传图片
                    fragment = UpLoadImageFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_uploadVideo:               // 工作室 - 产品 - 上传视频
                    fragment = UpLoadVideoFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_chage_price:
                    fragment = EdityServicePriceFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_commonselect:
                    fragment = CommonSelectFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editBaseInfo:
                    fragment = EditshopBaseInfo.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editCardInfo:
                    fragment = ShopCardInfoFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editWorkInfo:
                    fragment = ShopWorkInfoFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editStudyInfo:
                    fragment = ShopStudyFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editAwardInfo:
                    fragment = ShopAwardFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_editCircleInfo:
                    fragment = ShopCircleFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Shop_actor_info:
                    fragment = ActorInfoFragment.newInstance(getIntent().getExtras());
                    break;
            }
        } else if (model_type == ModelType_me) {
            switch (action) {
                case Action_Setting:
                    fragment = SettingFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_ChangePhone:
                    fragment = ChangePhoneFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_SetPayPwd:
                    fragment = SetPayPwdFragmentStep1.newInstance(getIntent().getExtras());
                    break;
                case Action_ResetSetPayPwd:
                    fragment = ResetPayPwdFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_MeDetail:
                    fragment = MeDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_MyCollect:
                    fragment = MyCollctMainFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_MyPocket:
                    fragment = MyPocketFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_MyLogDetail:
                    fragment = MyDetailMainFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_MyInviteCode:     //  原邀请码 ==>  改成收款码
//                    fragment = MyInviteCodeFragment.newInstance(getIntent().getExtras());
                    fragment = MyCollectionCodeFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_LogDetail:
                    fragment = LogDetailFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Edit_vadieo:
                    fragment = EditVideoFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Edit_image:
                    fragment = EditImageFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Auth:
                    fragment = AuthFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Auth_Personal:
                    fragment = PersonalAuthFragment.newInstance(getIntent().getExtras());
                    break;
                case Action_Auth_Business:
                    fragment = BusinessAuthFragment.newInstance(getIntent().getExtras());
                    break;

            }
        }

        return fragment;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // 视频 详情特有的 ;
        ShopDetailsVideoFragment fragment = new ShopDetailsVideoFragment();
        fragment.onKeyDown(keyCode, event);

        return super.onKeyDown(keyCode, event);
    }
}
