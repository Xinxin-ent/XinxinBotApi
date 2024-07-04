package com.xinxin.BotInterface;

import com.xinxin.GroupData.GroupInfo;
import com.xinxin.GroupData.GroupMemberInfo;

import java.util.List;

public interface BotActionBuilder {
    long sendPrivateMessage(long user_id, String message,boolean... auto_escape);
    long sendInterimMessage(long user_id, long group_id, String message,boolean... auto_escape);
    long sendGroupMessage(long group_id,String message, boolean... auto_escape);

    //发送list信息
    long sendPrivateMessage(long user_id, List<String> messages, boolean... auto_escape);
    long sendInterimMessage(long user_id, long group_id, List<String> messages,boolean... auto_escape);
    long sendGroupMessage(long group_id,List<String> messages, boolean... auto_escape);

    String getMessage(long message_id);
    void deleteMessage(long message_id,long target);
    void setFriendAdd(String flag,boolean approve,String remark,long fromId);
    void deleteFriend(long user_id);
    void setGroupAddRequest(String flag,String sub_type,boolean approve,String reason);

    void setGroupName(long group_id,String group_name);
    void setGroupCard(long group_id,long user_id,String card);
    void setGroupSpecialTitle(long group_id,long user_id,String special_title);

    void setGroupKick(long group_id,long user_id,boolean reject_add_request);
    void setGroupLeave(long group_id,boolean is_dismiss);
    void setGroupBan(long group_id,long user_id,long duration);
    void setGroupWholeBan(long group_id,boolean enable);
    void uploadGroupFile(long group_id,String filePath,String name,String folder);
    void uploadPrivateFile(long user_id,String filePath,String name);
    void deleteGroupFile(long group_id,String file_id,long busid);


    GroupInfo getGroupInfo(long group_id, boolean no_cache);
    List<GroupInfo> getGroupList(boolean no_cache);
    GroupMemberInfo getGroupMemberInfo(long group_id,long user_id, boolean no_cache);
    List<GroupMemberInfo> getGroupMemberList(long group_id, boolean no_cache);
}
