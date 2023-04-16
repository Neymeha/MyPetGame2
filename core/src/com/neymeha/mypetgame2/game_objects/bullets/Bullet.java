package com.neymeha.mypetgame2.game_objects.bullets;

import com.badlogic.gdx.math.Rectangle;
import com.neymeha.mypetgame2.game_objects.ships.SpaceShip;
import com.neymeha.mypetgame2.screens.GameScreen;

public abstract class Bullet {

    public Rectangle body;

    public Bullet(float x, float y) {
        body = new Rectangle();
        body.x = x;
        body.y = y;
        body.height=25;
        body.width=25;
    }

    public abstract void bulletMovement(SpaceShip spaceShip);

    public void bulletMovement(GameScreen game) {
        body.y -= 4;
    }

}
