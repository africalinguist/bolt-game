package com.afyber.bolt;

import com.badlogic.gdx.Game;

// Input stuff
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

// Graphics imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.afyber.bolt.gfx.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Bolt extends Game implements InputProcessor {
	SpriteBatch FrameBatch;
	Sprite playerSprite;

	private boolean playerShoot = false;

	private int bulletTime = 0;

	private ArrayList<Sprite> bullets = new ArrayList<Sprite>();

	private float playerSpeed = 10f;

	boolean paused = false;

	private BitmapFont font;
	
	@Override
	public void create () {
		playerSprite = new Sprite("/home/afyber/Documents/Pokeball 32x.png", 228, 100, 64, 64);

		FrameBatch = new SpriteBatch();

		font = new BitmapFont();

		// Set input handling functions to the ones defined in this class
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.5f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Don't execute any logic if paused
		if (!paused) {
			bulletTime++;

			if (bulletTime >= 1000) {
				if (playerShoot) {
					bullets.add(new Sprite("/home/afyber/Documents/Pokeball 32x.png", playerSprite.x + 16, playerSprite.y + 64, 32, 32));

					bulletTime = 0;
				}
			}

			for (int i = 0; i < bullets.size(); i++) {
				bullets.get(i).y += 500 * Gdx.graphics.getDeltaTime();
			}

			playerSprite.moveTowardsTarget(playerSpeed);
		}

		// Draw the things!
		FrameBatch.begin();

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(FrameBatch);
		}
		playerSprite.draw(FrameBatch);

		if (paused) {
			font.draw(FrameBatch, "Paused", 500/2-24, 600-100);
		}
		FrameBatch.end();
	}
	
	@Override
	public void dispose () {
		playerSprite.texture.dispose();
		FrameBatch.dispose();
		font.dispose();

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).texture.dispose();
		}
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	// For InputProcessor

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			if (!paused) {
				pause();
			}
			else {
				resume();
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		playerSprite.setTarget(screenX - playerSprite.width/2, playerSprite.y);
		playerShoot = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		playerSprite.setTarget(screenX - playerSprite.width/2, playerSprite.y);
		playerShoot = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		playerSprite.setTarget(screenX - playerSprite.width/2, playerSprite.y);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		playerSprite.setTarget(screenX - playerSprite.width/2, playerSprite.y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
