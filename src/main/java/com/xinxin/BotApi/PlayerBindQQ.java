package com.xinxin.BotApi;

import com.xinxin.PluginBasicTool.MySQL;
import com.xinxin.XinxinBotApi;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Deprecated
public class PlayerBindQQ {

    //获取某玩家的绑定QQ
    public static String getBind(String Player){
        if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
            try {
                String sql = "SELECT * FROM bot_player_data";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
                ResultSet rs = statement.executeQuery(sql);
                //历遍所有结果
                while (rs.next()) {
                    if(rs.getString("Player").equalsIgnoreCase(Player)){
                        return rs.getString("QQ");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            return null;
        }
        File file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        FileConfiguration BindData = YamlConfiguration.loadConfiguration(file);

        for(String qq : BindData.getKeys(false)){
            if(BindData.getString(qq).equalsIgnoreCase(Player)){
                return qq;
            }
        }
        return null;
    }


    //获取某QQ的绑定玩家
    public static String getBindPlayer(String QQ){
        if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
            try {
                String sql = "SELECT * FROM bot_player_data";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
                ResultSet rs = statement.executeQuery(sql);
                //历遍所有结果
                while (rs.next()) {
                    if(rs.getString("QQ").equalsIgnoreCase(QQ)){
                        return rs.getString("Player");
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return null;
            }
            return null;
        }

        File file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        FileConfiguration BindData = YamlConfiguration.loadConfiguration(file);

        if(BindData.getString(QQ)!=null){
            return BindData.getString(QQ);
        }else {
            return null;
        }
    }

    public static Boolean setBind(String QQ,String Player){
        if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
            try {
                String sql = "SELECT * FROM bot_player_data";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
                //ResultSet类，用来存放获取的结果集合！！
                ResultSet rs = statement.executeQuery(sql);
                //历遍所有结果
                while (rs.next()) {
                    if(rs.getString("QQ").equalsIgnoreCase(QQ)){
                        //要执行的SQL语句
                        sql = "UPDATE bot_player_data SET Player='"+Player+"' WHERE QQ='"+QQ+"'";
                        statement.executeUpdate(sql);
                        return true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
            return false;
        }

        File file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        FileConfiguration BindData = YamlConfiguration.loadConfiguration(file);

        BindData.set(QQ,Player);

        try{
            BindData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public static Boolean delBind(String QQ){
        if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
            try {
                String sql = "SELECT * FROM bot_player_data";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
                //ResultSet类，用来存放获取的结果集合！！
                ResultSet rs = statement.executeQuery(sql);

                //历遍所有结果
                while (rs.next()) {
                    if(rs.getString("QQ").equalsIgnoreCase(QQ)){
                        //要执行的SQL语句
                        sql = "DELETE FROM bot_player_data WHERE QQ='"+QQ+"'";
                        statement.executeUpdate(sql);
                        return true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
            return false;
        }

        File file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        FileConfiguration BindData = YamlConfiguration.loadConfiguration(file);
        BindData.set(QQ,null);
        try{
            BindData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static Boolean addBind(String QQ,String Player){
        if(XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.Enable")){
            try {
                //要执行的SQL语句
                String sql = "SELECT * FROM bot_player_data";
                PreparedStatement statement = MySQL.getConnection().prepareStatement(sql);
                //ResultSet类，用来存放获取的结果集合！！
                ResultSet rs = statement.executeQuery(sql);
                //历遍所有结果
                while (rs.next()) {
                    if(rs.getString("Player").equalsIgnoreCase(Player) || rs.getString("QQ").equalsIgnoreCase(QQ)){
                        return false;
                    }
                }


                //执行的sql语句，用？代替变量
                sql = "INSERT INTO bot_player_data(Player,QQ) values(?,?)";
                statement = MySQL.getConnection().prepareStatement(sql);

                //getlong //整数
                //getString //字符串，文本
                //getBoolean //布尔型，判断
                //getDouble //浮点型,小数
                //getDate //时间日期
                //getObject //泛型
                //绑定变量
                statement.setString(1,Player);
                statement.setString(2,QQ);
                //执行语句
                statement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return false;
            }
            return true;
        }

        File file = new File(XinxinBotApi.getInstance().getDataFolder(), "BindData.yml");
        FileConfiguration BindData = YamlConfiguration.loadConfiguration(file);

        for(String q : BindData.getKeys(false)){
            if(q.equalsIgnoreCase(QQ)){
                return false;
            }
            if(BindData.getString(q).contains(Player)){
                return false;
            }
        }

        BindData.set(QQ,Player);
        try{
            BindData.save(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
