package com.xinxin.BotEvent.ExtendsEvents;

import com.xinxin.BotApi.BotAction;

import java.util.List;

public class PrivateNoticeEvent extends NoticeEvent {
    long user_id;

    public PrivateNoticeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long user_id) {
        super(json,time, self_id, post_type, notice_type,sub_type);
        this.user_id = user_id;
    }
    public PrivateNoticeEvent(String json, long time, long self_id, String post_type, String notice_type, long user_id) {
        super(json,time, self_id, post_type, notice_type);
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }


    public void sendMessage(String message){
        BotAction.sendPrivateMessage(getUser_id(), message);
    }
    public void sendListMessage(List<String> message){
        BotAction.sendPrivateMessage(getUser_id(), message);
    }

    public void deleteFriend(){
        BotAction.deleteFriend(getUser_id());
    }

}
