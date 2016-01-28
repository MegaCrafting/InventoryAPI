package com.megacraft.menuapi;

import org.bukkit.inventory.Inventory;
import java.util.ArrayList;
import java.util.List;

public class MenuRegistry
{
    private List<Menu> menus;
    
    public MenuRegistry() {
        this.menus = new ArrayList<Menu>();
    }
    
    public List<Menu> getMenus() {
        return this.menus;
    }
    
    public void register(final Menu inv) {
        this.menus.add(inv);
    }
    
    public void deregister(final Menu inv) {
        this.menus.remove(inv);
    }
    
    public Menu getByInventory(final Inventory inventory) {
        for (final Menu menu : this.menus) {
            if (menu.getInventory().equals(inventory)) {
                return menu;
            }
        }
        return null;
    }
}
