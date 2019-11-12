package shakeanapple.backtracker.ui.explainer.model.graph.cell;

public class InputPin extends Pin {
    private boolean isInverted;

    public InputPin(ExplainerCell owner, String name, int order) {
        super(owner, name, order);
    }


    public void invert(){
//        this.isInverted = true;
        this.setStyle(String.format("-fx-background-color: %s", "#FFA500"));
    }

    public void usualColor() {
        this.setStyle(String.format("-fx-background-color: %s", "#BDB76B"));
    }
}
