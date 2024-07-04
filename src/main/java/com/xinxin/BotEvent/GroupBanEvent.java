package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupBanEvent extends GroupNoticeEvent {
    private long operator_id;
    private long duration;

    public GroupBanEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,long operator_id,long duration) {
        super(json, time, self_id, post_type, notice_type, sub_type, group_id, user_id);
        this.operator_id = operator_id;
        this.duration = duration;
    }


    public long getDuration() {
        return duration;
    }
    public long getOperator_id() {
        return operator_id;
    }

}
