package com.afyber.bolt;


import com.afyber.bolt.entities.ScrollingEnemy;
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
import com.afyber.bolt.gfx.ScrollingSprite;

import java.util.ArrayList;


public class Bolt extends Game implements InputProcessor {
	private SpriteBatch FrameBatch;
	private Sprite playerSprite;

	private static int screenWidth = 500;
	private static int screenHeight = 600;

	private boolean playerShoot = false;

	private int bulletTime = 0;

	private int cloudWaitTime = 0;


	// Used to hold all the player's bullets and all the clouds and all the enemies
	private ArrayList<ScrollingSprite> playerBullets = new ArrayList<ScrollingSprite>();

	private ArrayList<ScrollingSprite> clouds = new ArrayList<ScrollingSprite>();

	private ArrayList<ScrollingEnemy> enemies = new ArrayList<ScrollingEnemy>();


	boolean paused = false;

	private BitmapFont font;
	
	@Override
	public void create () {
		playerSprite = new Sprite("playerShip.png", 228, 100, 64, 64);

		FrameBatch = new SpriteBatch();

		font = new BitmapFont();

		// Set input handling functions to the ones defined in this class
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.6f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Don't execute any logic if paused
		if (!paused) {
			bulletTime--;
			cloudWaitTime--;

			for (int i = 0; i < playerBullets.size(); i++) {
				if (playerBullets.get(i).y - playerBullets.get(i).height > screenHeight) {
					playerBullets.get(i).texture.dispose();
					playerBullets.remove(i);
					if (i > 0) i--;
				}
			}

			if (bulletTime <= 0 ) {
				if (playerShoot) {
					playerBullets.add(new ScrollingSprite("playerBullet.png", playerSprite.x + 20, playerSprite.y + 16, 24, 48, -500f));
					bulletTime = 25;
				}
			}

			if (cloudWaitTime <= 0) {
				if ((int) (Math.random() * 200) == 1) {
					float size = (float) (Math.random() * 3) + 0.5f;
					int type = (int) (Math.random() * 3.0);

					String path = "cloud1.png";


					if (type == 1) {
						path = "cloud3.png";
					}
					if (type == 2) {
						path = "cloud2.png";
					}

					clouds.add(new ScrollingSprite(path, Math.round((float) Math.random() * screenWidth) - 30 * size, 30 * size, 40 * size, 33.3f * size));
					cloudWaitTime = 50;
				}
			}


			for (int i = 0; i < playerBullets.size(); i++) {
				playerBullets.get(i).scroll();
			}

			for (int i = 0; i < clouds.size(); i++) {
				clouds.get(i).scroll();
			}

			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).scroll();
			}

			playerSprite.moveTowardsTarget(10f);

			for (int i = 0; i < playerBullets.size(); i++) {
				for (int e = 0; e < enemies.size(); e++) {
					if (i < playerBullets.size()) {
						if (playerBullets.get(i).intersects(enemies.get(e)) && playerBullets.get(i).y < screenHeight) {
							enemies.get(e).hurt();
							playerBullets.get(i).texture.dispose();
							playerBullets.remove(i);
							if (e > 0) e--;
							if (i > 0) i--;
						}
					}
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				if (playerSprite.intersects(enemies.get(i))) {
					enemies.get(i).hurt();
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				if (enemies.get(i).health <= 0) {
					enemies.get(i).texture.dispose();
					enemies.remove(i);
					if (i > 0) i--;
				}
			}

		}

		// Draw the things!
		FrameBatch.begin();


		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).draw(FrameBatch);
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(FrameBatch);
		}

		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).draw(FrameBatch);
		}

		playerSprite.draw(FrameBatch);

		if (paused) {
			font.draw(FrameBatch, "Paused", screenWidth/2f-24, screenHeight-100);
		}

		FrameBatch.end();
	}
	
	@Override
	public void dispose () {
		playerSprite.texture.dispose();
		FrameBatch.dispose();
		font.dispose();

		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).texture.dispose();
		}

		for (int i = 0; i < clouds.size(); i++) {
			clouds.get(i).texture.dispose();
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).texture.dispose();
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
