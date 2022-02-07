package com.xinxin;

import com.xinxin.BotTool.BotData;
import com.xinxin.BotApi.BindApi;
import com.xinxin.BotTool.WebSocket;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Xinxin_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length == 1 && args[0].equalsIgnoreCase("connect")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg((Player) sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}

			if(BotData.getBotFrame().equalsIgnoreCase("Mirai")){
				WebSocket.main(BotData.getWs()+"/all?verifyKey="+BotData.getVerifyKey()+"&qq="+BotData.getQQ());
			}else if(BotData.getBotFrame().equalsIgnoreCase("go-cqhttp")){
				WebSocket.main(BotData.getWs());
			}else {
				Message.sendMsg(sender, "§b[新鑫互联] &f➥ &c未成功链接，请检查配置.");
				return true;
			}

			Message.sendMsg(sender, "§b[新鑫互联] &f➥ &a已启动WebSocket连接，开始监听事件.");
			return true;
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("close")) {
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg((Player) sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			if(WebSocket.client != null){
				WebSocket.client.close();
			}
			Message.sendMsg(sender, "§b[新鑫互联] &f➥ &a已关闭WebSocket连接，已不再监听事件.");
			return true;
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			XinxinBotApi.getInstance().loadConfig();
			Message.sendMsg(sender, "§b[新鑫互联] &f➥ &a已为你重新载入配置文件.");
			return true;
		}

		if(args.length == 3 && args[0].equalsIgnoreCase("bind")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg((Player) sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			if(BindApi.addBind(args[1],args[2])){
				Message.sendMsg(sender, "§b[新鑫互联] &f➥ &a添加绑定成功.");
			}else {
				Message.sendMsg(sender, "§b[新鑫互联] &f➥ &c绑定失败，QQ或玩家已被绑定.");
			}
			return true;
		}

		if(args.length == 2 && args[0].equalsIgnoreCase("delbind")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg((Player) sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			if(BindApi.delBind(args[1])){
				Message.sendMsg(sender, "§b[新鑫互联] &f➥ &a删除QQ绑定成功.");
			}else {
				Message.sendMsg(sender, "§b[新鑫互联] &f➥ &c删除失败，此QQ未绑定账号.");
			}
			return true;
		}

		sender.sendMessage("▌ §6插件："+ XinxinBotApi.getInstance().getName());
		sender.sendMessage("▌ §6版本："+ XinxinBotApi.getInstance().getDescription().getVersion());
		sender.sendMessage("▌ §6作者："+" §7[§6新鑫丶§7] §bQQ:§f1072565329");
		sender.sendMessage("☆┈━═§a插件指令");
		sender.sendMessage("§b■ §a/xbot bind [QQ] [玩家] §f- 新增一个绑定QQ");
		sender.sendMessage("§b■ §a/xbot delbind [QQ]§f- 删除一个QQ绑定");
		sender.sendMessage("§b■ §a/xbot connect §f- 连接WebSocket");
		sender.sendMessage("§b■ §a/xbot close §f- 关闭当前连接");
		sender.sendMessage("§b■ §a/xbot reload §f- 重载配置");
		return false;
	}

}
