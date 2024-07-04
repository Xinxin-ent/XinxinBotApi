package com.xinxin.BotInterface;

import com.xinxin.BotEvent.*;
import com.xinxin.XinxinBotApi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bukkit.Bukkit;

public interface BotDataProcessing {
    void handle(JSONObject json,String data);
    boolean response(JSONObject json);
    /**
     * 消息事件
     */
    default void callGroupMessageEvent(String json, long time, long self_id, String post_type, String message_type, String sub_type, long message_id, long user_id, String message, String arrayJsonMessage, long font, String sender_nickname, String sender_card, String sender_role, long group_id){
        Bukkit.getPluginManager().callEvent(new GroupMessageEvent(
                json, time, self_id, post_type, message_type, sub_type, message_id, user_id, message
                , arrayJsonMessage, font, sender_nickname, sender_card, sender_role, group_id));
    }
    default void callPrivateMessageEvent(String json, long time, long self_id, String post_type, String message_type, String sub_type, long message_id, long user_id, String message, String arrayJsonMessage, long font, String sender_nickname){
            Bukkit.getPluginManager().callEvent(new PrivateMessageEvent(json, time, self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname));

    }

    /**
     * 请求事件
     */
    default void callFriendRequestEvent(String json, long time, long self_id, String post_type, String request_type, String comment, String flag,long user_id){
        Bukkit.getPluginManager().callEvent(new FriendRequestEvent(json, time, self_id, post_type,request_type,comment,flag,user_id));
    };
    default void callGroupRequestEvent(String json, long time, long self_id, String post_type, String request_type, String sub_type, String comment, String flag,long group_id,long user_id){
        Bukkit.getPluginManager().callEvent(new GroupRequestEvent(json, time, self_id, post_type,request_type,sub_type,comment,flag,group_id,user_id));
    }
    /**
     * 通知事件
     */
    default void callFriendAddEvent(String json, long time, long self_id, String post_type,String notice_type,long user_id){
        Bukkit.getPluginManager().callEvent(new FriendAddEvent(json, time, self_id, post_type,notice_type,user_id));
    };
    default void callGroupBanEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,long operator_id,long duration){
        Bukkit.getPluginManager().callEvent(new GroupBanEvent(json, time, self_id, post_type,notice_type,sub_type,group_id,user_id,operator_id,duration));
    };

    default void callGroupCardEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id,String card_new,String card_old){

        Bukkit.getPluginManager().callEvent(new GroupCardEvent(json, time, self_id, post_type,notice_type,group_id,user_id,card_new,card_old));
    }
    default void callGroupMessageDeleteEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id,long operator_id,long message_id){

        Bukkit.getPluginManager().callEvent(new GroupMessageDeleteEvent(json, time, self_id, post_type,notice_type,group_id,user_id,operator_id,message_id));
    }

    default void callGroupPokeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,long target_id){

        Bukkit.getPluginManager().callEvent(new GroupPokeEvent(json, time, self_id, post_type,notice_type,sub_type,group_id,user_id,target_id));
    }

    default void callGroupTitleEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id,String title){

        Bukkit.getPluginManager().callEvent(new GroupTitleEvent(json, time, self_id, post_type,notice_type,sub_type,group_id,user_id,title));
    }
    default void callGroupUploadFileEvent(String json, long time, long self_id, String post_type, String notice_type, long group_id, long user_id
            ,String file_id,String file_name,long file_size,long file_busid){

        Bukkit.getPluginManager().callEvent(new GroupUploadFileEvent(json, time, self_id, post_type,notice_type,group_id,user_id,file_id,file_name,file_size,file_busid));
    }
    default void callGroupUserChangesEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long group_id, long user_id, long operator_id){

        Bukkit.getPluginManager().callEvent(new GroupUserChangesEvent(json, time, self_id, post_type,notice_type,sub_type,group_id,user_id,operator_id));
    }
    default void callPrivateMessageDeleteEvent(String json, long time, long self_id, String post_type, String notice_type, long user_id,long message_id){

        Bukkit.getPluginManager().callEvent(new PrivateMessageDeleteEvent(json, time, self_id, post_type,notice_type,user_id,message_id));
    }
    default void callPrivatePokeEvent(String json, long time, long self_id, String post_type, String notice_type, String sub_type, long user_id,long target_id){

        Bukkit.getPluginManager().callEvent(new PrivatePokeEvent(json, time, self_id, post_type,notice_type,sub_type,user_id,target_id));
    }
}
