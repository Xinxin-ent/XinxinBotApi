package com.xinxin.PluginBasicTool;


import com.xinxin.XinxinBotApi;
import net.sf.json.JSONObject;
import org.bukkit.Bukkit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;


import java.net.URI;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSocket extends WebSocketClient{
	public static AtomicInteger atomicInteger = new AtomicInteger(-1);
	public static int Reconnect = 0;
	public static final Map<String,CompletableFuture<JSONObject>> response = new HashMap<>();

	public WebSocket(URI serverUri) {
		super(serverUri);
	}
	public WebSocket(URI serverUri , Map<String,String> httpHeaders) {
		super(serverUri, httpHeaders);
	}

	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("§b[XinxinBotApi] §a机器人框架对接成功!");
	}

	// 创建一个固定大小的线程池，并将其转换为 ThreadPoolExecutor
	private static final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(BotData.getMaxActiveCount());

	public void onMessage(String data) {
		//启动了debug
		if(BotData.isDeBug()){
			System.out.println("§b[XinxinBotApi] §a接收到QQ数据:§e\n"+data+"\n");
			System.out.println("§b[XinxinBotApi] §a当前线程数量: §e" + executorService.getActiveCount()+"/§c"+BotData.getMaxActiveCount());
		}
		JSONObject json = JSONObject.fromObject(data);

		if (!BotData.getBotDataProcessing().response(json)) {
			executorService.submit(() -> {
				BotData.getBotDataProcessing().handle(json, data);
			});
		}
	}

	public void onClose(int code, String reason, boolean remote) {
		System.out.println("§b[XinxinBotApi] §c机器人框架已断开连接!");
		if(atomicInteger.get() != -1 || !XinxinBotApi.config.getBoolean("Reconnect")){
			return;
		}
		int max = XinxinBotApi.config.getInt("MaxReconnect");

		atomicInteger.set(Bukkit.getScheduler().runTaskTimer(XinxinBotApi.getInstance(), () -> {
			Reconnect++;
			System.out.println("§b[XinxinBotApi] §e【"+Reconnect+"/"+max+"】 §c正在尝试重新连接...");
			XinxinBotApi.getInstance().connectBot();
			if(BotData.getClient() != null && !BotData.getClient().isClosed()){
				System.out.println("§b[XinxinBotApi] §a重连成功！");
				Bukkit.getScheduler().cancelTask(atomicInteger.get());
			}else if(Reconnect >= max){
				System.out.println("§b[XinxinBotApi] §c尝试重连失败！");
				Bukkit.getScheduler().cancelTask(atomicInteger.get());
			}
		}, 60L, 60L).getTaskId());
	}


	public void onError(Exception ex) {
		System.out.println("§b[XinxinBotApi] §c对接框架出现错误!");
		ex.printStackTrace();
	}


	public JSONObject sendData(JsonAction data, boolean feedback) {
		send(data.toString());

		if (feedback) {
			CompletableFuture<JSONObject> res = new CompletableFuture<>();
			response.put(data.uuid, res);

			// 使用 Bukkit 的调度器设置超时任务
			Bukkit.getScheduler().runTaskLaterAsynchronously(XinxinBotApi.getInstance(), () -> {
				if (!res.isDone()) {
					res.completeExceptionally(new TimeoutException("Request timed out"));
					response.remove(data.uuid);
				}
			}, BotData.getTaskTimeout() * 20); // 转换为 tick，假设 1 秒 = 20 tick

			try {
				return res.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			} finally {
				response.remove(data.uuid);
			}
		}

		return null;
	}


	public void sendData(JsonAction data){
		send(data.toString());
	}
}