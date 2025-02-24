package de.mipa.lobby.Util;

import de.mipa.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuGUI implements Listener {

    public static void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 9, "§bNavigator");

        menu.setItem(2, createMenuItem(Material.RED_WOOL, "§cSpawn"));
        menu.setItem(4, createMenuItem(Material.GREEN_WOOL, "§aPvP Arena"));
        menu.setItem(6, createMenuItem(Material.BLUE_WOOL, "§9Parkour"));

        player.openInventory(menu);
    }

    private static ItemStack createMenuItem(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (!event.getView().getTitle().equals("§bNavigator")) return;

        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null) return;
        FileConfiguration config = Main.getPlugin(Main.class).getConfig();

        Location spawn = new Location(player.getWorld(), config.getDouble("locations.spawn.x"), config.getDouble("locations.spawn.y"), config.getDouble("locations.spawn.z"));
        Location pvp = new Location(player.getWorld(), config.getDouble("locations.pvp.x"), config.getDouble("locations.pvp.y"), config.getDouble("locations.pvp.z"));
        Location parkour = new Location(player.getWorld(), config.getDouble("locations.parkour.x"), config.getDouble("locations.parkour.y"), config.getDouble("locations.parkour.z"));

        if (clickedItem.getType() == Material.RED_WOOL) {
            player.teleport(spawn);
        } else if (clickedItem.getType() == Material.GREEN_WOOL) {
            player.teleport(pvp);
        } else if (clickedItem.getType() == Material.BLUE_WOOL) {
            player.teleport(parkour);
        }
    }
}