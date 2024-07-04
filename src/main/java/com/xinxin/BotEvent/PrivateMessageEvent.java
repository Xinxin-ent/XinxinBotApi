package com.xinxin.BotEvent;


import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.MessageEvent;
import com.xinxin.BotTool.OneBotMessageTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.List;

public final class PrivateMessageEvent extends MessageEvent {


    public PrivateMessageEvent(String json, long time, long self_id, String post_type, String message_type, String sub_type, long message_id, long user_id, String message, String arrayJsonMessage, long font, String sender_nickname) {
        super(json, time, self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname, "", "");
    }

    public void deleteMessage(){
        BotAction.deleteMessage(getMessage_id(),getUser_id());
    }
    public long replyMessage(String message){
        JSONArray array = new JSONArray();

        array.add(new JSONObject().element("type","reply").element("data",new JSONObject().element("id",getMessage_id())));
        array.add(new JSONObject().element("type","text").element("data",new JSONObject().element("text",message)));

        return BotAction.sendPrivateMessage(getUser_id(),array.toString(),true);
    }

    public long replyMessage(List<String> message){
        JSONArray array = new JSONArray();

        array.add(new JSONObject().element("type","reply").element("data",new JSONObject().element("id",getMessage_id())));
        array.addAll(JSONArray.fromObject(OneBotMessageTool.listToOneBotArray(message)));

        return BotAction.sendPrivateMessage(getUser_id(),array.toString(),true);
    }

}
