package org.eu.jhmc.dev.noboom;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.logging.Logger;

public class plugin extends JavaPlugin {
    public static JavaPlugin mcplugin;
    public static Logger logger;
    public static FileConfiguration config;

    public static Server server;

    public static boolean NoBoom = true;

    @Override
    @ParametersAreNonnullByDefault
    public void onEnable(){
        cmdNoboom.setTabCompleter();
        EventListener.init_cfg();


        if (Bukkit.getPluginCommand("noboom") != null) {
            Bukkit.getPluginCommand("noboom").setExecutor(new cmdNoboom());
            Bukkit.getPluginCommand("noboom").setTabCompleter(new cmdNoboom());
            logger.info("command /noboom registered");
        } else logger.warning("Unable to register command /noboom");
        if (Bukkit.getPluginCommand("noboom_reload") != null) {
            Bukkit.getPluginCommand("noboom_reload").setExecutor(new cmdReload());
            Bukkit.getPluginCommand("noboom_reload").setTabCompleter(new cmdReload());
            logger.info("command /noboom_reload registered");
        } else logger.warning("Unable to register command /noboom_reload");

        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onDisable(){
        saveConfig();
    }

    @Override
    @ParametersAreNonnullByDefault
    public void onLoad(){
        saveDefaultConfig();

        logger = getLogger();

        config = getConfig();

        mcplugin = this;

        server = getServer();
    }
}
