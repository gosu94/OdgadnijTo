package com.odgadnijto;

import java.awt.*;

/**
 * @author Tomasz Pilarczyk
 */
class Letter {
    float posX;
    float posY;
    private Point targetPos;
    private Point originalPos;
    private boolean isUsed;
    private boolean isActive;
    private char value;

    Letter(char value) {
        this.value = value;
        isUsed = false;
        isActive = false;
    }

    boolean isUsed() {
        return isUsed;
    }

    void setUsed(boolean used) {
        isUsed = used;
    }

    boolean isActive() {
        return isActive;
    }

    void setActive(boolean active) {
        isActive = active;
    }

    char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    Point getTargetPos() {
        return targetPos;
    }

    void setTargetPos(Point targetPos) {
        this.targetPos = targetPos;
    }

    Point getOriginalPos() {
        return originalPos;
    }

    void setOriginalPos(Point originalPos) {
        this.originalPos = originalPos;
    }
}
