package ca.utoronto.utm.assignment2.paint;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.util.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelFormat;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



public class View extends Observable implements EventHandler<ActionEvent> {

        private PaintModel paintModel;
        private PaintPanel paintPanel;
        private ShapeChooserPanel shapeChooserPanel;
        private VBox chooserbox;
        private BorderPane root;
        private HBox paintbox;
        private VBox colourbox;

        public View(PaintModel model, Stage stage) {
            this.paintModel = model;

            this.paintPanel = new PaintPanel(this.paintModel);
            this.addObserver(this.paintPanel);
            this.shapeChooserPanel = new ShapeChooserPanel(this, "Light");
            chooserbox = new VBox();
            chooserbox.getChildren().addAll(createMenuBar(), this.shapeChooserPanel);
            this.paintPanel.setHeight(400);
            this.paintPanel.setWidth(600);
            this.colourbox = new VBox();
            ColourChooserPanel ccp = new ColourChooserPanel(this, this.paintPanel);
            ccp.setAlignment(Pos.CENTER);
            this.colourbox.getChildren().add(ccp);

            HBox hbox = new HBox();
            this.paintbox = hbox;
            hbox.setMaxWidth(600);
            hbox.setMaxHeight(400);
            hbox.getChildren().addAll(this.paintPanel);
            BorderStroke bs = new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
            Border border = new Border(bs);
            hbox.setBorder(border);

            BorderPane root = new BorderPane();
            this.root = root;
            root.setTop(chooserbox);
            root.setCenter(paintbox);
            root.setBottom(colourbox);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("IPainterPro");
            stage.show();
            stage.getIcons().add(new Image(getClass().getResource("/Images/paint-bucket.png").toString()));
        }

    public void saveCanvasAsImage(Canvas canvas) {
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Canvas Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());

        if (file != null) {
            try {
                BufferedImage bufferedImage = new BufferedImage((int) writableImage.getWidth(),
                        (int) writableImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

                PixelReader pixelReader = writableImage.getPixelReader();
                for (int y = 0; y < writableImage.getHeight(); y++) {
                    for (int x = 0; x < writableImage.getWidth(); x++) {
                        int argb = pixelReader.getArgb(x, y);
                        bufferedImage.setRGB(x, y, argb);
                    }
                }
                ImageIO.write(bufferedImage, "png", file);
                System.out.println("Canvas saved successfully to " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

        // ugly way to do this?
        public void setMode(String mode){
            setChanged();
            notifyObservers(mode);
        }

        private MenuBar createMenuBar() {

                MenuBar menuBar = new MenuBar();
                Menu menu;
                MenuItem menuItem;

                // A menu for File

                menu = new Menu("File");

                menuItem = new MenuItem("New");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Open");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Save");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());

                menuItem = new MenuItem("Exit");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // Another menu for Edit

                menu = new Menu("Edit");

                menuItem = new MenuItem("Cut");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Copy");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Paste");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Duplicate");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menu.getItems().add(new SeparatorMenuItem());
                menuItem = new MenuItem("Undo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuItem = new MenuItem("Redo");
                menuItem.setOnAction(this);
                menu.getItems().add(menuItem);

                menuBar.getMenus().add(menu);

                // new menu for view
                Menu zoommenu = new Menu("View");

                menuItem = new MenuItem("Reset Zoom");
                menuItem.setOnAction(this);
                zoommenu.getItems().add(menuItem);

                menuItem = new MenuItem("Zoom In");
                menuItem.setOnAction(this);
                zoommenu.getItems().add(menuItem);

                menuItem = new MenuItem("Zoom Out");
                menuItem.setOnAction(this);
                zoommenu.getItems().add(menuItem);

                menuBar.getMenus().add(zoommenu);

                // new menu for themes

                Menu thememenu = new Menu("Theme");

                MenuItem themeitem1 = new MenuItem("Light");
                themeitem1.setOnAction(this);
                thememenu.getItems().add(themeitem1);

                MenuItem themeitem2 = new MenuItem("Dark");
                themeitem2.setOnAction(this);
                thememenu.getItems().add(themeitem2);

                MenuItem themeitem3 = new MenuItem("Tropical");
                themeitem3.setOnAction(this);
                thememenu.getItems().add(themeitem3);

                menuBar.getMenus().add(thememenu);

                Menu thickenssmenu = new Menu("Thickness");

                MenuItem thicknessItem0 = new MenuItem("0 (No border)");
                thicknessItem0.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem0);

                MenuItem thicknessItem1 = new MenuItem("1");
                thicknessItem1.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem1);

                MenuItem thicknessItem2 = new MenuItem("2");
                thicknessItem2.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem2);

