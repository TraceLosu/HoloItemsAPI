package com.strangeone101.holoitems.command;

import com.strangeone101.holoitems.CustomItem;
import com.strangeone101.holoitems.CustomItemRegistry;
import com.strangeone101.holoitems.EventContext;
import com.strangeone101.holoitems.HoloItemsPlugin;
import com.strangeone101.holoitems.Properties;
import com.strangeone101.holoitems.Property;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.stream.Collectors;

public class HoloItemsCommand implements CommandExecutor {

    //This entire command class is from the test plugin. Please feel free to scrap it.

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "You must provide a player to give the item to!");
                return true;
            }
        }

        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("debug")) {
                if (args.length == 1) {
                    sender.sendMessage("/holoitems debug cache");
                    sender.sendMessage("/holoitems debug item");
                    sender.sendMessage("/holoitems debug registry");
                } else if (args[1].equalsIgnoreCase("cache")) {
                    if (!EventContext.CACHED_POSITIONS_BY_SLOT.containsKey(sender)) {
                        sender.sendMessage("No cache found!");
                        return true;
                    }
                    sender.sendMessage("----- Cache -------");
                    for (Integer slot : EventContext.CACHED_POSITIONS_BY_SLOT.get(sender).keySet()) {
                        Set<MutableTriple<CustomItem, ItemStack, EventContext.Position>> set =
                                EventContext.CACHED_POSITIONS_BY_EVENT.get(sender).get(slot);
                        for (MutableTriple<CustomItem, ItemStack, EventContext.Position> triple : set) {
                            sender.sendMessage("[" + slot + "] " + triple.getLeft().getInternalName() + " = " + triple.getRight().toString());
                        }
                    }
                    sender.sendMessage("----- End cache -------");
                } else if (args[1].equalsIgnoreCase("registry")) {
                    for (String s : EventContext.getRegistryDebug()) {
                        sender.sendMessage(s);
                    }
                } else if (args[1].equalsIgnoreCase("item")) {
                    ItemStack stack = ((Player)sender).getInventory().getItemInMainHand();
                    if (!CustomItemRegistry.isCustomItem(stack)) {
                        sender.sendMessage("Not a custom item!");
                        return true;
                    }
                    CustomItem item = CustomItemRegistry.getCustomItem(stack);
                    if (item == null) {
                        sender.sendMessage("Custom item is not currently registered!");
                        sender.sendMessage("The item is currently has ID [" +
                                Properties.ITEM_ID.get(stack.getItemMeta().getPersistentDataContainer()) + "]");
                        return true;
                    }

                    sender.sendMessage("Item name: [" + item.getInternalName() + "]");
                    sender.sendMessage("Texture ID: [" + item.getInternalID() + "]");
                    sender.sendMessage("Unstackable: [" + !item.isStackable() + "]");
                    sender.sendMessage("Durability: [" + item.getDurability(stack) + "]");
                    sender.sendMessage("Max durability: [" + item.getMaxDurability() + "]");
                    sender.sendMessage("Properties: [" + String.join(", ",
                            item.getProperties().stream().map(property -> property.getPropertyName() + "="
                                    + property.get(stack.getItemMeta().getPersistentDataContainer()))
                                    .collect(Collectors.joining()) + "]"));
                }
            }
            return true;
        }

        int amount = CustomItemRegistry.getCustomItems().size();
        int rows = ((amount - 1) / 9) + 1;

        Inventory inv = Bukkit.createInventory(null, rows * 9, "HoloItems List");

        for (CustomItem ci : CustomItemRegistry.getCustomItems().values()) {
            inv.addItem(ci.buildStack((Player) sender));
        }

        ((Player)sender).openInventory(inv);

        return true;
    }
}
