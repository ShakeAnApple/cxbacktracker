package shakeanapple.backtracker.ui.explainer.model.graph.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class InputPin extends Pin {
    private boolean isInverted;

    public InputPin(ExplainerCell owner, String name, int order) {
        super(owner, name, order);
    }

    @Override
    protected List<String> getAdditionalStyles(){
        if (this.isInverted){
            return new ArrayList<>(){{add(String.format("-fx-background-radius: %s", "20px"));}};
        }
        return new ArrayList<>();
    }

    public void invert(){
       this.isInverted = true;
//       super.applyStyle(String.format("-fx-background-color: %s", "#DDAB00"));
       super.applyStyle(String.format("-fx-background-radius: %s", "20px"));
    }


    public void isTarget() {
        Line line = new Line();
        line.setStroke(Color.GRAY);
        line.setStrokeWidth(2);

        // TODO grid cell size should be here
        line.startXProperty()
                .bind(super.layoutXProperty().subtract(100));
        line.startYProperty()
                .bind(super.layoutYProperty().add(super.getMinHeight() / 2));

        // TODO grid cell size should be here
        line.endXProperty()
                .bind(super.layoutXProperty());
        line.endYProperty()
                .bind(super.layoutYProperty().add(super.getMinHeight() / 2));

        super.getChildren().add(line);
    }
}
