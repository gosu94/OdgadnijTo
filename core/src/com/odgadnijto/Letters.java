package com.odgadnijto;

import com.badlogic.gdx.Gdx;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.odgadnijto.OdgadnijTo.batch;

/**
 * @author Tomasz Pilarczyk
 */
class Letters {

    private final static int lettersMax = 28;
    static List<Letter> lettersAvailable;

    Letters() {
        lettersAvailable = new ArrayList<Letter>();
    }

    static String translateToString(List<Letter> listOfLetters) {
        String phrase = "";
        for (Letter letter : listOfLetters) {
            phrase += Character.toString(letter.getValue());
        }
        return phrase;
    }

    static void deleteLetterFromSolution() {
        Letter lastLetter = GuessPhrase.enteredPhrase.get(GuessPhrase.enteredPhrase.size() - 1);
        lastLetter.setTargetPos(lastLetter.getOriginalPos());
        lastLetter.setActive(true);
        lastLetter.setUsed(false);
        GuessPhrase.activeField--;
        GuessPhrase.enteredPhrase.remove(lastLetter);

    }

    static void addLetterToSolutionIfPossible(char letter) {
        for (Letter letterAv : lettersAvailable) {
            if (letterAv.getValue() == letter && !letterAv.isUsed() && GuessPhrase.activeField < GuessPhrase.getLettersInPhrase()) {
                addLetterToSolution(letterAv);
                break;
            }
        }
    }

    static void addLetterToSolution(Letter letter) {
        letter.setTargetPos(GuessPhrase.getPosOfActiveField());
        letter.setActive(true);
        letter.setUsed(true);
        GuessPhrase.activeField++;
        GuessPhrase.enteredPhrase.add(letter);
    }

    void init() {
        lettersAvailable.clear();
        getLettersFromGuessPhrase();
        addRandomLetters();
        shuffleLetters();
        setPositionOfLetters();
    }

    private void getLettersFromGuessPhrase() {
        List<String> wordsInPhrase = GuessPhrase.getWordsInPhrase();
        for (String word : wordsInPhrase) {
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != ' ' && word.charAt(i) != '\n') {
                    lettersAvailable.add(new Letter(Character.toUpperCase(word.charAt(i))));
                }
            }
        }
    }

    private void addRandomLetters() {
        for (int i = lettersAvailable.size(); i < lettersMax; i++) {
            lettersAvailable.add(getRandomLetter());
        }
    }

    private Letter getRandomLetter() {
        Random rand = new Random();
        int asciiCode = rand.nextInt((90 - 65) + 1) + 65;
        return new Letter((char) asciiCode);
    }

    private void shuffleLetters() {
        Collections.shuffle(lettersAvailable);
    }

    private void setPositionOfLetters() {
        for (int i = 0; i < lettersMax / 2; i++) {
            lettersAvailable.get(i).posX = 40 + (40 * i);
            lettersAvailable.get(i).posY = 135;
            lettersAvailable.get(i).setOriginalPos(new Point(40 + (40 * i), 135));
        }
        for (int i = lettersMax / 2; i < lettersMax; i++) {
            lettersAvailable.get(i).posX = 40 + (40 * (i - lettersMax / 2));
            lettersAvailable.get(i).posY = 75;
            lettersAvailable.get(i).setOriginalPos(new Point(40 + (40 * (i - lettersMax / 2)), 75));
        }
    }

    void draw() {
        for (Letter letter : lettersAvailable) {
            GuessPhrase.font.draw(batch,
                    Character.toString(letter.getValue()),
                    letter.posX, letter.posY);
        }
    }

    void checkIfLetterIsActive() {
        for (Letter letter : lettersAvailable) {
            if (letter.isActive()) {
                highlightPressedLetter(letter);
                moveLetterToPos(letter);
            }
        }
    }

    private void highlightPressedLetter(Letter letter) {
        GuessPhrase.highlightedFont.draw(batch, Character.toString(letter.getValue()),
                letter.posX, letter.posY);

    }

    private void moveLetterToPos(Letter letter) {
        final float pixelsPerSecond = 150.0f;

        if (Math.round(letter.posX) == letter.getTargetPos().x && Math.round(letter.posY) == letter.getTargetPos().y) {
            letter.setActive(false);
        } else if (Math.round(letter.posX) < letter.getTargetPos().x) {
            letter.posX += pixelsPerSecond * Gdx.graphics.getDeltaTime();
        } else if (Math.round(letter.posY) < letter.getTargetPos().y) {
            letter.posY += pixelsPerSecond * Gdx.graphics.getDeltaTime();
        } else if (Math.round(letter.posX) > letter.getTargetPos().x) {
            letter.posX -= pixelsPerSecond * Gdx.graphics.getDeltaTime();
        } else if (Math.round(letter.posY) > letter.getTargetPos().y) {
            letter.posY -= pixelsPerSecond * Gdx.graphics.getDeltaTime();
        }

    }

}
