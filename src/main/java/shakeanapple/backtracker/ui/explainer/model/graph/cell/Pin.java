package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Connectable;

public abstract class Pin extends Button implements Connectable {
    private ExplainerCell owner;
    private String name;

    private int order;

    public Pin(ExplainerCell owner, String name, int order) {
        this.owner = owner;
        this.name = name;
        this.order = order;

        Tooltip tooltip = new Tooltip(this.name);
        tooltip.setShowDelay(Duration.ZERO);
        this.setTooltip(tooltip);


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

    public int getOrder() {
        return this.order;
    }


    public ExplainerCell getOwner() {
        return this.owner;
    }

    public String getName() {
        return this.name;
    }
}
