package ca.utoronto.utm.assignment2.paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

        private View view;
        private Button selectedButton;
        String[] buttongraphicstrings;
        String buttoncolour;
        String selectedcolour;

        public ShapeChooserPanel(View view, String s) {

                this.view = view;

                String[] buttonLabels = { "Circle", "Oval", "Rectangle", "Square", "Triangle", "Squiggle", "Polyline" };

                switch (s) {
                        case "Tropical":
                                this.buttongraphicstrings = new String[]{getClass().getResource("/Images/Circlep.png").toString(),
                                        getClass().getResource("/Images/Ovalp.png").toString(),
                                        getClass().getResource("/Images/Rectanglep.png").toString(),
                                        getClass().getResource("/Images/Squarep.png").toString(),
                                        getClass().getResource("/Images/Trianglep.png").toString(),
                                        getClass().getResource("/Images/Squigglep.png").toString(),
                                        getClass().getResource("/Images/Polylinep.png").toString()};
                                buttoncolour = "-fx-background-color: #FFC0FF; -fx-text-fill: transparent";
                                selectedcolour = "-fx-background-color: pink; -fx-text-fill: transparent";
                                break;
                        case "Dark":
                                this.buttongraphicstrings = new String[]{getClass().getResource("/Images/Circlec.png").toString(),
                                        getClass().getResource("/Images/Ovalc.png").toString(),
                                        getClass().getResource("/Images/Rectanglec.png").toString(),
                                        getClass().getResource("/Images/Squarec.png").toString(),
                                        getClass().getResource("/Images/Trianglec.png").toString(),
                                        getClass().getResource("/Images/Squigglec.png").toString(),
                                        getClass().getResource("/Images/Polylinec.png").toString()};
                                buttoncolour = "-fx-background-color: #413838; -fx-text-fill: transparent";
                                selectedcolour = "-fx-background-color: #554b4b; -fx-text-fill: transparent";
                                break;
                        default:
                                this.buttongraphicstrings = new String[]{getClass().getResource("/Images/Circle.png").toString(),
                                        getClass().getResource("/Images/Oval.png").toString(),
                                        getClass().getResource("/Images/Rectangle.png").toString(),
                                        getClass().getResource("/Images/Square.png").toString(),
                                        getClass().getResource("/Images/Triangle.png").toString(),
                                        getClass().getResource("/Images/Squiggle.png").toString(),
                                        getClass().getResource("/Images/Polyline.png").toString()};
                                buttoncolour = "-fx-text-fill: transparent;";
                                selectedcolour = "-fx-background-color: yellow; -fx-text-fill: transparent";
                                break;


                }
                int row = 0;
                for (String label : buttonLabels) {
                        Button button = new Button(label);
                        Image image = new Image(buttongraphicstrings[row]);
                        button.setStyle(buttoncolour);
                        ImageView icon= new ImageView(image);
                        icon.setFitHeight(55);
                        icon.setFitWidth(75);
                        button.setGraphic(icon);
                        button.setMaxWidth(0);
                        this.add(button, row, 0);
                        row++;
                        button.setOnAction(this);
                }
        }

        @Override
        public void handle(ActionEvent event) {
                String command = ((Button) event.getSource()).getText();

                view.setMode(command);
                System.out.println(command);

                if (selectedButton != null) {
                        selectedButton.setStyle(buttoncolour);
                }

                selectedButton = (Button) event.getSource();
                selectedButton.setStyle(selectedcolour);
        }
}