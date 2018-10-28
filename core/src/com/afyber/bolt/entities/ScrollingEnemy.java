package com.afyber.bolt.entities;

import com.afyber.bolt.gfx.ScrollingSprite;
import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

/** A class for enemies that only moves up or down
 *
 * @author afyber
 */
public class ScrollingEnemy extends ScrollingSprite {

    private Rectangle collisionBox;
    public int health;

    public ScrollingEnemy(String InternalPath, float x, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = 700;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
        this.health = 1;
    }

    public ScrollingEnemy(String InternalPath, float x, float y, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
        this.health = 1;
    }

    public ScrollingEnemy(String InternalPath, float x, float width, float height, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = 700;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
        this.health = 1;
    }

    public ScrollingEnemy(String InternalPath, float x, float y, float width, float height, float speed) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
        this.speed = speed;
        this.health = 1;
    }

    /** Reduces enemies health by one
     *
     * @author afyber
     */
    public void hurt() {
        this.health--;
    }
}
