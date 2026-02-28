package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square implements Shape, Drawable{
    private Point topleft;
    private double size;
    private Color color;
    private double thickness = 0.0;

    public Square(Point tl, double size, Color colour) {
        this.topleft = tl;
        this.size = size;
        this.color = colour;
    }

    public Point gettopleft() {
        return topleft;
    }

    public void setTopleft(Point x) {
        this.topleft = x;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String toString() {return "Square";}

    @Override
    public Square cloner() {
        double newX = this.topleft.x + 60;
        Point newTopLeft = new Point(newX, this.topleft.y);
        return new Square(newTopLeft, this.size, color);
    }

    @Override
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public void draw(GraphicsContext g2d){
        g2d.setFill(color);
        g2d.setStroke(Color.TRANSPARENT);
        if (thickness > 0.0) {
            g2d.setStroke(Color.BLACK);
        }
        g2d.setLineWidth(thickness);
        g2d.fillRect(topleft.x, topleft.y, size, size);
        g2d.strokeRect(topleft.x, topleft.y, size, size);
    }
}