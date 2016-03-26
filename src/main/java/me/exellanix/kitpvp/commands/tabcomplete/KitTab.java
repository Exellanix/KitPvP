package me.exellanix.kitpvp.commands.tabcomplete;

import me.exellanix.kitpvp.KitPvP;
import me.exellanix.kitpvp.kits.Kit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mac on 3/25/2016.
 */
public class KitTab implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        ArrayList<String> tab = new ArrayList<>();
        if (strings.length == 1) {
            for (Kit k : KitPvP.getSingleton().getKitManager().getRegisteredKits()) {
                if (k.getName().toLowerCase().startsWith(strings[0].toLowerCase())) {
                    tab.add(k.getName());
                }
            }
        }
        return tab;
    }
}
