package cn.hym.superlib.activity.base;



import cn.hym.superlib.R;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/7/19.
 * <p>
 * Description 普通的入口activity,只为装载fragment 承担容器作用
 * <p>
 * otherTips
 */

public abstract class BaseRootActivity extends BasekitActivity {
    public BaseRootActivity() {
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_common_root;
    }

    @Override
    public void doLogic() {
        if(getTargetFragment()==null){
            throw new RuntimeException("targetFragment con't be null");
        }
        SupportFragment fragment=getTargetFragment();
        loadRootFragment(R.id.fl_container, getTargetFragment());
    }
    public abstract SupportFragment getTargetFragment();
}
