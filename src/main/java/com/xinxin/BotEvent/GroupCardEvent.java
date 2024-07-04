package com.xinxin.BotEvent;


import com.xinxin.BotEvent.ExtendsEvents.GroupNoticeEvent;

public final class GroupCardEvent extends GroupNoticeEvent {

    private String card_new;
    private String card_old;

    //群名片修改事件
    public GroupCardEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id,String card_new,String card_old) {
        super(json, time, self_id, post_type, notice_type, group_id, user_id);
        this.card_new = card_new;
        this.card_old = card_old;
    }

    public String getCard_new() {
        return card_new;
    }
    public String getCard_old() {
        return card_old;
    }
}
