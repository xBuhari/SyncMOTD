package me.SyncMOTD.xBuhari.Spigot.motd.listeners;

import me.SyncMOTD.xBuhari.Spigot.motd.MotdManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PingListener implements Listener {

    private MotdManager motdManager;

    public PingListener() {
        this.motdManager = new MotdManager();
    }

    @EventHandler
    public void onServerPing(ServerListPingEvent e) {
        e.setMotd(motdManager.getLocalServer().getMotd());
        e.setServerIcon(motdManager.getLocalServer().getFavicon());
        e.setMaxPlayers(motdManager.getLocalServer().getNumber());
    }
}
