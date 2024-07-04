package com.xinxin.BotEvent.ExtendsEvents;


import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class MessageEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private String json;
    private long time;
    private long self_id;
    private String post_type;
    private String message_type;
    private String sub_type;
    private long user_id;
    private long message_id;
    private String message;
    private String arrayJsonMessage;
    private long font;
    private String sender_nickname;
    private String sender_card;
    private String sender_role;


    public MessageEvent(String json, long time, long self_id, String post_type, String message_type, String sub_type
            ,long message_id, long user_id,String message,String arrayJsonMessage,long font
            ,String sender_nickname, String sender_card, String sender_role) {
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.post_type = post_type;
        this.sub_type = sub_type;
        this.user_id = user_id;
        this.message_type = message_type;
        this.message_id = message_id;
        this.message = message;
        this.arrayJsonMessage = arrayJsonMessage;
        this.font = font;
        this.sender_nickname = sender_nickname;
        this.sender_card = sender_card;
        this.sender_role = sender_role;
    }


    public String getSub_type() {
        return sub_type;
    }

    public String getPost_type() {
        return post_type;
    }

    public long getUser_id() {
        return user_id;
    }

    public long getTime() {
        return time;
    }

    public long getSelf_id() {
        return self_id;
    }

    public String getJson() {
        return json;
    }

    public long getMessage_id() {
        return message_id;
    }

    public long getFont() {
        return font;
    }

    public String getMessage() {
        return message;
    }


    public String getMessage_type() {
        return message_type;
    }

    public String getArrayJsonMessage() {
        return arrayJsonMessage;
    }

    public String getSender_card() {
        return sender_card;
    }

    public String getSender_nickname() {
        return sender_nickname;
    }

    public String getSender_role() {
        return sender_role;
    }


    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}
