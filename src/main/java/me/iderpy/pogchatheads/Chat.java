package me.iderpy.pogchatheads;

import com.destroystokyo.paper.ClientOption;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Chat implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        BufferedImage c;
        TextComponent.Builder textComponent = Component.text();
        char pixel = '#';

        Player player = event.getPlayer();

        URL url = player.getPlayerProfile().getTextures().getSkin();
        try {
            c = ImageIO.read(url);
        } catch (IOException e) {
            return;
        }

        for (int x = 0; x < 10; x++) {
            if (player.getClientOption(ClientOption.SKIN_PARTS).hasHatsEnabled()) {
                for (int y = 0; y < 8; y++) {
                    Color color = new Color(c.getRGB(40+y, 8+x-2), true);
                    if (color.getAlpha() <= 254) {
                        textComponent.append(Component.text(pixel + "!").font(Key.key("custom", "custom")).color(TextColor.color(c.getRGB(8+y, 8+x-2))));
                    } else {
                        textComponent.append(Component.text(pixel + "!").font(Key.key("custom", "custom")).color(TextColor.color(c.getRGB(40+y, 8+x-2))));
                    }
                }
            } else {
                for (int y = 0; y < 8; y++) {
                    textComponent.append(Component.text(pixel + "!").font(Key.key("custom", "custom")).color(TextColor.color(c.getRGB(8+y, 8+x-2))));
                }
            }
            switch (x) {
                case 1: pixel = '#';
                    break;
                case 2: pixel = '$';
                    break;
                case 3: pixel = '%';
                    break;
                case 4: pixel = '&';
                    break;
                case 5: pixel = '\'';
                    break;
                case 6: pixel = '(';
                    break;
                case 7: pixel = ')';
                    break;
                case 8: pixel = '*';
                    break;
            }
            textComponent.append(Component.text("!!!!!!!!").font(Key.key("custom", "custom")));
        }

        event.renderer((sender, displayName, message, viewer) -> {
            if (viewer instanceof ConsoleCommandSender) {
                Component consoleTextComponent =
                        displayName.color(NamedTextColor.GRAY)
                        .append(Component.text(" » ").color(NamedTextColor.DARK_GRAY))
                        .append(message.color(NamedTextColor.WHITE));
                return consoleTextComponent;
            } else {
                TextComponent.Builder chatTextComponent = textComponent
                        .append(Component.text(("\"\"\"\"\"\"\"\"\"\"\"")).font(Key.key("custom", "custom")))
                        .append(displayName.color(NamedTextColor.GRAY))
                        .append(Component.text(" » ").color(NamedTextColor.DARK_GRAY))
                        .append(message.color(NamedTextColor.WHITE));
                return chatTextComponent.asComponent();
            }
        });


    }

}