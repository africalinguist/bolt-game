package com.afyber.bolt.entities;

import com.afyber.bolt.gfx.Sprite;

import java.awt.Rectangle;

public class Player extends Sprite {

    private Rectangle collisionBox;
    public int health;

    public Player() {
        super("playerShip.png", 228, 100);
        this.health = 3;
    }

    public Player(Rectangle collisionBox) {
        super("playerShip.png", 228, 100);
        this.collisionBox = collisionBox;
        this.health = 3;
    }

    public Player(int x, int y) {
        super("playerShip.png", x, y);
        this.health = 3;
    }

    public Player(int x, int y, Rectangle collisionBox) {
        super("playerShip.png", x, y);
        this.collisionBox = collisionBox;
        this.health = 3;
    }

    public Player(int x, int y, int width, int height) {
        super("playerShip.png", x, y, width, height);
        this.health = 3;
    }

    public Player(int x, int y, int width, int height, Rectangle collisionBox) {
        super("playerShip.png", x, y, width, height);
        this.collisionBox = collisionBox;
        this.health = 3;
    }

    /** Reduces players health by one
     *
     * @author afyber
     */
    public void hurt() {
        this.health--;
    }

    public Rectangle getCollisionBox() {
        return new Rectangle((int)this.x + this.collisionBox.x, (int)this.y + this.collisionBox.y, (int)this.collisionBox.width, (int)this.collisionBox.height);
    }

    public void setCollisionBox(Rectangle rect) {
        this.collisionBox = rect;
    }

    public boolean isDead() {
        if (health < 1) {
            return true;
        } else {
            return false;
        }
    }

    /** Checks for collision with a {@link ScrollingEnemy}
     *
     * @author afyber
     */
    public boolean intersects(ScrollingEnemy other) {
        if (other.getCollisionBox() != null && this.getCollisionBox() != null) {
            if (this.getCollisionBox().intersects(other.getCollisionBox())) {
                return true;
            }
        } else {
            Rectangle rect1 = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
            Rectangle rect2 = new Rectangle((int)other.x, (int)other.y, (int)other.width, (int)other.height);

            if (rect1.intersects(rect2)) {
                return true;
            }
        }
        return false;
    }
}
