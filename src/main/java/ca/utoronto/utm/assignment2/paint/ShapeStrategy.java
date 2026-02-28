package ca.utoronto.utm.assignment2.paint;

import javafx.scene.input.MouseEvent;

public interface ShapeStrategy {
    public default void handle(MouseEvent mouseEvent){}

}
