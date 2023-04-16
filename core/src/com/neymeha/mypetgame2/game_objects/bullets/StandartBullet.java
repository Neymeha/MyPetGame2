package com.neymeha.mypetgame2.game_objects.bullets;

import com.neymeha.mypetgame2.game_objects.ships.EnemyShip;
import com.neymeha.mypetgame2.game_objects.ships.PlayerShip;
import com.neymeha.mypetgame2.game_objects.ships.SpaceShip;

public class StandartBullet extends Bullet{

    public StandartBullet(PlayerShip spaceShip) {
        super(spaceShip.body.x+spaceShip.body.width/2-15, spaceShip.body.y+spaceShip.body.height-15);
    }

    public StandartBullet(EnemyShip spaceShip) {
        super(spaceShip.body.x+spaceShip.body.width/2-15, spaceShip.body.y-15);
    }

    @Override
    public void bulletMovement(SpaceShip spaceShip) {
        if (spaceShip instanceof PlayerShip) {
            body.y += 4;
        }
        if (spaceShip instanceof EnemyShip) {
            body.y -= 4;
        }
    }
}
