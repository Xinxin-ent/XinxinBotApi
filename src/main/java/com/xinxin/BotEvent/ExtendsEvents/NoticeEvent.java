package com.xinxin.BotEvent.ExtendsEvents;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class NoticeEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    String json;
    long time,self_id;
    String post_type,notice_type,sub_type;
    public NoticeEvent(String json, long time, long self_id, String post_type, String notice_type){
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.post_type = post_type;
        this.notice_type = notice_type;
    }
    public NoticeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type){
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.post_type = post_type;
        this.notice_type = notice_type;
        this.sub_type = sub_type;
    }

    public String getNotice_type() {
        return notice_type;
    }

    public String getPost_type() {
        return post_type;
    }

    public String getJson() {
        return json;
    }

    public String getSub_type() {
        return sub_type;
    }

    public long getSelf_id() {
        return self_id;
    }

    public long getTime() {
        return time;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
