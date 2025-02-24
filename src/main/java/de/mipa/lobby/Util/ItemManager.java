package de.mipa.lobby.Util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemManager {

    public static ItemStack getNavigator() {
        return new ItemBuilder(Material.COMPASS)
                .setName("§bNavigator")
                .build();
    }

    public static ItemStack getFriendsHead(Player player) {
        return new ItemBuilder(Material.PLAYER_HEAD)
                .setName("§eFreunde")
                .build();
    }
}