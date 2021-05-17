package shakeanapple.backtracker.ui.infrasructure.control.causegraph.view;

import javafx.scene.paint.Color;

public class DiagramStyles {


    private DiagramStyles(){};

    public static final Color CONNECTION_COLOR_DEFAULT = Color.grayRgb(160);
    public static final Color CONNECTION_COLOR_CAUSE_SELECTED = Color.ROYALBLUE;
    public static final double CONNECTION_STROKE_WIDTH_DEFAULT = 2;
    public static final double CONNECTION_STROKE_WIDTH_SELECTED = 4;

    public static final Color CHANGE_NODE_COLOR = Color.CORAL;
    public static final Color REMAIN_NODE_COLOR = Color.LIGHTGRAY;
    public static final Color NODE_STROKE_COLOR = Color.DARKGRAY;

    public static final double NODE_DEF_HEIGHT = 30;
    public static final double  NODE_DEF_WIDTH = 40;


    public static final double DIAGRAM_WIDTH_PADDING = 50;
    public static final double DIAGRAM_HEIGHT_PADDING = 30;
    public static final double DIAGRAM_PADDING_TOP = 10;
    public static final double DIAGRAM_PADDING_LEFT = 10;
    public static final double DIAGRAM_ELEMENTS_PADDING = 10;

    public static final String CAUSE_SELECTED_STYLE = String.format("-fx-stroke: %s; -fx-stroke-width: 2;", toHexString(CONNECTION_COLOR_CAUSE_SELECTED));



    public static final String TABLE_CELL_CAUSE_STYLE = String.format("-fx-background-color: %s; -fx-text-fill: white;", toHexString(CONNECTION_COLOR_CAUSE_SELECTED));


    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    private static String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
}
