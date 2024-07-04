package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupUserChangesEvent extends GroupNoticeEvent {


    private long operator_id;

    //群用户变动事件
    public GroupUserChangesEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id, long operator_id) {
        super(json, time, self_id, post_type, notice_type, sub_type, group_id, user_id);
        this.operator_id = operator_id;
    }

    public boolean isIncrease(){
        return getNotice_type().equals("group_increase");
    }


    public long getOperator_id() {
        return operator_id;
    }

}
