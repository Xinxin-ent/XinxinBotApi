package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotDataProcessing;
import net.sf.json.JSONObject;

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
        long time = json.has("time") ? json.getLong("time") : 0;
        long self_id = json.has("self_id") ? json.getLong("self_id"):0;
        String post_type = json.has("post_type") ? json.getString("post_type") : null;

        //如果数据类型为 meta_event 则不处理数据
        if(post_type == null || json.getString("post_type").equalsIgnoreCase("meta_event")){
            return;
        }

        //多次调用数据
        long user_id = json.has("user_id") ? json.getLong("user_id") : 0;
        long group_id = json.has("group_id") ? json.getLong("group_id"): 0;
        long operator_id = json.has("operator_id") ? json.getLong("operator_id") : 0;
        long message_id = json.has("message_id") ? json.getLong("message_id") : 0;
        String sub_type = json.has("sub_type") ? json.getString("sub_type") : "";
        switch (post_type){
            //如果是消息类型数据
            case "message":
                String message_type = json.getString("message_type");
                String message = json.getString("raw_message");

                String arrayJsonMessage = "";

                try {
                    arrayJsonMessage = json.getJSONArray("message").toString();
                }catch (Exception e){
                    arrayJsonMessage = json.getString("message");
                }

                long font = json.getLong("font");
                String sender_nickname = JSONObject.fromObject(json.getString("sender")).getString("nickname");
                //如果是私聊消息
                if(message_type.equals("private")){
                    callPrivateMessageEvent(data,time,self_id, post_type, message_type, sub_type, message_id, user_id, message, arrayJsonMessage, font, sender_nickname);
                    break;
                }

                String sender_card = JSONObject.fromObject(json.getString("sender")).getString("card");
                String sender_role = JSONObject.fromObject(json.getString("sender")).getString("role");
                callGroupMessageEvent(data,time,self_id, post_type, message_type, sub_type,
                        message_id, user_id, message, arrayJsonMessage, font,
                        sender_nickname, sender_card, sender_role,group_id);
                break;
            //如果是请求类型数据
            case "request":
                String request_type = json.getString("request_type");
                String comment = json.getString("comment");
                String flag = json.getString("flag");
                if(request_type.equals("friend")){
                    callFriendRequestEvent(data,time,self_id, post_type, request_type, comment, flag,user_id);
                    break;
                }
                callGroupRequestEvent(data,time,self_id, post_type, request_type,sub_type, comment, flag,group_id,user_id);
                break;
            //如果是通知类型数据
            case "notice":
                String notice_type = json.getString("notice_type");
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
                        String file_id = JSONObject.fromObject(json.getString("file")).getString("id");
                        String file_name= JSONObject.fromObject(json.getString("file")).getString("name");
                        long file_size = JSONObject.fromObject(json.getString("file")).getInt("size");
                        long file_busid = JSONObject.fromObject(json.getString("file")).getInt("busid");
                        callGroupUploadFileEvent(data,time,self_id, post_type, notice_type,group_id,user_id,file_id,file_name,file_size,file_busid);
                        return;
                    //群内禁言事件
                    case "group_ban":
                        long duration = json.getLong("duration");
                        callGroupBanEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,operator_id,duration);
                        return;
                    //添加好友事件
                    case "friend_add":
                        callFriendAddEvent(data,time,self_id, post_type, notice_type,user_id);
                        return;
                    case "notify":
                        //戳一戳事件
                        if(sub_type.equals("poke")){
                            long target_id = json.getLong("target_id");
                            if(group_id == 0){
                                callPrivatePokeEvent(data,time,self_id, post_type, notice_type,sub_type,user_id,target_id);
                                return;
                            }
                            callGroupPokeEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,target_id);
                        }
                        //群头衔变更
                        if(sub_type.equals("title")){
                            String title = json.getString("title");
                            callGroupTitleEvent(data,time,self_id, post_type, notice_type,sub_type,group_id,user_id,title);
                        }
                        return;
                    //群名片更新
                    case "group_card":
                        String card_new = json.getString("card_new");
                        String card_old = json.getString("card_old");
                        callGroupCardEvent(data,time,self_id, post_type, notice_type,group_id,user_id,card_new,card_old);
                }
        }
    }

    @Override
    public boolean response(JSONObject json) {
        if (json.get("echo") != null && response.get(json.getString("echo"))!= null) {
            response.get(json.getString("echo")).complete(json);
            return true;
        }
        return false;
    }
}
