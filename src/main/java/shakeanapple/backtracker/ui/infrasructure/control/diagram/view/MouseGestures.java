package shakeanapple.backtracker.ui.infrasructure.control.diagram.view;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class MouseGestures {

    private final DragContext dragContext = new DragContext();

    private Canvas canvas;

    public MouseGestures(Canvas canvas) {
        this.canvas = canvas;
    }

    public void makeDraggable(final Node node) {
        node.setOnMousePressed(this.onMousePressedEventHandler);
        node.setOnMouseDragged(this.onMouseDraggedEventHandler);
        node.setOnMouseReleased(this.onMouseReleasedEventHandler);
    }

    private EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
        Node node = (Node) event.getSource();

        double scale = this.canvas.getScale();

//        this.dragContext.x = node.getBoundsInParent().getMinX() * scale - event.getSceneX();
//        this.dragContext.y = node.getBoundsInParent().getMinY() * scale - event.getSceneY();

        this.dragContext.x = node.getLayoutX() * scale - event.getSceneX();
        this.dragContext.y = node.getLayoutY() * scale - event.getSceneY();

//            System.out.println("PRESSED");
//            System.out.println("click on screen coords (" + event.getScreenX() + "," + event.getScreenY() + ")");
//            System.out.println("click on simple coords (" + event.getX() + "," + event.getY() + ")");
//            System.out.println("click on scene coords (" + event.getSceneX() + "," + event.getSceneY() + ")");
//            System.out.println("drag context (" + dragContext.x + "," + dragContext.y + ")");
    };

    private EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {

//            System.out.println("DRAGGED");
        Node node = (Node) event.getSource();
//            System.out.println("node old layout coords (" + node.getLayoutX() + "," + node.getLayoutY() + ")");
//            System.out.println("node bounds in parent (" + node.getBoundsInParent().getMinX() + "," + node.getBoundsInParent().getMinY() + ")");

        double offsetX = event.getSceneX() + this.dragContext.x;
        double offsetY = event.getSceneY() + this.dragContext.y;

        double scale = this.canvas.getScale();
//
        // adjust the offset in case we are zoomed

        offsetX /= scale;
        offsetY /= scale;

//        node.setLayoutX(offsetX);
//        node.setLayoutY(offsetY);

        node.relocate(offsetX, offsetY);


//        System.out.println("Offset: " + offsetX + "; " + offsetY + " coords: " + node.getLayoutX() + "; " + node.getLayoutY());
//

//            System.out.println("offset (" + offsetX + "," + offsetY + ")");
//            System.out.println("click on screen coords (" + event.getScreenX() + "," + event.getScreenY() + ")");
//            System.out.println("click on simple coords (" + event.getX() + "," + event.getY() + ")");
//            System.out.println("click on scene coords (" + event.getSceneX() + "," + event.getSceneY() + ")");
//            System.out.println("node bound in parent coords (" + node.getBoundsInParent().getMinX() + "," + node.getBoundsInParent().getMinY() + ")");
//            System.out.println("node new layout coords (" + (offsetX - node.getLayoutBounds().getMinX()) + "," + (offsetY - node.getLayoutBounds().getMinY()) + ")");
//            System.out.println("drag context (" + dragContext.x + "," + dragContext.y + ")");
    };

    private EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> {
    };

    class DragContext {

        double x;
        double y;

    }
}
