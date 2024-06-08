package com.top1.customdurability.command;

import com.top1.customdurability.listeners.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class CustomDurabilityCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("&4☺ &cNie jesteś graczem xd lol");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("mimicode.CustomDurability")) {
            MessageUtil.sendMessage(player, "&4☺ &cNie masz uprawnień do tego! (mimicode.CustomDurability)");
            return true;
        }

        if (args.length != 2 || !args[0].equalsIgnoreCase("set")) {
            MessageUtil.sendMessage(player,"&4☺ &cPoprawne użycie: /customdurability set <ilość>");
            return true;
        }

        int durability;
        try {
            durability = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            MessageUtil.sendMessage(player,"&4☺ &cIlość musi być liczbą!");
            return true;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        if (itemInHand == null || !itemInHand.getType().isItem()) {
            MessageUtil.sendMessage(player,"&4☺ &cMusisz trzymać w dłoni poprawny przedmiot.");
            return true;
        }

        ItemMeta meta = itemInHand.getItemMeta();
        if (meta instanceof Damageable) {
            Damageable damageable = (Damageable) meta;
            int maxDurability = itemInHand.getType().getMaxDurability();
            if (durability > maxDurability) {
                maxDurability = durability;
            }
            damageable.setDamage(maxDurability - durability);
            itemInHand.setItemMeta(meta);
            MessageUtil.sendMessage(player,"&2☺ &aPomyślnie zmieniłeś durability swojego przedmiotu na " + durability + "!");
        } else {
            MessageUtil.sendMessage(player,"&4☺ &cTen przedmiot nie może mieć ustawionej trwałości.");
        }

        return true;
    }
}
