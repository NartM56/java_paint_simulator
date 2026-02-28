package ca.utoronto.utm.assignment2.paint;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class OvalStrategy implements EventHandler<MouseEvent> , ShapeStrategy {

    private PaintModel model;
    private PaintPanel panel;
    private Oval oval;
    private Oval currentOval;
    private Color color;
    public double thickness = 0.0;

    public OvalStrategy(PaintModel m, PaintPanel p, Color c) {
        this.model = m;
        this.panel = p;
        this.color = c;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();

        if (mouseEventType.equals(MouseEvent.MOUSE_PRESSED)) {
            System.out.println("Started Oval");
            Point centre = new Point(mouseEvent.getX(), mouseEvent.getY());
            this.currentOval = new Oval(centre, 0, 0, color);
            this.currentOval.setThickness(thickness);
            this.model.dragshape(this.oval);
        } else if (mouseEventType.equals(MouseEvent.MOUSE_DRAGGED)) {
            Point ovalCenter = currentOval.getCentre();
            double dx = Math.abs(mouseEvent.getX() - ovalCenter.x);
            double dy = Math.abs(mouseEvent.getY() - ovalCenter.y);
            this.currentOval.setHradius(dx);
            this.currentOval.setVradius(dy);
            this.model.dragshape(this.currentOval);

        } else if (mouseEventType.equals(MouseEvent.MOUSE_MOVED)) {
        } else if (mouseEventType.equals(MouseEvent.MOUSE_RELEASED)) {
            if(this.currentOval!=null){
                double dx = Math.abs(mouseEvent.getX() - this.currentOval.getCentre().x);
                double dy = Math.abs(mouseEvent.getY() - this.currentOval.getCentre().y);
                this.model.removecurrentshape();
                this.oval = new Oval(this.currentOval.getCentre(), dx, dy, color);
                this.model.addshape(this.oval);
                this.model.setPreviousShape(this.oval);
                this.model.dragshape(null);
                System.out.println("Added Oval");
                this.oval = null;
            }
        }
    }

}
