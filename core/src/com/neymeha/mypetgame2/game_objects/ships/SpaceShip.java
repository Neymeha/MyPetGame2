package com.neymeha.mypetgame2.game_objects.ships;

import com.badlogic.gdx.math.Rectangle;
import com.neymeha.mypetgame2.screens.GameScreen;

public abstract class SpaceShip {

    public Rectangle body;

    public SpaceShip(int x, int y, int width, int height) {
        body = new Rectangle();
        body.x = x;
        body.y = y;
        body.width = width;
        body.height = height;
    }

    public abstract void movement(GameScreen game);

    public abstract void shoot(GameScreen game);

}
