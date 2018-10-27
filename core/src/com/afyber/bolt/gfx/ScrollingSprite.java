package com.afyber.bolt.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ScrollingSprite extends Sprite {
    private float speed;

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

    public void scroll() {
        this.y -= speed * Gdx.graphics.getDeltaTime();
    }
}
