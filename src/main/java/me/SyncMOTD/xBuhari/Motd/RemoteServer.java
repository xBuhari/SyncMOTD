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
    private String name, ip, status;

    private String alltext;

    //Proxy

    private int port;
    private int playercount;
    private int maxpcount;

    public RemoteServer(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public RemoteServer(String ip, int port, String proxyIP, Integer proxyPort, String proxyType) {
        this.ip = ip;
        this.port = port;
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
        Proxy proxy = new Proxy(Type.SOCKS, new InetSocketAddress("188.166.34.137", 9001));
        URL url = new URL("https://api.mcsrvstat.us/2/mc.hypixel.net");
        URLConnection con = url.openConnection(proxy);
        InputStream in = con.getInputStream();
        String encoding = con.getContentEncoding();
        encoding = encoding == null ? "UTF-8" : encoding;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int len = 0;
        while ((len = in.read(buf)) != -1) {
            baos.write(buf, 0, len);
        }
        String body = new String(baos.toByteArray(), encoding);
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(body);

        System.out.println(json.get("ip"));

        alltext = body;
    }

    public void updateVariables() {

    }
}
