package com.xinxin.SendMessage;

import com.xinxin.BotTool.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class GroupMessage {

	public GroupMessage(String group_id, String message) {//主动发送群消息

		if(BotData.getBotFrame().equals("Mirai")){
			JSONObject json = new JSONObject();
			JSONObject data = new JSONObject();

			data = XinxinJson.addJsonObject(data,"sessionKey",BotData.getSessionKey());
			data = XinxinJson.addJsonObject(data,"target",Integer.valueOf(group_id));
			data = XinxinJson.addJsonObject(data,"messageChain", message);

			json.element("syncId","");
			json.element("command","sendGroupMessage");
			json.element("content",data.toString());

			WebSocket.client.send(json.toString());
		}else if(BotData.getBotFrame().equals("go-cqhttp")){
			try {
				URL url = new URL(BotData.getHttp() +"/send_group_msg?group_id="+group_id+"&message="+ java.net.URLEncoder.encode(message,"utf-8"));
				url.openStream();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

}
