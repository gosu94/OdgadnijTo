package com.odgadnijto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Pilarczyk
 */
class Pictures {

    static List<Sprite> spriteList;
    private File jpgsFolder = new File("../assets");
    private TextureAtlas atlas;
    private int middleCoords;
    private int yPosition;
    private int spriteWidth;
    private int spriteHeight;
    private double scale;
    private float rotation;

    Pictures() {

    }

    void load() {
        spriteList = new ArrayList<Sprite>();
        atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
        scale = 0.25;
        setSpriteBounds();
        createSprites();

    }

    private void setSpriteBounds() {
        Sprite firstSprite;
        firstSprite = atlas.createSprite("1");
        spriteWidth = (int) (firstSprite.getWidth() * scale);
        spriteHeight = (int) (firstSprite.getHeight() * scale);
        yPosition = 310;
        middleCoords = Gdx.graphics.getWidth() / 2;
    }

    private void createSprites() {
        Sprite sprite;
        for (int i = 0; i < 2; i++) {
            sprite = atlas.createSprite(Integer.toString(i));
            sprite.setSize((float) (scale * sprite.getWidth()), (float) (scale * sprite.getHeight()));
            spriteList.add(sprite);
        }
    }

    private int countJpgFiles() {
        File[] listOfFiles = jpgsFolder.listFiles();
        int counter = 0;
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().contains(".jpg"))
                    counter++;
            }
            return counter;
        }
        return 0;

    }

    void draw(int image1iD, int image2iD) {
        spriteList.get(image1iD).setPosition(middleCoords - spriteWidth - 20, 310);
        spriteList.get(image2iD).setPosition(middleCoords + 20, 310);
        spriteList.get(image1iD).draw(OdgadnijTo.batch);
        spriteList.get(image2iD).draw(OdgadnijTo.batch);

    }

    void dispose() {
        for (Sprite sprite : spriteList) {
            sprite.getTexture().dispose();
        }
    }

}
