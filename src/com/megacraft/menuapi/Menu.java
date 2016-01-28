package com.megacraft.menuapi;

import java.util.Map;
import org.bukkit.entity.Player;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import com.megacraft.megacraftapi.MegaCraftAPI;

import org.bukkit.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import java.util.HashMap;

public class Menu
{
    private HashMap<Object, Object> metadata;
    private Inventory inv;
    private HashMap<Integer, MenuObject> objects;
    
    public Menu(final Inventory inv) {
    	MegaCraftAPI.i().getMenuRegistry().register(this);
        this.objects = new HashMap<Integer, MenuObject>();
        this.inv = inv;
        this.metadata = new HashMap<Object, Object>();
    }
    
    public Inventory getInventory() {
        return this.inv;
    }
    
    public void setInventory(final Inventory inv) {
        this.objects.clear();
        this.inv = inv;
    }
    
    public MenuObject getItemAt(final Coordinates coordinates) {
        return this.equals(coordinates.getMenu()) ? this.objects.get(coordinates.asSlotNumber()) : null;
    }
    
    public void setMenuObjectAt(final Coordinates coordinates, final MenuObject menuObject) {
        if (menuObject.getCoordinates() != null && this.objects.containsKey(menuObject.getCoordinates().asSlotNumber())) {
            this.objects.remove(menuObject.getCoordinates().asSlotNumber());
        }
        this.objects.put(coordinates.asSlotNumber(), menuObject);
        menuObject.setCoordinates(coordinates);
        final int slot = coordinates.asSlotNumber();
        if (slot >= this.inv.getSize() || slot < 0) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Unreachable coordinates \"(" + coordinates.getX() + ", " + coordinates.getY() + ")\"! These coordinates measure to " + slot + " which cannot be mapped on an inventory with a size of " + this.inv.getSize() + "." + ChatColor.RESET);
            throw new IllegalArgumentException();
        }
        this.inv.setItem(slot, menuObject.toItemStack());
    }
    
    public void removeMenuObjectAt(final Coordinates coordinates) {
        coordinates.getMenu().getItemAt(coordinates).setCoordinates(null);
        this.inv.setItem(coordinates.asSlotNumber(), (ItemStack)null);
    }
    
    public void addMenuObject(final MenuObject... menuObject) {
        for (final MenuObject me : menuObject) {
            if (this.inv.firstEmpty() != -1) {
                this.setMenuObjectAt(new Coordinates(this, this.inv.firstEmpty()), me);
            }
        }
    }
    
    @Deprecated
    public void close() {
        this.objects.clear();
        this.inv.clear();
        MegaCraftAPI.i().getMenuRegistry().deregister(this);
        for (final HumanEntity viewer : this.inv.getViewers()) {
            viewer.closeInventory();
        }
    }
    
    public void clear() {
        this.objects.clear();
        this.inv.clear();
    }
    
    public void close(final Player p) {
        p.closeInventory();
    }
    
    public void openForPlayer(final Player p) {
        p.openInventory(this.inv);
    }
    
    public MenuObject getItemByItemStack(final ItemStack currentItem) {
        for (final Map.Entry<Integer, MenuObject> entry : this.objects.entrySet()) {
            if (entry.getValue().toItemStack().equals((Object)currentItem)) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    public HashMap<Integer, MenuObject> getObjects() {
        return this.objects;
    }
    
    public HashMap<Object, Object> getMetadata() {
        return this.metadata;
    }
    
    public void setMetadata(final HashMap<Object, Object> metadata) {
        this.metadata = metadata;
    }
}
