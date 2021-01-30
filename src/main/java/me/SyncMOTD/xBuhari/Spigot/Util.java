package me.SyncMOTD.xBuhari.Spigot;

import me.SyncMOTD.xBuhari.Spigot.motd.listeners.PingListener;
import org.bukkit.Bukkit;

public class Util {

    public static void load() {
        Bukkit.getServer().getPluginManager().registerEvents(new PingListener(), SyncMOTD.getPlugin());
        SyncMOTD.getPlugin().saveDefaultConfig();
    }

}
