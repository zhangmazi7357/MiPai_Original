package cn.hym.superlib.interfaces.action;


import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 胡彦明 on 2017/12/5.
 * <p>
 * Description
 * <p>
 * otherTips
 */

public interface IFragmentAction {
    /**
     *
     * @param model_type 模块类型，具体目标fragment 类型
     * @param action
     * @return
     */
    SupportFragment getTargetFragment(int model_type, int action);
}
