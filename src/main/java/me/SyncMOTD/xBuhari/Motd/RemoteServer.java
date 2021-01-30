package me.SyncMOTD.xBuhari.Motd;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import java.net.URLConnection;

public class RemoteServer {

    //JSON
    private String alltext;

    //Proxy
    private Boolean useProxy;
    private String ProxyIP;
    private Integer ProxyPort;
    private Type ProxyType;

    //Server
    private String name, ip, status;
    private int port;
    private int playercount;
    private int maxpcount;

    public RemoteServer(String ip, int port) {
        this.useProxy = false;
        this.ip = ip;
        this.port = port;
    }

    public RemoteServer(String ip, int port, String proxyIP, Integer proxyPort, String proxyType) {
        this.useProxy = true;
        this.ip = ip;
        this.port = port;
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

    public String getName() {
        return name;
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getOnline() {
        return playercount;
    }

    public int getMaxPlayers() {
        return maxpcount;
    }

    public String getMotd() {
        return status;
    }

    public void update() throws IOException, ParseException {
        URL url = new URL("https://api.mcsrvstat.us/2/mc.hypixel.net");

        URLConnection urlConnection;
        if (useProxy) {
            Proxy proxy = new Proxy(this.ProxyType, new InetSocketAddress(this.ProxyIP, this.ProxyPort));
            urlConnection = url.openConnection(proxy);
        }
        else {
            urlConnection = url.openConnection();
        }

        InputStream in = urlConnection.getInputStream();

        String encoding = urlConnection.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buf = new byte[8192];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        this.alltext = new String(baos.toByteArray(), encoding);

    }

    public void updateVariables() {

    }
}
