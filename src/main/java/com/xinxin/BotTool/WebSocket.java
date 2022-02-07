package com.xinxin.BotTool;


import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;
public class WebSocket {

	public static WebSocketClient client = null;

	public static void main(String URI){
		if(WebSocket.client != null){
			WebSocket.client.close();
		}
		try{
			client = new WebSocketClient(new URI(URI)){
				@Override
				public void onOpen(ServerHandshake handshakedata){
					System.out.println("§b[新鑫互联] §a连接成功");
				}
				@Override
				public void onMessage(String message){//执行接收到消息体后的相应操作
					XinxinJson.BotEvent(message);
				}
				@Override
				public void onClose(int code,String reason,boolean remote){
					System.out.println("§b[新鑫互联] §c退出连接");
				}
				@Override
				public void onError(Exception ex){
					System.out.println("§b[新鑫互联] §c出现错误!");
					ex.printStackTrace();
				}
			};
			client.connect();
			return;
		} catch(Exception e){
			e.printStackTrace();
		}
		return;
	}
}