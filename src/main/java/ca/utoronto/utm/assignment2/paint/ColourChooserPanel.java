package ca.utoronto.utm.assignment2.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ColourChooserPanel extends GridPane implements EventHandler<ActionEvent> {

    View view;
    PaintPanel paintPanel;
    Button selectedbutton;

    public ColourChooserPanel(View v, PaintPanel paintPane) {
        this.view = v;
        this.paintPanel = paintPane;

        String[] buttonLabels = {"black", "grey", "red", "orange", "yellow", "mocassin", "green", "aqua", "blue", "purple",
                "white", "brown", "pink", "gold", "lightyellow", "lime", "lightskyblue",
                "lightslategrey", "lavender", "orchid"};

        String[] buttongraphics = {getClass().getResource("/Images/black.png").toString(),
                getClass().getResource("/Images/grey.png").toString(),
                getClass().getResource("/Images/red.png").toString(),
                getClass().getResource("/Images/orange.png").toString(),
                getClass().getResource("/Images/yellow.png").toString(),
                getClass().getResource("/Images/mocassin.png").toString(),
                getClass().getResource("/Images/green.png").toString(),
                getClass().getResource("/Images/aqua.png").toString(),
                getClass().getResource("/Images/blue.png").toString(),
                getClass().getResource("/Images/purple.png").toString(),
                getClass().getResource("/Images/white.png").toString(),
                getClass().getResource("/Images/brown.png").toString(),
                getClass().getResource("/Images/pink.png").toString(),
                getClass().getResource("/Images/gold.png").toString(),
                getClass().getResource("/Images/lightyellow.png").toString(),
                getClass().getResource("/Images/lime.png").toString(),
                getClass().getResource("/Images/lightskyblue.png").toString(),
                getClass().getResource("/Images/lightslategrey.png").toString(),
                getClass().getResource("/Images/lavender.png").toString(),
                getClass().getResource("/Images/orchid.png").toString()
        };

        int row = 0;
        for (String label : buttonLabels) {
            Button button = new Button(label);
            Image image = new Image(buttongraphics[row]);
            ImageView icon = new ImageView(image);
            icon.setFitHeight(20);
            icon.setFitWidth(20);
            button.setStyle("-fx-text-fill: transparent;");
            button.setGraphic(icon);
            button.setGraphicTextGap(-7.5);
            Circle circ = new Circle();
            circ.setRadius(40.0);
            button.setShape(circ);
            button.setMaxWidth(30);

            if (row < 10) {
                this.add(button, row, 0);
            } else {
                this.add(button, row - 10, 1);
            }
            row++;
            button.setOnAction(this);
        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        String colour = button.getText();
        switch (colour) {
            case "black":
                this.paintPanel.setColor(Color.BLACK);
                break;
            case "grey":
                this.paintPanel.setColor(Color.GREY);
                break;
            case "red":
                this.paintPanel.setColor(Color.RED);
                break;
            case "orange":
                this.paintPanel.setColor(Color.ORANGE);
                break;
            case "yellow":
                this.paintPanel.setColor(Color.YELLOW);
                break;
            case "mocassin":
                this.paintPanel.setColor(Color.MOCCASIN);
                break;
            case "green":
                this.paintPanel.setColor(Color.GREEN);
                break;
            case "aqua":
                this.paintPanel.setColor(Color.AQUA);
                break;
            case "blue":
                this.paintPanel.setColor(Color.BLUE);
                break;
            case "purple":
                this.paintPanel.setColor(Color.PURPLE);
                break;
            case "white":
                this.paintPanel.setColor(Color.WHITE);
                break;
            case "brown":
                this.paintPanel.setColor(Color.BROWN);
                break;
            case "pink":
                this.paintPanel.setColor(Color.PINK);
                break;
            case "gold":
                this.paintPanel.setColor(Color.GOLD);
                break;
            case "lightyellow":
                this.paintPanel.setColor(Color.LIGHTYELLOW);
                break;
            case "lime":
                this.paintPanel.setColor(Color.LIME);
                break;
            case "lightskyblue":
                this.paintPanel.setColor(Color.LIGHTSKYBLUE);
                break;
            case "lightslategrey":
                this.paintPanel.setColor(Color.LIGHTSLATEGRAY);
                break;
            case "lavender":
                this.paintPanel.setColor(Color.LAVENDER);
                break;
            case "orchid":
                this.paintPanel.setColor(Color.ORCHID);
                break;
            default:
                break;
        }
    }
}
