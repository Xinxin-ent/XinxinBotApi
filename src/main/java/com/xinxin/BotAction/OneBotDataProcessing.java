package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotDataProcessing;
import com.xinxin.BotTool.OneBotMessageTool;
import net.sf.json.JSONObject;

import java.util.Objects;

import static com.xinxin.PluginBasicTool.WebSocket.response;

public class OneBotDataProcessing implements BotDataProcessing {
    @Override
    public void handle(JSONObject json,String data) {
        /*
        //处理响应
        if (json.get("echo") != null && response.get(json.getString("echo"))!= null) {
            response.get(json.getString("echo")).complete(json);
            return;
        }*/

        //通用数据
        long time = OneBotMessageTool.getJsonLong(json,"time");
        long self_id = OneBotMessageTool.getJsonLong(json,"self_id");
        String post_type = OneBotMessageTool.getJsonString(json,"post_type");

        //如果数据类型为 meta_event 则不处理数据
        if(Objects.equals(post_type, "") || post_type.equalsIgnoreCase("meta_event")){
            return;
        }

        //多次调用数据
        long user_id = OneBotMessageTool.getJsonLong(json,"user_id");
        long group_id = OneBotMessageTool.getJsonLong(json,"group_id");
        long operator_id = OneBotMessageTool.getJsonLong(json,"operator_id");
        long message_id = OneBotMessageTool.getJsonLong(json,"message_id");
        String sub_type = OneBotMessageTool.getJsonString(json,"sub_type");
        switch (post_type){
            //如果是消息类型数据
            case "message":
                String message_type = OneBotMessageTool.getJsonString(json,"message_type");
                String message = OneBotMessageTool.getJsonString(json,"raw_message");

                String arrayJsonMessage = "";
                try {
                    arrayJsonMessage = json.getJSONArray("message").toString();
                }catch (Exception e){
                    arrayJsonMessage = OneBotMessageTool.getJsonString(json,"message");
                }

                long font = OneBotMessageTool.getJsonLong(json,"font");
                String sender_nickname = OneBotMessageTool.getJsonString(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"sender")),"nickname");
                //如果是私聊消息
                if(message_type.equals("private")){
                    callPrivateMessageEvent(data,time,self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname);
                    break;
                }

                String sender_card = OneBotMessageTool.getJsonString(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"sender")),"card");
                String sender_role = OneBotMessageTool.getJsonString(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"sender")),"role");
                callGroupMessageEvent(data,time,self_id, post_type, message_type, sub_type,
                        message_id, user_id, message, arrayJsonMessage, font,
                        sender_nickname, sender_card, sender_role,group_id);
                break;
            //如果是请求类型数据
            case "request":
                String request_type = OneBotMessageTool.getJsonString(json,"request_type");
                String comment = OneBotMessageTool.getJsonString(json,"comment");
                String flag = OneBotMessageTool.getJsonString(json,"flag");
                if(request_type.equals("friend")){
                    callFriendRequestEvent(data,time,self_id, post_type, request_type, comment, flag,user_id);
                    break;
                }
                callGroupRequestEvent(data,time,self_id, post_type, request_type,sub_type, comment, flag,group_id,user_id);
                break;
            //如果是通知类型数据
            case "notice":
                String notice_type = OneBotMessageTool.getJsonString(json,"notice_type");
                switch (notice_type){
                    //私聊消息撤回
                    case "friend_recall":
                        callPrivateMessageDeleteEvent(data,time,self_id, post_type, notice_type, user_id,message_id);
                        return;
                    //群消息撤回
                    case "group_recall":
                        callGroupMessageDeleteEvent(data,time,self_id, post_type, notice_type, group_id,user_id,operator_id,message_id);
                        return;
                    //群成员变动
                    case "group_increase":
                    case "group_decrease":
                        callGroupUserChangesEvent(data,time,self_id, post_type, notice_type, sub_type,group_id,user_id,operator_id);
                        return;
                    //群文件上传
                    case "group_upload":
                        String file_id = OneBotMessageTool.getJsonString(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"file")),"id");
                        String file_name= OneBotMessageTool.getJsonString(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"file")),"name");
                        long file_size = OneBotMessageTool.getJsonLong(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"file")),"size");
                        long file_busid = OneBotMessageTool.getJsonLong(JSONObject.fromObject(OneBotMessageTool.getJsonString(json,"file")),"busid");
                        callGroupUploadFileEvent(data,time,self_id, post_type, notice_type,group_id,user_id,file_id,file_name,file_size,file_busid);
                        return;
                    //群内禁言事件
                    case "group_ban":
                        long duration = OneBotMessageTool.getJsonLong(json,"duration");
                        callGroupBanEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,operator_id,duration);
                        return;
                    //添加好友事件
                    case "friend_add":
                        callFriendAddEvent(data,time,self_id, post_type, notice_type,user_id);
                        return;
                    case "notify":
                        //戳一戳事件
                        if(sub_type.equals("poke")){
                            long target_id = OneBotMessageTool.getJsonLong(json,"target_id");
                            if(group_id == 0){
                                callPrivatePokeEvent(data,time,self_id, post_type, notice_type,sub_type,user_id,target_id);
                                return;
                            }
                            callGroupPokeEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,target_id);
                        }
                        //群头衔变更
                        if(sub_type.equals("title")){
                            String title = OneBotMessageTool.getJsonString(json,"title");
                            callGroupTitleEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,title);
                        }
                        return;
                    //群名片更新
                    case "group_card":
                        String card_new = OneBotMessageTool.getJsonString(json,"card_new");
                        String card_old = OneBotMessageTool.getJsonString(json,"card_old");
                        callGroupCardEvent(data,time,self_id, post_type, notice_type,group_id,user_id,card_new,card_old);
                }
        }
    }

    @Override
    public boolean response(JSONObject json) {
        if (json.get("echo") != null && response.get(json.getString("echo"))!= null) {
            response.get(OneBotMessageTool.getJsonString(json,"echo")).complete(json);
            return true;
        }
        return false;
    }
}
