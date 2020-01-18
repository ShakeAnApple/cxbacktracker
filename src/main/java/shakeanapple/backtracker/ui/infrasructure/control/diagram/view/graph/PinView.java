package shakeanapple.backtracker.ui.infrasructure.control.diagram.view.graph;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Cause;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.model.Pin;
import shakeanapple.backtracker.ui.infrasructure.control.diagram.view.DiagramStyles;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PinView extends NodeView {
    private Pin pin;

    private Button view;

    private Property<String> tooltipText;

    private Label label;

    private DiagramCellView owner;

    public PinView(Group parent, Pin pin, DiagramCellView owner) {
        super(parent);
        this.pin = pin;
        this.owner = owner;

        this.view = new Button();
        this.view.setMinSize(DiagramStyles.PIN_SIZE, DiagramStyles.PIN_SIZE);
        this.view.getStylesheets().add(DiagramStyles.PIN_BACKGROUND_RADIUS_STYLE);
        this.view.onActionProperty().setValue(
                actionEvent -> this.pin.getPinClickHandler().apply(this.pin));
        parent.getChildren().add(view);

        ///////////// tooltip
        this.pin.getCausesObservable().addListener((ListChangeListener<? super Cause>)  c -> {
            List<String> causesString = pin.getCausesObservable().stream()
                    .distinct()
                    .sorted(Comparator.comparing(Cause::getTimestamp))
                    .map(cause -> (cause.getTimestamp()-1) + ": " + cause.getValue().toString())
                    .collect(Collectors.toList());

            String text = generateTooltipText(causesString);
            this.tooltipText.setValue(text);

            if (!this.pin.getCausesObservable().isEmpty()){
                this.view.getStylesheets().add(DiagramStyles.PIN_IS_CAUSE_STYLE);
            } else{
                this.view.getStylesheets().remove(DiagramStyles.PIN_IS_CAUSE_STYLE);
            }
        });

        this.tooltipText = new SimpleObjectProperty<>(this.pin.getName());

        Tooltip tooltip = new Tooltip(this.tooltipText.getValue());
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.textProperty().bind(this.tooltipText);

        this.view.setTooltip(tooltip);

        ///////////// label
        this.label = new Label();
        this.getPin().valueProperty().addListener(((observableValue, oldVal, newVal) ->{
            label.setText(newVal.toString());
        }));

        this.label.addEventHandler(MouseEvent.ANY, (e) -> this.view.fireEvent(e));

        this.getLabel().layoutXProperty().bind(this.getView().layoutXProperty().subtract(50));
        this.getLabel().layoutYProperty().bind(this.getView().layoutYProperty().add(this.getView().heightProperty().divide(2)));
        parent.getChildren().add(this.label);

    }

    protected Label getLabel(){
        return this.label;
    }

    private String generateTooltipText(List<String> causesString){
        String text = this.pin.getName();
        if (!causesString.isEmpty()){
            text += "\n";
            text += String.join("\n", causesString);
        }
        return text;
    }

    public Button getView() {
        return this.view;
    }

    public Pin getPin() {
        return pin;
    }

    public DiagramCellView getOwner() {
        return this.owner;
    }
}
