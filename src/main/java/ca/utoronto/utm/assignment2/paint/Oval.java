package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Oval implements Shape, Drawable {
    private Point centre;
    private double hradius;
    private double vradius;
    private Color color;
    private double thickness = 0.0;

    public Oval(Point centre, double hradius, double vradius, Color colour) {
        this.centre = centre;
        this.hradius = hradius;
        this.vradius = vradius;
        this.color = colour;
    }
    public Point getCentre() {
        return centre;
    }
    public void setCentre(Point centre) {
        this.centre = centre;
    }
    public double getHradius() {
        return hradius;
    }
    public void setHradius(double hradius) {
        this.hradius = hradius;
    }
    public double getVradius() {
        return vradius;
    }
    public void setVradius(double vradius) {
        this.vradius = vradius;
    }
    public String toString() {return "Oval";}

    public void draw(GraphicsContext g2d) {
        g2d.setFill(color);
        g2d.setStroke(Color.TRANSPARENT);
        if (thickness > 0.0) {
            g2d.setStroke(Color.BLACK);
        }
        g2d.setLineWidth(thickness);
        g2d.fillOval(centre.x, centre.y, hradius, vradius);
        g2d.strokeOval(centre.x, centre.y, hradius, vradius);
    }

    @Override
    public Oval cloner() {
        double newX = this.centre.x + 60;
        double newY = this.centre.y + 60;
        Point newCenter = new Point(newX, newY);
        return new Oval(newCenter, this.hradius, this.vradius, color);
    }

    @Override
    public void setThickness(double thickness) {
        this.thickness = thickness;
    }
}
