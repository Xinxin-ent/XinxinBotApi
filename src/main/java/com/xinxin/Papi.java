package com.xinxin;

import com.xinxin.BotApi.BotBind;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class Papi extends PlaceholderExpansion {
    public Papi() {
        register();
    }

    @Override
    public String getIdentifier() {
        return XinxinBotApi.getInstance().getName();
    }


    @Override
    public String getVersion() {
        return "1.0.0";
    }

    //占位符的作者
    @Override
    public String getAuthor() {
        return "新鑫";
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {
        //玩家绑定qq
        if(s.equals("bind_qq")){
            return BotBind.getBindQQ(player.getName()) != null ? BotBind.getBindQQ(player.getName()) : "未绑定";
        }

        return null;
    }
}
