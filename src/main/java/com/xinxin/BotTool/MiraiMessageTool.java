package com.xinxin.BotTool;

import com.xinxin.PluginBasicTool.BotData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiraiMessageTool {
    public static final String cqRegex = "(.*?)(\\[CQ:(\\w+),([^\\]]+)](.*?))(?=(\\[CQ:|$))";

    //将有cq码的文本解析为Mirai
    public static JSONArray msgToMiraiArray(String message){
        JSONArray array = new JSONArray();
        Pattern pattern = Pattern.compile(cqRegex);
        Matcher matcher = pattern.matcher(message);

        if(!containsCQCode(message)){
            array.add(new JSONObject().element("type", "Plain").element("text", message));
        }else {
            while (matcher.find()) {
                String beforeCQ = matcher.group(1); // CQ 码前面的文本
                String cqCommand = matcher.group(3); // CQ: 后面的命令，如 "face" 或 "image"
                String parameters = matcher.group(4); // 参数部分，如 "id=324" 或 "file=http://baidu.com/1.jpg,type=show,id=40004"
                String content = matcher.group(5); // 中间穿插的文本内容
                Map<String, String> paramMap = parseParameters(parameters);
                //CQ 码前面的文本
                if(!beforeCQ.isEmpty()){
                    array.add(new JSONObject().element("type","Plain").element("text",beforeCQ));
                }
                array.add(parseCQCoreToJson(cqCommand,paramMap));
                //中间穿插的文本内容
                if(!content.isEmpty()){
                    array.add(new JSONObject().element("type","Plain").element("text",content));
                }
            }
        }
        return array;
    }

    //解析参数部分
    public static JSONObject parseCQCoreToJson(String cqCommand,Map<String, String> paramMap){
        JSONObject cqJson = new JSONObject();
        if(cqCommand.equals("at")){
            if(paramMap.get("qq").equals("all")){
                return cqJson.element("type","AtAll");
            }
            return cqJson.element("type","At").element("target",paramMap.get("qq"));
        }
        if(cqCommand.equals("face")){
            return cqJson.element("type","Face").element("faceId",Integer.parseInt(paramMap.get("id")));
        }
        if(cqCommand.equals("image")){
            return cqJson.element("type","Image").element("url",paramMap.get("file")).element("path",paramMap.get("file"));
        }
        if(cqCommand.equals("poke")){
            return cqJson.element("type","Poke").element("name",paramMap.get("name")).element("target",paramMap.get("qq"));
        }
        return null;
    }


    public static String miraiArrayToMessage(JSONArray json){
        StringBuilder messages = new StringBuilder();
        for(Object sz : json) {
            JSONObject messageJson = JSONObject.fromObject(sz);
            String type = messageJson.getString("type");

            switch (type){
                case "Plain":
                    messages.append(messageJson.getString("text"));
                    break;
                case "At":
                    messages.append("[CQ:at,qq=").append(messageJson.getString("target")).append("]");
                    break;
                case "AtAll":
                    messages.append("[CQ:at,qq=all]");
                    break;
                case "Face":
                    messages.append("[CQ:face,id=").append(messageJson.getString("faceId")).append("]");
                    break;
                case "Image":
                    messages.append("[CQ:image,file=").append(messageJson.getString("url")).append("]");
                    break;
            }
        }
        return messages.toString();
    }



    //解析cq码的参数并返回map
    public static Map<String, String> parseParameters(String parameters) {
        Map<String, String> paramMap = new HashMap<>();
        String[] keyValuePairs = parameters.split(",");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                paramMap.put(keyValue[0], keyValue[1]);
            }
        }
        return paramMap;
    }

    //判断文本是否有cq码
    public static boolean containsCQCode(String text) {
        String regex = "\\[CQ:\\w+,[^\\]]+\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }


    public static String listToMiraiArray(List<String> msg){
        JSONArray json = new JSONArray();
        int size = msg.size();
        for (int i = 0; i < size; i++) {
            String message = msg.get(i);
            json.addAll(msgToMiraiArray(message));
            // 如果不是最后一行，则添加换行符
            if (i < size - 1) {
                json.add(new JSONObject().element("type","Plain").element("text","\n"));
            }
        }
        return json.toString();
    }
}
