package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SquareStrategy implements EventHandler<MouseEvent> , ShapeStrategy{
    private PaintModel model;
    private PaintPanel panel;
    private Point topLeft;
    private Square currentSquare;
    private Color color;
    public double thickness = 0.0;

    public SquareStrategy(PaintModel m, PaintPanel p, Color colour) {
        this.model = m;
        this.panel = p;
        this.color = colour;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            System.out.println("Started Square");
            topLeft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.model.dragshape(this.currentSquare);
        }
        else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            if (topLeft != null) {
                double size = Math.max(Math.abs(mouseEvent.getX() - topLeft.x), Math.abs(mouseEvent.getY() - topLeft.y));
                currentSquare = new Square(topLeft, size, color);
                this.currentSquare.setThickness(thickness);
                this.model.dragshape(currentSquare);
            }
        }
        else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if (currentSquare != null) {
                double size = Math.max(Math.abs(mouseEvent.getX() - topLeft.x), Math.abs(mouseEvent.getY() - topLeft.y));
                this.model.removecurrentshape();
                Square square = new Square(topLeft, size, color);
                this.model.addshape(square);
                this.model.setPreviousShape(square);
                this.model.dragshape(null);
                System.out.println("Added Square");
                topLeft = null;
                currentSquare = null;
            }
        }
    }
}