package com.megacraft.menuapi;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public interface ActionListener
{
    void onClick(final ClickType p0, final MenuObject p1, final Player p2);
}
