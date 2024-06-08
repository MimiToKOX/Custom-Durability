package com.top1.customdurability;

import com.top1.customdurability.command.CustomDurabilityCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        // pseudo "Zabezpieczenie"

        if (!getDescription().getName().equals("${project.artifactId}")
                && !getDescription().getAuthors().contains("mimitokox_ / mimicode")
                && !getDescription().getWebsite().equals("www.migmahost.pl / www.dsc.gg/mimicode")) {

            getLogger().warning("Wykryto nieupoważnioną zmianę danych w plugin.yml w wyniku czego " +
                    "plugin zostaje wyłączony. Prosimy o zmianę danych na prawidłowe. " +
                    "Jeśli to błąd - skontaktuj się z nami na Discordzie: https://discord.gg/weHSn6Bhjd");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        // Komendy
        this.getCommand("customdurability").setExecutor(new CustomDurabilityCommand());

    }
}
