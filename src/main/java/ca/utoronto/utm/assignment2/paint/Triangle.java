package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Triangle implements Shape, Drawable{
    private Point topCorner;
    private Point bottomLeftCorner;
    private Point bottomRightCorner;
    private Color color;
    private double thickness = 0.0;

    public Triangle(Point topCorner, Point bottomLeftCorner, Point bottomRightCorner, Color colour) {
        this.topCorner = topCorner;
        this.bottomLeftCorner = bottomLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        this.color = colour;
    }
    public void setTopCorner(Point topCorner) {
        this.topCorner = topCorner;
    }
    public void setBottomLeftCorner(Point bottomLeftCorner) {
        this.bottomLeftCorner = bottomLeftCorner;
    }
    public void setBottomRightCorner(Point bottomRightCorner) {
        this.bottomRightCorner = bottomRightCorner;
    }
    public Point getTopCorner() {
        return topCorner;
    }
    public Point getBottomLeftCorner() {
        return bottomLeftCorner;
    }
    public Point getBottomRightCorner() {
        return bottomRightCorner;
    }
    public String toString() {return "Triangle";}

    @Override
    public Triangle cloner() {
        double newX = this.topCorner.x + 60;
        double newX2 = this.bottomLeftCorner.x + 60;
        double newX3 = this.bottomRightCorner.x + 60;
        Point newTopCorner = new Point(newX, this.topCorner.y);
        Point newBottomLeftCorner = new Point(newX2, this.bottomLeftCorner.y);
        Point newBottomRightCorner = new Point(newX3, this.bottomRightCorner.y);
        return new Triangle(newTopCorner, newBottomLeftCorner, newBottomRightCorner, color);
    }

    @Override
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void draw(GraphicsContext g2d) {
        g2d.setFill(color);
        g2d.setStroke(Color.TRANSPARENT);
        if (thickness > 0.0) {
            g2d.setStroke(Color.BLACK);
        }
        g2d.setLineWidth(thickness);
        g2d.fillPolygon(new double[]{topCorner.x, bottomLeftCorner.x, bottomRightCorner.x}, new double[]{topCorner.y, bottomLeftCorner.y, bottomRightCorner.y}, 3);
        g2d.strokePolygon(new double[]{topCorner.x, bottomLeftCorner.x, bottomRightCorner.x}, new double[]{topCorner.y, bottomLeftCorner.y, bottomRightCorner.y}, 3);
    }
}
