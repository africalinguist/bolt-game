package com.afyber.bolt.entities;

import com.afyber.bolt.gfx.ScrollingSprite;
import com.afyber.bolt.gfx.Sprite;
import com.badlogic.gdx.graphics.Texture;

import java.awt.Rectangle;


/** A class for enemies that only moves up or down
 *
 * @author afyber
 */
public class ScrollingEnemy extends ScrollingSprite {

    private Rectangle collisionBox;
    public int health;

    public String type;

    public ScrollingEnemy(Texture texture, float x, float speed) {
        super(texture, x, speed);
        this.health = 1;
    }

    public ScrollingEnemy(Texture texture, float x, float y, float speed) {
        super(texture, x, y, speed);
        this.health = 1;
    }

    public ScrollingEnemy(Texture texture, float x, float width, float height, float speed) {
        super(texture, x, width, height, speed);
        this.health = 1;
    }

    public ScrollingEnemy(Texture texture, float x, float y, float width, float height, float speed) {
        super(texture, x, y, width, height, speed);
        this.health = 1;
    }

    /** Reduces enemies health by one
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

    /** Checks for collision with another {@link ScrollingEnemy}
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

    public boolean intersects(ScrollingSprite other) {
        Rectangle rect1 = new Rectangle((int)other.x, (int)other.y, (int)other.width, (int)other.height);

        if (this.getCollisionBox() != null) {
            if (this.getCollisionBox().intersects(rect1)) {
                return true;
            }
        } else {
            Rectangle rect2 = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);

            if (rect2.intersects(rect1)) {
                return true;
            }
        }
        return false;
    }

    public boolean intersects(Sprite other) {
        Rectangle rect1 = new Rectangle((int)other.x, (int)other.y, (int)other.width, (int)other.height);

        if (this.getCollisionBox() != null) {
            if (this.getCollisionBox().intersects(rect1)) {
                return true;
            }
        } else {
            Rectangle rect2 = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);

            if (rect2.intersects(rect1)) {
                return true;
            }
        }
        return false;
    }
}
