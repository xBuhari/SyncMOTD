package me.SyncMOTD.xBuhari.Bungee;

import me.SyncMOTD.xBuhari.Bungee.motd.MotdManager;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;


import java.io.File;

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
                    SyncMOTD.getPlugin().ReloadFile();
                }
                else {
                    SyncMOTD.getPlugin().saveDefaultConfig();
                }
                SyncMOTD.getPlugin().getLogger().info("Plugin has been reloaded!");

                SyncMOTD.getPlugin().motdManager = new MotdManager();
                return;
            }
            SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
            return;
        }
        SyncMOTD.getPlugin().getLogger().warning("Wrong Using! Please use: syncmotd reload");
    }
}
