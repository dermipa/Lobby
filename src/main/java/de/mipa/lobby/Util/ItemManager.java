package de.mipa.lobby.Util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemManager {

    public static ItemStack getNavigator() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta meta = compass.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§bNavigator");
            compass.setItemMeta(meta);
        }
        return compass;
    }

    public static ItemStack getProfileHead(Player player) {
        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§eProfil");
            meta.setOwningPlayer(player); // Setzt den Kopf auf den Spieler
            skull.setItemMeta(meta);
        }
        return skull;
    }
}