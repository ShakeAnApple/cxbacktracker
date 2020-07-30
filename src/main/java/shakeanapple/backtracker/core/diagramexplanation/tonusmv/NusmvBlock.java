package shakeanapple.backtracker.core.diagramexplanation.tonusmv;

public interface NusmvBlock {
    NusmvStringModel getStringModel();
    void writeTo(NusmvStringModelBuilder mb);
}
