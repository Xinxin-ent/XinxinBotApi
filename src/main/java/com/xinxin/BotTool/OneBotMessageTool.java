package com.xinxin.BotTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OneBotMessageTool {

    public static final String cqRegex = "(.*?)(\\[CQ:(\\w+),([^\\]]+)](.*?))(?=(\\[CQ:|$))";

    public static String listToString(List<String> msg) {
        if (msg.size() <= 1) {
            return (String)msg.get(0);
        } else {
            String Message = null;
            Iterator var2 = msg.iterator();

            while(var2.hasNext()) {
                String a = (String)var2.next();
                if (Message == null) {
                    Message = a;
                } else {
                    Message = Message + "\n" + a;
                }
            }
            return Message;
        }
    }

    //将有cq码的文本解析为消息链
    public static JSONArray msgToOneBotArray(String message){
        JSONArray array = new JSONArray();
        Pattern pattern = Pattern.compile(cqRegex);
        Matcher matcher = pattern.matcher(message);

        if(!containsCQCode(message)){
            array.add(new JSONObject().element("type", "text").element("data", new JSONObject().element("text",message)));
        }else {
            while (matcher.find()) {
                String beforeCQ = matcher.group(1); // CQ 码前面的文本
                String cqCommand = matcher.group(3); // CQ: 后面的命令，如 "face" 或 "image"
                String parameters = matcher.group(4); // 参数部分，如 "id=324" 或 "file=http://baidu.com/1.jpg,type=show,id=40004"
                String content = matcher.group(5); // 中间穿插的文本内容
                Map<String, String> paramMap = parseParameters(parameters);
                //CQ 码前面的文本
                if(!beforeCQ.isEmpty()){
                    array.add(new JSONObject().element("type","text").element("data",new JSONObject().element("text",beforeCQ)));
                }
                array.add(parseCQCoreToJson(cqCommand,paramMap));
                //中间穿插的文本内容
                if(!content.isEmpty()){
                    array.add(new JSONObject().element("type","text").element("data",new JSONObject().element("text",content)));
                }
            }
        }
        return array;
    }

    //解析参数部分
    public static JSONObject parseCQCoreToJson(String cqCommand,Map<String, String> paramMap){
        JSONObject cqJson = new JSONObject();
        if(cqCommand.equals("at")){
            return cqJson.element("type","at").element("data",new JSONObject().element("qq",paramMap.get("qq")));
        }
        if(cqCommand.equals("face")){
            return cqJson.element("type","face").element("data",new JSONObject().element("id",Integer.parseInt(paramMap.get("id"))));
        }
        if(cqCommand.equals("image")){
            return cqJson.element("type","image").element("data",new JSONObject().element("file",paramMap.get("file")));
        }
        if(cqCommand.equals("poke")){
            return cqJson.element("type","poke").element("data",new JSONObject().element("qq",paramMap.get("qq")));
        }
        if(cqCommand.equals("reply")){
            return cqJson.element("type","reply").element("data",new JSONObject().element("id",paramMap.get("id")));
        }
        return null;
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

    public static String listToOneBotArray(List<String> msg){
        JSONArray json = new JSONArray();
        int size = msg.size();
        for (int i = 0; i < size; i++) {
            String message = msg.get(i);
            json.addAll(msgToOneBotArray(message));
            // 如果不是最后一行，则添加换行符
            if (i < size - 1) {
                json.add(new JSONObject().element("type","text").element("data",new JSONObject().element("text","\n")));
            }
        }
        return json.toString();
    }
}
