package de.mipa.lobby;

import de.mipa.lobby.Listener.ItemListener;
import de.mipa.lobby.Listener.JoinListener;
import de.mipa.lobby.Listener.ProtectionListener;
import de.mipa.lobby.Util.MenuGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import de.mipa.lobby.Listener.DoubleJumpListener;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuGUI(this), this);
        getLogger().info("Lobby-Plugin wurde geladen!");


        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getPluginManager().registerEvents(new DoubleJumpListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Lobby-Plugin wurde deaktiviert!");
    }
}