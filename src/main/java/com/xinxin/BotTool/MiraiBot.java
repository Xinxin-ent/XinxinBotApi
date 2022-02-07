package com.xinxin.BotTool;

import com.xinxin.BotEvent.GroupMessageEvent;
import com.xinxin.BotEvent.PrivateMessageEvent;
import com.xinxin.BotTool.BotData;
import com.xinxin.XinxinBotApi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bukkit.Bukkit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MiraiBot {
	private String Json;//消息原本json文本

	private String SessionKey; //机器人发送消息所需的SessionKey
	private String type;//机器人收到事件类型

	private String group_id;//消息群号
	private String group_name;//群名称
	private String nickname;//发送人名称
	private String memberName;//发送人群名片
	private String user_id;//发送人qq、撤回消息qq、事件触发qq

	private List<MiraiMessage> raw_message;//收到消息
	//消息类型
	private String msgType;//消息类型

	private JSONArray origin;//被引用回复的原消息的消息链对象


	public MiraiBot(String jsonStr, JSONObject json) {
		this.Json = jsonStr;//消息原本json文本

		if(!json.containsKey("data")){
			return;
		}

		if(Json.contains("session")){
			SessionKey = JSONObject.fromObject(json.getString("data")).getString("session");
			BotData.setSessionKey(this.SessionKey);
		}


		Map<String, Object> data =JSONObject.fromObject(json.getString("data"));

		if(data.get("type")!=null){
			this.type = (String) data.get("type");
		}else {
			return;
		}
		//私聊消息
		if(type.equals("FriendMessage")){
			//聊天消息
			JSONArray jsonArray = (JSONArray) data.get("messageChain");
			raw_message = getMessageList(jsonArray);

			//发送人信息
			Map<String, Object> sender = (JSONObject)data.get("sender");
			user_id = String.valueOf(sender.get("id"));
			nickname = String.valueOf(sender.get("nickname"));

			//触发好友事件
			PrivateMessageEvent event = new PrivateMessageEvent(Json,raw_message,user_id,nickname);
			Bukkit.getPluginManager().callEvent(event);
		}

		//临时会话
		if(type.equals("TempMessage ")){
			//聊天消息
			JSONArray jsonArray = (JSONArray) data.get("messageChain");
			raw_message = getMessageList(jsonArray);

			//发送人信息
			Map<String, Object> sender = (JSONObject)data.get("sender");
			user_id = String.valueOf(sender.get("id"));
			memberName = String.valueOf(sender.get("memberName"));

			//群信息
			Map<String, Object> group = (JSONObject)sender.get("group");
			group_id = String.valueOf(group.get("id"));
			group_name = String.valueOf(group.get("name"));

			//触发私聊事件
			PrivateMessageEvent event = new PrivateMessageEvent(Json,raw_message,user_id,memberName,group_id,group_name);
			Bukkit.getPluginManager().callEvent(event);
		}


		//群聊消息
		if(type.equals("GroupMessage")){
			//聊天消息
			JSONArray jsonArray = (JSONArray) data.get("messageChain");
			raw_message = getMessageList(jsonArray);

			//发送人信息
			Map<String, Object> sender = (JSONObject)data.get("sender");
			user_id = String.valueOf(sender.get("id"));
			memberName = String.valueOf(sender.get("memberName"));

			//群信息
			Map<String, Object> group = (JSONObject)sender.get("group");
			group_id = String.valueOf(group.get("id"));
			group_name = String.valueOf(group.get("name"));

			//触发群聊事件
			GroupMessageEvent event = new GroupMessageEvent(Json,raw_message,user_id,memberName,group_id,group_name);
			Bukkit.getPluginManager().callEvent(event);
		}
	}







	public List<MiraiMessage> getMessageList(JSONArray json){
		List<MiraiMessage> message = new ArrayList<>();
		for(Object sz : json) {
			MiraiMessage mm = getMessage(JSONObject.fromObject(sz));

			if(JSONObject.fromObject(sz).get("origin")!=null){
				origin = (JSONArray) JSONObject.fromObject(sz).get("origin");

				List<MiraiMessage> originList = new ArrayList<>();
				for(Object ol : origin) {
					originList.add(getMessage(JSONObject.fromObject(ol)));
				}
				mm.setOrigin(originList);
			}
			message.add(mm);
		}


		if(XinxinBotApi.deBug){
			System.out.println("▌ §cMirai消息解析 §6┈━═☆");
			for(MiraiMessage mm : message){
				mm.deBug();
			}
		}

		return message;
	}

	public MiraiMessage getMessage(JSONObject json){
		msgType = json.getString("type");

		MiraiMessage mm = new MiraiMessage();

		mm.setJson(json.toString());
		mm.setType(msgType);

		//消息的数据Source类型永远为chain的第一个元素
		if(msgType.equals("Source")){
			mm.setTime((int) json.get("time"));
			mm.setId((int) json.get("id"));
		}else
			//普通消息
		if(msgType.equals("Plain")){
			mm.setText((String) json.get("text"));
		}else
			//图片消息
		if(msgType.equals("Image")){//图片消息
			mm.setImageId((String)json.get("imageId"));
			mm.setUrl((String)json.get("url"));
			mm.setPath(String.valueOf(json.get("path")));
			mm.setBase64(String.valueOf("base64"));
		}else
			//引用消息
		if(msgType.equals("Quote")){
			mm.setId((int)json.get("id"));
			mm.setGroupId(json.getLong("groupId"));
			mm.setSenderId(json.getLong("senderId"));
			mm.setTargetId(json.getLong("targetId"));
		}else
			//艾特消息
		if(msgType.equals("At")){
			mm.setTarget(json.getLong("target"));
			mm.setDisplay((String)json.get("dispaly"));
		}


		return mm;
	}
}
