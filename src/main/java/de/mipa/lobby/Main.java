package de.mipa.lobby;

import de.mipa.lobby.Listener.ItemListener;
import de.mipa.lobby.Listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig(); // Erstellt eine Standard-Config, falls nicht vorhanden
        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemListener(this), this);
        getLogger().info(ChatColor.GREEN + "Lobby-Plugin wurde geladen!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "Lobby-Plugin wurde deaktiviert!");
    }
}