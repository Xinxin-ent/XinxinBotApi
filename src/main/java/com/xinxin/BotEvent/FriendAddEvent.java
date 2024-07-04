package com.xinxin.BotEvent;


import com.xinxin.BotApi.BotAction;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class FriendAddEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    private String json;
    private long time;
    private long self_id;
    private String post_type;
    private String notice_type;
    private long user_id;

    public FriendAddEvent
        (String json, long time, long self_id, String post_type,String notice_type,long user_id)
    {
        this.json = json;
        this.time = time;
        this.self_id = self_id;
        this.notice_type = notice_type;
        this.post_type = post_type;
        this.user_id = user_id;
    }


    public String getNotice_type() {
        return notice_type;
    }

    public long getSelf_id() {
        return self_id;
    }

    public long getTime() {
        return time;
    }

    public long getUser_id() {
        return user_id;
    }


    public String getJson() {
        return json;
    }

    public String getPost_type() {
        return post_type;
    }
    
    public void deleteFriend() {
        BotAction.deleteFriend(getUser_id());
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
