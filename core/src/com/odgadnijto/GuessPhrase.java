package com.odgadnijto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Pilarczyk
 */
class GuessPhrase {

    static List<Letter> enteredPhrase;
    static BitmapFont font;
    static BitmapFont highlightedFont;
    static int activeField;
    private static List<String> wordsInPhrase;
    private static List<Point> fieldsCoordinates;
    private static int startPosition;
    private final int spaceBetweenFields = 30;
    private String guessPhrase;

    GuessPhrase() {
        wordsInPhrase = new ArrayList<String>();
        fieldsCoordinates = new ArrayList<Point>();
        enteredPhrase = new ArrayList<Letter>();
        font = new BitmapFont(Gdx.files.internal("gabriela.fnt"));
        highlightedFont = new BitmapFont(Gdx.files.internal("gabriela.fnt"));
        highlightedFont.setColor(Color.RED);
    }

    static Point getPosOfActiveField() {
        return fieldsCoordinates.get(activeField);
    }

    private static int countLetters(String word) {
        int counter = 0;
        for (int i = 0; i < word.length(); i++) {
            counter++;
        }
        return counter;
    }

    static List<String> getWordsInPhrase() {
        return wordsInPhrase;
    }

    static int getLettersInPhrase() {
        int lettersCount = 0;
        for (String word : wordsInPhrase) {
            lettersCount += countLetters(word);
        }
        return lettersCount;
    }

    static void solveLetter(char letter) {
        for (Letter letterAv : Letters.lettersAvailable) {
            if (letterAv.getValue() == letter && !letterAv.isUsed() && GuessPhrase.activeField < GuessPhrase.getLettersInPhrase()) {
                Letters.addLetterToSolution(letterAv);
                break;
            }
        }
    }

    static void solveAll() {
        for (String word : wordsInPhrase) {
            for (int i = 0; i < word.length(); i++) {
                solveLetter(Character.toUpperCase(word.charAt(i)));
            }
        }
    }

    void init(String guessPhrase) {
        this.guessPhrase = guessPhrase;
        activeField = 0;
        wordsInPhrase.clear();
        fieldsCoordinates.clear();
        enteredPhrase.clear();
        separateToWords();
        setInTheMiddle();
        setFieldsCoordinates();
    }

    private void setFieldsCoordinates() {
        int positionCounter = 0;
        for (String word : wordsInPhrase) {
            for (int i = 0; i < countLetters(word); i++) {
                fieldsCoordinates.add(new Point(startPosition + positionCounter * spaceBetweenFields, 230));
                positionCounter++;
            }
            positionCounter++;
        }
    }

    private void separateToWords() {
        int lastWord = 0;
        for (int i = 0; i < guessPhrase.length(); i++) {
            if (guessPhrase.charAt(i) == ' ') {
                wordsInPhrase.add(guessPhrase.substring(lastWord, i));
                lastWord = i + 1;
            }
            if (i == guessPhrase.length() - 1) {
                wordsInPhrase.add(guessPhrase.substring(lastWord, i + 1));
            }
        }
    }

    void draw() {
        font.setColor(Color.BLACK);
        for (Point fieldPos : fieldsCoordinates) {
            font.draw(OdgadnijTo.batch, "_", fieldPos.x, fieldPos.y);
        }
    }

    private void setInTheMiddle() {
        int lettersCount = countLetters(guessPhrase);
        int screenWidth = Gdx.graphics.getWidth();

        startPosition = (screenWidth / 2) - lettersCount * (spaceBetweenFields / 2);
    }

    boolean checkIfEnteredPhraseIsCorrect() {
        if (enteredPhrase.size() == GuessPhrase.getLettersInPhrase()) {
            String enteredPhraseStr = Letters.translateToString(enteredPhrase);
            String guessPhraseWithoutSpaces = guessPhrase.replaceAll("\\s+", "");
            if (replaceUpperWithLower(enteredPhraseStr).equals(guessPhraseWithoutSpaces)) {
                if (areAllLettersUnactive()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean areAllLettersUnactive() {
        for (Letter letter : enteredPhrase) {
            if (letter.isActive()) return false;
        }
        return true;
    }

    private String replaceUpperWithLower(String upperString) {
        String lowerString = "";
        for (int i = 0; i < upperString.length(); i++) {
            lowerString += Character.toLowerCase(upperString.charAt(i));
        }
        return lowerString;
    }

}
