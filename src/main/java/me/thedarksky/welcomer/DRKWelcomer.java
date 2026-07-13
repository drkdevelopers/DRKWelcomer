package me.thedarksky.welcomer;

import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class DRKWelcomer extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);

        getLogger().info("DRKWelcomer Enabled!");
    }

    @Override
    public void onDisable() {

        getLogger().info("DRKWelcomer Disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!command.getName().equalsIgnoreCase("drkwelcomer")) {
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("drkwelcomer.reload")) {
                sender.sendMessage(Component.text("§cYou don't have permission."));
                return true;
            }

            reloadConfig();

            sender.sendMessage(Component.text("§aDRKWelcomer configuration reloaded!"));
            return true;
        }

        sender.sendMessage(Component.text("§eUsage: /drkwelcomer reload"));
        return true;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        if (!getConfig().getBoolean("join-message.enabled")) {
            return;
        }

        String message = getConfig().getString("join-message.message");
        if (message == null) {
            return;
        }

        message = message.replace("%player%", event.getPlayer().getName());
        message = message.replace("&", "§");

        event.joinMessage(Component.text(message));

        if (!event.getPlayer().hasPlayedBefore()
                && getConfig().getBoolean("first-join.enabled")) {

            String firstJoin = getConfig().getString("first-join.message");

            if (firstJoin != null) {
                firstJoin = firstJoin.replace("%player%", event.getPlayer().getName());
                firstJoin = firstJoin.replace("&", "§");

                event.getPlayer().sendMessage(Component.text(firstJoin));
            }
        }
    }
}