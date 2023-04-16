package com.neymeha.mypetgame2.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.neymeha.mypetgame2.MyPetGame2;

public class MainMenu implements Screen {

    public MyPetGame2 game;
    public OrthographicCamera camera;
    public Texture moonBackground;
    public long time;
    public String message;
    public int count;

    public MainMenu (MyPetGame2 game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 625, 625);
        moonBackground = new Texture("moon_background.png");
        message = "Touch screen to play the Game!";
        count = 3;
    }

    public void changeMessage(){
        if(time<System.currentTimeMillis()-1000 && time!=0) {
            message = "           Game starts in: "+count;
            count --;
            time = System.currentTimeMillis();
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
        game.batch.draw(moonBackground,0,0);
        game.font.draw(game.batch, message, 210,310);
        game.batch.end();

        if (Gdx.input.isTouched() && time==0) {
            time = System.currentTimeMillis();
        }
        changeMessage();
        if (count<0){
            game.setScreen(new GameScreen(game));
        }
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
        moonBackground.dispose();
    }
}
