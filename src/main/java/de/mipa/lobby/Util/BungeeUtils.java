package de.mipa.lobby.Util;


import de.mipa.lobby.Main;
import org.bukkit.entity.Player;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeUtils {

    public static void sendToServer(Player player, String serverName) {
        if (Main.getInstance() == null) {
            player.sendMessage("§c[Fehler] Plugin-Instanz nicht gefunden!");
            return;
        }

        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(serverName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sicherstellen, dass das Plugin nicht null ist
        player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
    }
}