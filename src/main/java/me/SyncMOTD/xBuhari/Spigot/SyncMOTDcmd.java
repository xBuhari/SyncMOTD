package me.SyncMOTD.xBuhari.Spigot;

import me.SyncMOTD.xBuhari.Spigot.motd.MotdManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class SyncMOTDcmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmdLabel, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                File configFile = new File(SyncMOTD.getPlugin().getDataFolder(), "config.yml");
                if (configFile.exists()) {
                    SyncMOTD.getPlugin().reloadConfig();
                }
                else {
                    SyncMOTD.getPlugin().saveDefaultConfig();
                }
                SyncMOTD.getPlugin().getLogger().info("Plugin has been reloaded!");

                SyncMOTD.getPlugin().motdManager = new MotdManager();

                return false;
            }
            SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
            return false;
        }
        SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
        return false;
    }
}
