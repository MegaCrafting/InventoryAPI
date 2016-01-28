package com.megacraft.menuapi;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.Sound;

public class GUISound
{
    private Sound sound;
    private float volume;
    private float pitch;
    private boolean playOnClick;
    
    public GUISound(final Sound sound) {
        this(sound, 1.0f, 1.0f, true);
    }
    
    public GUISound(final Sound sound, final float volume, final float pitch) {
        this(sound, volume, pitch, true);
    }
    
    public GUISound(final Sound sound, final float volume, final float pitch, final boolean playOnClick) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.playOnClick = playOnClick;
    }
    
    public Sound getSound() {
        return this.sound;
    }
    
    public void setSound(final Sound sound) {
        this.sound = sound;
    }
    
    public float getVolume() {
        return this.volume;
    }
    
    public void setVolume(final float volume) {
        this.volume = volume;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public boolean getPlayOnClick() {
        return this.playOnClick;
    }
    
    public void setPlayOnClick(final boolean playOnClick) {
        this.playOnClick = playOnClick;
    }
    
    public void playGUISound(final Player player) {
        this.playGUISound(player.getLocation(), player);
    }
    
    public void playGUISound(final Location loc) {
        this.playGUISound(loc, null);
    }
    
    public void playGUISound(final Location loc, final Player player) {
        if (player == null) {
            loc.getWorld().playSound(loc, this.sound, this.pitch, this.volume);
        }
        else {
            player.playSound(loc, this.sound, this.pitch, this.volume);
        }
    }
}
