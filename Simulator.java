package PainSim;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Simulator {
    private final Mortal mortal;
    private final Pane root;
    private Timeline timeline;
    private int ticker;
    private int threshold;
    private Label escLabel;
    private int pain;
    private PaneOrganizer paneOrganizer;

    public Simulator(PaneOrganizer paneOrganizer){
        this.paneOrganizer = paneOrganizer;
        pain = 0;
        this.root = paneOrganizer.getRoot();
        root.setFocusTraversable(true);
        root.requestFocus();
        root.setOnKeyPressed((KeyEvent e) -> handleKeyPress(e));
        this.threshold = rng();
        this.ticker = 0;
        this.mortal = new Mortal("Jeff", root, this);
        this.setUpTimeline();
    }

    private void handleKeyPress(KeyEvent event) {
        KeyCode kc = event.getCode();
        Directions dir;
        switch (kc){
            case W:
                dir = Directions.UP;
                break;
            case A:
                dir = Directions.LEFT;
                break;
            case D:
                dir = Directions.RIGHT;
                break;
            case S:
                dir = Directions.DOWN;
                break;
            default:
                return;
        }
        this.mortal.move(dir);
    }

    private void setUpTimeline() {
        KeyFrame kf = new KeyFrame(Duration.seconds(Constants.DURATION),(ActionEvent e)-> this.tick());
        this.timeline = new Timeline(kf);
        this.timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }
    private int rng(){
        int rng =(int) (Math.random()*20);
        if (rng<5) rng = rng();
        return rng;
    }

    private void tick() {
        if (this.ticker%this.threshold == 0) {this.mortal.animate(true); this.threshold = rng();this.takePain();}
        else this.mortal.animate(false);
        this.ticker++;
    }

    private void takePain() {
        this.pain++;
        this.paneOrganizer.setPain(this.pain);
    }

    public void fakeEscape() {
        this.escLabel = new Label("You Escaped!");
        this.root.getChildren().add(escLabel);
    }

}
