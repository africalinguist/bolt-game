package com.afyber.bolt;


import com.afyber.bolt.entities.Player;
import com.afyber.bolt.entities.Powerup;
import com.afyber.bolt.entities.ScrollingEnemy;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Game;

// Input stuff
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

// Graphics imports
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;

import com.afyber.bolt.gfx.Sprite;
import com.afyber.bolt.gfx.ScrollingSprite;

import java.awt.Rectangle;
import java.util.ArrayList;


public class Bolt extends Game implements InputProcessor {
	private SpriteBatch FrameBatch;
	private Player player;

	private static int screenWidth = 512;
	private static int screenHeight = 600;

	private boolean playerShoot = false;

	private int bulletTime = 0;

	private int cloudWaitTime = 0;

	private int enemyTime = 40;

	private int enemiesDead = 0;

	private int mothershipHealth = 60;

	// Used to hold all the player's bullets and all the clouds and all the enemies and the powerups
	private ArrayList<ScrollingSprite> playerBullets = new ArrayList<ScrollingSprite>();

	private ArrayList<ScrollingSprite> clouds = new ArrayList<ScrollingSprite>();

	private ArrayList<ScrollingEnemy> enemies = new ArrayList<ScrollingEnemy>();

	private ArrayList<Powerup> powerups = new ArrayList<Powerup>();

	private String activePowerups = "";

	private AssetManager assetManager;

	private Sprite heart;

	private Sprite progressBar;

	private boolean paused = false;

	private BitmapFont font;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		loadAssets(assetManager);

		player = new Player(228, 100, 64, 64, new Rectangle(6, 6, 52, 52));

		heart = new Sprite((Texture)assetManager.get("heart.png"), screenWidth - 38, screenHeight - 44, 32, 32);

		progressBar = new Sprite((Texture)assetManager.get("progressBarSection.png"), 0, screenHeight - 6, screenWidth / mothershipHealth, 12);

		FrameBatch = new SpriteBatch();

		font = new BitmapFont();

		// needed for... stuff
		ScrollingEnemy thing = new ScrollingEnemy((Texture)assetManager.get("icon.png"), -64, 0, 0);
		thing.setCollisionBox(new Rectangle(0, 0, 64, 64));
		enemies.add(thing);


		// Set input handling functions to the ones defined in this class
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.6f, 1.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Don't execute any logic if paused or if the player is dead or if the ship is dead
		if (!paused && !player.isDead() && mothershipHealth > 0) {
			bulletTime--;
			cloudWaitTime--;
			enemyTime--;


			if (bulletTime <= 0 ) {
				if (playerShoot) {
					if (!activePowerups.contains("doubleShoot")) {
						playerBullets.add(new ScrollingSprite((Texture)assetManager.get("playerBullet.png"), player.x + 20, player.y + 16, 24, 48, -500f));
					} else {
						playerBullets.add(new ScrollingSprite((Texture)assetManager.get("playerBullet.png"), player.x + 4, player.y + 16, 24, 48, -500f));
						playerBullets.add(new ScrollingSprite((Texture)assetManager.get("playerBullet.png"), player.x + 36, player.y + 16, 24, 48, -500f));
					}

					if (!activePowerups.contains("speedShoot")) {
						bulletTime = 25;
					} else {
						bulletTime = 10;
					}
				}
			}

			if (cloudWaitTime <= 0) {
				if ((int) (Math.random() * 200) == 1) {
					float size = (float) (Math.random() * 3) + 0.5f;
					int type = (int) (Math.random() * 2.4);

					String path = "cloud1.png";


					if (type == 1) {
						path = "cloud3.png";
					}
					if (type == 2) {
						path = "cloud2.png";
					}

					clouds.add(new ScrollingSprite((Texture)assetManager.get(path), Math.round((float) Math.random() * screenWidth) - 30 * size, 30 * size, 40 * size, 33.3f * size));
					cloudWaitTime = 50;
				}
			}

			if (enemyTime <= 0) {
				ScrollingEnemy newEnemy = new ScrollingEnemy((Texture)assetManager.get("drone1.png"), 12 + ((int)(Math.random() * (screenWidth - 64) / 64) * 64), 64, 64, 150f + enemiesDead);
				newEnemy.setCollisionBox(new Rectangle(4, 8, 56, 56));

				int type = (int) (Math.random() * 3.4);

				if (type == 1) {
					newEnemy = new ScrollingEnemy((Texture)assetManager.get("heavy1.png"), 24 + ((int)(Math.random() * (screenWidth - 96) / 96) * 96), 96, 96, 100f + (enemiesDead / 3f));
					newEnemy.setCollisionBox(new Rectangle(8, 16, 80, 74));
					newEnemy.health = 3;
				}

				if (type == 2) {
					newEnemy = new ScrollingEnemy((Texture)assetManager.get("ship1.png"), 12 + ((int)(Math.random() * (screenWidth - 64) / 64) * 64), 64, 64, 200f + (enemiesDead / 4f));
					newEnemy.setCollisionBox(new Rectangle(4, 8, 56, 48));
					newEnemy.health = 2;
				}

				if (newEnemy.x > screenWidth) {
					newEnemy.x = screenWidth - 76;
				}
				enemies.add(newEnemy);
				enemyTime = 30 + (int)(Math.random() * 75);

			}

			for (ScrollingSprite bullet: playerBullets) {
				bullet.scroll();
			}

			for (ScrollingSprite cloud: clouds) {
				cloud.scroll();
			}

			player.moveTowardsTarget(10f);


			for (int i = 0; i < playerBullets.size(); i++) {
				playerBullets.get(i).scroll();

				if (playerBullets.get(i).y > screenHeight) {
					playerBullets.remove(i);
					if (i > 0) i--;
				}
			}

			for (ScrollingSprite cloud: clouds) {
				cloud.scroll();
			}

			for (ScrollingSprite powerup: powerups) {
				powerup.scroll();
			}

			for (int e = 0; e < enemies.size(); e++) {
				enemies.get(e).scroll();

				if (player.intersects(enemies.get(e))) {
					enemies.get(e).hurt();
					player.hurt();
				}

				if (enemies.get(e).health <= 0) {
					randomLoot(enemies.get(e));
					enemies.remove(e);
					enemiesDead++;
					if (e > 0) e--;
				}

				if (enemies.get(e).y + 96 < 0) {
					mothershipHealth -= enemies.get(e).health;
					enemies.remove(e);
					if (e > 0) e--;
				}
			}

			for (int p = 0; p < powerups.size(); p++) {
				if (powerups.get(p).intersects(player)) {
					activePowerups += powerups.get(p).type;
					powerups.remove(p);
					if (p > 0) p--;
				}
			}

			for (int e = 0; e < enemies.size(); e++) {
				for (int p = 0; p < playerBullets.size(); p++) {
					if (enemies.get(e).intersects(playerBullets.get(p)) && playerBullets.get(p).y < screenHeight) {
						enemies.get(e).hurt();
						playerBullets.remove(p);
						if (p > 0) p--;
						if (e > 0) e--;
					}
				}
			}
		}

