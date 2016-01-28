package com.megacraft.menuapi;

public class Coordinates
{
    private Menu menu;
    private int x;
    private int y;
    
    public Coordinates(final Menu menu, final int x, final int y) {
        this.menu = menu;
        this.x = x;
        this.y = y;
    }
    
    public Coordinates(final Menu menu, final int slot) {
        this.menu = menu;
        this.x = MenuUtils.calculateCoordinates(slot)[0];
        this.y = MenuUtils.calculateCoordinates(slot)[1];
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public Menu getMenu() {
        return this.menu;
    }
    
    public int asSlotNumber() {
        return MenuUtils.toSlotNumber(this);
    }
}
