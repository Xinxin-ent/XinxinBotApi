package com.xinxin;


import com.xinxin.BotTool.BotData;
import com.xinxin.MySQL.joinMySQL;
import com.xinxin.BotTool.WebSocket;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.SQLException;

public class XinxinBotApi extends JavaPlugin{

	public static Connection connection;
	private static XinxinBotApi instance;
	public static XinxinBotApi getInstance() { return instance; }
	public static FileConfiguration config;
	public static Boolean deBug;

	public void onEnable() {//加载时触发指令
		instance = this;

		String[] logo ={
						"§e       __                  __            ",
						"§e __  _/\\_\\    ___    __  _/\\_\\    ___    ",
						"§e/\\ \\/'\\/\\ \\ /' _ `\\ /\\ \\/'\\/\\ \\ /' _ `\\  ",
						"§e\\/>  </\\ \\ \\/\\ \\/\\ \\\\/>  </\\ \\ \\/\\ \\/\\ \\ ",
						"§e /\\_/\\_\\\\ \\_\\ \\_\\ \\_\\/\\_/\\_\\\\ \\_\\ \\_\\ \\_\\",
						"§e \\//\\/_/ \\/_/\\/_/\\/_/\\//\\/_/ \\/_/\\/_/\\/_/",
						" "
		};

		for(String s : logo){
			System.out.println(s);
		}
		System.out.println("▌ §a新鑫系列插件加载 §6┈━═☆");
		System.out.println("§7[§a§l*§7] §b成功加载插件：§f" + getName());
		System.out.println("§7[§a§l*§7] §b插件当前版本：§f" + this.getDescription().getVersion());
		System.out.println("§7[§a§l*§7] §b插件作者：§f新鑫丶");
		System.out.println("§7[§a§l*§7] §b新鑫娱乐交流群：§f1124109145");
		System.out.println("§7[§a§l*§7] §b新鑫社区网址：§fbbs.xxin.vip");
		System.out.println("▌ §a新鑫娱乐业务范围 §6┈━═☆");
		System.out.println("§7[§a§l*§7] §bVps租用、实体机租用……");
		System.out.println("§7[§a§l*§7] §b原创群服对接机器人 §c[推荐]");
		System.out.println("§7[§a§l*§7] §b定制机器人插件、服务端插件……");
		System.out.println("§7[§a§l*§7] §b详情联系作者：§f1072565329");
		Bukkit.getPluginManager().registerEvents(new Xinxin_Listener(), this);
		Bukkit.getPluginCommand("xbot").setExecutor(new Xinxin_Command());//绑定指令

		loadConfig();
	}
	public void onDisable() {//插件卸载触发
		if(WebSocket.client != null){
			WebSocket.client.close();
		}
		System.out.println("▌ §c新鑫系列插件卸载 §a┈━═☆");
		System.out.println("§7[§4§l*§7] §b成功卸载插件：§f" + getName());
		System.out.println("§7[§4§l*§7] §b感谢支持新鑫系列插件!" );
	}
	public void loadConfig() {
		if (!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		saveDefaultConfig();
		reloadConfig();
		config = getConfig();


		deBug = config.getBoolean("debug");


		System.out.println("▌ §a开始对接机器人框架 §6┈━═☆");
		if(config.getString("BotFrame").equalsIgnoreCase("go-cqhttp")){
			BotData.setWs(getConfig().getString("Go-cqhttp.WebSocket"));
			BotData.setHttp(getConfig().getString("Go-cqhttp.Http"));
			BotData.setBotFrame("go-cqhttp");

			if(getConfig().getBoolean("AutoOpen")){
				WebSocket.main(BotData.getWs());
			}
			System.out.println("§7[§a§l*§7] §a启用框架: §e"+BotData.getBotFrame());
		}else if(config.getString("BotFrame").equalsIgnoreCase("Mirai")){
			BotData.setWs(getConfig().getString("Mirai.WebSocket"));
			BotData.setQQ(getConfig().getString("Mirai.QQ"));
			BotData.setVerifyKey(getConfig().getString("Mirai.VerifyKey"));
			BotData.setBotFrame("Mirai");

			if(getConfig().getBoolean("AutoOpen")){
				WebSocket.main(BotData.getWs()+"/all?verifyKey="+BotData.getVerifyKey()+"&qq="+BotData.getQQ());
			}
			System.out.println("§7[§a§l*§7] §a启用框架: §e"+BotData.getBotFrame());
		}else {
			System.out.println("§7[§a§l*§7] §c未找到对应框架.");
			return;
		}






		if(getConfig().getBoolean("MySQL.Enable")){
			System.out.println("▌ §a开始连接数据库 §6┈━═☆");
			connection = joinMySQL.Join();
			//数据库掉线重连
			(new BukkitRunnable() {
				@Override
				public void run() {
					try {
						if (connection != null && !connection.isClosed()) {
							//System.out.println("§7[§a§l*§7] §a数据库检测");
							connection.createStatement().execute("SELECT 1");
						}
					} catch (SQLException e) {
						System.out.println("§7[§a§l*§7] §c未连接上数据库现为你重连");
						connection = joinMySQL.Join();
					}
				}
			}).runTaskTimerAsynchronously(this, 60 * 20, 60 * 20);
		}
	}
}

		