                MenuItem thicknessItem3 = new MenuItem("3");
                thicknessItem3.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem3);

                MenuItem thicknessItem4 = new MenuItem("4");
                thicknessItem4.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem4);

                MenuItem thicknessItem5 = new MenuItem("5");
                thicknessItem5.setOnAction(this);
                thickenssmenu.getItems().add(thicknessItem5);

                menuBar.getMenus().add(thickenssmenu);

                return menuBar;
        }


        @Override
        public void handle(ActionEvent event) {
                System.out.println(((MenuItem) event.getSource()).getText());
                String command = ((MenuItem) event.getSource()).getText();

                switch (command) {
                    case "Save":
                        saveCanvasAsImage(paintPanel);
                    case "Exit":
                        Platform.exit();
                        break;
                    case "New":
                        this.paintModel = new PaintModel();
                        this.paintPanel = new PaintPanel(this.paintModel);
                        this.addObserver(this.paintPanel);
                        this.paintPanel.setHeight(400);
                        this.paintPanel.setWidth(600);
                        this.paintbox.getChildren().clear();
                        this.paintbox.getChildren().add(this.paintPanel);

                        this.colourbox.getChildren().clear();
                        ColourChooserPanel ccp2 = new ColourChooserPanel(this, this.paintPanel);
                        ccp2.setAlignment(Pos.CENTER);
                        this.colourbox.getChildren().add(ccp2);
                    case "Duplicate":
                        paintModel.cloneSelectedShape(paintPanel);
                        break;
                    case "Undo":
                        paintModel.undo();
                        paintPanel.redraw();
                        break;
                    case "Redo":
                        paintModel.redo();
                        paintPanel.redraw();
                        break;
                    case "Reset Zoom":
                        paintPanel.setZoomLevel(1.0);
                        break;
                    case "Zoom In":
                        double newZoomLevel = paintPanel.getZoomLevel() * 1.1;
                        paintPanel.setZoomLevel(newZoomLevel);
                        break;
                    case "Zoom Out":
                        double newZoomLevelOut = paintPanel.getZoomLevel() / 1.1;
                        paintPanel.setZoomLevel(newZoomLevelOut);
                        break;
                    case "Light":
                        this.shapeChooserPanel = new ShapeChooserPanel(this, "Light");
                        chooserbox.getChildren().removeLast();
                        chooserbox.getChildren().add(this.shapeChooserPanel);
                        this.root.setStyle( "-fx-text-fill: transparent;");
                        break;
                    case "Dark":
                        this.shapeChooserPanel = new ShapeChooserPanel(this, "Dark");
                        chooserbox.getChildren().removeLast();
                        chooserbox.getChildren().add(this.shapeChooserPanel);
                        this.root.setStyle("-fx-background-color: #554b4b");
                        break;
                    case "Tropical":
                        this.shapeChooserPanel = new ShapeChooserPanel(this, "Tropical");
                        chooserbox.getChildren().removeLast();
                        chooserbox.getChildren().add(this.shapeChooserPanel);
                        this.root.setStyle("-fx-background-color: pink");
                        break;
                    case "1":
                        paintModel.setLineThickness(1.0);
                        paintPanel.setThickness(1.0);
                        break;
                    case "2": ;
                        paintModel.setLineThickness(2.0);
                        paintPanel.setThickness(2.0);
                        break;
                    case "3":
                        paintModel.setLineThickness(3.0);
                        paintPanel.setThickness(3.0);
                        break;
                    case "4":
                        paintModel.setLineThickness(4.0);
                        paintPanel.setThickness(4.0);
                        break;
                    case "5":
                        paintModel.setLineThickness(5.0);
                        paintPanel.setThickness(5.0);
                        break;
                    case "0 (No border)":
                        paintModel.setLineThickness(0.0);
                        paintPanel.setThickness(0.0);
                }

        }

}
