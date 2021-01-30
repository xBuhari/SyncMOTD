package me.SyncMOTD.xBuhari;

import org.bukkit.plugin.java.JavaPlugin;

public class SyncMOTD extends JavaPlugin {
    private SyncMOTD plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public SyncMOTD getPlugin() {
        return plugin;
    }
}
