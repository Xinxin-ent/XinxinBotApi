package com.xinxin.BotEvent;


import net.sf.json.JSONObject;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class RequestEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String Json;//原始文本
    private String self_id;//机器人qq
    private String user_id;//请求人QQ
    private String request_type;//请求类型
    private String comment;//验证消息
    private String flag;//请求flag
    private String group_id;//请求群号
    private String sub_type;//事件子类型

    public RequestEvent(String Json,String self_id,String user_id,String request_type,String comment,String flag,String group_id,String sub_type) {
        this.Json = Json;
        this.self_id = self_id;
        this.user_id = user_id;
        this.request_type = request_type;
        this.comment = comment;
        this.flag = flag;
        this.group_id = group_id;
        this.sub_type = sub_type;
    }

    public String getJson() {
        return this.Json;
    }
    public String getSelfId() {
        return this.self_id;
    }
    public String getUserId() {
        return this.user_id;
    }
    public String getRequestType() {
        return this.request_type;
    }
    public String getComment() {
        return this.comment;
    }
    public String getFlag() {
        return this.flag;
    }
    public String getGroupId() {
        return this.group_id;
    }
    public String getSubType() {
        return this.sub_type;
    }


    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