		// Draw the things!
		FrameBatch.begin();


		for (ScrollingSprite cloud: clouds) {
			cloud.draw(FrameBatch);
		}

		for (ScrollingSprite bullet: playerBullets) {
			bullet.draw(FrameBatch);
		}

		for (ScrollingEnemy enemy: enemies) {
			enemy.draw(FrameBatch);
		}

		for (ScrollingSprite powerup: powerups) {
			powerup.draw(FrameBatch);
		}


		if (player.health > 0) {
			heart.draw(FrameBatch);
			if (player.health == 2) {
				heart.draw(heart.x - 38, heart.y, FrameBatch);
			}
			if (player.health == 3) {
				heart.draw(heart.x - 76, heart.y, FrameBatch);
				heart.draw(heart.x - 38, heart.y, FrameBatch);
			}
		}

		if (mothershipHealth > 0) {
			for (int i = 0; i < mothershipHealth + mothershipHealth / 10; i++) {
				progressBar.draw((i * progressBar.width), progressBar.y, FrameBatch);
			}
		} else {
			font.draw(FrameBatch, "Ship destroyed!", screenWidth/2f-44, screenHeight-140);
		}

		player.draw(FrameBatch);

		if (paused) {
			font.draw(FrameBatch, "Paused", screenWidth/2f-24, screenHeight-100);
		}

		if (player.isDead()) {
			font.draw(FrameBatch, "You died!", screenWidth/2f-28, screenHeight-120);
		}

		FrameBatch.end();
	}
	
	@Override
	public void dispose () {
		player.texture.dispose();
		FrameBatch.dispose();
		font.dispose();

		assetManager.dispose();
	}

	@Override
	public void pause() {
		paused = true;
	}

	@Override
	public void resume() {
		paused = false;
	}

	private void loadAssets(AssetManager manager) {
		// Load the player
		manager.load("playerShip.png", Texture.class);
		manager.load("playerBullet.png", Texture.class);

		// Clouds
		manager.load("cloud1.png", Texture.class);
		manager.load("cloud2.png", Texture.class);
		manager.load("cloud3.png", Texture.class);

		// Load enemies
		manager.load("drone1.png", Texture.class);
		manager.load("heavy1.png", Texture.class);
		manager.load("ship1.png", Texture.class);

		// Misc
		manager.load("icon.png", Texture.class);
		manager.load("heart.png", Texture.class);
		manager.load("progressBarSection.png", Texture.class);

		// Power Ups
		manager.load("powerup1.png", Texture.class);
		manager.load("powerup2.png", Texture.class);

		manager.finishLoading();
	}

	private void randomLoot(ScrollingEnemy enemy) {
		double random = Math.random() * 100;

		if (random > 98 && enemiesDead > 100) {
			powerups.add(new Powerup((Texture) assetManager.get("powerup1.png"), enemy.x + enemy.width / 2 - 17, enemy.y, 34, 34, "speedShoot "));
		}
		if (random > 93.333 && enemiesDead > 50) {
			powerups.add(new Powerup((Texture)assetManager.get("powerup2.png"), enemy.x + enemy.width / 2 - 17, enemy.y, 34, 34, "doubleShoot "));
		}
	}

	// For InputProcessor

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			if (!player.isDead()) {
				if (!paused) {
					pause();
				} else {
					resume();
				}
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
		player.setTarget(screenX - player.width/2, player.y);
		if (bulletTime < 10) {
			bulletTime = 0;
		}
		playerShoot = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		player.setTarget(screenX - player.width/2, player.y);
		playerShoot = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		player.setTarget(screenX - player.width/2, player.y);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		player.setTarget(screenX - player.width/2, player.y);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
