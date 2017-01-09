package com.odgadnijto;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Tomasz Pilarczyk
 */
class FileLoader {
    private static final String filename = "levels.txt";
    static Point images;
    static String guessPhrase;
    private static ArrayList<String> levels = new ArrayList<String>();
    private static Random randomGenerator = new Random();

    static void loadFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                levels.add(line);
            }
            bufferedReader.close();
        } catch (IOException err) {
            OdgadnijTo.stage.addActor(new Controls.CantLoadFileDialog("Blad", OdgadnijTo.skin));
        }
    }

    static boolean loadRandomLevel() {
        try {
            int randomLevel = randomGenerator.nextInt(levels.size());
            String line = levels.get(randomLevel);
            guessPhrase = getPhrase(line);
            images = getImages(line);
            levels.remove(randomLevel);
            return true;
        } catch (IllegalArgumentException err) {
            return false;
        }

    }

    private static String getPhrase(String line) {
        return line.substring(0, line.indexOf("-"));
    }

    private static Point getImages(String line) {
        int firstOne = Integer.valueOf(line.substring(line.indexOf("(") + 1, line.indexOf(",")));
        int secondOne = Integer.valueOf(line.substring(line.indexOf(",") + 1, line.indexOf(")")));
        return new Point(firstOne, secondOne);
    }
}
