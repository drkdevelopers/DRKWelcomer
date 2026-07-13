package me.thedarksky.welcomer.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.joinMessage(
                Component.text("§8[§a+§8] §fWelcome §b" + event.getPlayer().getName() + " §fto the server!")
        );
    }
}