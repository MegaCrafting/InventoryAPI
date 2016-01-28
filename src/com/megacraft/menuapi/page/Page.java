package com.megacraft.menuapi.page;

import java.util.Arrays;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.megacraft.menuapi.Coordinates;
import com.megacraft.menuapi.Menu;
import com.megacraft.menuapi.MenuObject;

public class Page {	
	
	public static ItemStack item;
	
	public static void NameList(Player player, List<String> Names, String InvName, Integer Page, List<String> Materials, List<String> Names1, List<Integer> InvSlot) {
		Inventory inventory = Bukkit.createInventory(player, 54, InvName);
		
		int i = 45*Page;
		
		int j = i+45;
		
		Menu menu = new Menu(inventory);
		menu.openForPlayer(player);
				
		if(Names.size() >= j) {
			if(i != 0) {
				NameChooser("Previous", "MHF_ArrowLeft");
				inventory.setItem(45, item);
			}
			for (i = i; i < j; i++) {
				MenuObject menuObject = new MenuObject(Material.NAME_TAG, (byte) 0, "§a" + Names.get(i), Arrays.asList(""));
				menu.addMenuObject(menuObject);
			}
			NameChooser("Next", "MHF_ArrowRight");
			inventory.setItem(53, item);
		} else if(Names.size() < j) {
			j = i+(Names.size()-i);
			for (i = i; i < j; i++) {
				MenuObject menuObject = new MenuObject(Material.NAME_TAG, (byte) 0, "§a" + Names.get(i), Arrays.asList(""));
				menu.addMenuObject(menuObject);
			}
			if(i != 0) {
				NameChooser("Previous", "MHF_ArrowLeft");
				inventory.setItem(45, item);
			}
		}
		
		if(InvSlot == null || Names1 == null || Materials == null) {
			return;
		} else if(InvSlot.size() > 7 || Names1.size() > 7 || Materials.size() > 7) {
			System.out.println("The extra items cannot exceed 7 as they will exit the inventory.");
			return;
		} else if(InvSlot.size() > Names1.size() || InvSlot.size() > Materials.size()) {
			System.out.println("ALl lists need to contain the same amount of values.");
			return;
		} else {
			for (int k = 0; k < Names1.size(); k++) {
			    Material item = Material.getMaterial(Materials.get(k).toUpperCase());
				MenuObject menuObject = new MenuObject(item, (byte) 0, Names1.get(k), Arrays.asList(""));
				menu.setMenuObjectAt(new Coordinates(menu, InvSlot.get(k), 6), menuObject);
			}	
		}
		
		
		for (i = i; i < j; i++) {
			MenuObject menuObject = new MenuObject(Material.NAME_TAG, (byte) 0, "§a" + Names.get(i), Arrays.asList(""));
			menu.addMenuObject(menuObject);
		}		
	}
	
	public static void NameChooser(String name, String owner) {
		@SuppressWarnings("deprecation")
		ItemStack Arrow = new ItemStack(397, 1, (short) 3);
		
		ItemMeta ArrowUpMeta = Arrow.getItemMeta();
		ArrowUpMeta.setDisplayName(name);
		Arrow.setItemMeta(ArrowUpMeta);
		
		SkullMeta ArrowUp = (SkullMeta) Arrow.getItemMeta();
		ArrowUp.setOwner(owner);
		Arrow.setItemMeta(ArrowUp);
		
		item = Arrow;
	}
	
}
