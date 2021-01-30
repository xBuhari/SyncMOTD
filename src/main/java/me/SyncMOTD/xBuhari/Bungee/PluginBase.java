package me.SyncMOTD.xBuhari.Bungee;

import java.io.*;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.common.io.ByteStreams;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public abstract class PluginBase extends Plugin {

    private File configFile = new File(getDataFolder(), "config.yml");
    private Configuration config = null;

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

    public void LoadFile() {
        File configfile;
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (!(configfile = new File(getDataFolder(), "config.yml")).exists()) {
            try {
                Files.copy(getResourceAsStream("config.yml"), configfile.toPath(), new CopyOption[0]);
            }
            catch (IOException var1_1) {
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void ReloadFile() {
        File configfile;
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (!(configfile = new File(getDataFolder(), "config.yml")).exists()) {
            try {
                Files.copy(getResourceAsStream("config.yml"), configfile.toPath(), new CopyOption[0]);
            }
            catch (IOException var1_1) {
            }
        }
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
