package com.odgadnijto;

import java.awt.*;

/**
 * @author Tomasz Pilarczyk
 */
class Level {

    private static final int menuLevel = 0;
    private static int level;
    private static Point levelPictures;
    private static GuessPhrase guessPhrase;
    private static Letters letters;
    private static Controls.LevelFinishedDialog levelFinishedDialog;
    private static Menu menu;
    private static boolean gameOver;
    Pictures pictures;

    Level() {
        level = 0;
        guessPhrase = new GuessPhrase();
        letters = new Letters();
        pictures = new Pictures();
        menu = new Menu();
        gameOver = false;
        levelFinishedDialog = new Controls.LevelFinishedDialog("", OdgadnijTo.skin);

    }

    static void init(String guessPhraseString, Point images) {
        if (level == 0) {
            menu.addButtonsOnStage();
        }
        guessPhrase.init(guessPhraseString);
        letters.init();
        levelPictures = images;

    }

    static void increaseLevel() {
        RectAnimation.triggerAnimation();
    }

    static void loadNextLevel() {
        if (level == menuLevel) {
            Menu.playButton.remove();
            Menu.exitButton.remove();
            Tutorial.show();
        } else {
            if (FileLoader.loadRandomLevel()) {
                init(FileLoader.guessPhrase, FileLoader.images);
                levelFinishedDialog = new Controls.LevelFinishedDialog("", OdgadnijTo.skin);
            } else {
                gameOver = true;
                gameOver();
            }
        }
        level++;
    }

    private static void gameOver() {
        OdgadnijTo.stage.addActor(new Controls.EndGameDialog(OdgadnijTo.skin));
    }

    void draw() {
        if (level != menuLevel && !gameOver) {
            pictures.draw(levelPictures.x, levelPictures.y);
            guessPhrase.draw();
            letters.draw();
            letters.checkIfLetterIsActive();
        }
    }

    void checkIfPhraseIsGuessed() {
        if (guessPhrase.checkIfEnteredPhraseIsCorrect()) {
            OdgadnijTo.stage.addActor(levelFinishedDialog);
        }
    }

    void dispose() {
        pictures.dispose();
    }
}
