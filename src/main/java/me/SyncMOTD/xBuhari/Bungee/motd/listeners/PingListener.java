package me.SyncMOTD.xBuhari.Bungee.motd.listeners;

import me.SyncMOTD.xBuhari.Bungee.SyncMOTD;
import me.SyncMOTD.xBuhari.Bungee.motd.MotdManager;
import net.md_5.bungee.api.Favicon;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingListener implements Listener {

    private MotdManager motdManager;

    public PingListener() {
        this.motdManager = SyncMOTD.getPlugin().getMotdManager();
    }

    @EventHandler
    public void onServerPing(ProxyPingEvent event) {
        ServerPing e = event.getResponse();
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useIcon")) {
           try {
               e.setFavicon(Favicon.create(this.motdManager.getRemoteServer().getServerIcon()));
           } catch (Exception exception) {}
        }
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useMotd")) {
            e.setDescription(this.motdManager.getRemoteServer().getMotd()[0] +"\n" + this.motdManager.getRemoteServer().getMotd()[1]);
        }
        if (SyncMOTD.getPlugin().getConfig().getBoolean("motd.useMaxPlayers")) {
            ServerPing.Players current = e.getPlayers();
            e.setPlayers( new ServerPing.Players( this.motdManager.getRemoteServer().getMaxPlayers(), SyncMOTD.getPlugin().getProxy().getOnlineCount(), current.getSample() ) );

        }
        event.setResponse(e);
    }

}
