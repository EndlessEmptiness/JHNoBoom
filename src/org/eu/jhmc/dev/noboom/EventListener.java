package org.eu.jhmc.dev.noboom;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerCommandEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.eu.jhmc.dev.noboom.plugin.mcplugin;

public class EventListener implements Listener {
    private static boolean noboom_enable = true;

    private static boolean allow_tnt = false;
    private static boolean allow_crepper = false;
    private static boolean allow_tntMinecart = false;
    public static void init_cfg() {
        noboom_enable = mcplugin.getConfig().getBoolean("jhmcConfig.noboom.enable_noboom",true);
        allow_tnt = mcplugin.getConfig().getBoolean("jhmcConfig.noboom.allow_tnt",false);
        allow_crepper = mcplugin.getConfig().getBoolean("jhmcConfig.noboom.allow_crepper",false);
        allow_tntMinecart = mcplugin.getConfig().getBoolean("jhmcConfig.noboom.allow_tnt_minecart",false);
        if(allow_tnt) {
            plugin.logger.info("Allow tnt");
        }
        if(allow_crepper) {
            plugin.logger.info("Allow Crepper");
        }
        if(allow_tntMinecart) {
            plugin.logger.info("Allow tntMinecart");
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (!noboom_enable)
            return ;
        if (!plugin.NoBoom)
            return ;
        if(e.getEntityType() == EntityType.PRIMED_TNT && allow_tnt) { // tnt
            return ;
        }
        if(e.getEntityType() == EntityType.CREEPER && allow_crepper) { // crepper
            return ;
        }
        if(e.getEntityType() == EntityType.MINECART_TNT && allow_tntMinecart) { // tnt_minecart
            return ;
        }

        e.setCancelled(true);
    }
}

