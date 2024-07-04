package com.xinxin.BotApi;

import com.xinxin.GroupData.GroupInfo;
import com.xinxin.GroupData.GroupMemberInfo;
import com.xinxin.PluginBasicTool.BotData;

import java.util.List;

/**
 * BotAction 机器人动作
 * 不同的机器人框架处理传入的参数不同
 * 参数后缀会写明所需要的框架需要传入的参数
 * 如是后缀没标明框架则使用任何框架都需要传入这个参数
 */

public class BotAction {
	/**
	 * 发送好友消息
	 * @param user_id	发送消息的用户
	 * @param message	发送的文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendPrivateMessage(long user_id,String message,boolean... auto_escape){//发送私聊消息
		return BotData.getBotActionBuilder().sendPrivateMessage(user_id,message,auto_escape);
	}

	/**
	 * 发送好友 List 消息
	 * @param user_id	发送消息的用户
	 * @param message	发送的 List 文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendPrivateMessage(long user_id, List<String> message,boolean... auto_escape){//发送私聊消息
		return BotData.getBotActionBuilder().sendPrivateMessage(user_id,message,auto_escape);
	}

	/**
	 * 发送临时消息
	 * @param user_id	发送消息的用户
	 * @param group_id	发送消息的群
	 * @param message	发送的文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendInterimMessage(long user_id,long group_id,String message,boolean... auto_escape){//发送群临时消息
		return BotData.getBotActionBuilder().sendInterimMessage(user_id,group_id,message,auto_escape);
	}

	/**
	 * 发送临时 List 消息
	 * @param user_id	发送消息的用户
	 * @param group_id	发送消息的群
	 * @param message	发送的 List 文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendInterimMessage(long user_id,long group_id, List<String> message,boolean... auto_escape){//发送群临时消息
		return BotData.getBotActionBuilder().sendInterimMessage(user_id,group_id,message,auto_escape);
	}

	/**
	 * 发送群聊消息
	 * @param group_id	发送的群号
	 * @param message	发送的文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendGroupMessage(long group_id,String message,boolean... auto_escape){//发送群消息
		return BotData.getBotActionBuilder().sendGroupMessage(group_id,message,auto_escape);
	}

	/**
	 * 发送 List 群聊消息
	 * @param group_id	发送的群号
	 * @param message	发送的 List 文本消息
	 * @param auto_escape	消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) - OneBot
	 * @return
	 */
	public static long sendGroupMessage(long group_id,List<String> message,boolean... auto_escape){//发送群消息
		return BotData.getBotActionBuilder().sendGroupMessage(group_id, message,auto_escape);
	}

	public static String getMessage(long message_id){
		return BotData.getBotActionBuilder().getMessage(message_id);
	}

	/**
	 * 撤回消息
	 * @param message_id	消息id
	 * @param target	撤回目标（群号、好友账号）- Mirai
	 */
	public static void deleteMessage(long message_id,long target){
		BotData.getBotActionBuilder().deleteMessage(message_id, target);
	}

	/**
	 * 操作好友申请请求
	 * @param flag	响应申请事件的标识
	 * @param approve	是否同意
	 * @param remark	添加后给好友的备注 - OneBot
	 * @param fromId	处理事件的好友账号 - Mirai
	 */
	public static void setFriendAdd(String flag,boolean approve,String remark,long fromId){
		BotData.getBotActionBuilder().setFriendAdd(flag,approve,remark,fromId);
	}

	/**
	 * 删除好友
	 * @param user_id	需要操作的好友
	 */
	public static void deleteFriend(long user_id){
		BotData.getBotActionBuilder().deleteFriend(user_id);
	}

	/**
	 * 操作群聊申请请求
	 * @param flag	响应申请事件的标识
	 * @param sub_type	操作的子类型
	 * @param approve	是否同意进群
	 * 0 = 同意 1 = 拒绝 | Mirai 2 = 忽略请求 3 = 拒绝入群并添加黑名单 4 = 忽略入群并添加黑名单
	 * @param reason	拒绝的理由（拒绝时生效） - OneBot
	 */
	public static void setGroupAddRequest(String flag,String sub_type,boolean approve,String reason){
		BotData.getBotActionBuilder().setGroupAddRequest(flag,sub_type,approve,reason);
	}

	/**
	 * 修改群名字
	 * @param group_id	需要修改的群号
	 * @param group_name	新的群名字
	 */
	public static void setGroupName(long group_id,String group_name){
		BotData.getBotActionBuilder().setGroupName(group_id,group_name);
	}

