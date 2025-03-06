package de.mipa.lobby;

import de.mipa.lobby.Listener.*;
import de.mipa.lobby.Util.MenuGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this; // ✅ Instanz setzen
        saveDefaultConfig();

        getLogger().info("Lobby-Plugin wird geladen...");

        // ✅ Plugin-Kanal für BungeeCord registrieren
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        // ✅ Events registrieren
        Bukkit.getPluginManager().registerEvents(new JoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ItemListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ProtectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new DoubleJumpListener(), this);
        Bukkit.getPluginManager().registerEvents(new de.mipa.lobby.listeners.BungeeSignListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuGUI(this), this);

        getLogger().info("Lobby-Plugin erfolgreich geladen!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Lobby-Plugin wurde deaktiviert!");
    }

    public static Main getInstance() {
        return instance;
    }
}
