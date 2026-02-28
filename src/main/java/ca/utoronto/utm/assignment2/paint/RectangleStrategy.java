package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class RectangleStrategy implements EventHandler<MouseEvent>, ShapeStrategy{

    private PaintModel model;
    private PaintPanel panel;
    private Rectangle rectangle;
    private Point topleft;
    private Rectangle currentRectangle;
    private Color color;
    public double thickness = 0.0;

    public RectangleStrategy(PaintModel m, PaintPanel p, Color colour) {
        this.model = m;
        this.panel = p;
        this.color = colour;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            System.out.println("Started Rectangle");
            topleft = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.model.dragshape(this.rectangle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            if (topleft != null) {
                Point br = new Point(mouseEvent.getX(), mouseEvent.getY());
                currentRectangle = new Rectangle(topleft, br, color);
                currentRectangle.setThickness(thickness);
                this.model.dragshape(currentRectangle);
            }
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if (currentRectangle != null) {
                Point br2 = new Point(mouseEvent.getX(), mouseEvent.getY());
                this.model.removecurrentshape();
                this.rectangle = new Rectangle(topleft, br2, color);
                this.model.addshape(this.rectangle);
                this.model.setPreviousShape(this.rectangle);
                this.model.dragshape(null);
                System.out.println("Added Rectangle");
                topleft = null;
                currentRectangle = null;
                this.rectangle = null;
            }
        }
    }
}
