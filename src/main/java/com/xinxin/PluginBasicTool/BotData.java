package com.xinxin.PluginBasicTool;

import com.xinxin.BotAction.*;
import com.xinxin.BotEnum.BotFrameEnum;
import com.xinxin.BotInterface.*;
import com.xinxin.XinxinBotApi;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BotData {
	private static String http;
	private static String ws;
	private static BotFrameEnum BotFrame;
	private static String QQ;
	private static String VerifyKey;
	private static String SessionKey;
	private static Boolean deBug = false;
	private static int TaskTimeout;
	private static int MaxActiveCount;

	private static WebSocket client;

	private static final Map<BotFrameEnum, BotActionBuilder> FRAME_ACTION_MAP = new HashMap<>();
	private static final Map<BotFrameEnum, BotDataProcessing> DATA_PROCESSING_MAP = new HashMap<>();
	private static final BotBindAction BIND_ACTION;

	static {
		FRAME_ACTION_MAP.put(BotFrameEnum.Mirai, new MiraiActionBuilder());
		FRAME_ACTION_MAP.put(BotFrameEnum.OneBot, new OneBotActionBuilder());
		DATA_PROCESSING_MAP.put(BotFrameEnum.Mirai, new MiraiDataProcessing());
		DATA_PROCESSING_MAP.put(BotFrameEnum.OneBot, new OneBotDataProcessing());
		if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
			System.out.println("§7[§a§l*§7] §b启用MySQL储存数据");
			BIND_ACTION = new BindMysqlAction();
		}else {
			BIND_ACTION = new BindYamlAction();
			System.out.println("§7[§a§l*§7] §b启用本地Yaml储存数据");
		}
	}

	public static BotActionBuilder getBotActionBuilder(){
		if(FRAME_ACTION_MAP.get(BotFrame) != null){
			return FRAME_ACTION_MAP.get(BotFrame);
		} else {
			System.out.println("§c未找到机器人框架.");
			return null;
		}
	}
	public static BotDataProcessing getBotDataProcessing(){
		if(DATA_PROCESSING_MAP.get(BotFrame) != null){
			return DATA_PROCESSING_MAP.get(BotFrame);
		} else {
			System.out.println("§c未找到机器人框架.");
			return null;
		}
	}

	public static BotBindAction getBindAction(){
		return BIND_ACTION;
	}

	public static WebSocket getClient() {
		return client;
	}

	public static void clientInit(String uri) {
		closeWebSocket();
		//判断框架为OneBot则添加规则头
		Map<String,String> headers = new HashMap<>();
		if(BotData.getBotFrame().equals(BotFrameEnum.OneBot) && !BotData.getVerifyKey().equals("")){
			headers.put("Authorization", "Bearer " + BotData.getVerifyKey());
		}
		try{
			client = new WebSocket(new URI(uri),headers);
			client.connect();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	public static long getTaskTimeout() {
		return TaskTimeout;
	}

	public static void setTaskTimeout(int taskTimeout) {
		TaskTimeout = taskTimeout;
	}

	public static void setMaxActiveCount(int maxActiveCount) {
		MaxActiveCount = maxActiveCount;
	}

	public static int getMaxActiveCount() {
		return MaxActiveCount;
	}

	public static void closeWebSocket() {
		if(client != null){
			client.close();
		}
	}

	public static BotFrameEnum getBotFrame() {
		return BotFrame;
	}

	public static String getSessionKey() {
		return SessionKey;
	}

	public static void setSessionKey(String sessionKey) {
		SessionKey = sessionKey;
	}

	public static String getHttp() {
		return http;
	}

	public static String getQQ() {
		return QQ;
	}

	public static String getVerifyKey() {
		return VerifyKey;
	}

	public static String getWs() {
		return ws;
	}

	public static void setHttp(String http) {
		BotData.http = http;
	}

	public static void setBotFrame(BotFrameEnum botFrame) {
		BotFrame = botFrame;
	}

	public static Boolean isDeBug() {
		return deBug;
	}

	public static void setDeBug(Boolean deBug) {
		BotData.deBug = deBug;
	}

	public static void setWs(String ws) {
		BotData.ws = ws;
	}

	public static void setQQ(String QQ) {
		BotData.QQ = QQ;
	}

	public static void setVerifyKey(String verifyKey) {
		VerifyKey = verifyKey;
	}


}
