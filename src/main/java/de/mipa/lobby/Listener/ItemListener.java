package de.mipa.lobby.Listener;

import de.mipa.lobby.Util.MenuGUI;
import de.mipa.lobby.Main;
import de.mipa.lobby.Util.ItemManager;
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
        } else if (item.isSimilar(ItemManager.getProfileHead(player))) {
            String command = plugin.getConfig().getString("profile-command", "say Hallo!");
            player.performCommand(command);
            event.setCancelled(true);
        }
    }
}