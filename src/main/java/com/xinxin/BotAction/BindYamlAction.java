package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotBindAction;
import com.xinxin.PluginBasicTool.Message;
import com.xinxin.XinxinBotApi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BindYamlAction implements BotBindAction {
    public Map<String,String> qqBindPlayer = new HashMap<>();
    public Map<String,String> playerBindQQ = new HashMap<>();

    public File file;
    public YamlConfiguration BindData;

    public BindYamlAction(){
        file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        BindData = YamlConfiguration.loadConfiguration(file);
        for(String qq : BindData.getKeys(false)){
            qqBindPlayer.put(qq,BindData.getString(qq));
            playerBindQQ.put(BindData.getString(qq),qq);
        }
        System.out.println("§7[§a§l*§7] §a已加载 §e"+qqBindPlayer.size()+" §a条绑定数据");
    }

    @Override
    public String getBindQQ(String Player) {

        return playerBindQQ.get(Player);
    }

    @Override
    public String getBindPlayerName(String QQ) {
        return qqBindPlayer.get(QQ);
    }

    @Override
    public boolean setBind(String QQ, String Player) {
        qqBindPlayer.put(QQ, Player);
        playerBindQQ.put(Player,QQ);

        //写入文件
        BindData.set(QQ, Player);

        return saveFile();
    }

    @Override
    public boolean unBind(String QQ) {
        playerBindQQ.remove(getBindPlayerName(QQ));
        qqBindPlayer.remove(QQ);

        //写入文件
        BindData.set(QQ, null);

        return saveFile();
    }

    @Override
    public boolean addBind(String QQ, String Player) {
        if(qqBindPlayer.get(QQ)!=null || playerBindQQ.get(Player) != null){
            return false;
        }

        //写入Map
        qqBindPlayer.put(QQ, Player);
        playerBindQQ.put(Player,QQ);

        //写入文件
        BindData.set(QQ, Player);

        return saveFile();
    }


    public boolean saveFile(){
        try {
            BindData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
