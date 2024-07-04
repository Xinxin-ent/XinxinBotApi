package com.xinxin.BotEvent;


import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.ExtendsEvents.MessageEvent;
import com.xinxin.BotEvent.ExtendsEvents.PrivateNoticeEvent;
import net.sf.json.JSONArray;

public final class PrivatePokeEvent extends PrivateNoticeEvent {
    long target_id;

    public PrivatePokeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long user_id,long target_id) {
        super(json, time, self_id, post_type, notice_type, sub_type, user_id);
        this.target_id = target_id;
    }

    public long getTarget_id() {
        return target_id;
    }
}
