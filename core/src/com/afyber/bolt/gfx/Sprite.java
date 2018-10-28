package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Rectangle;

/** A class for sprites and things that need to move around the screen
 *
 * @author afyber
 */
public class Sprite {
    public Texture texture;
    public float x;
    public float y;
    public float width;
    public float height;
    protected float targetX;
    protected float targetY;

    // Location of the sprite (i.e. North, SE, Center), overrides x and y, currently not in use
    String location;

    public Sprite(String InternalPath, float x, float y) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = texture.getWidth();
        this.height = texture.getHeight();
        this.targetX = x;
        this.targetY = y;
    }

    public Sprite(String InternalPath, float x, float y, float width, float height) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
    }

    // TODO: implement location
    public Sprite(String InternalPath, String location) {
        this.texture = new Texture(InternalPath);

    }

    public Sprite() {

    }

    /** Draws the texture at its x and y
     *
     * @param batch the {@link SpriteBatch} to draw onto
     *
     * @author afyber
     */
    public void draw(SpriteBatch batch) {
        draw(this.x, this.y, batch);
    }

    /** Draws the texture onto the given {@link SpriteBatch}
     *
     * @param x x to draw at
     * @param y y to draw at
     * @param batch the {@link SpriteBatch} to draw onto
     *
     * @author afyber
     */
    public void draw(float x, float y, SpriteBatch batch) {
        batch.draw(this.texture, x, y, this.width, this.height);
    }

    /** Sets the target
     *
     * @param x what to set targetX to
     * @param y what to set targetY to
     *
     * @author afyber
     */
    public void setTarget(float x, float y) {
        targetX = x;
        targetY = y;
    }

    /** Gets the targetX
     *
     * @return targetX
     *
     * @author afyber
     */
    public float getTargetX() {
        return targetX;
    }

    /** Gets the targetY
     *
     * @return targetY
     *
     * @author afyber
     */
    public float getTargetY() {
        return targetY;
    }

    /** Moves towards targetX and targetY
     *
     * @param speed how to quickly to move
     *
     * @author afyber
     */
    public void moveTowardsTarget(float speed) {
        float newX = x + (targetX - x) * speed * Gdx.graphics.getDeltaTime();
        float newY = y + (targetY - y) * speed * Gdx.graphics.getDeltaTime();
        x = newX;
        y = newY;

        if (x < 0) {
            x = 0;
        }
        if (x > 500 - this.texture.getWidth() * 4) {
            x = 500 - this.texture.getWidth() * 4;
        }
        if (y < 1) {
            y = 1;
        }
        if (y > 600 - this.texture.getHeight() * 4) {
            y = 600 - this.texture.getHeight() * 4;
        }
    }

    /** Checks if this sprite intersects with another based on width and height
     *
     * @param other the sprite to check for intersection
     * @return true if they intersect, false if else
     *
     * @author afyber
     */
    public boolean intersects(Sprite other) {
        Rectangle rect1 = new Rectangle((int)this.x, (int)this.y, (int)this.width, (int)this.height);
        Rectangle rect2 = new Rectangle((int)other.x, (int)other.y, (int)other.width, (int)other.height);

        if (rect1.intersects(rect2)) {
            return true;
        }
        else {
            return false;
        }
    }
}
