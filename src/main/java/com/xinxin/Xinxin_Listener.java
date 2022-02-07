package com.xinxin;

import com.xinxin.BotEvent.GroupMessageEvent;
import com.xinxin.BotEvent.NoticeEvent;
import com.xinxin.BotEvent.PrivateMessageEvent;
import com.xinxin.BotEvent.RequestEvent;
import com.xinxin.BotApi.BindApi;
import com.xinxin.BotApi.SendMessage;
import com.xinxin.BotTool.BotData;
import com.xinxin.BotTool.MiraiMessage;
import com.xinxin.BotTool.WebSocket;
import com.xinxin.BotTool.XinxinJson;
import net.sf.json.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.List;


public class Xinxin_Listener implements Listener {
	@EventHandler
	public void GroupMessageEvent(GroupMessageEvent event){

		if(!XinxinBotApi.getInstance().getConfig().getBoolean("SetBind.Enable")){
			return;
		}

		String cmd = XinxinBotApi.getInstance().getConfig().getString("SetBind.Command"),message;

		if(BotData.getBotFrame().equals("Mirai")){
			message = event.getMiraiMessage().get(1).getMessage();
		}else if(BotData.getBotFrame().equals("go-cqhttp")) {
			message = event.getMessage();
		}else {
			System.out.println("§c未找到机器人框架.");
			return;
		}



		if(message.length() >= cmd.length() && message.substring(0,cmd.length()).equalsIgnoreCase(cmd)){

			String BindPlay = message.substring(cmd.length());
			List<String> msg = new ArrayList<>();

			if(Bukkit.getPlayer(BindPlay) == null || !Bukkit.getPlayer(BindPlay).isOnline() || !Bukkit.getPlayer(BindPlay).getName().equalsIgnoreCase(BindPlay)){
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.NotOnline")){
					msg.add(m.replace("%Player%",BindPlay));
				}
				SendMessage.Group(event.getGroupId(),msg);
				return;
			}

			if(BindApi.addBind(event.getUserId(),BindPlay)){
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.Bind")){
					msg.add(m.replace("%Player%",BindPlay));
				}
			}else {
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.BindFail")){
					msg.add(m.replace("%Player%",BindPlay));
				}
			}

			SendMessage.Group(event.getGroupId(),msg);
			return;
		}
	}
	@EventHandler
	public void PrivateMessageEvent(PrivateMessageEvent event){
		//System.out.println(event.getUserId()+"|发送了私聊消息|"+event.getMessage());

	}
	@EventHandler
	public void NoticeEvent(NoticeEvent event){
		//System.out.println("接受到了通知消息|"+event.getJson());

	}
	@EventHandler
	public void RequestEvent(RequestEvent event){
		//System.out.println("接受到了请求消息|"+event.getJson());

	}
}
