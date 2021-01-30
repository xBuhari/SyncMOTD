package me.SyncMOTD.xBuhari.Spigot.motd;

import me.SyncMOTD.xBuhari.Motd.RemoteServer;
import me.SyncMOTD.xBuhari.Spigot.SyncMOTD;

public class MotdManager {

    private SyncMOTD plugin;

    private Boolean useRemote;

    private RemoteServer remoteServer;

    public MotdManager() {
        this.plugin = SyncMOTD.getPlugin();
        this.useRemote = plugin.getConfig().getBoolean("motd.remote.use");
    }



    public RemoteServer getRemoteServer() {
        return remoteServer;
    }

    public void update() {
        if (useRemote) {

        }
    }
}
