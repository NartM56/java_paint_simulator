package ca.utoronto.utm.assignment2.paint;
import javafx.scene.canvas.Canvas;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PaintPanel extends Canvas implements EventHandler<MouseEvent>, Observer {
    private String mode="Circle";
    private PaintModel model;
    private final Map<String, ShapeStrategy> strategyMap = new HashMap<>();
    private CircleStrategy circleStrategy;
    private RectangleStrategy rectangleStrategy;
    private SquiggleStrategy squiggleStrategy;
    private TriangleStrategy triangleStrategy;
    private OvalStrategy ovalStrategy;
    private SquareStrategy squareStrategy;
    private PolylineStrategy polylineStrategy;
    private double zoomLevel = 1.0;
    private Color colour = Color.BLACK;
    private Color outline = Color.BLACK;

    public PaintPanel(PaintModel model) {
        super(300, 300);
        this.model=model;
        this.model.addObserver(this);

        this.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        this.addEventHandler(MouseEvent.MOUSE_RELEASED, this);
        this.addEventHandler(MouseEvent.MOUSE_MOVED, this);
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
        this.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
        this.setColor(this.colour);
    }

    public void shapeHandler(){
        strategyMap.put("Circle", circleStrategy);
        strategyMap.put("Rectangle", rectangleStrategy);
        strategyMap.put("Squiggle", squiggleStrategy);
        strategyMap.put("Triangle", triangleStrategy);
        strategyMap.put("Oval", ovalStrategy);
        strategyMap.put("Square", squareStrategy);
        strategyMap.put("Polyline", polylineStrategy);
    }

    public void setZoomLevel(double zoomLevel) {
        this.zoomLevel = zoomLevel;
        redraw();
    }

    public void setColor(Color colour) {
        this.colour = colour;
        this.circleStrategy = new CircleStrategy(this.model, this, colour);
        this.rectangleStrategy = new RectangleStrategy(this.model, this, colour);
        this.squiggleStrategy = new SquiggleStrategy(this.model, this, colour);
        this.triangleStrategy = new TriangleStrategy(this.model, this, colour);
        this.ovalStrategy = new OvalStrategy(this.model, this, colour);
        this.squareStrategy = new SquareStrategy(this.model, this, colour);
        this.polylineStrategy = new PolylineStrategy(this.model, this, colour);
        this.setThickness(this.model.getLineThickness());
    }

    public void setThickness(double thickness) {
        this.circleStrategy.thickness = thickness;
        this.rectangleStrategy.thickness = thickness;
        this.squareStrategy.thickness = thickness;
        this.triangleStrategy.thickness = thickness;
        this.ovalStrategy.thickness = thickness;
        this.squiggleStrategy.thickness = thickness;
        this.polylineStrategy.thickness = thickness;
    }

    public double getZoomLevel() {
        return zoomLevel;
    }

    public void redraw() {
        GraphicsContext g2d = getGraphicsContext2D();
        g2d.clearRect(0, 0, getWidth(), getHeight());
        drawShapes(g2d);
    }

    public void drawShapes(GraphicsContext g2d) {
        g2d.save();
        g2d.scale(zoomLevel, zoomLevel);

        for (Shape shape : model.getShapes()) {
            ((Drawable) shape).draw(g2d);
        }

        g2d.restore();
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
        // Later when we learn about inner classes...
        // https://docs.oracle.com/javafx/2/events/DraggablePanelsExample.java.htm

        EventType<MouseEvent> mouseEventType = (EventType<MouseEvent>) mouseEvent.getEventType();
        shapeHandler();
        ShapeStrategy strategies = strategyMap.get(this.mode);

        // "Circle", "Rectangle", "Square", "Squiggle", "Polyline", "Triangle", "Oval"
        if (strategies != null) {
            strategies.handle(mouseEvent);
        }
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg != null) {this.mode = (String) arg; }

        ArrayList<Shape> shapes = this.model.getShapes();
        Shape currentshape = this.model.getCurrentShape();

        GraphicsContext g2d = this.getGraphicsContext2D();
        g2d.clearRect(0, 0, this.getWidth(), this.getHeight());

        // draw shapes
        for (Shape shape : shapes) {
            ((Drawable) shape).draw(g2d);
        }

        // draw current shape
        if (currentshape instanceof Drawable && currentshape != null){
            ((Drawable) currentshape).draw(g2d);
        }
    }
}
