package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.nio.file.Paths;

public class PartiesApplication extends Application{
    
    public void start(Stage stage){
        
        // Reading info file and saving in an ArrayList;
        ArrayList<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(Paths.get("partiesdata.tsv"))){
            while (scanner.hasNextLine()){
                String row = scanner.nextLine();
                data.add(row);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        HashMap<String, HashMap<Integer, Double>> values = new HashMap<>();
        
        for (int i = 1; i < data.size(); i++){
            String yearList = data.get(0);
            String[] year = yearList.split("\t");
            String row = data.get(i);
            String[] pieces = row.split("\t");
            HashMap<Integer, Double> yearPercentage = new HashMap<>();
            for (int j = 1; j < year.length; j++){
                if (pieces[j].equals("-")){
                    continue;
                } else {
                    yearPercentage.put(Integer.valueOf(year[j]), Double.valueOf(pieces[j]));
                }
            }
            values.put(pieces[0], yearPercentage);
        }
        
        NumberAxis xAxis = new NumberAxis(1968, 2008, 4);
        NumberAxis yAxis = new NumberAxis(0, 30, 5);
        
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties");
        
        values.keySet().stream().forEach(party -> {
            XYChart.Series chartData = new XYChart.Series();
            chartData.setName(party);
            values.get(party).entrySet().stream().forEach(pair -> {
                chartData.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
            });
            lineChart.getData().add(chartData);
        });
        
        Scene view = new Scene(lineChart, 640, 480);
        stage.setScene(view);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(PartiesApplication.class);
    }

}
