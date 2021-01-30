package me.SyncMOTD.xBuhari.Spigot;

import me.SyncMOTD.xBuhari.Spigot.motd.MotdManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SyncMOTD extends JavaPlugin {
    private static SyncMOTD plugin;

    private MotdManager motdManager;

    @Override
    public void onEnable() {
        plugin = this;
        motdManager = new MotdManager();
        Util.load();
    }

    public static SyncMOTD getPlugin() {
        return plugin;
    }

    public MotdManager getMotdManager() {
        return motdManager;
    }
}
