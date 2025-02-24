package de.mipa.lobby.Listener;

import de.mipa.lobby.Main;
import de.mipa.lobby.Util.ItemManager;
import de.mipa.lobby.Util.MenuGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {

    private final Main plugin;

    public ItemListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        if (!event.hasItem()) return;

        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (item.isSimilar(ItemManager.getNavigator())) {
            MenuGUI.openMenu(player);
            event.setCancelled(true);
        } else if (item.isSimilar(ItemManager.getFriendsHead(player))) {
            String command = plugin.getConfig().getString("friends-command", "say Freunde-Menü geöffnet!");
            player.performCommand(command);
            event.setCancelled(true);
        }
    }
}