package com.xinxin.SendMessage;

import com.xinxin.BotTool.BotData;
import com.xinxin.BotTool.WebSocket;
import com.xinxin.BotTool.XinxinJson;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class PrivateMessage {
    public PrivateMessage(String user_id, String message){//发送好友消息
        try {
            message = java.net.URLEncoder.encode(message,"utf-8");//java中的中文字符转URLEncode
            URL url = new URL(BotData.getHttp()+"/send_private_msg?user_id="+user_id+"&message="+message);
            url.openStream();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PrivateMessage(String user_id, String group_id, String message) {//主动发送群临时消息

        if(BotData.getBotFrame().equals("Mirai")){
            JSONObject json = new JSONObject();
            JSONObject data = new JSONObject();

            data = XinxinJson.addJsonObject(data,"sessionKey",BotData.getSessionKey());
            data = XinxinJson.addJsonObject(data,"qq",Long.valueOf(user_id));
            data = XinxinJson.addJsonObject(data,"group",Long.valueOf(group_id));
            data = XinxinJson.addJsonObject(data,"messageChain", message);

            json.element("syncId","");
            json.element("command","sendGroupMessage");
            json.element("content",data.toString());

            WebSocket.client.send(json.toString());
        }else if(BotData.getBotFrame().equals("go-cqhttp")) {
            try {
                URL url = new URL(BotData.getHttp() + "/send_private_msg?user_id=" + user_id + "&group_id=" + group_id + "&message=" + java.net.URLEncoder.encode(message, "utf-8"));
                url.openStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
