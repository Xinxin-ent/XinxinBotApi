package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotActionBuilder;
import com.xinxin.BotTool.OneBotMessageTool;
import com.xinxin.GroupData.GroupInfo;
import com.xinxin.GroupData.GroupMemberInfo;
import com.xinxin.PluginBasicTool.BotData;
import com.xinxin.PluginBasicTool.JsonAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.xinxin.BotTool.OneBotMessageTool.listToOneBotArray;
import static com.xinxin.BotTool.OneBotMessageTool.msgToOneBotArray;

public class OneBotActionBuilder implements BotActionBuilder {
    @Override
    public long sendPrivateMessage(long user_id, String message, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_private_msg")
                        .add("user_id",user_id)
                        .add("message",auto_escape.length == 1 && auto_escape[0] ? message : msgToOneBotArray(message))
                        .add("auto_escape", auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }

    @Override
    public long sendInterimMessage(long user_id, long group_id, String message, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_private_msg")
                    .add("user_id",user_id)
                    .add("group_id",group_id)
                    .add("message",auto_escape.length == 1 && auto_escape[0] ? message : msgToOneBotArray(message))
                    .add("auto_escape", auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }

    @Override
    public long sendGroupMessage(long group_id, String message, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_group_msg")
                    .add("group_id",group_id)
                    .add("message",auto_escape.length == 1 && auto_escape[0] ? message : msgToOneBotArray(message))
                    .add("auto_escape",auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }
    @Override
    public long sendPrivateMessage(long user_id, List<String> messages, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_private_msg")
                    .add("user_id",user_id)
                    .add("message", auto_escape.length == 1 && auto_escape[0] ? listToOneBotArray(messages) : OneBotMessageTool.listToString(messages))
                    .add("auto_escape",auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }

    @Override
    public long sendInterimMessage(long user_id, long group_id, List<String> messages, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_private_msg")
                    .add("user_id",user_id)
                    .add("group_id",group_id)
                    .add("message",auto_escape.length == 1 && auto_escape[0] ? listToOneBotArray(messages) : OneBotMessageTool.listToString(messages))
                    .add("auto_escape",auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }

    @Override
    public long sendGroupMessage(long group_id, List<String> messages, boolean... auto_escape) {
        return getData(BotData.getClient().sendData(
                new JsonAction("send_group_msg")
                    .add("group_id",group_id)
                    .add("message",auto_escape.length == 1 && auto_escape[0] ? listToOneBotArray(messages) : OneBotMessageTool.listToString(messages))
                    .add("auto_escape",auto_escape.length == 1 && auto_escape[0]),true
        )).getInt("message_id");
    }

    @Override
    public String getMessage(long message_id) {
        return getData(BotData.getClient().sendData(
                new JsonAction("get_msg")
                        .add("message_id",message_id),true
            )).getString("raw_message");
    }

    @Override
    public void deleteMessage(long message_id,long target) {
        BotData.getClient().sendData(new JsonAction("delete_msg")
                .add("message_id",message_id));
    }

    @Override
    public void setFriendAdd(String flag, boolean approve, String remark,long fromId) {
        BotData.getClient().sendData(new JsonAction("set_friend_add_request")
                .add("flag",flag)
                .add("approve", approve)
                .add("remark",remark)
        );
    }

    @Override
    public void deleteFriend(long user_id) {
        BotData.getClient().sendData(new JsonAction("delete_friend")
                .add("user_id",user_id)
        );
    }

    @Override
    public void setGroupAddRequest(String flag, String sub_type, boolean approve, String reason) {
        BotData.getClient().sendData(new JsonAction("set_group_add_request")
                .add("flag",flag)
                .add("sub_type",sub_type)
                .add("approve",approve)
                .add("reason",reason)
        );
    }

    @Override
    public void setGroupName(long group_id, String group_name) {
        BotData.getClient().sendData(new JsonAction("set_group_name")
                .add("group_id",group_id)
                .add("group_name",group_name)
        );
    }

    @Override
    public void setGroupCard(long group_id, long user_id, String card) {
        BotData.getClient().sendData(new JsonAction("set_group_card")
                .add("group_id",group_id)
                .add("user_id",user_id)
                .add("card",card)
        );
    }

    @Override
    public void setGroupSpecialTitle(long group_id, long user_id, String special_title) {
        BotData.getClient().sendData(new JsonAction("set_group_special_title")
                .add("group_id",group_id)
                .add("user_id",user_id)
                .add("special_title",special_title)
        );
    }

    @Override
    public void setGroupKick(long group_id, long user_id, boolean reject_add_request) {
        BotData.getClient().sendData(new JsonAction("set_group_kick")
                .add("group_id",group_id)
                .add("user_id",user_id)
                .add("reject_add_request",reject_add_request)
        );
    }

    @Override
    public void setGroupLeave(long group_id, boolean is_dismiss) {
        BotData.getClient().sendData(new JsonAction("set_group_leave")
                .add("group_id",group_id)
                .add("is_dismiss",is_dismiss)
        );
    }

    @Override
    public void setGroupBan(long group_id, long user_id, long duration) {
        BotData.getClient().sendData(new JsonAction("set_group_ban")
                .add("group_id",group_id)
                .add("user_id",user_id)
                .add("duration",duration)
        );
    }

    @Override
    public void setGroupWholeBan(long group_id, boolean enable) {
        BotData.getClient().sendData(new JsonAction("set_group_whole_ban")
                .add("group_id",group_id)
                .add("enable",enable)
        );
    }

    @Override
    public void uploadGroupFile(long group_id, String filePath, String name,String folder) {
        BotData.getClient().sendData(new JsonAction("upload_group_file")
                .add("group_id",group_id)
                .add("file",filePath)
                .add("name",name)
                .add("folder",folder)
        );
    }

    @Override
    public void uploadPrivateFile(long user_id, String filePath, String name) {
        BotData.getClient().sendData(new JsonAction("upload_private_file")
                .add("user_id",user_id)
                .add("file",filePath)
                .add("name",name)
        );
    }

    @Override
    public void deleteGroupFile(long group_id, String file_id, long busid) {
        BotData.getClient().sendData(new JsonAction("delete_group_file")
                .add("group_id",group_id)
                .add("file_id",file_id)
                .add("busid",busid)
        );
    }

    @Override
    public GroupInfo getGroupInfo(long group_id, boolean no_cache) {
        JsonAction jsonAction = new JsonAction("get_group_info")
                .add("group_id",group_id)
                .add("no_cache",no_cache);

        return new GroupInfo(getData(BotData.getClient().sendData(jsonAction,true)));
    }

    @Override
    public List<GroupInfo>  getGroupList(boolean no_cache) {
        JsonAction jsonAction = new JsonAction("get_group_list")
                .add("no_cache",no_cache);

        List<GroupInfo> groupInfos = new ArrayList<>();
        JSONArray jsonArray = getArrayData(BotData.getClient().sendData(jsonAction,true));
        for (int i = 0; i < jsonArray.size(); i++) {
            groupInfos.add(new GroupInfo(jsonArray.getJSONObject(i)));
        }
        return groupInfos;
    }

    @Override
    public GroupMemberInfo getGroupMemberInfo(long group_id,long user_id, boolean no_cache) {
        JsonAction jsonAction = new JsonAction("get_group_member_info")
                .add("group_id",group_id)
                .add("user_id",user_id)
                .add("no_cache",no_cache);

        return new GroupMemberInfo(getData(BotData.getClient().sendData(jsonAction,true)));
    }

    @Override
    public List<GroupMemberInfo>  getGroupMemberList(long group_id,boolean no_cache) {
        JsonAction jsonAction = new JsonAction("get_group_member_list")
                .add("group_id",group_id)
                .add("no_cache",no_cache);

        List<GroupMemberInfo> groupMemberInfos = new ArrayList<>();
        JSONArray jsonArray = getArrayData(BotData.getClient().sendData(jsonAction,true));
        for (int i = 0; i < jsonArray.size(); i++) {
            groupMemberInfos.add(new GroupMemberInfo(jsonArray.getJSONObject(i)));
        }
        return groupMemberInfos;
    }


    public JSONObject getData(JSONObject json){
        return JSONObject.fromObject(json.getString("data"));
    }
    public JSONArray getArrayData(JSONObject json){
        return JSONArray.fromObject(json.getString("data"));
    }
}
