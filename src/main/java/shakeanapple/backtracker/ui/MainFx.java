package shakeanapple.backtracker.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainFx  extends Application {

    @Override
    public void start(Stage stage) throws IOException, JAXBException {

//        Block b = XmlSerializer.deserializeFromXML("C:\\Users\\ovsianp1\\projects\\SEARCH\\basic-components\\_12.xml");
//        Block b = XmlSerializer.deserializeFromXML("C:\\Users\\ovsianp1\\projects\\SEARCH\\modchk\\models\\simple-model-flip-flop\\basics\\FLIP_FLOP.xml");
//        BlockDefinition def = b.translate();
//        FunctionBlockComplex fb = def.instance("lol");

        URL url = new File("src/main/java/shakeanapple/backtracker/ui/explainer/index.fxml").toURI().toURL();
//      URL url = new File("src\\main\\java\\shakeanapple\\backtracker\\ui\\basiccomponentsconstructor\\index.fxml").toURI().toURL();
//
//        Parent root = FXMLLoader.load(url);
        URL res = getClass().getClassLoader().getResource("index.fxml");
        Parent root = FXMLLoader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
