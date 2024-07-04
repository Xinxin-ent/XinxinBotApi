package com.xinxin.BotAction;

import com.xinxin.BotInterface.BotBindAction;
import com.xinxin.PluginBasicTool.MySQL;
import com.xinxin.XinxinBotApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BindMysqlAction implements BotBindAction {
    @Override
    public String getBindQQ(String Player) {
        Connection connection = null;
        try  {
            connection = MySQL.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bot_player_data WHERE Player = ?");
            selectStatement.setString(1, Player);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("QQ");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }finally {
            MySQL.closeConnection(connection);
        }
        return null;
    }

    @Override
    public String getBindPlayerName(String QQ) {
        Connection connection = null;
        try  {
            connection = MySQL.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bot_player_data WHERE QQ = ?");

            selectStatement.setString(1, QQ);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                return rs.getString("Player");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //关闭数据库
            MySQL.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean setBind(String QQ, String Player) {
        try {
            Connection connection = MySQL.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bot_player_data WHERE QQ = ?");
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE bot_player_data SET Player = ? WHERE QQ = ?");

            selectStatement.setString(1, QQ);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                updateStatement.setString(1, Player);
                updateStatement.setString(2, QQ);
                updateStatement.executeUpdate();

                //关闭数据库
                MySQL.closeConnection(connection);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean unBind(String QQ) {
        try  {
            Connection connection = MySQL.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bot_player_data WHERE QQ = ?");
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM bot_player_data WHERE QQ = ?");

            selectStatement.setString(1, QQ);
            ResultSet rs = selectStatement.executeQuery();

            if (rs.next()) {
                deleteStatement.setString(1, QQ);
                deleteStatement.executeUpdate();

                //关闭数据库
                MySQL.closeConnection(connection);
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean addBind(String QQ, String Player) {
        try {
            Connection connection = MySQL.getConnection();
            PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM bot_player_data");
            ResultSet rs = selectStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("Player").equalsIgnoreCase(Player) || rs.getString("QQ").equalsIgnoreCase(QQ)) {
                    return false;
                }
            }

            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO bot_player_data(Player,QQ) values(?,?)");
            insertStatement.setString(1, Player);
            insertStatement.setString(2, QQ);
            insertStatement.executeUpdate();

            //关闭数据库
            MySQL.closeConnection(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }
}
