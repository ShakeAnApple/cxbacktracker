package shakeanapple.backtracker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainFx  extends Application {

    @Override
    public void start(Stage stage) throws IOException {
       URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\mainview\\index.fxml").toURI().toURL();
//
//        Parent root = FXMLLoader.load(url);
        URL res = getClass().getClassLoader().getResource("index.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root, 700, 700));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
