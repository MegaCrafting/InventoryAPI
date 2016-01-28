package com.megacraft.menuapi;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import org.bukkit.Material;

import java.util.HashMap;
import org.bukkit.inventory.ItemStack;

public class MenuObject
{
    private ItemStack item;
    private Coordinates coordinates;
    private ActionListener actionListener;
    private HashMap<Object, Object> metadata;
    private GUISound sound;
    
    public void setIcon(final ItemStack holder) {
        this.item = holder;
        this.update();
    }
    
    public void setIcon(final Material icon, final short data, final String name, final List<String> tooltip) {
        this.item = new ItemStack(icon, 1, (short)data);
        final ItemMeta meta = this.item.getItemMeta();
        meta.setLore((List)tooltip);
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        this.update();
    }
    
    public MenuObject(final ItemStack holder) {
        this.metadata = new HashMap<Object, Object>();
        if (holder == null) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "The ItemStack used as a menu object was null." + ChatColor.RESET);
            throw new IllegalArgumentException();
        }
        this.item = holder;
        this.coordinates = null;
        this.actionListener = null;
    }
    
    public MenuObject(final Material icon, final short data, final String name, final List<String> tooltip) {
        this.metadata = new HashMap<Object, Object>();
        this.item = new ItemStack(icon, 1, (short)data);
        final ItemMeta meta = this.item.getItemMeta();
        meta.setLore((List)tooltip);
        meta.setDisplayName(name);
        this.item.setItemMeta(meta);
        this.coordinates = null;
        this.actionListener = null;
    }
    
    public ItemStack toItemStack() {
        return this.item;
    }
    
    public Coordinates getCoordinates() {
        return this.coordinates;
    }
    
    public void setCoordinates(final Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    
    public void setActionListener(final ActionListener actionListener) {
        this.actionListener = actionListener;
    }
    
    public ActionListener getActionListener() {
        return this.actionListener;
    }
    
    public void update() {
        this.coordinates.getMenu().getInventory().setItem(this.coordinates.asSlotNumber(), this.toItemStack());
    }
    
    public HashMap<Object, Object> getMetadata() {
        return this.metadata;
    }
    
    public void setMetadata(final HashMap<Object, Object> metadata) {
        this.metadata = metadata;
    }
    
    public Menu getMenu() {
        return this.getCoordinates().getMenu();
    }
    
    public void setGUISound(final GUISound sound) {
        this.sound = sound;
    }
    
    public GUISound getGUISound() {
        return this.sound;
    }
}
