package com.xinxin.BotEvent;

import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.RequestEvent;

public class GroupRequestEvent extends RequestEvent {
    long group_id,user_id;

    public GroupRequestEvent(String json, long time, long self_id, String post_type, String request_type, String sub_type, String comment, String flag,long group_id,long user_id) {
        super(json, time, self_id, post_type, request_type, sub_type, comment, flag);
        this.group_id = group_id;
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroupRequest(boolean approve, String reason){
        BotAction.setGroupAddRequest(getFlag(),getSub_type(),approve,reason);
    }

}
