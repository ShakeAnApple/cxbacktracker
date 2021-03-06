package shakeanapple.backtracker.ui.infrasructure.control.diagram.view;

import javafx.scene.paint.Color;

public class DiagramStyles {


    private DiagramStyles(){};

    public static final Color CONNECTION_COLOR_DEFAULT = Color.grayRgb(160);
    public static final Color CONNECTION_COLOR_CAUSE = Color.ROYALBLUE;
    public static final double CONNECTION_STROKE_WIDTH_DEFAULT = 2;
    public static final double CONNECTION_STROKE_WIDTH_SELECTED = 4;

    public static final int PIN_SIZE = 26;
    public static final int PIN_CHAR_MAX_COUNT = 5;
    public static final String PIN_INVERTED_STYLE = String.format("-fx-background-radius: %s", "20px");
    public static final String PIN_IS_CAUSE_STYLE = String.format("-fx-border-color: %s", toHexString(CONNECTION_COLOR_CAUSE));
    public static final String PIN_BACKGROUND_RADIUS_STYLE = "-fx-background-radius:0";

    public static final Color CELL_COLOR = Color.LAVENDER;
    public static final Color CELL_STROKE_COLOR = Color.DARKGRAY;

    public static final Color INPUT_CELL_COLOR = Color.ALICEBLUE;
    public static final Color INPUT_CELL_STROKE_COLOR = Color.DARKGRAY;

    public static final Color OUTPUT_CELL_COLOR = Color.ALICEBLUE;
    public static final Color OUTPUT_CELL_STROKE_COLOR = Color.DARKGRAY;


    public static final double DIAGRAM_BLOCK_PADDING = 100;
    public static final double DIAGRAM_ELEMENTS_PADDING = 10;

    public static final String TABLE_CELL_CAUSE_STYLE = String.format("-fx-background-color: %s; -fx-text-fill: white;", toHexString(CONNECTION_COLOR_CAUSE));


    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    private static String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
}
