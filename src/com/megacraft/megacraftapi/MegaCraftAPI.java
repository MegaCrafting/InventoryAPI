package com.megacraft.megacraftapi;

import org.bukkit.ChatColor;

import org.bukkit.Bukkit;

import com.megacraft.menuapi.MenuRegistry;
import com.megacraft.menuapi.listener.MenuActions;
import com.megacraft.menuapi.listener.MenuCloseAction;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MegaCraftAPI extends JavaPlugin {
    private static MenuRegistry menuRegistry;
    private static MegaCraftAPI plugin;
    
    public void onEnable() {
    	MegaCraftAPI.plugin = this;
    	MegaCraftAPI.menuRegistry = new MenuRegistry();
    	MegaCraftAPI.plugin.getServer().getPluginManager().registerEvents((Listener)new MenuActions(), (Plugin)this);
    	MegaCraftAPI.plugin.getServer().getPluginManager().registerEvents((Listener)new MenuCloseAction(), (Plugin)this);
        Bukkit.getConsoleSender().sendMessage("Initializing " + ChatColor.DARK_AQUA + ChatColor.BOLD + "MegaCraft" + ChatColor.RESET + "'s " + ChatColor.YELLOW + "InventoryAPI. All libraries loaded." + ChatColor.RESET);
    }
    
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.getLogger().info(String.valueOf(pdfFile.getName()) + " v" + pdfFile.getVersion() + " has been disabled!");
    }
    
    public MenuRegistry getMenuRegistry() {
        return MegaCraftAPI.menuRegistry;
    }
    
    public static MegaCraftAPI i() {
        return MegaCraftAPI.plugin;
    }
    
    
}
