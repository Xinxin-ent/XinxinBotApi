package com.xinxin.PluginBasicTool;

import com.xinxin.BotEnum.BotFrameEnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.UUID;

public class JsonAction {
    JSONObject data = new JSONObject();
    JSONObject params = new JSONObject();
    String uuid = UUID.randomUUID().toString();

    public JsonAction(String action){
        if(BotData.getBotFrame().equals(BotFrameEnum.Mirai)){
            data.element("syncId", uuid).element("command",action).element("content",params.toString());
        }else if(BotData.getBotFrame().equals(BotFrameEnum.OneBot)){
            data.element("action",action).element("params",params.toString()).element("echo",uuid);
        }else {
            System.out.println("§c未找到机器人框架.");
        }
    }

    public JsonAction setSubCommand(String subCommand){
        data.element("subCommand", subCommand);
        return this;
    }
    public JsonAction(){
        data = new JSONObject();
        params = new JSONObject();
    }

    public JsonAction add(String key, Object value){
        params.element(key, value);
        return this;
    }

    public String toString(){
        return getJson().toString();
    }

    public JSONObject getData() {
        return data;
    }
    public JSONObject getParams() {
        return params;
    }

    public JSONObject getJson() {
        if(BotData.getBotFrame().equals(BotFrameEnum.Mirai)){
            data.element("content",params.toString());
        }else if(BotData.getBotFrame().equals(BotFrameEnum.OneBot)){
            data.element("params",params.toString());
        }else {
            System.out.println("§c未找到机器人框架.");
        }
        return data;
    }
}
