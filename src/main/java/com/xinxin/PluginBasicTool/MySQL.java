//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xinxin.PluginBasicTool;

import com.xinxin.XinxinBotApi;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySQL {
    private static BasicDataSource dataSource;

    // 初始化数据库配置
    public static void initialize() {
        String host = XinxinBotApi.getInstance().getConfig().getString("MySQL.Host");
        String port = XinxinBotApi.getInstance().getConfig().getString("MySQL.Port");
        String database = XinxinBotApi.getInstance().getConfig().getString("MySQL.DataBase");
        String user = XinxinBotApi.getInstance().getConfig().getString("MySQL.User");
        String password = XinxinBotApi.getInstance().getConfig().getString("MySQL.Password");
        boolean useSSL = XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.useSSL", false);

        // 如果连接池已经存在，先关闭它
        if (dataSource != null) {
            closeDataSource();
        }

        // 重新创建连接池
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?characterEncoding=utf8&useSSL=" + useSSL + "&serverTimezone=UTC");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);   // 初始化连接数
        dataSource.setMaxTotal(XinxinBotApi.config.getInt("MaxActiveCount"));     // 最大活跃连接数
        dataSource.setMaxIdle(10);      // 最大空闲连接数
        dataSource.setMinIdle(5);       // 最小空闲连接数

        //System.out.println("数据库连接成功.");
    }


    // 关闭连接池并释放所有资源
    public static void closeDataSource() {
        try {
            if (dataSource != null) {
                dataSource.close();
                //System.out.println("DataSource 已关闭，所有资源已释放.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        int numActive = dataSource.getNumActive();
        int numIdle = dataSource.getNumIdle();
        if (BotData.isDeBug()) {
            System.out.println("当前活动连接数：" + numActive);
            System.out.println("当前空闲连接数：" + numIdle);
        }

        // 检查是否已达到最大活动连接数
        if (numActive >= dataSource.getMaxTotal()) {
            //System.out.println("活动连接数已达到最大值，清空连接池...");
            closeDataSource(); // 关闭并清空连接池
            //initialize(); // 重新初始化连接池
        }

        return dataSource.getConnection();
    }

    public static void createTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS bot_player_data(Player varchar(20) not null,QQ varchar(20) not null)charset=utf8mb4";
            if (statement.executeUpdate(sql) == 0) {
                System.out.println("§7[§a§l*§7] §b成功创建表单或已存在表单");
            } else {
                System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
            }

        } catch (SQLException var3) {
            SQLException e = var3;
            System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException var2) {
                SQLException e = var2;
                e.printStackTrace();
            }
        }

    }
}
