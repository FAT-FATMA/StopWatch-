module com.example.github_stopwatch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.github_stopwatch to javafx.fxml;
    exports com.example.github_stopwatch;
}