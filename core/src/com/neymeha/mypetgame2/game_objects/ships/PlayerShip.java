package com.neymeha.mypetgame2.game_objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.neymeha.mypetgame2.game_objects.bullets.Bullet;
import com.neymeha.mypetgame2.screens.GameScreen;
import com.neymeha.mypetgame2.game_objects.bullets.StandartBullet;

public class PlayerShip extends SpaceShip{

    public Vector3 touchPos;
    public Array <Bullet> bullets;
    public long lastBulletTimeSpawn;

    public PlayerShip(int x, int y, int width, int height) {
        super(x, y, width, height);
        touchPos = new Vector3();
        bullets = new Array<>();
    }

    @Override
    public void movement(GameScreen game) {
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.camera.unproject(touchPos);
            body.x = (int) (touchPos.x - body.width/2);
        }

        if (body.x<0) {
            body.x=0;
        }

        if (body.x>Gdx.graphics.getWidth()-body.width) {
            body.x=Gdx.graphics.getWidth()-body.width;
        }
    }

    @Override
    public void shoot(GameScreen game) {
        if (Gdx.input.isTouched()) {
            if (lastBulletTimeSpawn==0) {
                bullets.add(new StandartBullet(this));
                lastBulletTimeSpawn = TimeUtils.nanoTime();
            }
            if (TimeUtils.nanoTime() - lastBulletTimeSpawn > 500000000) {
                bullets.add(new StandartBullet(this));
                lastBulletTimeSpawn = TimeUtils.nanoTime();
            }
        }
        if (bullets!=null) {
            for (int i=0; i<bullets.size; i++){
                bullets.get(i).bulletMovement(this);
                if (bullets.get(i).body.y>Gdx.graphics.getHeight()) {
                    bullets.removeIndex(i);
                    i--;
                }
            }
        }
    }
}
