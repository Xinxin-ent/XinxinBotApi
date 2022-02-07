package com.xinxin.BotTool;



import com.xinxin.XinxinBotApi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class XinxinJson {

    public XinxinJson() {
        /*
        if(raw_message != null && self_id != null){
            System.out.println("["+raw_message+"]["+user_id+"]");
            // 创建一个自定义事件对象
            GroupMessageEvent event = new GroupMessageEvent(Json);
            // 触发事件（创建好自定义事件对象，要使用本语句触发事件，否则什么都不会发生）
            Bukkit.getServer().getPluginManager().callEvent(event);
            // 然后继续操作事件
            // Bukkit.getServer().broadcastMessage(event.getMessage());
        }*/
    }
    public static void BotEvent(String jsonStr){
        JSONObject json = JSONObject.fromObject(jsonStr);

        if(XinxinBotApi.deBug){
            System.out.println(jsonStr);
        }

        if(BotData.getBotFrame().equalsIgnoreCase("go-cqhttp")){
            new cqHttpBot(jsonStr,json);
        }else if(BotData.getBotFrame().equalsIgnoreCase("Mirai")){
            new MiraiBot(jsonStr,json);
        }
    }
    public static JSONObject addJsonObject(JSONObject jsonObject,String key,Object value){
        jsonObject.element(key,value);
        return jsonObject;
    }

    public static JSONArray addJSONArray(JSONArray array, JSONObject jsonObject){
        array.add(jsonObject);
        return array;
    }
}

