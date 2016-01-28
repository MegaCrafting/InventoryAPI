package com.megacraft.menuapi.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.HumanEntity;
import java.util.List;

import com.megacraft.megacraftapi.MegaCraftAPI;
import com.megacraft.menuapi.Menu;

import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.Listener;

public class MenuCloseAction implements Listener
{
    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        final Menu menu = MegaCraftAPI.i().getMenuRegistry().getByInventory(event.getInventory());
        final List<HumanEntity> viewers = (List<HumanEntity>)event.getViewers();
        if (menu != null) {
            viewers.remove(event.getPlayer());
            if (viewers.size() == 0) {
                menu.getObjects().clear();
                menu.getInventory().clear();
                MegaCraftAPI.i().getMenuRegistry().deregister(menu);
            }
        }
    }
}
