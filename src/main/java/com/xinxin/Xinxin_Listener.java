package com.xinxin;

import com.xinxin.BotApi.BotBind;
import com.xinxin.BotApi.BotAction;
import com.xinxin.BotEvent.*;
import com.xinxin.PluginBasicTool.BotData;
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

		message = event.getMessage();

		if(message != null && message.length() >= cmd.length() && message.substring(0,cmd.length()).equalsIgnoreCase(cmd)){

			String BindPlay = message.substring(cmd.length());
			List<String> msg = new ArrayList<>();

			if(Bukkit.getPlayer(BindPlay) == null || !Bukkit.getPlayer(BindPlay).isOnline() || !Bukkit.getPlayer(BindPlay).getName().equalsIgnoreCase(BindPlay)){
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.NotOnline")){
					msg.add(m.replace("%Player%",BindPlay));
				}

				BotAction.sendGroupMessage(event.getGroup_id(),msg);
				return;
			}

			if(BotBind.addBind(String.valueOf(event.getUser_id()),BindPlay)){
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.Bind")){
					msg.add(m.replace("%Player%",BindPlay));
				}
			}else {
				for(String m : XinxinBotApi.getInstance().getConfig().getStringList("Message.BindFail")){
					msg.add(m.replace("%Player%",BindPlay));
				}
			}
			BotAction.sendGroupMessage(event.getGroup_id(),msg);
		}
	}
}
