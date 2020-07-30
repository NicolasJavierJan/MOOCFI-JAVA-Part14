package smiley;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class SmileyApplication extends Application{
    
    public void start(Stage window){
        
        Canvas paintingCanvas = new Canvas(800, 800);
        GraphicsContext painter = paintingCanvas.getGraphicsContext2D();
        
        painter.setFill(Color.BLACK);
        painter.fillRect(200,100,100,100);
        painter.fillRect(500, 100, 100, 100);
        painter.fillRect(100, 400, 100, 100);
        painter.fillRect(200, 500, 100, 100);
        painter.fillRect(300, 500, 100, 100);
        painter.fillRect(400, 500, 100, 100);
        painter.fillRect(500, 500, 100, 100);
        painter.fillRect(600, 400, 100, 100);

        BorderPane paintingLayout = new BorderPane();
        paintingLayout.setCenter(paintingCanvas);
        
        paintingCanvas.setOnMouseDragged((event) -> {
            double xLocation = event.getX();
            double yLocation = event.getY();
            painter.fillOval(xLocation, yLocation, 4, 4);
        });

        Scene view = new Scene(paintingLayout, Color.WHITE);

        window.setScene(view);
        window.show();
    }


    public static void main(String[] args) {
        launch(SmileyApplication.class);
    }

}
