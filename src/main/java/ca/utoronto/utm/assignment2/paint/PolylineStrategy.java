package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class PolylineStrategy implements EventHandler<MouseEvent> , ShapeStrategy{

    private PaintModel model;
    private PaintPanel panel;
    private Color color;
    public double thickness = 1.0;

    public PolylineStrategy(PaintModel m, PaintPanel p, Color c) {
        this.model = m;
        this.panel = p;
        this.color = c;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            Shape shape = this.model.getCurrentShape();
            if (shape instanceof Polyline polyline) {
                polyline.setThickness(thickness);
                this.model.dragshape(polyline);
                System.out.println("Started Polyline");
            } else {
                Polyline polyline = new Polyline(color);
                polyline.setThickness(thickness);
                this.model.dragshape(polyline);
                System.out.println("Started Polyline");
            }

        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Shape shape = this.model.getCurrentShape();
            if (shape instanceof Polyline polyline) {
                Point point = new Point(mouseEvent.getX(), mouseEvent.getY());
                polyline.addpolylines(point);
                this.model.dragshape(polyline);
            }

        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            this.model.addshape(this.model.getCurrentShape());
        }
    }
}
