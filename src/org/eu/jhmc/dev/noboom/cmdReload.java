package org.eu.jhmc.dev.noboom;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.eu.jhmc.dev.noboom.plugin.mcplugin;

public class cmdReload implements TabCompleter, CommandExecutor {
    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player)
            if((!sender.isOp()) && (!sender.hasPermission("noboom.reload"))) {
                sender.sendMessage("No Permission");
                return true;
            }

        if(args.length != 0) return false;
        mcplugin.reloadConfig();
        EventListener.init_cfg();
        cmdNoboom.init_cfg();
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        return null;
    }
}
