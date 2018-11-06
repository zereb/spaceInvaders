package invaders.objects.util;

import invaders.Main;
import invaders.objects.Invader;
import org.gamelib.core.Handler;

import java.util.LinkedList;

public class InvaderRow {

    private LinkedList<Invader> invaders = new LinkedList<Invader>();
    private Invader tepm;

    public InvaderRow(int x, int y, int width, int ammount, int type, Handler handler){
        tepm = new Invader(x, y, Main.SCALE,Main.INVADER, 1,handler);
        for (int i = 0; i < ammount; i++){
            handler.addObject(new Invader(x + i * tepm.getWidth()*2 , y, Main.SCALE, type, Main.INVADER, handler));
        }
    }

}
