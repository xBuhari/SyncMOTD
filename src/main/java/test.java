import me.SyncMOTD.xBuhari.Motd.RemoteServer;

import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {
        RemoteServer remoteServer = new RemoteServer("mc.hypixel.net" ,25565);
        remoteServer.update();
    }

}
