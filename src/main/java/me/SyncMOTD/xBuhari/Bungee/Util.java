package me.SyncMOTD.xBuhari.Bungee;

import me.SyncMOTD.xBuhari.Bungee.motd.listeners.PingListener;

public class Util {
    public void load() {
        SyncMOTD.getPlugin().saveDefaultConfig();
        SyncMOTD.getPlugin().registerListener(new PingListener());
        SyncMOTD.getPlugin().getProxy().getPluginManager().registerCommand(SyncMOTD.getPlugin(), new SyncMOTDcmd("syncmotd"));
    }
}
