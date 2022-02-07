package com.xinxin.BotApi;

import com.xinxin.BotTool.BotData;
import com.xinxin.BotTool.MessageTool;
import com.xinxin.BotTool.MiraiMessage;
import com.xinxin.SendMessage.GroupMessage;
import com.xinxin.SendMessage.PrivateMessage;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SendMessage {
	public static void Private(String qq,String msg){//发送私聊消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new PrivateMessage(qq,msg);
		}else if(BotData.getBotFrame().equals("Mirai")){
			new PrivateMessage(qq,MessageTool.getMessage(msg));
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

	public static void Private(String qq, Object msg){//发送私聊消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new PrivateMessage(qq,MessageTool.setListMessage((List<String>) msg));
		}else if(BotData.getBotFrame().equals("Mirai")){
			try{
				new PrivateMessage(qq,MessageTool.getMessage(MessageTool.setListMessage((List<String>) msg)));
			} catch (Exception e) {
				new PrivateMessage(qq,MessageTool.toString((List<MiraiMessage>) msg));
			}
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

	public static void Interim(String qq,String group,String msg){//发送群临时消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new PrivateMessage(qq,group,msg);
		}else if(BotData.getBotFrame().equals("Mirai")){
			new PrivateMessage(qq,group,MessageTool.getMessage(msg));
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

	public static void Interim(String qq,String group, Object msg){//发送群临时消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new PrivateMessage(qq,group, MessageTool.setListMessage((List<String>) msg));
		}else if(BotData.getBotFrame().equals("Mirai")){
			try{
				new PrivateMessage(qq,group, MessageTool.getMessage(MessageTool.setListMessage((List<String>) msg)));
			} catch (Exception e) {
				new PrivateMessage(qq,group, MessageTool.toString((List<MiraiMessage>) msg));
			}
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

	public static void Group(String group,String msg){//发送群消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new GroupMessage(group,msg);
		}else if(BotData.getBotFrame().equals("Mirai")){
			new GroupMessage(group,MessageTool.getMessage(msg));
		}else {
			System.out.println("§c未找到机器人框架.");
		}
	}

	public static void Group(String group,Object msg){//发送群消息
		if(BotData.getBotFrame().equals("go-cqhttp")){
			new GroupMessage(group, MessageTool.setListMessage((List<String>) msg));
		}else if(BotData.getBotFrame().equals("Mirai")){
			try{
				new GroupMessage(group, MessageTool.getMessage(MessageTool.setListMessage((List<String>) msg)));
			} catch (Exception e) {
				new GroupMessage(group, MessageTool.toString((List<MiraiMessage>) msg));
			}
		}else {
			System.out.println("§c未找到机器人框架.");
		}

	}
}
