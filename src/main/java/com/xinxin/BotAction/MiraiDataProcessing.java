package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotDataProcessing;
import com.xinxin.BotTool.MiraiMessageTool;
import com.xinxin.PluginBasicTool.BotData;
import net.sf.json.JSONObject;
import org.bukkit.event.Event;

import static com.xinxin.PluginBasicTool.WebSocket.response;

public class MiraiDataProcessing implements BotDataProcessing {
    @Override
    public void handle(JSONObject json,String data) {

        /*
        //处理响应
        if (data.contains("syncId") && json.get("syncId") != null && response.get(json.getString("syncId"))!= null) {
            response.get(json.getString("syncId")).complete(json);
            return;
        }*/

        if(!data.contains("data")){
            return;
        }
        json = JSONObject.fromObject(data).getJSONObject("data");

        //设置机器人密匙
        if(data.contains("session")){
            String SessionKey = json.getString("session");
            BotData.setSessionKey(SessionKey);
            return;
        }

        //通用数据
        long time = 0;
        long self_id = data.contains("eventId") ? json.getLong("eventId") : 0;
        String post_type = json.getString("type");

        //无用参数
        long user_id,group_id,message_id,operator_id,font = 0;
        String sub_type = "",message_type,message,arrayJsonMessage,sender_nickname,sender_role = ""
                ,comment = "",request_type,flag,notice_type = post_type;

        switch (post_type) {
            //好友消息
            case "FriendMessage":
                message_type = "FriendMessage";
                message_id = JSONObject.fromObject(json.getJSONArray("messageChain").get(0)).getInt("id");
                user_id = json.getJSONObject("sender").getInt("id");
                message = MiraiMessageTool.miraiArrayToMessage(json.getJSONArray("messageChain"));
                arrayJsonMessage = json.getJSONArray("messageChain").toString();
                sender_nickname = json.getJSONObject("sender").getString("nickname");
                callPrivateMessageEvent(data,time,self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname);
                break;
            //临时消息
            case "TempMessage":
                message_type = "TempMessage";
                message_id = JSONObject.fromObject(json.getJSONArray("messageChain").get(0)).getInt("id");
                user_id = json.getJSONObject("sender").getInt("id");
                message = MiraiMessageTool.miraiArrayToMessage(json.getJSONArray("messageChain"));
                arrayJsonMessage = json.getJSONArray("messageChain").toString();
                sender_nickname = json.getJSONObject("sender").getString("memberName");
                callPrivateMessageEvent(data,time,self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname);
                break;
            case "GroupMessage":
                message_type = "GroupMessage";
                message_id = JSONObject.fromObject(json.getJSONArray("messageChain").get(0)).getInt("id");
                user_id = json.getJSONObject("sender").getInt("id");
                message = MiraiMessageTool.miraiArrayToMessage(json.getJSONArray("messageChain"));
                arrayJsonMessage = json.getJSONArray("messageChain").toString();
                sender_nickname = json.getJSONObject("sender").getString("memberName");
                String sender_card = json.getJSONObject("sender").getString("memberName");
                group_id = json.getJSONObject("sender").getJSONObject("group").getInt("id");
                callGroupMessageEvent(data,time,self_id, post_type, message_type, sub_type,
                        message_id, user_id, message, arrayJsonMessage, font,
                        sender_nickname, sender_card, sender_role,group_id);
                break;
            //好友申请
            case "NewFriendRequestEvent":
                request_type = "NewFriendRequestEvent";
                flag = String.valueOf(json.getLong("eventId"));
                user_id = json.getLong("fromId");
                callFriendRequestEvent(data,time,self_id, post_type, request_type, comment, flag,user_id);
                break;
            //进群申请
            case "MemberJoinRequestEvent":
                request_type = "MemberJoinRequestEvent";
                flag = String.valueOf(json.getLong("eventId"));
                user_id = json.getLong("fromId");
                group_id = json.getLong("groupId");
                callGroupRequestEvent(data,time,self_id, post_type, request_type,sub_type, comment, flag,group_id,user_id);
                break;
            //被邀请入群
            case "BotInvitedJoinGroupRequestEvent":
                request_type = "BotInvitedJoinGroupRequestEvent";
                flag = String.valueOf(json.getLong("eventId"));
                user_id = json.getLong("fromId");
                group_id = json.getLong("groupId");
                callGroupRequestEvent(data,time,self_id, post_type, request_type,sub_type, comment, flag,group_id,user_id);
                break;
            //好友消息撤回
            case "FriendRecallEvent":
                user_id = json.getLong("authorId");
                message_id = json.getLong("messageId");
                callPrivateMessageDeleteEvent(data,time,self_id, post_type, notice_type, user_id,message_id);
                return;
            case "GroupRecallEvent":
                group_id = json.getJSONObject("group").getInt("id");
                user_id = json.getLong("authorId");
                operator_id = json.getJSONObject("operator").getInt("id");
                message_id = json.getLong("messageId");
                callGroupMessageDeleteEvent(data,time,self_id, post_type, notice_type, group_id,user_id,operator_id,message_id);
                return;
            //群成员入群
            case "MemberJoinEvent":
                group_id = json.getJSONObject("member").getJSONObject("group").getInt("id");
                operator_id = 0;
                notice_type = "group_increase";
                user_id = json.getJSONObject("member").getInt("id");
                callGroupUserChangesEvent(data,time,self_id, post_type, notice_type, sub_type,group_id,user_id,operator_id);
                return;
            //群成员被踢
            case "MemberLeaveEventKick":
                group_id =  json.getJSONObject("member").getJSONObject("group").getInt("id");
                operator_id = json.getJSONObject("operator").getInt("id");
                user_id = json.getJSONObject("member").getInt("id");
                callGroupUserChangesEvent(data,time,self_id, post_type, notice_type, sub_type,group_id,user_id,operator_id);
                return;
            //群成员主动退群
            case "MemberLeaveEventQuit":
                group_id =  json.getJSONObject("member").getJSONObject("group").getInt("id");
                operator_id = 0;
                user_id = json.getJSONObject("member").getInt("id");
                callGroupUserChangesEvent(data,time,self_id, post_type, notice_type, sub_type,group_id,user_id,operator_id);
                return;
            //群成员被禁言
            case "MemberMuteEvent":
                group_id = json.getJSONObject("operator").getJSONObject("group").getInt("id");
                operator_id = json.getJSONObject("operator").getInt("id");
                user_id = json.getJSONObject("member").getInt("id");
                long duration = json.getLong("durationSeconds");
                callGroupBanEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,operator_id,duration);
                return;
            //添加好友事件
            case "FriendAddEvent":
                user_id = json.getJSONObject("friend").getInt("id");
                callFriendAddEvent(data,time,self_id, post_type, notice_type,user_id);
                return;
            //戳一戳事件
            case "NudgeEvent":
                user_id = json.getLong("fromId");
                long target_id = json.getJSONObject("subject").getInt("id");
                if(!data.contains("Group")){
                    callPrivatePokeEvent(data,time,self_id, post_type, notice_type,sub_type,user_id,target_id);
                    return;
                }
                group_id = json.getJSONObject("subject").getInt("id");
                callGroupPokeEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,target_id);
                return;
            //群头衔变动
            case "MemberSpecialTitleChangeEvent":
                user_id = json.getJSONObject("member").getInt("id");
                group_id = json.getJSONObject("member").getJSONObject("group").getInt("id");
                String title = json.getString("current");
                callGroupTitleEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,title);
                return;
            //群名片更新
            case "MemberCardChangeEvent":
                user_id = json.getJSONObject("member").getInt("id");
                group_id = json.getJSONObject("member").getJSONObject("group").getInt("id");
                String card_new = json.getString("current");
                String card_old = json.getString("origin");
                callGroupCardEvent(data,time,self_id, post_type, notice_type,group_id,user_id,card_new,card_old);
        }
    }
    @Override
    public boolean response(JSONObject json) {
        //处理响应
        if (json.get("syncId") != null && response.get(json.getString("syncId"))!= null) {
            response.get(json.getString("syncId")).complete(json);
            return true;
        }
        return false;
    }
}
