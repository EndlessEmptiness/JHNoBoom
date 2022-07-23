package org.eu.jhmc.dev.noboom;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class cmdNoboom implements TabCompleter, CommandExecutor {
    private static final List<String> tc = new ArrayList<>();
    private static boolean noboom_cmd_onlyServer = false;
    public static void init_cfg(){
        noboom_cmd_onlyServer = plugin.config.getBoolean("jhmcConfig.noboom.cmd_onlyServer",false);
    }
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof  Player && noboom_cmd_onlyServer) {
            sender.sendMessage(ChatColor.RED+"此指令已被禁用 仅后台可执行");
            return true;
        }
        if(sender instanceof Player)
            if((!sender.hasPermission("noboom.noboom"))) {
                sender.sendMessage("No Permission");
                return true;
            }

        if(args.length != 1) return false;
        if(Objects.equals(args[0], "true")) {
            plugin.NoBoom = true;
        }
        else if(Objects.equals(args[0], "false")) {
            plugin.NoBoom = false;
        }
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player)
            if((!sender.hasPermission("noboom.noboom"))) {
                sender.sendMessage("No Permission");
                return null;
            }
        if (args.length >= 2) {
            return null;
        }

        return tc;

    }

    public static void setTabCompleter() {
        tc.add("true");
        tc.add("false");
    }
}
