package com.hym.eshoplib.bean.home;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 胡彦明 on 2018/3/5.
 * <p>
 * Description 右侧商品列表
 * <p>
 * otherTips
 */

public class GoodsSectionBean extends SectionEntity<ClassicSecondListBean.DataBean.SonBean>{
    public GoodsSectionBean(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public GoodsSectionBean(ClassicSecondListBean.DataBean.SonBean sonBean) {
        super(sonBean);
    }
}
