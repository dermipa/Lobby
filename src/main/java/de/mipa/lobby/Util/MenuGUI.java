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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MenuGUI implements Listener {

    public MenuGUI(Main main) {
    }

    public static void openMenu(Player player) {
        Inventory menu = Bukkit.createInventory(null, 45, "§bNavigator");

        menu.setItem(11, createMenuItem(Material.GRASS_BLOCK, "§aCityBuild"));
        menu.setItem(15, createMenuItem(Material.IRON_SWORD, "§cSurvival"));
        menu.setItem(22, createMenuItem(Material.MAGMA_CREAM, "§6Spawn"));
        menu.setItem(29, createMenuItem(Material.BARRIER, "§4In Arbeit"));
        menu.setItem(33, createMenuItem(Material.BARRIER, "§4In Arbeit"));

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

        // Lese die Koordinaten und Blickrichtungen aus der Config
        Location spawn = new Location(player.getWorld(),
                config.getDouble("locations.spawn.x"),
                config.getDouble("locations.spawn.y"),
                config.getDouble("locations.spawn.z"),
                (float) config.getDouble("locations.spawn.yaw", 0),
                (float) config.getDouble("locations.spawn.pitch", 0));

        Location citybuild = new Location(player.getWorld(),
                config.getDouble("locations.citybuild.x"),
                config.getDouble("locations.citybuild.y"),
                config.getDouble("locations.citybuild.z"),
                (float) config.getDouble("locations.citybuild.yaw", 0),
                (float) config.getDouble("locations.citybuild.pitch", 0));

        Location survival = new Location(player.getWorld(),
                config.getDouble("locations.survival.x"),
                config.getDouble("locations.survival.y"),
                config.getDouble("locations.survival.z"),
                (float) config.getDouble("locations.survival.yaw", 0),
                (float) config.getDouble("locations.survival.pitch", 0));

        // Teleportation je nach Item
        if (clickedItem.getType() == Material.MAGMA_CREAM) {
            player.teleport(spawn);
        } else if (clickedItem.getType() == Material.GRASS_BLOCK) {
            player.teleport(citybuild);
        } else if (clickedItem.getType() == Material.IRON_SWORD) {
            player.teleport(survival);

    }}

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        event.setCancelled(true); // Verhindert das Verschieben von Items im Inventar
    }
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        event.setCancelled(true); // Verhindert das Wegwerfen von Items
    }}
