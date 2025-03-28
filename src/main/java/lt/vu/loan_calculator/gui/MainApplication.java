package lt.vu.loan_calculator.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main entry point for the Loan Calculator JavaFX application.
 * <p>
 * This class initializes and displays the main application window.
 */
public class MainApplication extends Application {

    /**
     * Starts the JavaFX application by loading the main view and configuring the stage.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If the FXML file for the main view cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        MainController mainController = fxmlLoader.getController();
        mainController.configureButtons();
        stage.setTitle("Būsto paskolos skaičiuoklė");
        stage.setMinWidth(380);
        stage.setMaxWidth(600);
        stage.setMinHeight(550);
        stage.setMaxHeight(800);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
