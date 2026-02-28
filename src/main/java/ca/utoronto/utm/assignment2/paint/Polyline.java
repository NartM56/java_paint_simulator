package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;

public class Polyline implements Shape, Drawable {

    private ArrayList<Point> polylines = new ArrayList<>();
    private Color color;
    private double thickness = 1.0;

    public Polyline(Color colour) {
        this.color = colour;
    }

    public void addpolylines(Point p) {
        this.polylines.add(p);
    }

    public ArrayList<Point> getPolylines() {
        return polylines;
    }

    public String toString() {
        return "Polyline";
    }

    @Override
    public Polyline cloner() {
        return new Polyline(color);
    }

    @Override
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void draw(GraphicsContext g2d) {
        g2d.setStroke(color);
        g2d.setLineWidth(thickness);
        ArrayList<Point> points = this.getPolylines();
        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2d.strokeLine(p1.x, p1.y, p2.x, p2.y);
        }
    }
}
