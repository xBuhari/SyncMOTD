package me.SyncMOTD.xBuhari.Bungee;

public class SyncMOTD extends PluginBase {
    private SyncMOTD plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public SyncMOTD getPlugin() {
        return plugin;
    }
}
