package at.late.crossservercommand.utils;

import at.late.crossservercommand.Main;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.ConsoleHandler;

import static at.late.crossservercommand.Main.SQL;

public class SQLGetter {

    private Main plugin;
    public SQLGetter(Main plugin) {
        this.plugin = plugin;
    }

    public void createTable() {
        PreparedStatement preparedStatement;
        try{
            preparedStatement = SQL.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS cmds " + "(SERVER VARCHAR(100),CMD VARCHAR(100))");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Check() {
        try {
            PreparedStatement preparedStatement = SQL.getConnection().prepareStatement("SELECT * FROM cmds WHERE SERVER=?");
            preparedStatement.setString(1, Config.GetServer());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String cmd = GetCMD(Config.GetServer());
                Bukkit.getConsoleSender().sendMessage(cmd);
                Bukkit.getConsoleSender().sendMessage("§c§lSYNCCMD§7 | §fDer Command " + cmd + " wurde von der Datenbank ausgeführt!");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
                RemoveCMD(Config.GetServer());
            } else {
                return;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String GetCMD(String server) {
        try {
            PreparedStatement preparedStatement = SQL.getConnection().prepareStatement("SELECT CMD FROM cmds WHERE SERVER=?");
            preparedStatement.setString(1, server);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return (resultSet.getString("CMD"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void AddCMD(String server, String cmd) {
        try {
            PreparedStatement preparedStatement1 = SQL.getConnection().prepareStatement ("INSERT IGNORE INTO cmds" + " (SERVER,CMD) VALUES (?,?)");
            preparedStatement1.setString(1, server);
            preparedStatement1.setString(2, cmd);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void RemoveCMD(String server) throws SQLException {
        PreparedStatement preparedStatement = SQL.getConnection().prepareStatement("DELETE FROM cmds WHERE SERVER=?");
        preparedStatement.setString(1, server);
        preparedStatement.executeUpdate();
    }
}
