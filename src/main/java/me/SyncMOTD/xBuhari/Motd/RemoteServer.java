package me.SyncMOTD.xBuhari.Motd;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    private String ServerIP;
    private String[] ServerMOTD;
    private int ServerPort;
    private int ServerPlayerCount;
    private int ServerMaxPlayerCount;

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

    public String[] getMotd() {
        return ServerMOTD;
    }

    public void update() throws IOException {
        URL url = new URL("https://api.mcsrvstat.us/2/" + getIP() + ":" + getPort());

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
        this.updateVariables();
    }

    public void updateVariables()  {
        Gson gson = new Gson();
        JsonObject j = gson.fromJson(alltext, JsonObject.class);

        //this.ServerIP = j.get("ip").getAsString();
        //this.ServerPort = j.get("port").getAsInt();
        this.ServerMOTD = gson.fromJson(j.get("motd").getAsJsonObject().get("raw"),  String[].class);
        this.ServerPlayerCount = j.get("players").getAsJsonObject().get("online").getAsInt();
        this.ServerMaxPlayerCount = j.get("players").getAsJsonObject().get("max").getAsInt();

        System.out.println(getOnline());
                //String result = jobj.get("debug").getAsJsonObject().get("ping").getAsString();

     //   System.out.println(result);
    }
}
