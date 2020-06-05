package com.hym.loginmodule.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.ContextCompat;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.loginmodule.R;
import com.hym.loginmodule.bean.ChangeLanguageBean;
import com.hym.loginmodule.bean.LanguageItemBean;
import com.hym.loginmodule.bean.LocalTokenBean;
import com.hym.loginmodule.http.LoginApi;
import com.hym.loginmodule.utils.LanguageUtil;
import com.orhanobut.logger.Logger;

import cn.hym.superlib.fragment.base.BaseListFragment;
import cn.hym.superlib.languages.utils.AppLanguageUtils;
import cn.hym.superlib.utils.common.DialogUtil;
import cn.hym.superlib.utils.common.SharePreferenceUtil;
import cn.hym.superlib.utils.common.ToastUtil;
import cn.hym.superlib.utils.user.UserUtil;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by 胡彦明 on 2018/1/8.
 * <p>
 * Description 语言选择，首次登录必须 选择一个语言
 * <p>
 * otherTips
 */

public class LanguagesSelectFragment extends BaseListFragment<LanguageItemBean> {
    public static final String key_has_selected = "key_has_selected";//判断应用是否已经选择过语言
    private int current_position = -1;
    boolean canback = false;   //第一次进入必须选择，所以不可以关闭，从我的中进入可以关闭

    public static LanguagesSelectFragment newInstance(Bundle args) {
        LanguagesSelectFragment fragment = new LanguagesSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemRestId() {
        return R.layout.item_language;
    }

    @Override
    public void excuteLogic() {
        setShowProgressDialog(true);
        canback = getArguments().getBoolean("canback");
        if (canback) {
            showBackButton();
        }
        setTitle(R.string.SelectLanguage);
        setRight_tv(R.string.Confirm, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_position < 0) {
                    ToastUtil.toast("You must Select a language");
                    return;
                }
                final String tag = getAdapter().getData().get(current_position).getLanguageTag();
                final String language = LanguageUtil.getLanguageTypeBytag(tag);
                String token = UserUtil.getToken(_mActivity);
                if (TextUtils.isEmpty(token)) {
                    //没有token 说明还没登录过则获取本地token
                    Logger.d("未登录过，获取本地token");
                    LoginApi.getLocalToken(language, new ResponseImpl<LocalTokenBean>() {
                        @Override
                        public void onSuccess(LocalTokenBean data) {
                            String token = data.getData().getLocaltoken();
                            UserUtil.saveToken(_mActivity, token);
                            //获取token后，存入sp，并切通知接口更新语言。
                            changeLanguage(language, token, tag);

                        }
                    }, LocalTokenBean.class);

                } else {
                    //有token,说明是切换语言
                    Logger.d("登录过，切换语言");
                    changeLanguage(language, token, tag);

                }


            }
        });
        getRefreshLayout().setEnabled(false);
        getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                current_position = position;
                switch (position) {
                    case 0:
                        setTitle("语言选择");
                        setRight_tvContent("确认");
                        break;
                    case 1:
                        setTitle("Select Language");
                        setRight_tvContent("Confirm");
                        break;
                    case 2:
                        setTitle("言語選択");
                        setRight_tvContent("確認");
                        break;
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    //调用接口，改变语言
    public void changeLanguage(final String language, final String token, final String tag) {
        String confirm = getResources().getString(R.string.Confirm);
        String cancle = getResources().getString(R.string.Cancel);
        String title = "切换语言";
        String message = "您确定要切换语言么?";
        Dialog pDialog = DialogUtil.getTowButtonDialog(_mActivity, title, message, cancle, confirm, new DialogUtil.OnDialogHandleListener() {
            @Override
            public void onCancleClick(SweetAlertDialog sDialog) {
                sDialog.dismiss();

            }

            @Override
            public void onConfirmeClick(SweetAlertDialog sDialog) {
                sDialog.dismiss();
                LoginApi.changeLanguage(language, token, new ResponseImpl<ChangeLanguageBean>() {
                    @Override
                    public void onSuccess(ChangeLanguageBean data) {
                        SharePreferenceUtil.setStringData(_mActivity, getResources().getString(R.string.app_language_pref_key), tag);
                        onChangeAppLanguage(tag);
                        //设置城市名称
                        String regionName = data.getData().getRegion_name();
                        SharePreferenceUtil.setStringData(_mActivity, "region", regionName);
                        //切换语言成功，将本地语言选择状态设置为已选择
                        SharePreferenceUtil.setBooleanData(_mActivity, LanguagesSelectFragment.key_has_selected, true);
                        _mActivity.setResult(Activity.RESULT_OK);
                        _mActivity.finish();
                    }
                }, ChangeLanguageBean.class);

            }
        });
        pDialog.show();

    }

    @Override
    public boolean enableLoadMore() {
        return false;
    }

    @Override
    public void getData(boolean refresh, int pageSize, int pageNum) {
        getAdapter().addData(new LanguageItemBean("zh", R.drawable.ic_language_zh, getResources().getString(R.string.language_zh)));
        getAdapter().addData(new LanguageItemBean("en", R.drawable.ic_language_en, getResources().getString(R.string.language_en)));
        getAdapter().addData(new LanguageItemBean("ja", R.drawable.ic_language_ja, getResources().getString(R.string.language_ja)));
        dissMissDialog();

    }

    @Override
    public void bindData(BaseViewHolder helper, LanguageItemBean item, int position) {
        View space = helper.getView(R.id.view);
        if (position == 0) {
            space.setVisibility(View.VISIBLE);
        } else {
            space.setVisibility(View.GONE);
        }
        ImageView iv_icon = helper.getView(R.id.iv_icon);
        //ImageUtil.getInstance().loadImage(LanguagesSelectFragment.this, item.getIcon_res(), iv_icon);
        iv_icon.setImageResource(item.getIcon_res());
        RelativeLayout rl_tab = helper.getView(R.id.rl_tab);
        TextView tv_name = helper.getView(R.id.tv_language_name);
        tv_name.setText(item.getName() + "");
        if (current_position == position) {
            rl_tab.setBackground(ContextCompat.getDrawable(_mActivity, R.drawable.shape_orange_solid_conner5));
            tv_name.setTextColor(ContextCompat.getColor(_mActivity, R.color.white));
        } else {
            rl_tab.setBackground(ContextCompat.getDrawable(_mActivity, R.drawable.shape_gray_stroke_conner5));
            tv_name.setTextColor(ContextCompat.getColor(_mActivity, R.color.black));
        }

    }

    //使用eventBust
    @Override
    public boolean onBackPressedSupport() {
        if (canback) {
            return super.onBackPressedSupport();
        } else {
            return true;
        }

    }

    private void onChangeAppLanguage(String newLanguage) {
        AppLanguageUtils.changeAppLanguage(_mActivity, newLanguage);
        AppLanguageUtils.changeAppLanguage(_mActivity.getApplicationContext(), newLanguage);
    }


}
