package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Sprite {
    public Texture texture;
    public float x;
    public float y;
    public float width;
    public float height;
    float targetX;
    float targetY;

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

    public Sprite(String InternalPath, float x, float y, int width, int height) {
        this.texture = new Texture(InternalPath);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetX = x;
        this.targetY = y;
    }

    public Sprite(String InternalPath, String location) {
        this.texture = new Texture(InternalPath);

    }

    public void draw(SpriteBatch batch) {
        draw(this.x, this.y, batch);
    }

    public void draw(float x, float y, SpriteBatch batch) {
        batch.draw(this.texture, x, y, this.width, this.height);
    }

    public void setTarget(float x, float y) {
        targetX = x;
        targetY = y;
    }

    public float getTargetX() {
        return targetX;
    }

    public float getTargetY() {
        return targetY;
    }

    public void moveTowardsTarget(float speed) {
        float newX = x + (targetX - x) * speed * Gdx.graphics.getDeltaTime();
        float newY = y + (targetY - y) * speed * Gdx.graphics.getDeltaTime();
        x = newX;
        y = newY;

        if (x < 0) {
            x = 0;
        }
        if (x > 500 - this.texture.getWidth() * 2) {
            x = 500 - this.texture.getWidth() * 2;
        }
        if (y < 1) {
            y = 1;
        }
        if (y > 600 - this.texture.getHeight() * 2) {
            y = 600 - this.texture.getHeight() * 2;
        }
    }
}
