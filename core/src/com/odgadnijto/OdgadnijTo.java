package com.odgadnijto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.*;

public class OdgadnijTo extends ApplicationAdapter {
    static SpriteBatch batch;
    static Stage stage;
    static Skin skin;
    private static int secondsPassed;
    private BitmapFont regularFont;
    private int mouseX;
    private int mouseY;
    private float timer;
    private InputMultiplexer inputMultiplexer;
    private RectAnimation rectAnimation;
    private Level level;
    private boolean firstLevelInit;

    @Override
    public void create() {
        classesInit();
        gameVarsInit();
        level.pictures.load();
        inputMultiplexer.addProcessor(new InputReader());
        inputMultiplexer.addProcessor(stage = new Stage());
        Gdx.input.setInputProcessor(inputMultiplexer);
        FileLoader.loadFile();


    }

    @Override
    public void render() {
        if (Tutorial.isActive) {
            Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        } else {
            Gdx.gl.glClearColor(1, 1, 1, 1);
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (firstLevelInit) {
            Level.init("czarno na bialym", new Point(0, 1));
            firstLevelInit = false;

        }

        batch.begin();
        drawDevHUD();
        level.draw();
        batch.end();

        stage.act();
        stage.draw();

        rectAnimation.draw();
        level.checkIfPhraseIsGuessed();

    }

    @Override
    public void dispose() {
        batch.dispose();
        regularFont.dispose();
        level.dispose();
        GuessPhrase.font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height, true);
    }

    private void drawDevHUD() {
        mouseX = Gdx.input.getX();
        mouseY = Gdx.input.getY();
        regularFont.setColor(Color.BLACK);
        regularFont.draw(batch, "X:" + Integer.toString(mouseX) + "  Y:" + Integer.toString(480 - mouseY), 555, 475);
        regularFont.draw(batch, "Frames:" + Integer.toString(Gdx.graphics.getFramesPerSecond()), 555, 455);
        regularFont.draw(batch, "Secs:" + getSecondsPassed(), 555, 435);
    }

    private int getSecondsPassed() {
        timer += Gdx.graphics.getDeltaTime();
        if (timer >= 1) {
            timer--;
            secondsPassed++;
        }
        return secondsPassed;
    }

    private void classesInit() {
        batch = new SpriteBatch();
        regularFont = new BitmapFont();
        inputMultiplexer = new InputMultiplexer();
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        level = new Level();
        rectAnimation = new RectAnimation();
    }

    private void gameVarsInit() {
        mouseX = 0;
        mouseY = 0;
        timer = 0;
        secondsPassed = 0;
        firstLevelInit = true;
    }


}
