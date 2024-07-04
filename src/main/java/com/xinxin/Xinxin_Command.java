package com.xinxin;

import com.xinxin.BotApi.BotBind;
import com.xinxin.BotEnum.BotFrameEnum;
import com.xinxin.PluginBasicTool.BotData;
import com.xinxin.PluginBasicTool.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Xinxin_Command implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length == 1 && args[0].equalsIgnoreCase("connect")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}

			if(BotData.getBotFrame().equals(BotFrameEnum.Mirai)){
				BotData.clientInit(BotData.getWs()+"/all?verifyKey="+BotData.getVerifyKey()+"&qq="+BotData.getQQ());
			}else if(BotData.getBotFrame().equals(BotFrameEnum.OneBot)){
				BotData.clientInit(BotData.getWs());
			}else {
				Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &c未成功链接，请检查配置.");
				return true;
			}

			Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a已启动WebSocket连接，开始监听事件.");
			return true;
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("close")) {
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			BotData.closeWebSocket();
			Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a已关闭WebSocket连接，已不再监听事件.");
			return true;
		}
		if(args.length == 1 && args[0].equalsIgnoreCase("reload")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			XinxinBotApi.getInstance().loadConfig();
			Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a已为你重新载入配置文件.");
			return true;
		}

		if(args.length == 3 && args[0].equalsIgnoreCase("bind")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			if(BotBind.addBind(args[1],args[2])){
				Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a添加绑定成功.");
			}else {
				Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &c绑定失败，QQ或玩家已被绑定.");
			}
			return true;
		}

		if(args.length == 2 && args[0].equalsIgnoreCase("unbind")){
			if (!sender.hasPermission(XinxinBotApi.getInstance().getName()+".admin")) {
				Message.sendMsg(sender, "&c你需要："+XinxinBotApi.getInstance().getName()+".admin权限才能执行.");
				return true;
			}
			if(BotBind.unBind(args[1])){
				Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a删除QQ绑定成功.");
			}else {
				Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &c删除失败，此QQ未绑定账号.");
			}
			return true;
		}

		if(args.length == 2 && args[0].equalsIgnoreCase("sel")){
			Message.sendMsg(sender, "§b[XinxinBotApi] &f➥ &a此QQ绑定玩家：&e"+BotBind.getBindPlayerName(args[1]));
			return true;
		}

		if(args.length == 1 && args[0].equalsIgnoreCase("list")){
			//XinxinBotApi附属插件列表
			List<String> subsidiarys = new ArrayList<>();
			Plugin[] plugins = XinxinBotApi.getInstance().getServer().getPluginManager().getPlugins();
			for (Plugin plugin : plugins){
				List<String> mergeDepend = new ArrayList<>();
				mergeDepend.addAll(plugin.getDescription().getDepend());
				mergeDepend.addAll(plugin.getDescription().getSoftDepend());

				if (mergeDepend.contains("XinxinBotApi")){
					subsidiarys.add(plugin.getName());
				}
			}
			sender.sendMessage("已经加载附属");
			for (String subsidiary : subsidiarys){
				sender.sendMessage("├─"+subsidiary);
			}
			return true;
		}

		sender.sendMessage("▌ §6插件："+ XinxinBotApi.getInstance().getName());
		sender.sendMessage("▌ §6版本："+ XinxinBotApi.getInstance().getDescription().getVersion());
		sender.sendMessage("▌ §6作者："+" §7[§6新鑫丶§7] §bQQ:§f1072565329");
		sender.sendMessage("☆┈━═§a插件指令");
		sender.sendMessage("§b■ §a/xbot bind [QQ] [玩家] §f- 新增一个绑定QQ");
		sender.sendMessage("§b■ §a/xbot unbind [QQ]§f- 删除一个QQ绑定");
		sender.sendMessage("§b■ §a/xbot sel [QQ]§f- 查看QQ绑定玩家");
		sender.sendMessage("§b■ §a/xbot connect §f- 连接WebSocket");
		sender.sendMessage("§b■ §a/xbot close §f- 关闭当前连接");
		sender.sendMessage("§b■ §a/xbot list §f- 查看加载的附属插件");
		sender.sendMessage("§b■ §a/xbot reload §f- 重载配置");
		return false;
	}

}
