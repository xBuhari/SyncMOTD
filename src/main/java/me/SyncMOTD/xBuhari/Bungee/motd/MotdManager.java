package me.SyncMOTD.xBuhari.Bungee.motd;

import me.SyncMOTD.xBuhari.Bungee.SyncMOTD;
import me.SyncMOTD.xBuhari.Motd.RemoteServer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MotdManager {
    private SyncMOTD plugin;

    private Boolean useRemote;
    private Integer updateTime;

    private RemoteServer remoteServer;

    public MotdManager() {
        this.plugin = SyncMOTD.getPlugin();
        this.useRemote = plugin.getConfig().getBoolean("motd.remote.use");
        this.updateTime = plugin.getConfig().getInt("motd.remote.updateTime");
        plugin.getProxy().getScheduler().cancel(SyncMOTD.getPlugin());
        if (useRemote) {
            plugin.getProxy().getScheduler().schedule(SyncMOTD.getPlugin(), () -> {
                this.update();
            },1, this.updateTime* 60, TimeUnit.SECONDS);
        }
    }

    public RemoteServer getRemoteServer() {
        return remoteServer;
    }

    public void update() {
        if (useRemote) {
            if (plugin.getConfig().getBoolean("motd.remote.proxy.use")) {
                this.remoteServer = new RemoteServer(
                        plugin.getConfig().getString("motd.remote.server.IP"),
                        plugin.getConfig().getInt("motd.remote.server.Port"),
                        plugin.getConfig().getString("motd.remote.proxy.server.IP"),
                        plugin.getConfig().getInt("motd.remote.proxy.server.Port"),
                        plugin.getConfig().getString("motd.remote.proxy.server.ProxyType")
                );
            }
            else {
                this.remoteServer = new RemoteServer(
                        plugin.getConfig().getString("motd.remote.server.IP"),
                        plugin.getConfig().getInt("motd.remote.server.Port")
                );
            }
            try {
                this.remoteServer.update();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
