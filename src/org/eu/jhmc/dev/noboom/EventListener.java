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

public class EventListener implements Listener {
    private static boolean noboom_cmd_onlyServer = false;
    private static boolean noboom_enable = true;

    private static boolean allow_tnt = false;
    private static boolean allow_crepper = false;
    private static boolean allow_tntMinecart = false;
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if(!noboom_cmd_onlyServer)
            return ;
        if(e.getMessage().toLowerCase().startsWith("/noboom ")) {
            e.setCancelled(true);
        }
    }
    public static void init_cfg() {
        noboom_cmd_onlyServer = plugin.config.getBoolean("jhmcConfig.event.disCmds",false);
        noboom_enable = plugin.config.getBoolean("jhmcConfig.noboom.enable_noboom",true);
        allow_tnt = plugin.config.getBoolean("jhmcConfig.noboom.allow_tnt",false);
        allow_crepper = plugin.config.getBoolean("jhmcConfig.noboom.allow_crepper",false);
        allow_tntMinecart = plugin.config.getBoolean("jhmcConfig.noboom.allow_tnt_minecart",false);
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

