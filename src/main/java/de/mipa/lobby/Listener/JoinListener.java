package de.mipa.lobby.Listener;

import de.mipa.lobby.Main;
import de.mipa.lobby.Util.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.entity.Player;

public class JoinListener implements Listener {

    private final Main plugin;

    public JoinListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.getPlayer().teleport(new Location(Bukkit.getWorld("lobbymap"), 0.65, 51, 0.2));
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().setItem(0, ItemManager.getNavigator());
        player.getInventory().setItem(8, ItemManager.getFriendsHead(player));
    }
}