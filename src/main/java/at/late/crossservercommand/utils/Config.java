package at.late.crossservercommand.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    public static File configFile = new File("plugins//CmdSync", "config.yml");

    public static YamlConfiguration configuration = YamlConfiguration.loadConfiguration(configFile);

    public static void SetupConfig() {
        configuration.options().copyDefaults(true);
        configuration.addDefault("MSQL." + "host", "localhost");
        configuration.addDefault("MSQL." + "port", "3306");
        configuration.addDefault("MSQL." + "database", "database");
        configuration.addDefault("MSQL." + "username", "root");
        configuration.addDefault("MSQL." + "password", "password");
        configuration.addDefault("ThisServerName", "server01");
        configuration.addDefault("UpdateDelay", "10");
        SaveConfig();
    }


    public static String GetServer() {
        return configuration.getString("ThisServerName");
    }
    public static int GetUpdateDelay() {
        return configuration.getInt("UpdateDelay");
    }



    public static void SaveConfig() {
        try {
            configuration.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ReloadConfig() {
        configuration = YamlConfiguration.loadConfiguration(configFile);
        SaveConfig();
    }


}
