package com.megacraft.menuapi;

public class MenuUtils
{
    public static int[] calculateCoordinates(final int slot) {
        final int slotx = slot % 9 + 1;
        final int sloty = slot / 9 + 1;
        return new int[] { slotx, sloty };
    }
    
    public static int toSlotNumber(final Coordinates coordinates) {
        return (coordinates.getY() - 1) * 9 + (coordinates.getX() - 1);
    }
}
