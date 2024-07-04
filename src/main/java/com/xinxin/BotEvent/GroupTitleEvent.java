package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupTitleEvent extends GroupNoticeEvent {

    private String title;

    //群头衔事件
    public GroupTitleEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,String title) {
        super(json, time, self_id, post_type, notice_type, sub_type, group_id, user_id);
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

}
