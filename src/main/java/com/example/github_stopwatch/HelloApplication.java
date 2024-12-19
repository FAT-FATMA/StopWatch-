package com.example.github_stopwatch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}*/

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloApplication extends Application {
    private long startTime;
    private Timeline timeline;
    private Text timeDisplay;
    private boolean running = false;
    @Override
    public void start(Stage stage) {
        timeDisplay = new Text("00:00:00.000");
        timeDisplay.setStyle("-fx-font-size: 24px;");
        timeDisplay.setFill(Color.BROWN);

        Button startButton = new Button("Start");
        Button stopButton = new Button("Stop");
        Button resetButton = new Button("Reset");
        Button logoutButton= new Button("Logout");

        HBox buttonBox = new HBox(10, startButton, stopButton, resetButton, logoutButton);
        buttonBox.setStyle("-fx-alignment: center;");

        VBox root = new VBox(20, timeDisplay, buttonBox);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 300, 200);
        stage.setTitle("Stopwatch");
        Image logo =new Image("C:\\Users\\FATIMA\\demo\\src\\Clock.jpg");
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();

        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> updateDisplay()));
        timeline.setCycleCount(Animation.INDEFINITE);

        startButton.setOnAction(e -> {
            if (!running) {
                startTime = System.currentTimeMillis();
                timeline.play();
                running = true;
            }
        });

        stopButton.setOnAction(e -> {
            if (running) {
                timeline.stop();
                running = false;
            }
        });

        resetButton.setOnAction(e -> {
            timeline.stop();
            running = false;
            timeDisplay.setText("00:00:00.000");
        });
        logoutButton.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            public void handle(ActionEvent event) {
                System.out.println("You loged out...");
                stage.close();
            }
        });


    }

    private void updateDisplay() {
        long elapsed = System.currentTimeMillis() - startTime;
        long hours = elapsed / (60 * 60 * 1000);
        long minutes = (elapsed / (60 * 1000)) % 60;
        long seconds = (elapsed / 1000) % 60;
        long milliseconds = elapsed % 1000;

        timeDisplay.setText(String.format("%02d:%02d:%02d.%03d", hours, minutes, seconds, milliseconds));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
