package invaders.objects.util;

import invaders.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.utils.HudObjects.HudObject;


public class HudMesage extends HudObject {

    private String message;
    private Color color = Color.WHITE;
    private GameLogic gl;

    public HudMesage(int x, int y, Color color) {
        super(x, y);
        this.gl = gl;
        this.color = color;
    }

    @Override
    public void update(double timeStep, double time) {

    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(color);
        gc.setFont(ResourseLoader.resourseLoader.loadFont("arcade.ttf", 16));
        gc.fillText(message, x, y);
    }

    public void setMessage(String message){
        this.message = message;
    }

}
