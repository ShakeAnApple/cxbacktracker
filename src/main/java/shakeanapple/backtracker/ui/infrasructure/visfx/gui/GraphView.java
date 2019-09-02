package shakeanapple.backtracker.ui.infrasructure.visfx.gui;

import shakeanapple.backtracker.ui.infrasructure.visfx.graph.VisGraph;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class GraphView extends Application {

    public GraphView(VisGraph graph){
        this.graph = graph;
    }

    private VisGraph graph;

    @Override
    public void start(Stage stage) {
        // create the scene
        Scene scene = new Scene(new Browser(graph), 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

class Browser extends Region {

    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();
    private VisGraph graph;

    public Browser(VisGraph g) {
        this.graph = g;
        //apply the styles
        getStyleClass().add("browser");

        // load the web page
        webEngine.load((getClass().getClassLoader().getResource("ltlGraph.html")).toString());

        //add the web view to the scene
        getChildren().add(browser);
        setGraph();
    }

    private void setGraph(){
        String script = "setTheData(" + graph.getNodesJson() +  "," + graph.getEdgesJson() + ")";
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == Worker.State.SUCCEEDED)
                webEngine.executeScript(script);
        });
    }



    @Override
    protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    protected double computePrefWidth(double height) {
        return 750;
    }

    @Override
    protected double computePrefHeight(double width) {
        return 500;
    }
}
