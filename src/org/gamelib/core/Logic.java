package org.gamelib.core;

import java.util.Random;

public  abstract class Logic {

    protected Handler handler;
    protected Random rnd;

    public Logic(Handler handler){
        this.handler = handler;
        rnd = new Random();
    }

    public abstract void update(double timeStep, double time);


}
