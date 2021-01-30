# SyncMOTD
Synchronizing with another server`s motd.


------------


##### This plugin using  `api.mcsrvstat.us`
##### This plugin has BungeeCord and Spigot support.
##### Restart the server to reload plugin.

##### For support contact me on Discord: `Buhari#8356`

------------

###### config.yml:

```yaml
motd:
  useFavicon: true  # Sync with favicon?
  useMaxPlayers: true # Sync with playerCount/MaxplayerCount?
  useMotd: true  # Sync with MOTD?
  remote:
    use: true  # Do u want use this plugin?
    updateTime: 5  # How long will it take to be updated?
    server:  # Remote Server Info.
      IP: 'mc.hypixel.net'  # Remote Server IP Adress
      Port: 25565  # Remote Server Port
    proxy:  # Proxy Settings
      use: false  # Do u want use Proxy?
      server:  # Proxy Settings
        IP: '188.166.34.137'  # Proxy IP Adress
        Port: 9000  # Proxy Port
        ProxyType: SOCKS  # Proxy Type (SOCKS,HTTP)
```
