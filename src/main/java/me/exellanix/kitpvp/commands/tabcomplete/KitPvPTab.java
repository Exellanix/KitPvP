package me.exellanix.kitpvp.commands.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 3/25/2016.
 */
public class KitPvPTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("kitpvp")) {
            ArrayList<String> list = new ArrayList<>();
            if (strings.length == 1) {
                if ("help".startsWith(strings[0].toLowerCase())) {
                    list.add("help");
                }
                if ("reload".startsWith(strings[0].toLowerCase())) {
                    list.add("reload");
                }
            }
            return list;
        }
        return null;
    }
}
