package org.gamelib.core.objects;

import org.gamelib.core.Handler;
import org.gamelib.core.utils.Grid;

import java.awt.*;

public abstract class GameGridObject extends GameObject {

    protected Grid grid;

    public GameGridObject(int x, int y, int id, Handler handler, Grid grid) {
        super(x, y, id, handler);
        this.grid = grid;
    }

    @Override
    public Rectangle getBounce() {
        return null;
    }
}
