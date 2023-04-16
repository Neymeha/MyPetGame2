package com.neymeha.mypetgame2.game_objects.ships;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.neymeha.mypetgame2.game_objects.bullets.Bullet;
import com.neymeha.mypetgame2.screens.GameScreen;
import com.neymeha.mypetgame2.game_objects.bullets.StandartBullet;

public class EnemyShip extends SpaceShip {

    public Array <Bullet> bullets;
    public long lastBulletTimeSpawn;
    public int lastMovementChangeX;
    public int lastMovementChangeY;
    public boolean destroyed;

    public EnemyShip(int x, int y, int width, int height) {
        super(x, y, width, height);
        bullets = new Array<>();
        lastMovementChangeX=MathUtils.random(-3,3);
        lastMovementChangeY=MathUtils.random(-3,3);
    }

    public void flyToScreenAndMove(GameScreen game){
        if (body.y>Gdx.graphics.getHeight()-body.height) {
            body.y-=3;
        } else if (body.x<0){
            body.x+=3;
        } else if (body.x>Gdx.graphics.getWidth()-body.width) {
            body.x-=3;
        } else {
            movement(game);
        }
    }

    @Override
    public void movement(GameScreen game) {
        body.x+=lastMovementChangeX;
        body.y+=lastMovementChangeY;
        if (body.x<0) {
            lastMovementChangeX = MathUtils.random(1,3);
        } else if (body.x>Gdx.graphics.getWidth()-body.width) {
            lastMovementChangeX = -MathUtils.random(1,3);
        }
        if (body.y<Gdx.graphics.getHeight()/2) {
            lastMovementChangeY = MathUtils.random(1,3);
        } else if (body.y>Gdx.graphics.getHeight()-body.height) {
            lastMovementChangeY = -MathUtils.random(1,3);
        }
    }

    @Override
    public void shoot(GameScreen game) {
        if (lastBulletTimeSpawn==0) {
            bullets.add(new StandartBullet(this));
            lastBulletTimeSpawn = TimeUtils.nanoTime();
        }
        if (TimeUtils.nanoTime() - lastBulletTimeSpawn > 1000000000) {
            bullets.add(new StandartBullet(this));
            lastBulletTimeSpawn = TimeUtils.nanoTime();
        }
        if (bullets!=null) {
            for (int i=0; i<bullets.size; i++){
                bullets.get(i).bulletMovement(this);
                if (bullets.get(i).body.y<0) {
                    bullets.removeIndex(i);
                    i--;
                }
            }
        }
    }
}
