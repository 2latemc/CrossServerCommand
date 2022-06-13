package at.late.crossservercommand.commands;

import at.late.crossservercommand.utils.SQLGetter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CmdSyncCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length < 2) {
            return false;
        }
        String cmd = "";
        for (int i = 1; i < args.length; i++) {
            if(cmd == "") {
                cmd = args[i];
            } else {
                cmd = cmd + " " + args[i];
            }
        }
        SQLGetter.AddCMD(args[0], cmd);
        sender.sendMessage("§aCommand has been sent.");
        return false;
    }
}
