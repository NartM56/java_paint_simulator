package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class CircleStrategy implements EventHandler<MouseEvent>, ShapeStrategy {

    private PaintModel model;
    private PaintPanel panel;
    private Circle circle;
    private Color colour;
    public double thickness = 0.0;

    public CircleStrategy(PaintModel m, PaintPanel p, Color color) {
        this.model = m;
        this.panel = p;
        this.colour = color;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            System.out.println("Started Circle");
            Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.circle = new Circle(centre, 0, colour);
            this.circle.setThickness(thickness);
            this.model.dragshape(this.circle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point circleCenter = this.circle.getCentre();
            double dx = mouseEvent.getX() - circleCenter.x;
            double dy = mouseEvent.getY() - circleCenter.y;
            double radius = Math.sqrt(dx * dx + dy * dy);
            this.circle.setRadius(radius);
            this.model.dragshape(this.circle);

        } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if(this.circle!=null){
                double dx = mouseEvent.getX() - this.circle.getCentre().x;
                double dy = mouseEvent.getY() - this.circle.getCentre().y;
                double radius = Math.sqrt(dx * dx + dy * dy);
                this.circle.setRadius(radius);
                this.model.removecurrentshape();
                this.model.addshape(this.circle);
                this.model.setPreviousShape(this.circle);
                this.model.dragshape(null);
                System.out.println("Added Circle");
                this.circle = null;
            }
        }
    }

}
