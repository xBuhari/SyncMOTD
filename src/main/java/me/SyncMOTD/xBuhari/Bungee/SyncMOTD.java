package me.SyncMOTD.xBuhari.Bungee;

import me.SyncMOTD.xBuhari.Bungee.motd.MotdManager;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.*;

public class SyncMOTD extends PluginBase {
    private static SyncMOTD plugin;
    protected Configuration configuration;
    protected MotdManager motdManager;

    @Override
    public void onEnable() {
        plugin = this;
        new Util().load();
        try {
            this.configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(),"config.yml"));
        } catch (IOException e) {}
        motdManager = new MotdManager();
    }

    public MotdManager getMotdManager() {
        return motdManager;
    }

    public static SyncMOTD getPlugin() {
        return plugin;
    }

    public Configuration getConfig() {
        return configuration;
    }
}
