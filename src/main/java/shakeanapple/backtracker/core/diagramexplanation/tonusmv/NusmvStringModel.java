package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

import java.util.ArrayList;
import java.util.List;

public class NusmvStringModel {
    private String contents;
    private List<String> specifications;

    public NusmvStringModel(String contents) {
        this.contents = contents;
        this.specifications = new ArrayList<>();
    }

    public String getContents(){
        return this.contents;
    }

    public void addSpecification(String spec){
        this.specifications.add(spec);
    }

    public List<String> getSpecifications(){
        return this.specifications;
    }
}
