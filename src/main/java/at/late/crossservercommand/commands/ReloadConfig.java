package at.late.crossservercommand.commands;

import at.late.crossservercommand.utils.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Config.ReloadConfig();
        sender.sendMessage("§6§lCOMMANDSYNC §7| §fDie config wurde neu geladen!");
        return true;
    }
}
