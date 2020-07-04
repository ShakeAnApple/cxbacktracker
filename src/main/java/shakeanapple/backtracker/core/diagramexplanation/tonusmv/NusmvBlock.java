package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

public interface NusmvBlock {
    String getStatements();
    void writeTo(NusmvStringModelBuilder mb);
}
