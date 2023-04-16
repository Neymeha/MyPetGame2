package com.neymeha.mypetgame2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.neymeha.mypetgame2.MyPetGame2;
import com.neymeha.mypetgame2.game_objects.bullets.Bullet;
import com.neymeha.mypetgame2.game_objects.ships.EnemyShip;
import com.neymeha.mypetgame2.game_objects.ships.PlayerShip;
import com.neymeha.mypetgame2.world_controllers.Collision;

public class GameScreen implements Screen {

    public PlayerShip ship;

    public OrthographicCamera camera;

    public MyPetGame2 game;

    public Texture backgroundImg, shipImg, liveImg, bulletImg, enemyImg;

    public Array <Circle> lives;
    public Array <EnemyShip> enemies;
    public Array <Bullet> bullets;
    public int destroyedShipsCount,livesLeft;

    public GameScreen(MyPetGame2 game) {

        this.game = game;

        backgroundImg = new Texture("moon_background.png");
        shipImg = new Texture("ship_1.png");
        liveImg = new Texture("live.png");
        bulletImg = new Texture("laserBullet.png");
        enemyImg = new Texture("enemyShip_1.png");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 625, 625);

        lives = new Array<>();
        for (int i=1; i<4; i++) {
            lives.add(new Circle(0+i*15,570,1));
        }
        enemies = new Array<>();
        ship = new PlayerShip(625/2-71/2, 20, 71 , 71);
        bullets = new Array<>();
        for (int i=0; i<20; i++) {
            enemies.add(new EnemyShip(Gdx.graphics.getWidth()+i*500*MathUtils.random(-1,1),  Gdx.graphics.getHeight()+i*500, 71,53));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backgroundImg,0,0);
        game.batch.draw(shipImg, ship.body.x, ship.body.y);
        game.font.draw(game.batch, "Enemy destroyed: "+destroyedShipsCount,Gdx.graphics.getWidth()-160, Gdx.graphics.getHeight()-10);
        for (EnemyShip enemyShip: enemies){
            game.batch.draw(enemyImg, enemyShip.body.x, enemyShip.body.y);
        }
        if (ship.bullets!=null) {
            for (Bullet bullet:ship.bullets) {
                game.batch.draw(bulletImg, bullet.body.x, bullet.body.y);
            }
        }
        for (EnemyShip enemyShip: enemies) {
            for (Bullet bullet : enemyShip.bullets) {
                game.batch.draw(bulletImg, bullet.body.x, bullet.body.y);
            }
        }
        if (bullets!=null){
            for (int i=0; i<bullets.size; i++) {
                game.batch.draw(bulletImg, bullets.get(i).body.x, bullets.get(i).body.y);
                bullets.get(i).bulletMovement(this);
                if (bullets.get(i).body.y<0) {
                    bullets.removeIndex(i);
                    i--;
                }
            }
        }
        for (Circle live: lives) {
            game.batch.draw(liveImg, live.x, live.y);
        }
        game.batch.end();

        camera.update();

        for (EnemyShip enemyShip:enemies){
            enemyShip.flyToScreenAndMove(this);
            if (enemyShip.body.x>0 && enemyShip.body.x+enemyShip.body.width<Gdx.graphics.getWidth()
                && enemyShip.body.y<Gdx.graphics.getHeight()) {
                enemyShip.shoot(this);
            }
        }

        Collision.bulletCollisionWithEnemy(enemies, ship.bullets, this);
        Collision.bulletCollisionWithPlayer(enemies, bullets, ship, this);

        ship.movement(this);
        ship.shoot(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        backgroundImg.dispose();
        shipImg.dispose();
    }
}
