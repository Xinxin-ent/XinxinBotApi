package com.xinxin.BotTool;

public class BotData {
	private static String http;
	private static String ws;
	private static String BotFrame;
	private static String QQ;
	private static String VerifyKey;
	private static String SessionKey;
	public BotData (){
	}

	public static String getBotFrame() {
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

	public static void setBotFrame(String botFrame) {
		BotFrame = botFrame;
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
