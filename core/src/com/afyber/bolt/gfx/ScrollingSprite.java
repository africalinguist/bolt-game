package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/** A class for sprites that only move up or down, like clouds
 *
 * @author afyber
 */
public class ScrollingSprite extends Sprite {
    private float speed;

    public ScrollingSprite(Texture texture, float x, float speed) {
        super(texture, x, 700);
        this.speed = speed;
    }

    public ScrollingSprite(Texture texture, float x, float y, float speed) {
        super(texture, x, y);
        this.speed = speed;
    }

    public ScrollingSprite(Texture texture, float x, float width, float height, float speed) {
        super(texture, x, 700f, width, height);
        this.speed = speed;
    }

    public ScrollingSprite(Texture texture, float x, float y, float width, float height, float speed) {
        super(texture, x, y, width, height);
        this.speed = speed;
    }

    /** Moves the sprite based on its speed
     *
     * @author afyber
     */
    public void scroll() {
        this.y -= speed * Gdx.graphics.getDeltaTime();
    }
}
