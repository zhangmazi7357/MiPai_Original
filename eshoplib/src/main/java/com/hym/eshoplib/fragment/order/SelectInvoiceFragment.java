package com.hym.eshoplib.fragment.order;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hym.eshoplib.R;
import com.hym.eshoplib.R2;
import com.hym.eshoplib.bean.invoice.InvoiceBean;
import com.hym.eshoplib.bean.invoice.SelectInvoiceBean;
import com.hym.eshoplib.http.order.OrderApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.hym.superlib.fragment.base.BaseKitFragment;
import cn.hym.superlib.utils.common.ToastUtil;

/**
 * Created by 胡彦明 on 2018/5/1.
 * <p>
 * Description 选择发票
 * <p>
 * otherTips
 */

public class SelectInvoiceFragment extends BaseKitFragment {
    @BindView(R2.id.tv_normal)
    TextView tvNormal;
    @BindView(R2.id.iv_select_1)
    ImageView ivSelect1;
    @BindView(R2.id.tv_vat)
    TextView tvVat;
    @BindView(R2.id.iv_select_2)
    ImageView ivSelect2;
    @BindView(R2.id.invoice_title)
    TextView invoiceTitle;
    @BindView(R2.id.btn_person)
    RadioButton btnPerson;
    @BindView(R2.id.btn_company)
    RadioButton btnCompany;
    @BindView(R2.id.et_company)
    EditText etCompany;
    @BindView(R2.id.et_number)
    EditText etNumber;
    @BindView(R2.id.ll_paper)
    LinearLayout llPaper;
    @BindView(R2.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R2.id.tv_vat_num)
    TextView tvVatNum;
    @BindView(R2.id.tv_address)
    TextView tvAddress;
    @BindView(R2.id.tv_phone)
    TextView tvPhone;
    @BindView(R2.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R2.id.tv_bank_acount)
    TextView tvBankAcount;
    @BindView(R2.id.ll_vat)
    LinearLayout llVat;
    @BindView(R2.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R2.id.rg_type)
    RadioGroup rg_type;
    Unbinder unbinder;
    SelectInvoiceBean bean;
    public static SelectInvoiceFragment newInstance(Bundle args) {
        SelectInvoiceFragment fragment = new SelectInvoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentViewResId() {
        return R.layout.fragment_select_invoice;
    }

    @Override
    public void doLogic() {
        bean= (SelectInvoiceBean) getArguments().getSerializable("data");
        bean.setInvoice("1");
        bean.setInvoice_type("1");
        InvoiceBean.DataBean dataBean=new InvoiceBean.DataBean();
        //dataBean.set
        setShowProgressDialog(true);
        showBackButton();
        setTitle(R.string.Invoiceinformation);
        setRight_tv(R.string.Instruction, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        String invoice;//发票类型（1：普票，2：增票）
//        String invoice_type;//普票类型（1：个人，2：公司）
//        String invoice_taxpayer;//纳税人识别号
//        String invoice_company;//公司名称
        tvNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择纸质发票
                llPaper.setVisibility(View.VISIBLE);
                llVat.setVisibility(View.GONE);
                tvNormal.setBackgroundResource(R.drawable.shape_orangef9f1ef_solid_conner5);
                tvNormal.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_orange_f2927e));
                ivSelect1.setVisibility(View.VISIBLE);

                tvVat.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_585858));
                tvVat.setBackgroundResource(R.drawable.shape_grayf6f6f6_solid_conner5);
                ivSelect2.setVisibility(View.GONE);
                bean.setInvoice("1");
            }
        });
        tvVat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选择增值发票
                OrderApi.GetAptitude(_mActivity, new ResponseImpl<InvoiceBean>() {
                    @Override
                    public void onSuccess(InvoiceBean data) {
                        tvVat.setBackgroundResource(R.drawable.shape_orangef9f1ef_solid_conner5);
                        tvVat.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_orange_f2927e));
                        ivSelect2.setVisibility(View.VISIBLE);

                        tvNormal.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_585858));
                        tvNormal.setBackgroundResource(R.drawable.shape_grayf6f6f6_solid_conner5);
                        ivSelect1.setVisibility(View.GONE);
                        llPaper.setVisibility(View.GONE);
                        llVat.setVisibility(View.VISIBLE);
                        bean.setInvoice("2");
                    }

                    @Override
                    public void onEmptyData() {
                        super.onEmptyData();
                        ToastUtil.toast("您还没有增票资质");
                    }
                },InvoiceBean.class);
//                if(bean==null){
//
//                }else {
//                    tvVat.setBackgroundResource(R.drawable.shape_orangef9f1ef_solid_conner5);
//                    tvVat.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_orange_f2927e));
//                    ivSelect2.setVisibility(View.VISIBLE);
//
//                    tvNormal.setTextColor(ContextCompat.getColor(_mActivity,R.color.resource_gray_585858));
//                    tvNormal.setBackgroundResource(R.drawable.shape_grayf6f6f6_solid_conner5);
//                    ivSelect1.setVisibility(View.GONE);
//                    llPaper.setVisibility(View.GONE);
//                    llVat.setVisibility(View.VISIBLE);
//                }
            }
        });
        rg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId==R.id.btn_person){
                    etNumber.setVisibility(View.GONE);
                    etCompany.setVisibility(View.GONE);
                    bean.setInvoice_type("1");
                }else {
                    etNumber.setVisibility(View.VISIBLE);
                    etCompany.setVisibility(View.VISIBLE);
                    bean.setInvoice_type("2");
                }
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bean==null){
                    pop();
                    return;
                }
                if(bean.getInvoice().equals("1")){
                       if(bean.getInvoice_type().equals("2")){
                           //普票公司
                           String company=etCompany.getText().toString();
                           String num=etNumber.getText().toString();
                           if(TextUtils.isEmpty(company)){
                               ToastUtil.toast("请输入公司名称");
                               return;
                           }
                           if(TextUtils.isEmpty(num)){
                               ToastUtil.toast("请输入纳税人识别号");
                               return;
                           }
                           bean.setInvoice_company(company);
                           bean.setInvoice_taxpayer(num);
                           Bundle bundle=new Bundle();
                           bundle.putInt("position",getArguments().getInt("position"));
                           bundle.putSerializable("data",bean);
                           setFragmentResult(RESULT_OK,bundle);
                           pop();
                       }else {
                           Bundle bundle=new Bundle();
                           bundle.putInt("position",getArguments().getInt("position"));
                           bundle.putSerializable("data",bean);
                           setFragmentResult(RESULT_OK,bundle);
                           pop();
                       }
                }else if(bean.getInvoice().equals("2")){
                    //增票直接返回
                    Bundle bundle=new Bundle();
                    bundle.putInt("position",getArguments().getInt("position"));
                    bundle.putSerializable("data",bean);
                    setFragmentResult(RESULT_OK,bundle);
                    pop();
                }
            }
        });



    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
