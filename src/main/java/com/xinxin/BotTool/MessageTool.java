package com.xinxin.BotTool;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MessageTool {

	public static String setListMessage(List<String> msg){
		if(msg.size() <= 1){
			return msg.get(0);
		}
		String Message = null;
		for (String a : msg){
			if(Message == null){
				Message = a;
				continue;
			}
			Message = Message+ "\n" + a ;
		}
		return Message;
	}

	public static String toString(List<MiraiMessage> ListMM){
		JSONArray array=new JSONArray();

		for(MiraiMessage mm : ListMM){
			JSONObject m = new JSONObject();

			System.out.println(mm.getType());

			if(mm.getType().equals("Plain")){
				m.element("type","Plain");
				m.element("text",mm.getMessage());
			}else

			if(mm.getType().equals("Image")){
				m.element("type","Image");
				m.element("url",mm.getUrl());
				m.element("path",mm.getPath());
				m.element("base64",mm.getBase64());
			}else

			if(mm.getType().equals("At")){
				m.element("type","At");
				m.element("target",mm.getTarget());
			}

			array.add(m);
		}

		return array.toString();
	}

	public static String getMessage(String message){
		JSONObject msg = new JSONObject();
		msg = XinxinJson.addJsonObject(msg,"type","Plain");
		msg = XinxinJson.addJsonObject(msg,"text",message);

		JSONArray array=new JSONArray();
		array.add(msg);

		return array.toString();
	}

	public static String getSubString(String text, String left, String right) {
		String result = "";
		int zLen;
		if (left == null || left.isEmpty()) {
			zLen = 0;
		} else {
			zLen = text.indexOf(left);
			if (zLen > -1) {
				zLen += left.length();
			} else {
				zLen = 0;
			}
		}
		int yLen = text.indexOf(right, zLen);
		if (yLen < 0 || right == null || right.isEmpty()) {
			yLen = text.length();
		}
		result = text.substring(zLen, yLen);
		return result;
	}
}
