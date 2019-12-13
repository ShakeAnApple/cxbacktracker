package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connectable;

import java.util.ArrayList;
import java.util.List;

public abstract class Pin extends Button implements Connectable {
    private ExplainerCell owner;
    private String name;

    private int order;

    protected static class Styles {
        public static String BACKGROUND_RADIUS = "-fx-background-radius:0";
        public static String INVERTED = "-fx-background-color: #FFA500";
    }

    public Pin(ExplainerCell owner, String name, int order) {
        this.owner = owner;
        this.name = name;
        this.order = order;

        Tooltip tooltip = new Tooltip(this.name);
        tooltip.setShowDelay(Duration.ZERO);
        this.setTooltip(tooltip);

        this.applyStyles(Styles.BACKGROUND_RADIUS);

//        node.setOnMouseMoved(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event) {
//                // +15 moves the tooltip 15 pixels below the mouse cursor;
//                // if you don't change the y coordinate of the tooltip, you
//                // will see constant screen flicker
//                tooltip.show(node, event.getScreenX(), event.getScreenY() + 15);
//            }
//        });
//        node.setOnMouseExited(new EventHandler<MouseEvent>(){
//            @Override
//            public void handle(MouseEvent event){
//                tooltip.hide();
//            }
//        });

//        .tooltip {
//            -fx-show-delay: 250ms;
//        }
    }

    private void applyStyles(String... styles) {
        this.setStyle(String.join("; ", styles));
    }

    private void applyStyles(List<String> styles) {
        this.setStyle(String.join("; ", styles));
    }

    public int getOrder() {
        return this.order;
    }


    public ExplainerCell getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }

    public void usualColor() {
        this.setStyle(null);
        List<String> styles = new ArrayList<>();
        styles.addAll(this.getDefStyle());
        styles.addAll(this.getAdditionalStyles());
        this.applyStyles(styles);
    }

    protected List<String> getDefStyle(){
        return new ArrayList<>(){{add(Styles.BACKGROUND_RADIUS);}};
    }

    public void colorBorder(String color){
        List<String> styles = new ArrayList<>();
        styles.addAll(this.getDefStyle());
        styles.add(String.format("-fx-border-color: %s", color));
        styles.addAll(this.getAdditionalStyles());
        this.applyStyles(styles);
    }

    protected void applyStyle(String style){
        List<String> styles = new ArrayList<>();
        styles.addAll(this.getDefStyle());
        styles.add(style);
        this.applyStyles(style);
    }

    protected List<String> getAdditionalStyles() {
        return new ArrayList<>();
    }
}
