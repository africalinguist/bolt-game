package com.afyber.bolt.entities;

import com.afyber.bolt.gfx.ScrollingSprite;
import com.badlogic.gdx.graphics.Texture;

public class Powerup extends ScrollingSprite {
    public String type;

    public Powerup(Texture texture, float x, float y, String type) {
        super(texture, x, y, 80f);
        this.type = type;
    }

    public Powerup(Texture texture, float x, float y, float width, float height, String type) {
        super(texture, x, y, width, height, 80f);
        this.type = type;
    }
}