	/**
	 * 修改群员名片
	 * @param group_id	需要修改的群号
	 * @param user_id	需要操作的用户QQ
	 * @param card	新的群名片
	 */
	public static void setGroupCard(long group_id,long user_id,String card){
		BotData.getBotActionBuilder().setGroupCard(group_id,user_id,card);
	}

	/**
	 * 修改群员头衔
	 * @param group_id	需要修改的群号
	 * @param user_id	需要操作的用户QQ
	 * @param special_title	新的群头衔
	 */
	public static void setGroupSpecialTitle(long group_id,long user_id,String special_title){
		BotData.getBotActionBuilder().setGroupSpecialTitle(group_id,user_id,special_title);
	}

	/**
	 * 踢出群员
	 * @param group_id	需要操作的群号
	 * @param user_id	需要操作的用户QQ
	 * @param reject_add_request	是否拉黑
	 */
	public static void setGroupKick(long group_id,long user_id,boolean reject_add_request){
		BotData.getBotActionBuilder().setGroupKick(group_id,user_id,reject_add_request);
	}

	/**
	 * 退出群聊
	 * @param group_id	需要操作的群号
	 * @param is_dismiss	是否解散 - OneBot
	 */
	public static void setGroupLeave(long group_id,boolean is_dismiss){
		BotData.getBotActionBuilder().setGroupLeave(group_id,is_dismiss);
	}

	/**
	 * 设置群内用户禁言
	 * @param group_id	需要操作的群号
	 * @param user_id	需要操作的群员
	 * @param duration	禁言时间
	 */
	public static void setGroupBan(long group_id,long user_id,long duration){
		BotData.getBotActionBuilder().setGroupBan(group_id,user_id,duration);
	}

	/**
	 * 设置全员禁言
	 * @param group_id	需要操作的群号
	 * @param enable	是否启用
	 */
	public static void setGroupWholeBan(long group_id,boolean enable){
		BotData.getBotActionBuilder().setGroupWholeBan(group_id,enable);
	}

	/**
	 * 上传群文件 - OneBot
	 * @param group_id	需要操作的群号
	 * @param filePath	文件路径
	 * @param name	上传文件名
	 * @param folder	上传文件夹
	 */
	public static void uploadGroupFile(long group_id,String filePath,String name,String folder){
		BotData.getBotActionBuilder().uploadGroupFile(group_id,filePath,name,folder);
	}

	/**
	 * 上传私聊文件 - OneBot
	 * @param user_id	发送的用户
	 * @param filePath	文件路径
	 * @param name	上传文件名
	 */
	public static void uploadPrivateFile(long user_id,String filePath,String name){
		BotData.getBotActionBuilder().uploadPrivateFile(user_id,filePath,name);
	}

	/**
	 * 删除群聊文件 - OneBot
	 * @param group_id	需要操作的群号
	 * @param file_id	文件id
	 * @param busid	文件类型
	 */
	public static void deleteGroupFile(long group_id,String file_id,long busid){
		BotData.getBotActionBuilder().deleteGroupFile(group_id,file_id,busid);
	}

	/**
	 * 获取群信息 - OneBot
	 * @param group_id	需要操作的群号
	 * @param no_cache	是否不使用缓存（使用缓存可能更新不及时）
	 * @return
	 */
	public static GroupInfo getGroupInfo(long group_id, boolean no_cache) {
		return BotData.getBotActionBuilder().getGroupInfo(group_id,no_cache);
	}

	/**
	 * 获取群列表 - OneBot
	 * @param no_cache	是否不使用缓存（使用缓存可能更新不及时）
	 * @return
	 */
	public static List<GroupInfo> getGroupList( boolean no_cache) {
		return BotData.getBotActionBuilder().getGroupList(no_cache);
	}

	/**
	 * 获取群信息 - OneBot
	 * @param group_id	需要操作的群号
	 * @param user_id	需要操作的QQ号
	 * @param no_cache	是否不使用缓存（使用缓存可能更新不及时）
	 * @return
	 */
	public static GroupMemberInfo getGroupMemberInfo(long group_id, long user_id, boolean no_cache){
		return BotData.getBotActionBuilder().getGroupMemberInfo(group_id,user_id,no_cache);
	}

	/**
	 * 获取群成员列表 - OneBot
	 * @param group_id	需要操作的群号
	 * @param no_cache	是否不使用缓存（使用缓存可能更新不及时）
	 * @return
	 */

	public static List<GroupMemberInfo> getGroupMemberList(long group_id,boolean no_cache){
		return BotData.getBotActionBuilder().getGroupMemberList(group_id,no_cache);
	}


}
