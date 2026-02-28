package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class SquiggleStrategy implements EventHandler<MouseEvent> , ShapeStrategy{

    private PaintModel model;
    private PaintPanel panel;
    private Color color;
    public double thickness = 1.0;

    public SquiggleStrategy(PaintModel m, PaintPanel p, Color c) {
        this.model = m;
        this.panel = p;
        this.color = c;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            Squiggle squiggle = new Squiggle(color);
            squiggle.setThickness(thickness);
            this.model.dragshape(squiggle);
            System.out.println("Started Squiggle");
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point s = new Point(mouseEvent.getX(), mouseEvent.getY());
            Squiggle squig = (Squiggle) this.model.getCurrentShape();
            if (squig != null) {
                squig.addsquiggles(s);
            } else {
                this.model.dragshape(new Squiggle(color));
                squig = (Squiggle) this.model.getCurrentShape();
                squig.addsquiggles(s);
            }
            this.model.dragshape(squig);

        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            this.model.addshape(this.model.getCurrentShape());
        }
    }
}
