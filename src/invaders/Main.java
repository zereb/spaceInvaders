package invaders;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.gamelib.core.Handler;
import org.gamelib.core.Loop;
import org.gamelib.core.ResourseLoader;
import org.gamelib.core.objects.GameObject;
import org.gamelib.core.utils.Hud;
import org.gamelib.core.utils.Save;
import org.ietf.jgss.GSSManager;

public class Main extends Loop {

    private Handler handler;
    private GameLogic gl;
    private Hud hud;
    private Save save;

    public static final int WIDTH = 640, HEIGHT = 640;
    public static final double GAME_SPEED = 0.016;
    public  static final int SCALE = 3;

    public static final int PLAYER = 0,
                            BULLET = 1,
                            INVADER = 2,
                            E_BULET = 3,
                            NPO = 4;
    public static final int DEATH_EVENT = 0,
                            ENEMY_DEATH = 1,
                            PLAYER_GET_HIT = 3,
                            UFO_DEATH = 4,
                            NEXT_LVL = 5;


    @Override
    public void start(Stage primaryStage) throws Exception {
        initLoop(WIDTH, HEIGHT, primaryStage, "Space Invaders", GAME_SPEED);
        save = new Save("invaders");
        handler = new Handler();
        gl = new GameLogic(handler, save);
        hud = new Hud();
    }

    @Override
    public void update(double timeStep, double time) {
        handler.update(timeStep, time);
        gl.update(timeStep, time);
        hud.update(timeStep, time);
        if (handler.containsEvent(DEATH_EVENT)){
            save.setSaveValue("test", String.valueOf(gl.getScore()));
            gl = new GameLogic(handler, save);
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,WIDTH, HEIGHT);
        handler.render(gc);
        hud.render(gc);
    }

    @Override
    public void inputHandler() {
        for (GameObject object : handler.getGameObjects()){
            if (object.getId() == PLAYER){
                if(input.contains("RIGHT")) {
                    object.setVelX(4);
                }else if (input.contains("LEFT"))
                    object.setVelX(-4);
                else
                    object.setVelX(0);

                if (input.contains("SPACE")){
                    object.setEvent(true);
                }
                if (input.contains("ESCAPE")){
                    pause = !pause;
                    input.remove("ESCAPE");
                }

            }
        }
    }

    public static void main(String[] args){
        launch(args);
    }
}
