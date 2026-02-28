package ca.utoronto.utm.assignment2.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
        private ArrayList<Shape> shapes = new ArrayList<>();
        private ArrayList<ArrayList<Shape>> undoList = new ArrayList<>();
        private ArrayList<ArrayList<Shape>> redoList = new ArrayList<>();
        private Shape currentShape;
        private Shape previousShape;
        private double lineThickness = 1.0;

        public void addshape(Shape shape) {
                addUndo();
                shape.setThickness(lineThickness);
                this.shapes.add(shape);
                redoList.clear();
                setChanged();
                notifyObservers();
        }

        public void dragshape(Shape shape) {
                this.currentShape = shape;
                setChanged();
                notifyObservers();
        }

        public void setLineThickness(double lineThickness) {
                this.lineThickness = lineThickness;
        }

        public double getLineThickness() {
                return this.lineThickness;
        }

        public Shape getCurrentShape() {return currentShape;}

        public void removecurrentshape() {currentShape = null;}

        public void setPreviousShape(Shape shape){
                this.previousShape = shape;
                setChanged();
                notifyObservers();
        }

        public ArrayList<Shape> getShapes() {return shapes;}

        public void addUndo() {
                undoList.add(new ArrayList<>(shapes));
        }

        public void undo() {
                if (!undoList.isEmpty()) {
                        redoList.add(new ArrayList<>(shapes));
                        shapes = undoList.remove(undoList.size() - 1);
                }
        }

        public void redo() {
                if (!redoList.isEmpty()) {
                        undoList.add(new ArrayList<>(shapes));
                        shapes = redoList.remove(redoList.size() - 1);
                }
        }

        public void cloneSelectedShape(PaintPanel paintPanel) {
                Shape previousShape = this.previousShape;
                if (previousShape != null) {
                        Shape clonedShape = previousShape.cloner();
                        addshape(clonedShape);
                        paintPanel.redraw();
                        this.previousShape = clonedShape;
                        setChanged();
                        notifyObservers();
                }
        }
}
