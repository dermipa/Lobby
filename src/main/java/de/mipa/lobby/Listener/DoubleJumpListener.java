package de.mipa.lobby.Listener;


import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class DoubleJumpListener implements Listener {

    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE) return;

        event.setCancelled(true);
        player.setFlying(false);

        // Doppelsprung-Effekt
        Vector jump = player.getLocation().getDirection().multiply(1.2).setY(1);
        player.setVelocity(jump);
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 15);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);

        // Erlaubt erneut zu springen, sobald man landet
        player.setAllowFlight(false);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE && player.isOnGround()) {
            player.setAllowFlight(true);
        }

        // Launchpad (Schleuderplatten mit Schleimblöcken)
        Location loc = player.getLocation().subtract(0, 1, 0);
        if (loc.getBlock().getType() == Material.OAK_PRESSURE_PLATE) {
            player.setVelocity(new Vector(0, 2, 0));
            player.playSound(player.getLocation(), Sound.ENTITY_SLIME_JUMP, 1, 1);
            player.getWorld().spawnParticle(Particle.CRIT, player.getLocation(), 20);
        }
    }
}
