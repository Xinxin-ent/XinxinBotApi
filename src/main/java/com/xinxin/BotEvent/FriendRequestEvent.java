package com.xinxin.BotEvent;

import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.RequestEvent;

public class FriendRequestEvent extends RequestEvent {
    long user_id;
    public FriendRequestEvent(String json, long time, long self_id, String post_type, String request_type, String comment, String flag,long user_id) {
        super(json, time, self_id, post_type, request_type, comment, flag);
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setFriendRequest(boolean approve,String remark){
        BotAction.setFriendAdd(getFlag(),approve,remark,getUser_id());
    }

}
