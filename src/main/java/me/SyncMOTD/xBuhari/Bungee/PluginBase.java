package me.SyncMOTD.xBuhari.Bungee;

import java.nio.file.Path;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public abstract class PluginBase extends Plugin {
    public String getName() {
        return getDescription().getName();
    }

    public String getVersion() {
        return getDescription().getVersion();
    }

    public Path getPluginFolder() {
        return getDataFolder().toPath();
    }

    protected void registerListener(Listener listener) {
        getProxy().getPluginManager().registerListener(this, listener);
    }

    protected void unregisterListener(Listener listener) {
        getProxy().getPluginManager().unregisterListener(listener);
    }
}
