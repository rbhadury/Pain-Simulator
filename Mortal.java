package PainSim;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class Mortal {
    private Pane characterPane;
    private Rectangle body;
    private Rectangle rEye;
    private Rectangle lEye;
    private boolean attemptedEscape;
    private Simulator simulator;
    private Label name;

    public Mortal(String name, Pane root, Simulator sim) {
        simulator = sim;
        attemptedEscape = false;
        this.characterPane = new Pane();
        root.getChildren().add(this.characterPane);
        this.makeCharacter(name);
    }

    private void makeCharacter(String name) {
        Font myFont = Font.loadFont(this.getClass().getResource("Fleftex_M.TTF").toExternalForm(),8);
        this.body = new Rectangle(Constants.CHARACTER_SIZE,Constants.CHARACTER_SIZE);
        body.setFill(Color.RED);
        this.characterPane.getChildren().add(this.body);

        this.lEye = new Rectangle(10,10);
        this.rEye = new Rectangle(10,10);

        this.name = new Label(name);
        this.characterPane.getChildren().add(this.name);
        this.name.setTextFill(Color.WHITE);
        this.name.setFont(myFont);

        for (Shape shape:
             new Shape[] {this.lEye,this.rEye}) {
            shape.setFill(Color.BLACK);
            this.characterPane.getChildren().add(shape);
        }

        this.setCharacterPosition(Constants.STARTING_POS, Constants.STARTING_POS);
    }

    private void setCharacterPosition(int dx, int dy) {
        double newX = this.body.getX()+dx;
        double newY = this.body.getY()+dy;

        if (newX > Constants.WINDOW_WIDTH+100) newX = -Constants.CHARACTER_SIZE; attemptEscape();
        if (newX < -100) newX = Constants.WINDOW_WIDTH + Constants.CHARACTER_SIZE; attemptEscape();

        if (newY > Constants.WINDOW_HEIGHT+100) newY = -Constants.CHARACTER_SIZE; attemptEscape();
        if (newY < -100) newY = Constants.WINDOW_HEIGHT + Constants.CHARACTER_SIZE; attemptEscape();

        this.body.setX(newX);
        this.body.setY(newY);

        this.lEye.setX(newX+Constants.LEFT_EYE_OFFSET);
        this.rEye.setX(newX + Constants.RIGHT_EYE_OFFSET);

        this.name.setLayoutX(newX);
        this.name.setLayoutY(newY - 20);

        for (Rectangle shape:
                new Rectangle[] {this.lEye,this.rEye}) {
            shape.setY(Constants.EYE_HEIGHT + newY);
        }
    }

    private void attemptEscape() {
        if (!this.attemptedEscape) this.attemptedEscape = true;
        //this.simulator.fakeEscape();
    }

    public void animate(Boolean paining) {
        if (paining) this.body.setFill(Color.RED);
        else this.body.setFill(Color.WHITE);

        //this.move();
    }

    public void move(Directions dir) {
        switch (dir){
            case RIGHT:
            this.setCharacterPosition(Constants.MOVEMENT_DELTA,0);
            break;
            case LEFT:
            this.setCharacterPosition(-Constants.MOVEMENT_DELTA,0);
            break;
            case UP:
            this.setCharacterPosition(0,-Constants.MOVEMENT_DELTA);
            break;
            case DOWN:
            this.setCharacterPosition(0,Constants.MOVEMENT_DELTA);
            break;
            default:
                break;
        }
    }
}
