package me.SyncMOTD.xBuhari.Spigot;

import org.bukkit.plugin.java.JavaPlugin;

public class SyncMOTD extends JavaPlugin {
    private static SyncMOTD plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static SyncMOTD getPlugin() {
        return plugin;
    }
}
