package me.SyncMOTD.xBuhari.Bungee;

import me.SyncMOTD.xBuhari.Bungee.motd.MotdManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

import java.io.File;
import java.io.IOException;

public class SyncMOTDcmd extends Command {

    public SyncMOTDcmd(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                File configFile = new File(SyncMOTD.getPlugin().getDataFolder(), "config.yml");

                if (configFile.exists()) {
                    try {
                        SyncMOTD.getPlugin().reloadConfig();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    SyncMOTD.getPlugin().saveDefaultConfig();
                }

                SyncMOTD.getPlugin().motdManager = new MotdManager();

                SyncMOTD.getPlugin().getLogger().info("Plugin has been reloaded!");
                return;
            }
            SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
            return;
        }
        SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
    }
}
