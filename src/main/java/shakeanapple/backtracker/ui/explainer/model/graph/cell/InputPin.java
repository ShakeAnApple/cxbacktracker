package shakeanapple.backtracker.ui.explainer.model.graph.cell;

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


}
