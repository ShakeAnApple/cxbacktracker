package shakeanapple.backtracker.ui.infrasructure.control.visgraph;

import javafx.beans.NamedArg;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import shakeanapple.backtracker.ui.infrasructure.control.visgraph.visfx.graph.VisGraph;

public class VisGraphControl extends Region {
    private final WebView browser = new WebView();
    private final WebEngine webEngine = browser.getEngine();

    private final String resource;

    public VisGraphControl(@NamedArg("resource") String resource) {
        this.resource = resource;
        initialize();
    }

    private void initialize() {
        //apply the styles
        super.getStyleClass().add("browser");

        // load the web page
        this.webEngine.load((getClass().getClassLoader().getResource(resource)).toString());

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

    public void clear() {
        VisGraph graph = new VisGraph();
        String script = "setTheData(" + graph.getNodesJson() +  "," + graph.getEdgesJson() + ")";
        this.webEngine.executeScript(script);
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
