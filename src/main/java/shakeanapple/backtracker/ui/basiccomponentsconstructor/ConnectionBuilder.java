package shakeanapple.backtracker.ui.basiccomponentsconstructor;

import shakeanapple.backtracker.ui.basiccomponentsconstructor.model.Connection;

import java.util.*;

public class ConnectionBuilder {
    private Long fromId;
    private Long toId;
    private String color;
    private boolean isInverted;

    private Random r = new Random();
    private Map<Long, String> pinColors = new HashMap<>();

    public ConnectionBuilder() {
        this.fromId = null;
        this.toId = null;
        this.color = null;
    }

    public boolean ready(){
        return this.fromId != null && this.toId != null;
    }

    public String registerPin(long id){
        if (this.fromId == null){
            if (!this.pinColors.containsKey(id)) {
                int colNum = this.r.nextInt(COLORS.size());
                this.color = COLORS.get(colNum);
                COLORS.remove(COLORS.indexOf(this.color));

                this.pinColors.put(id, this.color);
            } else{
                this.color = this.pinColors.get(id);
            }
            this.fromId = id;
        } else if (this.toId == null){
            this.toId = id;
        }
        return this.color;
    }

    public Connection get(){
        Connection conn = new Connection(this.color, this.fromId, this.toId, this.isInverted);
        this.color = null;
        this.fromId = null;
        this.toId = null;
        return conn;
    }

    private static List<String> COLORS = new ArrayList<>(Arrays.asList(
            "#FA8072",
                    "#E9967A",
                    "#CD5C5C",
                    "#DC143C",
                    "#B22222",
                    "#FF0000",
                    "#8B0000",
                    "#FF7F50",
                    "#FF6347",
                    "#FF4500",
                    "#FFD700",
                    "#FFA500",
                    "#FF8C00",
                    "#FFEFD5",
                    "#FFE4B5",
                    "#FFDAB9",
                    "#F0E68C",
                    "#BDB76B",
                    "#FFFF00",
                    "#7FFF00",
                    "#32CD32",
                    "#008000",
                    "#006400",
                    "#ADFF2F",
                    "#9ACD32",
                    "#00FF7F",
                    "#90EE90",
                    "#8FBC8F",
                    "#3CB371",
                    "#2E8B57",
                    "#808000",
                    "#556B2F",
                    "#6B8E23",
                    "#RRGGBB",
                    "#E0FFFF",
                    "#00FFFF",
                    "#7FFFD4",
                    "#66CDAA",
                    "#AFEEEE",
                    "#40E0D0",
                    "#00CED1",
                    "#20B2AA",
                    "#5F9EA0",
                    "#008B8B",
                    "#008080",
                    "#ADD8E6",
                    "#87CEFA",
                    "#00BFFF",
                    "#B0C4DE",
                    "#1E90FF",
                    "#6495ED",
                    "#4682B4",
                    "#4169E1",
                    "#0000CD",
                    "#00008B",
                    "#7B68EE",
                    "#6A5ACD",
                    "#483D8B",
                    "#E6E6FA",
                    "#D8BFD8",
                    "#DDA0DD",
                    "#EE82EE",
                    "#DA70D6",
                    "#FF00FF",
                    "#BA55D3",
                    "#9370DB",
                    "#8A2BE2",
                    "#800080",
                    "#4B0082",
                    "#FFB6C1",
                    "#FF69B4",
                    "#FF1493",
                    "#DB7093",
                    "#C71585",
                    "#DCDCDC",
                    "#D3D3D3",
                    "#A9A9A9",
                    "#696969",
                    "#708090",
                    "#2F4F4F",
                    "#000000",
                    "#FFF8DC",
                    "#FFE4C4",
                    "#F5DEB3",
                    "#DEB887",
                    "#D2B48C",
                    "#BC8F8F",
                    "#F4A460",
                    "#DAA520",
                    "#CD853F",
                    "#D2691E",
                    "#8B4513",
                    "#A0522D",
                    "#A52A2A",
                    "#800000"
    ));
}
