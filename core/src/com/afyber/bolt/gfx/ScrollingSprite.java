package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;


/** A class for sprites that only move up or down, like clouds
 *
 * @author afyber
 */
public class ScrollingSprite extends Sprite {
    protected float speed;

    public ScrollingSprite(String InternalPath, float x, float speed) {
        super(InternalPath, x, 700);
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float y, float speed) {
        super(InternalPath, x, y);
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float width, float height, float speed) {
        super(InternalPath, x, 700f, width, height);
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float y, float width, float height, float speed) {
        super(InternalPath, x, y, width, height);
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
