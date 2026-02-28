package ca.utoronto.utm.assignment2.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Rectangle implements Shape, Drawable {
    private Point topleft;
    private Point bottomright;
    private Color color;
    private double thickness = 0.0;

    public Rectangle(Point tl, Point br, Color colour) {
        this.topleft = tl;
        this.bottomright = br;
        this.color = colour;
    }

    public Point getTopleft() {
        return topleft;
    }

    public void setTopleft(Point topl) {
        this.topleft = topl;
    }

    public Point getBottomright() {
        return bottomright;
    }

    public void setBottomright(Point bottomr) {
        this.bottomright = bottomr;
    }

    public String toString(){return "Rectangle";}

    @Override
    public Rectangle cloner() {
        double newX = this.topleft.x + 60;
        double newX2 = this.bottomright.x + 60;
        Point newtopLeft = new Point(newX, this.topleft.y);
        Point newbottomRight = new Point(newX2, this.bottomright.y);
        return new Rectangle(newtopLeft, newbottomRight, color);
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
        double width = Math.abs(bottomright.x - topleft.x);
        double height = Math.abs(bottomright.y - topleft.y);
        g2d.fillRect(topleft.x, topleft.y, width, height);
        g2d.strokeRect(topleft.x, topleft.y, width, height);
    }
}
