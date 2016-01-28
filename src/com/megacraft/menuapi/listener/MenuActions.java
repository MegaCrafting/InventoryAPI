package com.megacraft.menuapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.megacraft.megacraftapi.MegaCraftAPI;
import com.megacraft.menuapi.Coordinates;
import com.megacraft.menuapi.GUISound;
import com.megacraft.menuapi.Menu;
import com.megacraft.menuapi.MenuObject;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;

public class MenuActions implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent event) {
        if (event.getCurrentItem() == null) {
            return;
        }
        final Menu menu = MegaCraftAPI.i().getMenuRegistry().getByInventory(event.getInventory());
        if (menu == null) {
            return;
        }
        event.setCancelled(true);
        final MenuObject menuObject = menu.getItemAt(new Coordinates(menu, event.getSlot()));
        if (menuObject == null) {
            return;
        }
        if (menuObject.getActionListener() == null) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                final GUISound sound = menuObject.getGUISound();
                if (menuObject.getGUISound() != null && sound.getPlayOnClick()) {
                    sound.playGUISound((Player)event.getWhoClicked());
                }
                menuObject.getActionListener().onClick(event.getClick(), menuObject, (Player)event.getWhoClicked());
            }
        }.runTask((Plugin)MegaCraftAPI.i());
    }
}
