package com.xinxin;


import com.xinxin.BotEnum.BotFrameEnum;
import com.xinxin.PluginBasicTool.BotData;
import com.xinxin.PluginBasicTool.MySQL;
import com.xinxin.PluginBasicTool.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.SQLException;

public class XinxinBotApi extends JavaPlugin{

	//public static Connection connection;
	private static XinxinBotApi instance;
	public static XinxinBotApi getInstance() { return instance; }
	public static FileConfiguration config;


	public void onEnable() {//加载时触发指令
		instance = this;
		int pluginId = 21026; // <-- Replace with the id of your plugin!
		new Metrics(this, pluginId);


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
		System.out.println("§7[§a§l*§7] §b新鑫社区网址：§fbbs.mcxin.cn");
		System.out.println("▌ §a新鑫娱乐业务范围 §6┈━═☆");
		System.out.println("§7[§a§l*§7] §bVps租用、实体机租用……");
		System.out.println("§7[§a§l*§7] §b原创群服对接机器人 §c[推荐]");
		System.out.println("§7[§a§l*§7] §b定制机器人插件、服务端插件……");
		System.out.println("§7[§a§l*§7] §b详情联系作者：§f1072565329");
		Bukkit.getPluginManager().registerEvents(new Xinxin_Listener(), this);
		Bukkit.getPluginManager().registerEvents(new DeBugListener(), this);
		Bukkit.getPluginCommand("xbot").setExecutor(new Xinxin_Command());//绑定指令
		new Papi();
		loadConfig();
	}
	public void onDisable() {//插件卸载触发
		//关闭ws
		BotData.closeWebSocket();
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
		if(getConfig().getBoolean("MySQL.Enable")){
			System.out.println("▌ §a开始连接数据库 §6┈━═☆");
			MySQL.createTable();
			/*
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
						connection = MySQL.Join();
					}
				}
			}).runTaskTimerAsynchronously(this, 60 * 20, 60 * 20);

			 */
		}
		connectBot();
	}

	public void connectBot(){
		System.out.println("▌ §a开始对接机器人框架 §6┈━═☆");
		BotData.setDeBug(config.getBoolean("debug"));
		BotData.setTaskTimeout(config.getInt("TaskTimeout",10));
		BotData.setMaxActiveCount(config.getInt("MaxActiveCount",10));

		if(config.getString("BotFrame").equalsIgnoreCase("OneBot")){
			BotData.setWs(getConfig().getString("OneBot.WebSocket"));
			BotData.setHttp(getConfig().getString("OneBot.Http"));
			BotData.setVerifyKey(getConfig().getString("OneBot.AccessToken"));
			BotData.setBotFrame(BotFrameEnum.OneBot);

			if(getConfig().getBoolean("AutoOpen")){
				BotData.clientInit(BotData.getWs());
			}
			System.out.println("§7[§a§l*§7] §a启用框架: §e"+BotData.getBotFrame());
		}else if(config.getString("BotFrame").equalsIgnoreCase("Mirai")){
			BotData.setWs(getConfig().getString("Mirai.WebSocket"));
			BotData.setQQ(getConfig().getString("Mirai.QQ"));
			BotData.setVerifyKey(getConfig().getString("Mirai.VerifyKey"));
			BotData.setBotFrame(BotFrameEnum.Mirai);

			if(getConfig().getBoolean("AutoOpen")){
				BotData.clientInit(BotData.getWs()+"/all?verifyKey="+BotData.getVerifyKey()+"&qq="+BotData.getQQ());
			}
			System.out.println("§7[§a§l*§7] §a启用框架: §e"+BotData.getBotFrame());
		}else {
			System.out.println("§7[§a§l*§7] §c未找到对应框架.");
		}
	}

}

		
