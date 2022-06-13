package at.late.crossservercommand;

import at.late.crossservercommand.commands.CmdSyncCommand;
import at.late.crossservercommand.commands.ReloadConfig;
import at.late.crossservercommand.sheudler.Sheudler;
import at.late.crossservercommand.utils.Config;
import at.late.crossservercommand.utils.MySQL;
import at.late.crossservercommand.utils.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {
    public static SQLGetter sqlGetter;
    public static MySQL SQL;
    private static Main instance;
    private Sheudler sheudler;
    public static int updateDelay;

    @Override
    public void onLoad() {
        instance = this;
    }

    @Override
    public void onEnable() {

        Config.SetupConfig();
        updateDelay = Config.GetUpdateDelay();

        PluginManager manager = Bukkit.getPluginManager();

        getCommand("synccmdreload").setExecutor(new ReloadConfig());
        getCommand("synccmd").setExecutor(new CmdSyncCommand());

        sheudler = new Sheudler();

        SQL = new MySQL();
        sqlGetter = new SQLGetter(this);


        try {
            SQL.connect();
        } catch (ClassNotFoundException | SQLException e) {
            Bukkit.getLogger().info("§c§lCMDSYNC §r§7| §cDatenbank nicht verbunden");
            Bukkit.shutdown();
        }
        if(SQL.isConnected()) {
            Bukkit.getLogger().info("§c§lCMDSYNC §r§7| §aDatenbank verbunden!");
            sqlGetter.createTable();
        }


        Sheudler.run();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Main getInstance() {
        return instance;
    }

}
