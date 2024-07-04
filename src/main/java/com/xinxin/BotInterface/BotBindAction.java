package com.xinxin.BotInterface;

public interface BotBindAction {
    String getBindQQ(String Player);

    String getBindPlayerName(String QQ);

    //String getBindPlayerUUID(String QQ);
    boolean setBind(String QQ, String Player);
    boolean unBind(String QQ);
    boolean addBind(String QQ, String Player);
}
