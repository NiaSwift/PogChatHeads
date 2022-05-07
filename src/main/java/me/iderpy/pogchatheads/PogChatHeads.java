package me.iderpy.pogchatheads;

import org.bukkit.plugin.java.JavaPlugin;

public final class PogChatHeads extends JavaPlugin {

    @Override
    public void onEnable() {

        Chat chat = new Chat();
        getServer().getPluginManager().registerEvents(chat, this);

        this.getLogger().info("Plugin Enabled");

    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin Disabled");
    }
}
