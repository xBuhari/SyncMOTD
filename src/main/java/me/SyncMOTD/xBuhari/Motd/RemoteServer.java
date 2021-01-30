package me.SyncMOTD.xBuhari.Motd;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.SyncMOTD.xBuhari.Motd.ServerListPing17.Player;
import me.SyncMOTD.xBuhari.Motd.ServerListPing17.StatusResponse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class RemoteServer {

    //JSON
    private String alltext;

    //Proxy
    private Boolean useProxy;
    private String ProxyIP;
    private Integer ProxyPort;
    private Type ProxyType;

    //Server
    private String ServerIP;
    private String ServerMOTD;
    private int ServerPort;
    private int ServerPlayerCount;
    private int ServerMaxPlayerCount;
    private BufferedImage ServerIcon;
    private List<String> SamplePlayers;

    public RemoteServer(String ip, int port) {
        this.useProxy = false;
        this.ServerIP = ip;
        this.ServerPort = port;
    }

    public RemoteServer(String ip, int port, String proxyIP, Integer proxyPort, String proxyType) {
        this.useProxy = true;
        this.ServerIP = ip;
        this.ServerPort = port;
        this.ProxyIP = proxyIP;
        this.ProxyPort = proxyPort;
        switch (proxyType) {
            case "HTTP":
                this.ProxyType = Type.HTTP;
                break;
            case "HTTPS":
                this.ProxyType = Type.HTTP;
                break;
            case "SOCKS":
                this.ProxyType = Type.SOCKS;
                break;
            case "SOCKS4":
                this.ProxyType = Type.SOCKS;
                break;
            case "SOCKS5":
                this.ProxyType = Type.SOCKS;
                break;
            default:
                this.useProxy = false;
                break;
        }
    }

    public String getIP() {
        return ServerIP;
    }

    public int getPort() {
        return ServerPort;
    }

    public int getOnline() {
        return ServerPlayerCount;
    }

    public int getMaxPlayers() {
        return ServerMaxPlayerCount;
    }

    public String getMotd() {
        return ServerMOTD;
    }

    public BufferedImage getServerIcon() {
        return ServerIcon;
    }


    public List<String> getSamplePlayers() {
        return SamplePlayers;
    }

    private void update() throws IOException {
        ServerListPing17 serverListPing17 = new ServerListPing17();
        serverListPing17.setAddress(new InetSocketAddress(this.ServerIP, this.ServerPort));

        if (useProxy) {
            Proxy proxy = new Proxy(this.ProxyType, new InetSocketAddress(this.ProxyIP, this.ProxyPort));
            serverListPing17.setProxy(proxy);
        }

        StatusResponse statusResponse = serverListPing17.fetchData();

        this.ServerMOTD = statusResponse.getDescription();
        this.ServerPlayerCount = statusResponse.getPlayers().getOnline();
        this.ServerMaxPlayerCount = statusResponse.getPlayers().getMax();
        this.ServerIcon = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(statusResponse.getFavicon().split(",")[1])));
        this.SamplePlayers = statusResponse.getPlayers().getSample().stream().map(Player::getName).collect(Collectors.toList());

    }
}
