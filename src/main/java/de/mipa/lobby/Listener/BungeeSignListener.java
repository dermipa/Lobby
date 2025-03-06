package de.mipa.lobby.listeners;

import de.mipa.lobby.utils.BungeeUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.block.sign.SignSide;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BungeeSignListener implements Listener {

    @EventHandler
    public void onSignCreate(SignChangeEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.sign.create")) return;

        if (event.getLine(0).equalsIgnoreCase("[Server]")) {
            String targetServer = event.getLine(1);
            if (targetServer == null || targetServer.isEmpty()) {
                player.sendMessage(ChatColor.RED + "Bitte gib in der zweiten Zeile einen Servernamen ein!");
                return;
            }

            event.setLine(0, ChatColor.GOLD + "[Server]");
            event.setLine(1, ChatColor.YELLOW + targetServer);
            event.setLine(2, ChatColor.GREEN + "Beitreten");
            player.sendMessage(ChatColor.GREEN + "BungeeCord-Schild für " + targetServer + " erstellt!");
        }
    }

    @EventHandler
    public void onSignClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null || !(event.getClickedBlock().getState() instanceof Sign)) return;

        Sign sign = (Sign) event.getClickedBlock().getState();
        SignSide front = sign.getSide(Side.FRONT);
        String line1 = ChatColor.stripColor(front.getLine(0));
        String targetServer = ChatColor.stripColor(front.getLine(1));

        if (line1.equalsIgnoreCase("[Server]") && targetServer != null && !targetServer.isEmpty()) {
            Player player = event.getPlayer();
            player.sendMessage(ChatColor.GREEN + "Verbinde zu " + targetServer + "...");
            BungeeUtils.sendToServer(player, targetServer);
        }
    }
}
