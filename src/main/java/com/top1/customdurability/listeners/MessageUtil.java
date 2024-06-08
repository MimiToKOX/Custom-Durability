package com.top1.customdurability.listeners;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageUtil {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(Player player, String message) {
        player.sendMessage(colorize(message));
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(message));
    }
}
