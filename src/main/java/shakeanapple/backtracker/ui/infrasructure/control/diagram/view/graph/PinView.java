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
import shakeanapple.backtracker.common.variable.BooleanValueHolder;
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

        this.view = new Button(pin.getShortName().length() > DiagramStyles.PIN_CHAR_MAX_COUNT ? pin.getShortName().substring(0,DiagramStyles.PIN_CHAR_MAX_COUNT) + "..." : pin.getShortName());
        this.view.setMinSize(DiagramStyles.PIN_SIZE, DiagramStyles.PIN_SIZE);
        this.view.setMinWidth(47);
        this.view.setMaxWidth(47);
//        this.view.getStylesheets().add(DiagramStyles.PIN_BACKGROUND_RADIUS_STYLE);
        this.view.setStyle(DiagramStyles.PIN_BACKGROUND_RADIUS_STYLE);
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
                this.view.setStyle(this.view.getStyle().concat(";").concat(DiagramStyles.PIN_IS_CAUSE_STYLE));
            } else{
                this.view.setStyle(this.view.getStyle().replace(DiagramStyles.PIN_IS_CAUSE_STYLE, ""));
            }
            this.view.applyCss();
        });

        this.tooltipText = new SimpleObjectProperty<>(this.pin.getShortName());

        Tooltip tooltip = new Tooltip(this.tooltipText.getValue());
        tooltip.setShowDelay(Duration.ZERO);
        tooltip.textProperty().bind(this.tooltipText);
        this.view.setTooltip(tooltip);

        ///////////// label
        this.label = new Label();
        this.label.setText(new BooleanValueHolder(false).toString());
        this.getPin().valueProperty().addListener(((observableValue, oldVal, newVal) ->{
            label.setText(newVal.toString());
        }));
        this.label.addEventHandler(MouseEvent.ANY, (e) -> this.view.fireEvent(e));


        parent.getChildren().add(this.label);

    }

    protected Label getLabel(){
        return this.label;
    }

    private String generateTooltipText(List<String> causesString){
        String text = this.pin.getShortName();
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

    public double getWidth(){
        return this.view.getWidth();
    }
}
