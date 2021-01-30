package me.SyncMOTD.xBuhari.Bungee.motd.listeners;

import me.SyncMOTD.xBuhari.Bungee.SyncMOTD;
import me.SyncMOTD.xBuhari.Bungee.motd.MotdManager;
import me.SyncMOTD.xBuhari.Motd.ServerListPing17.Player;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.ServerPing.PlayerInfo;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.stream.Collectors;

public class PingListener implements Listener {

    private MotdManager motdManager;

    public PingListener() {
        this.motdManager = SyncMOTD.getPlugin().getMotdManager();
    }

    @EventHandler
    public void onServerPing(ProxyPingEvent event) {
        this.motdManager = SyncMOTD.getPlugin().getMotdManager();
        ServerPing e = event.getResponse();
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useFavicon")) {
            e.setFavicon(Favicon.create(this.motdManager.getRemoteServer().getServerIcon()));
        }
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useMotd")) {
            e.setDescription(this.motdManager.getRemoteServer().getMotd());
        }
        ServerPing.Players current = e.getPlayers();
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useMaxPlayers")) {
            current.setMax(this.motdManager.getRemoteServer().getMaxPlayers());
            current.setOnline(this.motdManager.getRemoteServer().getOnline());
        }
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useSample")) {
            current.setSample(this.motdManager.getRemoteServer().getSamplePlayers().stream().map(Player::getInfo).collect(Collectors.toList()).toArray(new PlayerInfo[0]));
        }
        e.setPlayers(new ServerPing.Players( current.getMax(), current.getOnline(), current.getSample()));
        event.setResponse(e);
    }
}
