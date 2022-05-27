package com.example.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Group group = new Group();
        Scene scene = new Scene(group, 700, 700);

        Button button_red = new Button("button 1");
        button_red.setLayoutX(0);
        button_red.setLayoutY(0);
        double btn1_sizeX = 200;
        double btn1_sizeY = 100;
        button_red.setStyle("-fx-background-color: red");
        button_red.setPrefSize(btn1_sizeX, btn1_sizeY);
        group.getChildren().add(button_red);

        Button button_green = new Button("button 2");
        button_green.setLayoutX(0);
        button_green.setLayoutY(300);
        double btn2_sizeX = 200;
        double btn2_sizeY = 100;
        button_green.setStyle("-fx-background-color: green");
        button_green.setPrefSize(btn2_sizeX, btn2_sizeY);
        group.getChildren().add(button_green);

        Label label1 = new Label("Area is");
        label1.setLayoutY(560);
        label1.setLayoutX(200);
        group.getChildren().add(label1);

        Label area_value = new Label();
        area_value.setLayoutY(600);
        group.getChildren().add(area_value);

        final double[] btn1_coords = new double[2];

        button_red.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn1_coords[0] = mouseEvent.getX();
                btn1_coords[1] = mouseEvent.getY();
            }
        });

        final double[] btn2_coords = new double[2];

        button_green.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn2_coords[0] = mouseEvent.getX();
                btn2_coords[1] = mouseEvent.getY();
            }
        });

        double[] btn_1 = new double[2];
        double[] btn_2 = new double[2];
        double[] coords1 = new double[2];

        button_red.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                coords1[0] = mouseEvent.getSceneX() - btn1_coords[0];
                coords1[1] = mouseEvent.getSceneY() - btn1_coords[1];
                button_red.setLayoutX(coords1[0]);
                button_red.setLayoutY(coords1[1]);
                btn_1[0] = button_red.getLayoutX();
                btn_1[1] = button_red.getLayoutY();
                btn_2[0] = button_green.getLayoutX();
                btn_2[1] = button_green.getLayoutY();
                double area = areaCalculate(btn1_sizeX, btn1_sizeY, btn2_sizeX, btn2_sizeY, btn_1, btn_2);
                area_value.setText(String.valueOf(area));
            }
        });

        double[] coords2 = new double[2];
        button_green.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                coords2[0] = mouseEvent.getSceneX() - btn2_coords[0];
                coords2[1] = mouseEvent.getSceneY() - btn2_coords[1];
                button_green.setLayoutX(coords2[0]);
                button_green.setLayoutY(coords2[1]);
                btn_1[0] = button_red.getLayoutX();
                btn_1[1] = button_red.getLayoutY();
                btn_2[0] = button_green.getLayoutX();
                btn_2[1] = button_green.getLayoutY();
                double area = areaCalculate(btn1_sizeX, btn1_sizeY, btn2_sizeX, btn2_sizeY, btn_1, btn_2);
                area_value.setText(String.valueOf(area));
            }
        });

        
        stage.setScene(scene);
        stage.setTitle("Area");
        stage.show();
    }


    public double areaCalculate(double button1sizeX, double button1sizeY, double button2sizeX, double button2sizeY, double[] coords1, double[] coords2) {
        double area = 0;
        if ((Math.abs(coords1[0] - coords2[0]) > 300) || (Math.abs(coords1[1] - coords2[1]) > 150)) {
            area = 0;
        } else if (coords1[0] <= coords2[0]) {
            if (coords1[1] <= coords2[1]) {
                area = (coords1[0] + button1sizeX - coords2[0]) * (coords1[1] + button1sizeY - coords2[1]);
            } else {
                area = (coords1[0] + button1sizeX - coords2[0]) * (coords2[1] + button2sizeY - coords1[1]);
            }
        } else {
            if (coords1[1] <= coords2[1]) {
                area = (coords2[0] + button2sizeX - coords1[0]) * (coords1[1] + button1sizeY - coords2[1]);
            } else {
                area = (coords2[0] + button2sizeX - coords1[0]) * (coords2[1] + button2sizeY - coords1[1]);
            }
        }
        return area;
    }
    public static void main(String[] args) {
        launch();
    }
}