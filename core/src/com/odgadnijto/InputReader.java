package com.odgadnijto;

import com.badlogic.gdx.InputProcessor;

/**
 * @author Tomasz Pilarczyk
 */
class InputReader implements InputProcessor {

    @Override
    public boolean keyDown(int keycode) {
        if (!Tutorial.isActive) {
            int asciiCode = keycode + 36;
            Letters.addLetterToSolutionIfPossible((char) asciiCode);
        }

        if (keycode == 67 && !GuessPhrase.enteredPhrase.isEmpty()) { //67 - Backspace
            Letters.deleteLetterFromSolution();
        }
        if (keycode == 61) { //61 - Tab
            GuessPhrase.solveAll();
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
