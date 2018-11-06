package org.gamelib.core;


import javafx.scene.canvas.GraphicsContext;
import org.gamelib.core.objects.GameObject;

import java.util.LinkedList;

public class Handler {

    private LinkedList<GameObject> objects = new LinkedList<>();
    private LinkedList<GameObject> objectsToRemove = new LinkedList<>();
    private LinkedList<GameObject> objectsToAdd = new LinkedList<>();

    private LinkedList<Integer> events = new LinkedList<>();

    public void update(double timeStep, double time){
        for (GameObject object : objectsToRemove)
            objects.remove(object);
       objectsToRemove.clear();

       for (GameObject object : objectsToAdd)
           objects.add(object);
       objectsToAdd.clear();

        for (GameObject object : objects)
            object.update(timeStep, time);
    }

    public void render(GraphicsContext gc){
        for (GameObject object : objects){
            object.render(gc);
        }
    }

    public void addObject(GameObject object){
        objectsToAdd.add(object);
    }

    public void removeObject(GameObject object){
        objectsToRemove.add(object);
    }


    public LinkedList<GameObject> getGameObjects(){
        return objects;
    }

    public void addEvent(int event){
        events.add(event);
    }

    public LinkedList<Integer> getEvents(){
        return events;
    }

    public boolean containsEvent(int event){
        if (events.contains(event)){
            events.removeFirstOccurrence(event);
            return  true;
        }
        return false;
    }

    public void eventsClear(){
        events.clear();
    }

    public void clear() {
        objects.clear();
    }
}