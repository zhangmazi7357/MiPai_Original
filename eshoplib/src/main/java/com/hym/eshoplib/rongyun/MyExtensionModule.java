package com.hym.eshoplib.rongyun;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.plugin.ImagePlugin;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

/**
 * Created by 胡彦明 on 2018/8/21.
 * <p>
 * Description 自定义展开框
 * <p>
 * otherTips
 */

public class MyExtensionModule  extends DefaultExtensionModule {
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules=new ArrayList<>();
        pluginModules.add(new ImagePlugin());
        pluginModules.add(new FilePlugin());
        return pluginModules;
    }


}
