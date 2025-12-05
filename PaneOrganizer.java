package PainSim;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PaneOrganizer {

    private final Font myFont;
    private BorderPane root;
    private Label painLabel;

    public PaneOrganizer(){
       this.myFont = Font.loadFont(this.getClass().getResource("Fleftex_M.TTF").toExternalForm(),16);

        this.root = new BorderPane();
        this.root.setStyle("-fx-background-color: BLACK");

        this.initalizeUI();

    }

    private void initalizeUI() {
        HBox UIPane = new HBox();

        this.painLabel = new Label("Pain: 0");
        this.painLabel.setTextFill(Color.WHITE);
        this.painLabel.setFont(myFont);

        UIPane.getChildren().add(painLabel);
        this.root.setTop(UIPane);
    }

    public Pane getRoot(){
        return this.root;
    }

    public void setKeyPress() {
        new Simulator(this);
    }

    public void setPain(int pain) {
        this.painLabel.setText("Pain:" + pain);
    }
}
