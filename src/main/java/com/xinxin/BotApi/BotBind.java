package com.xinxin.BotApi;

import com.xinxin.PluginBasicTool.BotData;
public class BotBind {
    // 获取某玩家的绑定QQ
    public static String getBindQQ(String playerName) {
        return BotData.getBindAction().getBindQQ(playerName);
    }
    // 获取某QQ的绑定玩家
    public static String getBindPlayerName(String qqID) {
        return BotData.getBindAction().getBindPlayerName(qqID);
    }
    // 获取某QQ的绑定玩家
    public static String getBindPlayerUUID(String qqID) {
        return BotData.getBindAction().getBindPlayerName(qqID);
    }

    //设置某QQ的绑定玩家
    public static boolean setBind(String qqID, String playerName) {
        return BotData.getBindAction().setBind(qqID, playerName);
    }

    //删除绑定玩家
    public static boolean unBind(String qqID) {
        return BotData.getBindAction().unBind(qqID);
    }

    //新增绑定玩家
    public static boolean addBind(String qqID, String playerName) {
        return BotData.getBindAction().addBind(qqID, playerName);
    }
}
