package at.late.crossservercommand.sheudler;

import at.late.crossservercommand.Main;
import at.late.crossservercommand.utils.Config;
import at.late.crossservercommand.utils.SQLGetter;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Sheudler {
    public static void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                SQLGetter.Check();
       //        Bukkit.getConsoleSender().sendMessage("§c§lSYNCCMD§7 | §fChecked for Updates!");
            } // delay with config not working idk why 
        }.runTaskTimer(Main.getInstance(), 0L, 3 * 20);
    }
}
