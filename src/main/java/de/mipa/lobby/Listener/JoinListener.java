package de.mipa.lobby.Listener;

import de.mipa.lobby.Main;
import de.mipa.lobby.Util.ItemManager;
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
        Player player = event.getPlayer();
        player.getInventory().clear();
        player.getInventory().setItem(0, ItemManager.getNavigator());
        player.getInventory().setItem(8, ItemManager.getProfileHead(player));
    }
}