package com.xinxin.MySQL;

import com.xinxin.XinxinBotApi;

import java.sql.*;

public class joinMySQL {
    public static Connection Join() {
        //声明Connection对象
        Connection con;
        // 获取之前准备的配置
        String host = XinxinBotApi.getInstance().getConfig().getString("MySQL.Host");//地址
        String port = XinxinBotApi.getInstance().getConfig().getString("MySQL.Port");//端口
        String database = XinxinBotApi.getInstance().getConfig().getString("MySQL.DataBase");//数据库名
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
        //MySQL配置时的用户名
        String user = XinxinBotApi.getInstance().getConfig().getString("MySQL.User");
        //MySQL配置时的密码
        String password = XinxinBotApi.getInstance().getConfig().getString("MySQL.Password");

        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("§7[§a§l*§7] §b数据库连接成功！");
                System.out.println("§7[§a§l*§7] §b开始创建表单……");
            try {
                //创建statement类对象，用来执行SQL语句！！
                Statement statement = con.createStatement();
                //要执行的SQL语句,创建一个player_data表单,已有则跳过创建
                String sql = "CREATE TABLE IF NOT EXISTS bot_player_data("
                        + "Player varchar(20) not null,"
                        + "QQ varchar(20) not null"
                        + ")charset=utf8;";
                if(0 == statement.executeLargeUpdate(sql)) {
                    System.out.println("§7[§a§l*§7] §b成功创建表单或已存在表单");
                }else {
                    System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
                }
                //statement.executeQuery(sql);
            } catch (SQLException throwables) {
                System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
                throwables.printStackTrace();
            }
                return con;
        } catch (ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("§7[§a§l*§7] §c数据库驱动异常，找不到驱动程序！");
            e.printStackTrace();
        } catch (SQLException throwables) {
            //数据库连接失败异常处理
            System.out.println("§7[§a§l*§7] §c连接失败，请检查配置文件");
            throwables.printStackTrace();
        }
        return null;
    }
}
