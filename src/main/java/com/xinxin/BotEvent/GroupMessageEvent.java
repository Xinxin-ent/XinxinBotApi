package com.xinxin.BotEvent;


import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.MessageEvent;
import com.xinxin.BotTool.OneBotMessageTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;


public final class GroupMessageEvent extends MessageEvent {

    long group_id;

    public GroupMessageEvent(String json, long time, long self_id, String post_type, String message_type, String sub_type, long message_id, long user_id, String message, String arrayJsonMessage, long font, String sender_nickname, String sender_card, String sender_role, long group_id) {
        super(json, time, self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname, sender_card, sender_role);
        this.group_id = group_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void deleteMessage(){
        BotAction.deleteMessage(getMessage_id(),getGroup_id());
    }

    public long replyMessage(String message){
        JSONArray array = new JSONArray();

        array.add(new JSONObject().element("type","reply").element("data",new JSONObject().element("id",getMessage_id())));
        array.add(new JSONObject().element("type","text").element("data",new JSONObject().element("text",message)));

        return BotAction.sendGroupMessage(getGroup_id(),array.toString(),true);
    }

    public long replyMessage(List<String> message){
        JSONArray array = new JSONArray();

        array.add(new JSONObject().element("type","reply").element("data",new JSONObject().element("id",getMessage_id())));
        array.addAll(JSONArray.fromObject(OneBotMessageTool.listToOneBotArray(message)));

        return BotAction.sendGroupMessage(getGroup_id(),array.toString(),true);
    }
}
