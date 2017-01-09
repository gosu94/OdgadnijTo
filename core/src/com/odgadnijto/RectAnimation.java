package com.odgadnijto;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Tomasz Pilarczyk
 */
class RectAnimation {
    private static float rectX, rectY;
    private static int width, height;
    private static ShapeRenderer shape;
    private static boolean isTrigger;
    private static boolean isNextLevelLoaded;
    private float speed = 1100f;

    RectAnimation() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        rectY = 0;
        rectX = 0 - width;
        shape = new ShapeRenderer();
        isTrigger = false;
        isNextLevelLoaded = false;
    }

    static void triggerAnimation() {
        isTrigger = true;
    }

    void draw() {
        if (isTrigger) {
            shape.begin(ShapeRenderer.ShapeType.Filled);
            shape.rect(rectX, rectY, width, height, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
            whenRectCoversScreenLoadNextLevel();
            whenAnimationOverReset();
            shape.end();
        }
    }

    private void whenRectCoversScreenLoadNextLevel() {
        rectX += speed * Gdx.graphics.getDeltaTime();
        if (rectX > 0 && !isNextLevelLoaded) {
            Level.loadNextLevel();
            isNextLevelLoaded = true;
        }
    }

    private void whenAnimationOverReset() {
        if (rectX > width) {
            isTrigger = false;
            isNextLevelLoaded = false;
            rectX = 0 - width;
        }
    }
}
