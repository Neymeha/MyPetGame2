package com.neymeha.mypetgame2.world_controllers;

import com.badlogic.gdx.utils.Array;
import com.neymeha.mypetgame2.game_objects.bullets.Bullet;
import com.neymeha.mypetgame2.game_objects.ships.EnemyShip;
import com.neymeha.mypetgame2.game_objects.ships.PlayerShip;
import com.neymeha.mypetgame2.screens.GameScreen;

public class Collision {

    public static void bulletCollisionWithEnemy(Array<EnemyShip> enemies, Array<Bullet> bullets, GameScreen game) {
        OUTER: for (int i=0; i<bullets.size; i++){
            for (int j=0; j<enemies.size; j++) {
                if (bullets.get(i).body.overlaps(enemies.get(j).body)) {
                    enemies.get(i).destroyed=true;
                    game.bullets.addAll(enemies.get(j).bullets);
                    enemies.removeIndex(j);
                    game.destroyedShipsCount++;
                    j--;
                    bullets.removeIndex(i);
                    i--;
                    continue OUTER;
                }
            }
        }
    }

    public static void bulletCollisionWithPlayer(Array<EnemyShip> enemies, Array<Bullet> bullets, PlayerShip ship, GameScreen game) {
        for (int i=0; i<enemies.size; i++) {
            for (int j=0; j<enemies.get(i).bullets.size; j++) {
                if (enemies.get(i).bullets.get(j).body.overlaps(ship.body)) {

                }
            }
        }
    }
}
