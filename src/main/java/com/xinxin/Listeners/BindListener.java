package com.xinxin.Listeners;

import com.xinxin.BotApi.BotAction;
import com.xinxin.BotApi.BotBind;
import com.xinxin.BotEvent.GroupMessageEvent;
import com.xinxin.BotTool.OneBotMessageTool;
import com.xinxin.XinxinBotApi;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Random;

/**
 * 绑定监听器类，用于处理群消息事件并执行相应的账号绑定操作
 * @作者 haishen668
 * @版本 2.10.0
 * @日期 2024/7/6
 */
public class BindListener implements Listener {
    // 使用线程安全的集合来存储验证码和玩家名称的映射关系
    public static final Map<String, String> codeMap = new ConcurrentHashMap<>();

    // 处理群消息事件的方法
    @EventHandler
    public void onGroupMsg(GroupMessageEvent event) {
        FileConfiguration config = XinxinBotApi.getInstance().getConfig();

        // 检查是否启用了绑定功能
        if (!config.getBoolean("SetBind.enable", true)) {
            return;
        }

        // 获取配置文件中允许绑定的群组列表
        List<Long> groups = config.getLongList("SetBind.groups");

        // 检查消息是否来自允许绑定的群组
        if (!groups.contains(event.getGroup_id())) {
            return;
        }

        // 获取配置文件中设置的关键词
        List<String> keywords = config.getStringList("SetBind.keywords");
        // 将消息按空格分割为数组
        String[] arg = event.getMessage().split(" ");

        // 检查消息中的第一个单词是否为绑定关键词
        if (keywords.contains(arg[0])) {
            if (arg.length > 1) {
                handleBinding(event, arg[1], config);
            }
        } else if (isLong(event.getMessage()) && event.getMessage().length() == 6) {
            handleVerification(event, config);
        }
    }

    private void handleBinding(GroupMessageEvent event, String playerName, FileConfiguration config) {
        // 检查玩家是否已经绑定过账号
        boolean isAlreadyBound = BotBind.getBindPlayerName(String.valueOf(event.getUser_id())) != null || BotBind.getBindQQ(playerName) != null;

        // 检查玩家是否在线且未绑定过账号
        Player player = Bukkit.getPlayer(playerName);
        if (player != null && player.isOnline() && !isAlreadyBound) {
            sendBindingMessage(event, playerName, config);
        } else if (isAlreadyBound) {
            // 如果账号已经绑定，发送相应的提示信息
            List<String> isBind = config.getStringList("SetBind.isBind");
            BotAction.sendGroupMessage(event.getGroup_id(), isBind, true);
        } else {
            // 如果玩家不在线，发送相应的提示信息
            List<String> isOnline = config.getStringList("SetBind.isOnline");
            BotAction.sendGroupMessage(event.getGroup_id(), isOnline, true);
        }
    }

    private void sendBindingMessage(GroupMessageEvent event, String playerName, FileConfiguration config) {
        List<String> bindMessage = config.getStringList("SetBind.bind");
        String code = createCode(); // 生成验证码
        // 异步向玩家发送绑定信息
        Bukkit.getScheduler().runTaskAsynchronously(XinxinBotApi.getInstance(), () -> {
            Player player = Bukkit.getPlayer(playerName);
            if (player != null && player.isOnline()) {
                player.sendMessage(OneBotMessageTool.listToString(bindMessage)
                        .replace("{qq}", String.valueOf(event.getUser_id()))
                        .replace("{group}", String.valueOf(event.getGroup_id()))
                        .replace("{code}", code).replace("&", "§"));
            }
        });

        codeMap.put(code, playerName.trim()); // 存储验证码和玩家名称的映射关系

        int validTimeInMinutes = config.getInt("SetBind.validTime");
        long delayInTicks = validTimeInMinutes * 60 * 20L;
        // 启动异步任务，在指定时间后删除验证码
        Bukkit.getScheduler().runTaskLaterAsynchronously(XinxinBotApi.getInstance(), () -> codeMap.remove(code), delayInTicks);

        // 回复玩家查看游戏内聊天栏信息
        event.replyMessage("请查看游戏内聊天栏信息");
    }

    private void handleVerification(GroupMessageEvent event, FileConfiguration config) {
        String code = event.getMessage().trim();
        if (codeMap.containsKey(code)) {
            String playerName = codeMap.get(code);
            codeMap.remove(code);
            if (BotBind.addBind(String.valueOf(event.getUser_id()), playerName)) {
                event.replyMessage(config.getStringList("SetBind.bindSuccess"));
            } else {
                event.replyMessage(config.getStringList("SetBind.bindFailed"));
            }
        }
    }

    // 检查字符串是否为数字
    private static boolean isLong(String msg) {
        try {
            Long.parseLong(msg);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    // 生成六位数字验证码
    private String createCode() {
        String words = "0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        // 生成六位数的验证码
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(words.length());
            sb.append(words.charAt(index));
        }
        return sb.toString();
    }
}
