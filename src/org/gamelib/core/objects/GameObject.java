package org.gamelib.core.objects;


import javafx.scene.canvas.GraphicsContext;
import org.gamelib.core.Handler;

import java.awt.*;

public abstract class GameObject {
    protected int x, y;
    protected int velX, velY;
    protected int id;
    protected int width;
    protected int height;
    protected Handler handler;
    protected boolean event;

    public GameObject(int x, int y, int id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract Rectangle getBounce();

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

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }
}