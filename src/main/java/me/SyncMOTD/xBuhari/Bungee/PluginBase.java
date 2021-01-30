package me.SyncMOTD.xBuhari.Bungee;

import java.io.*;
import java.nio.file.Path;

import com.google.common.io.ByteStreams;
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

    public void saveDefaultConfig() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        File configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
                try (InputStream is = getResourceAsStream("config.yml");
                     OutputStream os = new FileOutputStream(configFile)) {
                    ByteStreams.copy(is, os);
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to create configuration file", e);
            }
        }
    }
}
