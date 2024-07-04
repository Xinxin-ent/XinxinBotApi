package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;
import com.xinxin.BotEvent.ExtendsEvents.PrivateNoticeEvent;

public final class GroupPokeEvent extends GroupNoticeEvent {
    long target_id;

    public GroupPokeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,long target_id) {
        super(json, time, self_id, post_type, notice_type, sub_type, group_id, user_id);
        this.target_id = target_id;
    }


    public long getTarget_id() {
        return target_id;
    }
}
