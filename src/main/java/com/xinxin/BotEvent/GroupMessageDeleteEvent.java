package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupMessageDeleteEvent extends GroupNoticeEvent {
    private long operator_id;
    private long message_id;

    public GroupMessageDeleteEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id,long operator_id,long message_id) {
        super(json, time, self_id, post_type, notice_type, group_id, user_id);
        this.operator_id = operator_id;
        this.message_id = message_id;
    }

    public long getOperator_id() {
        return operator_id;
    }

    public long getMessage_id() {
        return message_id;
    }
}
