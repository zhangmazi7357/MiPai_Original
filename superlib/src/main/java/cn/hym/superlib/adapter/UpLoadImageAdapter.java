package cn.hym.superlib.adapter;

import androidx.fragment.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hym.imagelib.ImageUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import cn.hym.superlib.R;
import cn.hym.superlib.bean.local.UpLoadImageBean;

/**
 * Created by 胡彦明 on 2018/3/11.
 * <p>
 * Description 上传图片adapter
 * <p>
 * otherTips
 */

public class UpLoadImageAdapter extends BaseMultiItemQuickAdapter<UpLoadImageBean,BaseViewHolder>{

    Fragment fragment;
    onDeleteListener listener;
    boolean showMain=true;//显示主图
    boolean showDelete=true;//显示删除按钮

    public boolean isShowMain() {
        return showMain;
    }

    public void setShowMain(boolean showMain) {
        this.showMain = showMain;
    }

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public void setListener(onDeleteListener listener) {
        this.listener = listener;
    }

    public UpLoadImageAdapter(Fragment fragment, List<UpLoadImageBean> data) {
        super(data);
        this.fragment=fragment;
        addItemType(UpLoadImageBean.type_normal, R.layout.item_upload_image);
        addItemType(UpLoadImageBean.type_add, R.layout.item_add_image);

    }
    int max=8;

    public void setMax(int max) {
        this.max = max-1;
    }

    @Override
    protected void convert(final BaseViewHolder helper, UpLoadImageBean item) {
        switch (item.getItemType()){
            case UpLoadImageBean.type_normal:
                ImageView iv_icon=helper.getView(R.id.iv_icon);
                ImageView iv_delete=helper.getView(R.id.iv_delete);
                TextView tv_main=helper.getView(R.id.tv_main_image);
                Logger.d("layoutPosition="+helper.getLayoutPosition()+"===adapterPosition="+helper.getAdapterPosition());
                if(helper.getLayoutPosition()==0&&showMain){
                    tv_main.setVisibility(View.VISIBLE);
                }else {
                    tv_main.setVisibility(View.GONE);
                }
                iv_delete.setVisibility(showDelete?View.VISIBLE:View.GONE);
                iv_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // ToastUtil.toast("删除position="+helper.getAdapterPosition());
                        if(listener!=null){
                            listener.onDelete(helper.getLayoutPosition());
                        }
                        mData.remove(helper.getLayoutPosition());
                        if(mData.size()==max){
                           boolean hasAdd=false;
                           for (UpLoadImageBean bean:mData){
                               if (bean.getItemType()==UpLoadImageBean.type_add){
                                   hasAdd=true;
                                   break;
                               }
                           }
                           if(!hasAdd){
                               mData.add(new UpLoadImageBean());
                           }
                        }
                        notifyDataSetChanged();

                    }
                });
                ImageUtil.getInstance().loadRoundCornerImage(fragment,item.getImage().getCompressPath(),iv_icon,5);
                break;
            case UpLoadImageBean.type_add:
                ImageView iv_add=helper.getView(R.id.iv_icon);
                break;
        }
    }
    public interface onDeleteListener{
        public void onDelete(int position);
    }

}
