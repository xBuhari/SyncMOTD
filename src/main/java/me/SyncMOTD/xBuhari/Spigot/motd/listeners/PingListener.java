package me.SyncMOTD.xBuhari.Spigot.motd.listeners;

import me.SyncMOTD.xBuhari.Spigot.SyncMOTD;
import me.SyncMOTD.xBuhari.Spigot.motd.MotdManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import java.util.Arrays;

public class PingListener implements Listener {

    private MotdManager motdManager;

    public PingListener() {
        this.motdManager = SyncMOTD.getPlugin().getMotdManager();
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        try {e.setServerIcon(Bukkit.loadServerIcon(this.motdManager.getRemoteServer().getServerIcon())); } catch (Exception exception) {}
        e.setMotd(Arrays.toString(this.motdManager.getRemoteServer().getMotd()));
        e.setMaxPlayers(this.motdManager.getRemoteServer().getMaxPlayers());
    }
}
