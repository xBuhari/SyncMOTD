package me.SyncMOTD.xBuhari.Bungee;

public class SyncMOTD extends PluginBase {
    private static SyncMOTD plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static SyncMOTD getPlugin() {
        return plugin;
    }
}
