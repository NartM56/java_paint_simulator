package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class TriangleStrategy implements EventHandler<MouseEvent>, ShapeStrategy {
    private PaintModel model;
    private PaintPanel panel;
    private Point topcorner;
    private Triangle currentTriangle;
    private Triangle triangle;
    private Color color;
    public double thickness = 0.0;

    public TriangleStrategy(PaintModel m, PaintPanel p, Color colour) {
        this.model = m;
        this.panel = p;
        this.color = colour;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            System.out.println("Started Triangle");
            topcorner = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.model.dragshape(this.triangle);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            if (topcorner != null) {
                Point bc = new Point(mouseEvent.getX(), mouseEvent.getY());
                if (topcorner.x > bc.x) {
                    double gap = topcorner.x - bc.x;
                    Point lbc = bc;
                    Point rbc = new Point(topcorner.x + gap, lbc.y);
                    currentTriangle = new Triangle(topcorner, lbc, rbc, color);
                    currentTriangle.setThickness(thickness);
                    this.model.dragshape(currentTriangle);
                } else if (topcorner.x <= bc.x) {
                    double gap = bc.x - topcorner.x;
                    Point rbc = bc;
                    Point lbc = new Point(topcorner.x - gap, rbc.y);
                    currentTriangle = new Triangle(topcorner, lbc, rbc, color);
                    currentTriangle.setThickness(thickness);
                    this.model.dragshape(currentTriangle);
                }
            }
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if (currentTriangle != null) {
                Point bc = new Point(mouseEvent.getX(), mouseEvent.getY());
                this.model.removecurrentshape();
                if (topcorner.x > bc.x) {
                    double gap = topcorner.x - bc.x;
                    Point lbc = bc;
                    Point rbc = new Point(topcorner.x + gap, lbc.y);
                    this.triangle = new Triangle(topcorner, lbc, rbc, color);
                    this.model.addshape(triangle);
                    System.out.println("Added Triangle");
                    topcorner = null;
                    currentTriangle = null;
                    this.triangle = null;
                } else if (topcorner.x <= bc.x) {
                    double gap = bc.x - topcorner.x;
                    Point rbc = bc;
                    Point lbc = new Point(topcorner.x - gap, rbc.y);
                    this.triangle = new Triangle(topcorner, lbc, rbc, color);
                    this.model.addshape(this.triangle);
                    this.model.setPreviousShape(this.triangle);
                    this.model.dragshape(null);
                    System.out.println("Added Triangle");
                    topcorner = null;
                    currentTriangle = null;
                    this.triangle = null;
                }
            }
        }
    }
}

