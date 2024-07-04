package com.xinxin.PluginBasicTool;

import com.xinxin.XinxinBotApi;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;

public class MySQL {
    private static final String host = XinxinBotApi.getInstance().getConfig().getString("MySQL.Host");
    private static final String port = XinxinBotApi.getInstance().getConfig().getString("MySQL.Port");
    private static final String database = XinxinBotApi.getInstance().getConfig().getString("MySQL.DataBase");
    private static final String user = XinxinBotApi.getInstance().getConfig().getString("MySQL.User");
    private static final String password = XinxinBotApi.getInstance().getConfig().getString("MySQL.Password");
    private static final boolean useSSL = XinxinBotApi.getInstance().getConfig().getBoolean("MySQL.useSSL", false); // Added SSL configuration option

    // 设置数据库连接池
    private static final BasicDataSource dataSource = setupDataSource();

    private static BasicDataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver"); // 设置 MySQL 驱动程序
        ds.setUrl("jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=" + useSSL);
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setInitialSize(5); // 设置连接池初始连接数
        ds.setMaxTotal(20); // 设置连接池最大连接数
        ds.setMaxIdle(10); // 设置连接池最大空闲连接数
        ds.setMinIdle(5); // 设置连接池最小空闲连接数
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        int numActive = dataSource.getNumActive();
        int numIdle = dataSource.getNumIdle();
        if(BotData.isDeBug()){
            System.out.println("当前活动连接数：" + numActive);
            System.out.println("当前空闲连接数：" + numIdle);
        }
        return dataSource.getConnection();
    }

    public static void createTable() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS bot_player_data("
                    + "Player varchar(20) not null,"
                    + "QQ varchar(20) not null"
                    + ")charset=utf8mb4"; // 使用 utf8mb4 编码，支持 emoji 等特殊字符
            if (statement.executeUpdate(sql) == 0) {
                System.out.println("§7[§a§l*§7] §b成功创建表单或已存在表单");
            } else {
                System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
            }
        } catch (SQLException e) {
            System.out.println("§7[§a§l*§7] §c创建表单失败请检查数据库");
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
