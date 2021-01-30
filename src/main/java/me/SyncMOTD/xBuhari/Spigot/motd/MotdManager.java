package me.SyncMOTD.xBuhari.Spigot.motd;

import me.SyncMOTD.xBuhari.Motd.RemoteServer;
import me.SyncMOTD.xBuhari.Spigot.SyncMOTD;
import org.bukkit.Bukkit;

import java.io.IOException;

public class MotdManager {

    private SyncMOTD plugin;

    private Boolean useRemote;
    private Integer updateTime;

    private RemoteServer remoteServer;

    public MotdManager() {
        this.plugin = SyncMOTD.getPlugin();
        this.useRemote = plugin.getConfig().getBoolean("motd.remote.use");
        this.updateTime = plugin.getConfig().getInt("motd.remote.updateTime");

        Bukkit.getScheduler().cancelTasks(SyncMOTD.getPlugin());
        if (useRemote) {
            Bukkit.getScheduler().runTaskLaterAsynchronously(SyncMOTD.getPlugin(), () -> {
                this.update();
            }, 20L * 60 * this.updateTime);
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
