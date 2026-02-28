package ca.utoronto.utm.assignment2.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Shape, Drawable {
        private Point centre;
        private double radius;
        private Color color;
        private double thickness = 0.0;


        public Circle(Point centre, double radius, Color color) {
                this.centre = centre;
                this.radius = radius;
                this.color = color;
        }

        public Point getCentre() {
                return centre;
        }

        @Override
        public void setThickness(double thickness) {
                this.thickness = thickness;
        }

        public void setCentre(Point centre) {
                this.centre = centre;
        }

        public double getRadius() {
                return radius;
        }

        public void setRadius(double radius) {
                this.radius = radius;
        }

        public String toString(){return "Circle";}

        @Override
        public Circle cloner() {
                double newX = this.centre.x + 60;
                double newY = this.centre.y + 60;
                Point newCenter = new Point(newX, newY);
                return new Circle(newCenter, this.radius, this.color);
        }

        public void draw(GraphicsContext g2d) {
                g2d.setFill(color);
                g2d.setStroke(Color.TRANSPARENT);
                if (thickness > 0.0) {
                        g2d.setStroke(Color.BLACK);
                }
                g2d.setLineWidth(thickness);
                double x = this.getCentre().x - radius;
                double y = this.getCentre().y - radius;
                g2d.fillOval(x, y, 2 * radius, 2 * radius);
                g2d.strokeOval(x, y, 2 * radius, 2 * radius);
        }
}
