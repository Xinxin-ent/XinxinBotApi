package com.xinxin;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Message {
	public static void sendMsg(CommandSender player, String msg) {
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', msg)));
	}
}
