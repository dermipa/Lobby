package de.mipa.lobby.Listener;

import de.mipa.lobby.Main;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.block.Action;

public class ProtectionListener implements Listener {

    public ProtectionListener(Main main) {
    }

    @EventHandler
    public void onBlockBreak(final BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(final PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("lobby.build")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onSignInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        // Prüfen, ob der Spieler ein Schild rechtsklickt
        if (block != null && (block.getState() instanceof Sign)) {
            if (!player.hasPermission("lobby.sign.edit") && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true); // Schild-Editor blockieren
            }
        }
    }

    @EventHandler
    public void onUnderwaterBreath(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        // Falls der Spieler unter Wasser ist, setze seine Luft auf Maximum
        if (player.isInWater()) {
            player.setRemainingAir(player.getMaximumAir());
        }
    }
}
