package com.xinxin.PluginBasicTool;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

@Deprecated
public class Message {
	public static void sendMsg(CommandSender player, String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', msg)));
	}
}
