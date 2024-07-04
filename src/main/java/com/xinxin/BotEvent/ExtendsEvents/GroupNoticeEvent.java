package com.xinxin.BotEvent.ExtendsEvents;

import com.xinxin.BotApi.BotAction;

import java.util.List;

public class GroupNoticeEvent extends NoticeEvent {
    long group_id,user_id;
    public GroupNoticeEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id) {
        super(json,time, self_id, post_type, notice_type);
        this.group_id = group_id;
        this.user_id = user_id;

    }
    public GroupNoticeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id) {
        super(json,time, self_id, post_type, notice_type,sub_type);
        this.group_id = group_id;
        this.user_id = user_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void kickUser(boolean reject_add_request){
        BotAction.setGroupKick(getGroup_id(), getUser_id(), reject_add_request);
    }

    public void setUserCard(String card){
        BotAction.setGroupCard(getGroup_id(), getUser_id(), card);
    }

    public void setUserTitle(String special_title){
        BotAction.setGroupSpecialTitle(getGroup_id(), getUser_id(), special_title);
    }

    public void sendMessage(String message){
        BotAction.sendGroupMessage(getGroup_id(), message);
    }
    public void sendListMessage(List<String> message){
        BotAction.sendGroupMessage(getGroup_id(), message);
    }
    public void setDuration(long duration) {
        BotAction.setGroupBan(getGroup_id(), getUser_id(), duration);
    }

}
