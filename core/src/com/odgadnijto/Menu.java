package com.odgadnijto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Tomasz Pilarczyk
 */
class Menu {
    private final static int buttonWidth = 200;
    private final static int buttonHeight = 50;
    private final static int middle = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;

    static PlayButton playButton;
    static ExitButton exitButton;

    Menu() {
        playButton = new PlayButton();
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                playButton.listener();
            }
        });
        exitButton = new ExitButton();
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                exitButton.listener();
            }
        });
    }

    void addButtonsOnStage() {
        OdgadnijTo.stage.addActor(playButton);
        OdgadnijTo.stage.addActor(exitButton);
    }

    static class PlayButton extends Controls.MyButton {
        PlayButton() {
            super("Zagraj", OdgadnijTo.skin);
            width = buttonWidth;
            height = buttonHeight;
            x = middle;
            y = 200;
            setPosition(x, y);
            setSize(width, height);
        }

        void listener() {
            Level.increaseLevel();
        }
    }

    static class ExitButton extends Controls.MyButton {
        ExitButton() {
            super("Wyjdz", OdgadnijTo.skin);
            width = buttonWidth;
            height = buttonHeight;
            x = middle;
            y = 120;
            setPosition(x, y);
            setSize(width, height);
        }

        void listener() {
            Gdx.app.exit();
        }
    }

}
