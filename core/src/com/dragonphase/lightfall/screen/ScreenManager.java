/*
    Copyright (C) 2014 Dragonphase || Contributing Developers.
    View the Game class for full notice of license.
 */

package com.dragonphase.lightfall.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dragonphase.lightfall.core.LogicBase;

import java.util.LinkedHashSet;

public class ScreenManager implements LogicBase {

    private LinkedHashSet<Screen> screens;

    private Screen activeScreen;

    public ScreenManager() {
        screens = new LinkedHashSet<>();
    }

    public LinkedHashSet<Screen> getScreens() {
        return screens;
    }

    public <T extends Screen> T getScreen(Class<T> type) {
        for (final Screen screen : getScreens()) {
            if (screen.getClass() == type) {
                return type.cast(screen);
            }
        }
        return null;
    }

    public void addScreen(Screen screen) {
        if (getActiveScreen() == null || getScreens().size() < 1) {
            setActiveScreen(screen);
        }

        getScreens().add(screen);
    }

    public void removeScreen(Screen screen) {
        getScreens().remove(screen);
    }

    public <T extends Screen> void removeScreen(Class<T> type) {
        getScreens().remove(getScreen(type));
    }

    public Screen getActiveScreen() {
        return activeScreen;
    }

    public <T extends Screen> T getActiveScreen(Class<T> type) {
        return getActiveScreen().getClass() == type ? type.cast(getActiveScreen()) : null;
    }

    public void setActiveScreen(Screen screen) {
        this.activeScreen = screen;
    }

    @Override
    public void update(float delta) {
        if (getActiveScreen() != null) {
            getActiveScreen().update(delta);
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch, float delta) {
        for (final Screen screen : getScreens()) {
            if (screen.isVisible()) {
                screen.draw(spriteBatch, delta);
            }
        }
    }
}
