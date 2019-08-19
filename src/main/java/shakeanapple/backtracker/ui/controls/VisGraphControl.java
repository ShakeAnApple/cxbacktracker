package shakeanapple.backtracker.ui.controls;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import shakeanapple.backtracker.infrastructure.visfx.graph.VisGraph;

import java.io.IOException;

public class VisGraphControl extends Region {
    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();


    public VisGraphControl(VisGraph g) {
        this();
        updateGraph(g);
    }

    public VisGraphControl() {
        initialize();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
//                "visgraph.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        System.out.println("VisGraph started");
//        try {
//            fxmlLoader.load();
//            System.out.println("VisGraph done");
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
    }

    private void initialize() {
        //apply the styles
        super.getStyleClass().add("browser");

        // load the web page
        this.webEngine.load((getClass().getClassLoader().getResource("baseGraph.html")).toString());

        //add the web view to the scene
        super.getChildren().add(browser);
    }

    public void updateGraph(VisGraph graph){
        String script = "setTheData(" + graph.getNodesJson() +  "," + graph.getEdgesJson() + ")";
        this.webEngine.executeScript(script);

//        this.webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
//            if(newValue == Worker.State.SUCCEEDED)
//                this.webEngine.executeScript(script);
//        });
    }

    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

//    @Override
//    protected double computePrefWidth(double height) {
//        return 750;
//    }
//
//    @Override
//    protected double computePrefHeight(double width) {
//        return 500;
//    }
}
