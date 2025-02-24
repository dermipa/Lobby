package de.mipa.lobby.Listener;

import de.mipa.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class ProtectionListener implements Listener {

    public ProtectionListener(Main main) {
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")){
            event.setCancelled(true);

        }
    }

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }
}
