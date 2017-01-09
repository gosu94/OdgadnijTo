package com.odgadnijto;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * @author Tomasz Pilarczyk
 */
class Controls {

    static class LevelFinishedDialog extends Dialog {
        private int width = 200;
        private int height = 100;
        private int x = Gdx.graphics.getWidth() / 2 - width / 2;
        private int y = Gdx.graphics.getHeight() / 2 - height / 2;

        LevelFinishedDialog(String title, Skin skin) {
            super(title, skin);
            text("Gratulacje!");
            button("Nastepny poziom");
            setBounds(x, y, width, height);
        }

        @Override
        protected void result(Object object) {
            super.result(object);
            Level.increaseLevel();
        }
    }

    static class CantLoadFileDialog extends Dialog {
        private int width = 400;
        private int height = 100;
        private int x = Gdx.graphics.getWidth() / 2 - width / 2;
        private int y = Gdx.graphics.getHeight() / 2 + height;

        CantLoadFileDialog(String title, Skin skin) {
            super(title, skin);
            text("Nie udalo sie zaladowac pliku z poziomami");
            button("Wyjdz");
            setBounds(x, y, width, height);
        }

        @Override
        protected void result(Object object) {
            super.result(object);
            Gdx.app.exit();
        }
    }

    static class EndGameDialog extends Dialog {
        private int width = 400;
        private int height = 100;
        private int x = Gdx.graphics.getWidth() / 2 - width / 2;
        private int y = Gdx.graphics.getHeight() / 2 - height / 2;

        EndGameDialog(Skin skin) {
            super("Gratulacje", skin);
            text("Przeszedles gre!!!");
            button("Wyjdz");
            setBounds(x, y, width, height);
        }

        @Override
        protected void result(Object object) {
            super.result(object);
            Gdx.app.exit();
        }
    }

    abstract static class MyButton extends TextButton {
        int width;
        int height;
        float x;
        float y;

        MyButton(String text, Skin skin) {
            super(text, skin);
        }

        abstract void listener();

    }
}
