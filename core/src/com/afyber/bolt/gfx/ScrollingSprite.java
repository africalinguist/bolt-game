package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;


/** A class for sprites that only move up or down, like clouds
 *
 * @author afyber
 */
public class ScrollingSprite extends Sprite {
    protected float speed;

    public ScrollingSprite(String InternalPath, float x, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = 700;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float y, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float width, float height, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = 700;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
    }

    public ScrollingSprite(String InternalPath, float x, float y, float width, float height, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
    }

    public ScrollingSprite() {

    }

    /** Moves the sprite based on its speed
     *
     * @author afyber
     */
    public void scroll() {
        this.y -= speed * Gdx.graphics.getDeltaTime();
    }
}
