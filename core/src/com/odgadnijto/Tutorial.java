package com.odgadnijto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;


/**
 * @author Tomasz Pilarczyk
 */
class Tutorial {
    static boolean isActive;

    static void show() {
        OdgadnijTo.stage.addActor(new TutorialDialog("Witaj w tutorialu OdgadnijTo !\nKliknij 'Dalej' aby kontynuowac"));
        isActive = true;
    }

    private static class TutorialDialog extends Dialog {
        private static int width = 400;
        private static int height = 100;
        private static int x = Gdx.graphics.getWidth() / 2 - width / 2;
        private static int y = Gdx.graphics.getHeight() / 2 + height;
        private static int tutorialLevel = 0;


        TutorialDialog(String text) {
            super("Tutorial", OdgadnijTo.skin);
            text(text);
            button("Pomin", "skip");
            button("Dalej", "next");
            setBounds(x, y, width, height);
        }

        TutorialDialog(String text, int x, int y) {
            super("Tutorial", OdgadnijTo.skin);
            text(text);
            button("Pomin", "skip");
            button("Dalej", "next");
            setBounds(x, y, width, height);
        }

        TutorialDialog(String text, String buttonText) {
            super("Tutorial", OdgadnijTo.skin);
            text(text);
            button(buttonText, "next");
            setBounds(x, y, width, height);

        }

        @Override
        protected void result(Object object) {
            super.result(object);
            if (object.equals("skip")) {
                isActive = false;
            }
            if (object.equals("next")) {
                tutorialLevel++;
                switch (tutorialLevel) {
                    case 1:
                        OdgadnijTo.stage.addActor(new TutorialDialog("Zabawa polega na tym, aby odgadywac\n" +
                                "przedstawione na obrazkach hasla"));
                        break;
                    case 2:
                        OdgadnijTo.stage.addActor(new TutorialDialog("Moga to byc przyslowia, metafory, nazwy wlasne\n" +
                                "lub po prostu znane frazesy"));
                        break;
                    case 3:
                        int x = Gdx.graphics.getWidth() / 2 - width / 2;
                        int y = Gdx.graphics.getHeight() / 2 - height / 2;
                        OdgadnijTo.stage.addActor(new TutorialDialog("Pozwol ze pomoge Ci z pierwszym przykladem", x, y));
                        break;
                    case 4:
                        GuessPhrase.solveLetter('C');
                        GuessPhrase.solveLetter('Z');
                        GuessPhrase.solveLetter('A');
                        GuessPhrase.solveLetter('R');
                        GuessPhrase.solveLetter('N');
                        GuessPhrase.solveLetter('O');
                        GuessPhrase.solveLetter('N');
                        GuessPhrase.solveLetter('A');
                        OdgadnijTo.stage.addActor(new TutorialDialog("Sproboj go skonczyc i pamietaj,\n" +
                                "w grze nie uzywamy polskich liter! ", "Zakoncz"));
                        break;
                    case 5:
                        isActive = false;
                }

            }
        }
    }
}
