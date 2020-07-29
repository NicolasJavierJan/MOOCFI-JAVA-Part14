package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.beans.binding.Bindings;

public class SavingsCalculatorApplication extends Application{
    
    public void start(Stage stage){
        BorderPane layout = new BorderPane();
        
        NumberAxis xAxis = new NumberAxis(0, 30, 1);
        NumberAxis yAxis = new NumberAxis();
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        
        XYChart.Series savings = new XYChart.Series();
        XYChart.Series interest = new XYChart.Series();
        
        VBox verticalLayout = new VBox();
        verticalLayout.setSpacing(10);
        
        BorderPane firstBorder = new BorderPane();
        firstBorder.setLeft(new Label("Monthly savings"));
        Slider firstSlider = new Slider(25, 250, 25);
        firstSlider.setShowTickMarks(true);
        firstSlider.setShowTickLabels(true);        
        Label firstSliderLabel = new Label("25.0");
        firstBorder.setCenter(firstSlider);
        firstBorder.setRight(firstSliderLabel);        
        
        firstSliderLabel.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        firstSlider.valueProperty()
                )
        );
        
        firstSlider.setOnMouseReleased(event -> {
            savings.getData().clear(); 
            for (int i = 0; i <= 30; i++){
                int savingCalculator = i * Integer.valueOf(firstSliderLabel.getText()) * 12;
                savings.getData().add(new XYChart.Data(i, savingCalculator));                
        }                     
        });
        
        lineChart.getData().add(savings);  
        
        
        BorderPane secondBorder = new BorderPane();
        secondBorder.setLeft(new Label("Yearly interest rate"));
        Slider secondSlider = new Slider(0, 10, 0);
        Label secondSliderLabel = new Label("0.0");
        secondSliderLabel.textProperty().bind(
                Bindings.format(
                        "%.0f",
                        secondSlider.valueProperty()
                )
        );
        
        secondSlider.setOnMouseReleased(event -> {            
            interest.getData().clear();
            interest.getData().add(new XYChart.Data(0, 0));
            double percentage = Double.valueOf(secondSliderLabel.getText()) / 100;            
            double total = 0;
            double actual = Double.valueOf(firstSliderLabel.getText()) * 12;
            for (int i = 1; i <= 30; i++){
                actual = actual * percentage + actual;
                total = total + actual;
                interest.getData().add(new XYChart.Data(i, total));                
        }                     
        });
        
        lineChart.getData().add(interest);
        
        
        secondBorder.setCenter(secondSlider);
        secondBorder.setRight(secondSliderLabel);           
        
        verticalLayout.getChildren().addAll(firstBorder, secondBorder);
        
        layout.setCenter(lineChart);
        layout.setTop(verticalLayout);
        
        Scene view = new Scene(layout, 640, 480);
        stage.setScene(view);
        stage.show();
    }

    public static void main(String[] args) {
        launch(SavingsCalculatorApplication.class);
    }

}
