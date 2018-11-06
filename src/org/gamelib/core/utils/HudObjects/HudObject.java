package org.gamelib.core.utils.HudObjects;

import javafx.scene.canvas.GraphicsContext;

public abstract class HudObject {

    protected int x, y;

    public HudObject(int x, int y){
        this.x = x;
        this.y = y;
    }

    public abstract void update(double timeStep, double time);
    public abstract void render(GraphicsContext gc);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
