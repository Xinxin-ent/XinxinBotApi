package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotActionBuilder;
import com.xinxin.BotTool.MiraiMessageTool;
import com.xinxin.GroupData.GroupInfo;
import com.xinxin.GroupData.GroupMemberInfo;
import com.xinxin.PluginBasicTool.BotData;
import com.xinxin.PluginBasicTool.JsonAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MiraiActionBuilder implements BotActionBuilder {
    @Override
    public long sendPrivateMessage(long user_id, String message, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("sendFriendMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("target",user_id)
                    .add("messageChain", MiraiMessageTool.msgToMiraiArray(message)),true
        )).getInt("messageId");
    }

    @Override
    public long sendInterimMessage(long user_id, long group_id, String message, boolean... auto_escape) {
       return getData(BotData.getClient().sendData(
                new JsonAction("sendFriendMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("qq",user_id)
                    .add("group",group_id)
                    .add("messageChain",MiraiMessageTool.msgToMiraiArray(message)),true
        )).getInt("messageId");
    }

    @Override
    public long sendGroupMessage(long group_id, String message, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("sendGroupMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("target",group_id)
                    .add("messageChain",message),true
        )).getInt("messageId");
    }

    @Override
    public long sendPrivateMessage(long user_id, List<String> messages, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("sendFriendMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("target",user_id)
                    .add("messageChain", MiraiMessageTool.listToMiraiArray(messages)),true
        )).getInt("messageId");
    }

    @Override
    public long sendInterimMessage(long user_id, long group_id, List<String> messages, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("sendFriendMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("qq",user_id)
                    .add("group",group_id)
                    .add("messageChain",MiraiMessageTool.listToMiraiArray(messages)),true
        )).getInt("messageId");
    }

    @Override
    public long sendGroupMessage(long group_id, List<String> messages, boolean... auto_escape) {

        return getData(BotData.getClient().sendData(
                new JsonAction("sendGroupMessage")
                    .add("sessionKey",BotData.getSessionKey())
                    .add("target",group_id)
                    .add("messageChain",MiraiMessageTool.listToMiraiArray(messages)),true
        )).getInt("messageId");
    }

    @Override
    public String getMessage(long message_id) {
        return null;
    }


    @Override
    public void deleteMessage(long message_id,long target) {
        BotData.getClient().sendData(new JsonAction("recall")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",target)
                .add("messageId",message_id));
    }

    @Override
    public void setFriendAdd(String flag, boolean approve, String remark,long fromId) {
        BotData.getClient().sendData(new JsonAction("resp_newFriendRequestEvent")
                .add("sessionKey",BotData.getSessionKey())
                .add("eventId",Long.valueOf(flag))
                .add("fromId",fromId)
                .add("operate",approve?0:1)
        );
    }

    @Override
    public void deleteFriend(long user_id) {
        BotData.getClient().sendData(new JsonAction("deleteFriend")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",user_id)
        );
    }

    @Override
    public void setGroupAddRequest(String flag, String sub_type, boolean approve, String reason) {
        BotData.getClient().sendData(new JsonAction("resp_memberJoinRequestEvent")
                .add("sessionKey",BotData.getSessionKey())
                .add("eventId",Long.valueOf(flag))
                .add("operate",approve?0:1)
        );
    }

    @Override
    public void setGroupName(long group_id, String group_name) {
        BotData.getClient().sendData(new JsonAction("groupConfig").setSubCommand("update")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
                .add("config",new JsonAction().add("group_name",group_name).getParams().toString())
        );
    }

    @Override
    public void setGroupCard(long group_id, long user_id, String card) {
        BotData.getClient().sendData(new JsonAction("memberInfo").setSubCommand("update")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
                .add("memberId",user_id)
                .add("info",new JsonAction().add("name",card).getParams().toString())
        );
    }

    @Override
    public void setGroupSpecialTitle(long group_id, long user_id, String special_title) {
        BotData.getClient().sendData(new JsonAction("memberInfo").setSubCommand("update")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
                .add("memberId",user_id)
                .add("info",new JsonAction().add("specialTitle",special_title).getParams().toString())
        );
    }

    @Override
    public void setGroupKick(long group_id, long user_id, boolean reject_add_request) {
        BotData.getClient().sendData(new JsonAction("kick")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
                .add("memberId",user_id)
                .add("block",reject_add_request)
        );
    }

    @Override
    public void setGroupLeave(long group_id, boolean is_dismiss) {
        BotData.getClient().sendData(new JsonAction("quit")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
        );
    }

    @Override
    public void setGroupBan(long group_id, long user_id, long duration) {
        BotData.getClient().sendData(new JsonAction("mute")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
                .add("memberId",user_id)
                .add("duration",duration)
        );
    }

    @Override
    public void setGroupWholeBan(long group_id, boolean enable) {
        BotData.getClient().sendData(new JsonAction(enable?"muteAll":"unmuteAll")
                .add("sessionKey",BotData.getSessionKey())
                .add("target",group_id)
        );
    }

    @Override
    @Deprecated
    public void uploadGroupFile(long group_id, String filePath, String name, String folder) {
        System.out.println("MiraiAPI: uploadGroupFile is deprecated");
    }

    @Override
    @Deprecated
    public void uploadPrivateFile(long user_id, String filePath, String name) {
        System.out.println("MiraiAPI: uploadPrivateFile is deprecated");
    }

    @Override
    @Deprecated
    public void deleteGroupFile(long group_id, String file_id, long busid) {
        System.out.println("MiraiAPI: deleteGroupFile is deprecated");
    }

    @Override
    public GroupInfo getGroupInfo(long group_id, boolean no_cache) {
        System.out.println("MiraiAPI: deleteGroupFile is deprecated");
        return null;
    }
    @Override
    public List<GroupInfo>  getGroupList(boolean no_cache) {
        System.out.println("MiraiAPI: deleteGroupFile is deprecated");
        return null;
    }
    @Override
    public GroupMemberInfo getGroupMemberInfo(long group_id, long user_id, boolean no_cache) {
        System.out.println("MiraiAPI: deleteGroupFile is deprecated");
        return null;
    }

    @Override
    public List<GroupMemberInfo>  getGroupMemberList(long group_id,boolean no_cache) {
        System.out.println("MiraiAPI: deleteGroupFile is deprecated");
        return null;
    }




    public JSONObject getData(JSONObject json){
        return JSONObject.fromObject(json.getString("data"));
    }
    public JSONArray getArrayData(JSONObject json){
        return JSONArray.fromObject(json.getString("data"));
    }
}
