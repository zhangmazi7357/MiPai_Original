package com.hym.eshoplib.bean.home;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class FirstPagerShopBean implements MultiItemEntity {

    public static final int TEHUI = 1;
    public static final int YANXUAN = 2;
    public static int TABLE = 3;
    public static int LISTITEM = 4;
    private final int itemType;

    public FirstPagerShopBean(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
