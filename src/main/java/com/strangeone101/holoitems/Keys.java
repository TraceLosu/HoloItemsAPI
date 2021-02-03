package com.strangeone101.holoitems;

import org.bukkit.NamespacedKey;

public class Keys {

    //Generic Keys

    /** The key for storing custom item IDs. Should be <strong>STRING</strong> */
    public final NamespacedKey CUSTOM_ITEM_ID = new NamespacedKey(HoloItemsPlugin.INSTANCE, "customitemid");
    /** The key for storing custom item owner UUIDs. Should be <strong>UUID</strong> */
    public final NamespacedKey CUSTOM_ITEM_OWNER = new NamespacedKey(HoloItemsPlugin.INSTANCE, "itemowner");
    /** The key for storing custom item owner UUIDs. Should be <strong>STRING</strong> */
    public final NamespacedKey CUSTOM_ITEM_OWNER_NAME = new NamespacedKey(HoloItemsPlugin.INSTANCE, "itemownername");
    /** The key for storing when the item can be used next. Should be <strong>LONG</strong> */
    public final NamespacedKey CUSTOM_ITEM_COOLDOWN = new NamespacedKey(HoloItemsPlugin.INSTANCE, "itemcooldown");


    //Rushia Shield

    /** The key for how many mobs are stored. Should be <strong>INT</strong> */
    public final NamespacedKey RUSHIA_SHIELD_COUNT = new NamespacedKey(HoloItemsPlugin.INSTANCE, "rushia_shield_count");
    /** The key for the types of mobs. Should be <strong>STRING</strong> */
    public final NamespacedKey RUSHIA_SHIELD_MOBS = new NamespacedKey(HoloItemsPlugin.INSTANCE, "rushia_shield_mobs");
}
