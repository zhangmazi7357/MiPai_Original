package cn.hym.superlib.activity.base;

import android.os.Bundle;
import androidx.annotation.Nullable;


import cn.hym.superlib.R;
import cn.hym.superlib.interfaces.action.IFragmentAction;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/7/19.
 * <p>
 * Description 普通的入口activity,只为装载fragment 承担容器作用,
 * <p>
 * otherTips
 */

public abstract class BaseActionActivity extends BasekitActivity {
    public static String key_model_type = "model_type";
    public static String key_action_type = "action_type";
    IFragmentAction iFragmentAction;
    int model_type = -1;//模块类型
    int action = -1;//目标fragment 类型

    @Override
    public int getContentViewResId() {
        return R.layout.activity_common_root;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //功能模块类型 比如登录模块，购物车模块等
        model_type = getIntent().getIntExtra(key_model_type, -1);
        //功能模块下 具体业务类型，比如登录模块下的 手机登录，邮箱登录等
        action = getIntent().getIntExtra(key_action_type, -1);
        iFragmentAction = getAction();
        if (iFragmentAction == null) {
            throw new RuntimeException("Must implements IFragmentAction and cont't return null");
        }
        SupportFragment fragment = iFragmentAction.getTargetFragment(model_type, action);
        if (fragment == null) {
            throw new RuntimeException("TargetFragment con't be null");
        }
        //默认传入bundle.所以fragment.newInstance时候穿不传bundele都无所谓，最终会传入activity带过来的bundle
        fragment.setArguments(getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras());
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, fragment);
        }
        // Logger.d("加载fragment="+fragment==null);
    }

    @Override
    public void doLogic() {

    }

    public abstract IFragmentAction getAction();

    //模板启动代码
//    public static void start(Activity from, Bundle args, int model_type, int action){
//        Intent intent=new Intent(from,ActionActivity.class);
//        if(args!=null){
//            intent.putExtras(args);
//        }
//        intent.putExtra(ActionActivity.key_model_type,model_type);
//        intent.putExtra(ActionActivity.key_action_type,action);
//        from.startActivity(intent);
//    }
    public static Bundle getActionBundle(int model_type, int action_type) {
        Bundle bundle = new Bundle();
        bundle.putInt(BaseActionActivity.key_model_type, model_type);
        bundle.putInt(BaseActionActivity.key_action_type, action_type);
        return bundle;
    }
}
