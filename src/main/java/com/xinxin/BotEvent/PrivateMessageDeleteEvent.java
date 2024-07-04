package com.xinxin.BotEvent;


import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.PrivateNoticeEvent;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class PrivateMessageDeleteEvent extends PrivateNoticeEvent {


    private long message_id;

    public PrivateMessageDeleteEvent(String json, long time, long self_id, String post_type, String notice_type, long user_id,long message_id) {
        super(json, time, self_id, post_type, notice_type, user_id);
        this.message_id = message_id;
    }


    public long getMessage_id() {
        return message_id;
    }

    public String getMessage(){
        return BotAction.getMessage(getMessage_id());
    }
}
