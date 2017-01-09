package com.odgadnijto.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.odgadnijto.OdgadnijTo;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = -1;
        config.y = -1;
        new LwjglApplication(new OdgadnijTo(), config);
    }
}
