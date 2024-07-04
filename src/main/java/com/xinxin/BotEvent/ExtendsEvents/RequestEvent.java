package com.xinxin.BotEvent.ExtendsEvents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class RequestEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    String json;
    long time,self_id;
    String post_type,request_type,sub_type,comment,flag;

    public RequestEvent(String json, long time, long self_id, String post_type, String request_type,String comment,String flag){
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.post_type = post_type;
        this.request_type = request_type;
        this.comment = comment;
        this.flag = flag;
    }
    public RequestEvent(String json, long time, long self_id, String post_type, String request_type, String sub_type,String comment,String flag){
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.post_type = post_type;
        this.request_type = request_type;
        this.sub_type = sub_type;
        this.comment = comment;
        this.flag = flag;
    }

    public String getRequest_type() {
        return request_type;
    }

    public String getComment() {
        return comment;
    }

    public String getFlag() {
        return flag;
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
