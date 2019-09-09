package shakeanapple.backtracker.ui.control.visgraph.visfx.graph.jsonutils;

import com.google.gson.*;
import shakeanapple.backtracker.ui.control.visgraph.visfx.graph.VisEdge;

import java.lang.reflect.Type;
import java.util.List;

public class VisEdgeAdapter implements JsonSerializer<VisEdge>{

    @Override
    public JsonElement serialize(VisEdge visEdge, Type type, JsonSerializationContext jsonSerializationContext) {
        return getAsJsonObject(visEdge);

    }

    public static JsonArray getAsJsonArray(List<VisEdge> edges){
        JsonArray jsonArray = new JsonArray();
        for(VisEdge visEdge : edges){
            jsonArray.add(getAsJsonObject(visEdge));
        }
        return jsonArray;
    }

    private static JsonObject getAsJsonObject(VisEdge visEdge){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("from",visEdge.getFrom().getId());
        jsonObject.addProperty("to",visEdge.getTo().getId());
        jsonObject.addProperty("label",visEdge.getLabel());
        jsonObject.addProperty("arrows",visEdge.getArrows());
        jsonObject.addProperty("color",visEdge.getColor());
        // smooth: {type: 'curvedCW', roundness: -0.2}

        JsonObject smooth = new JsonObject();
        smooth.addProperty("type", "curvedCW");
        smooth.addProperty("roundness", visEdge.getRoundness());
        jsonObject.add("smooth", smooth);

        JsonObject font = new JsonObject();
        font.addProperty("color", visEdge.getColor());
        jsonObject.add("font", font);
        return jsonObject;
    }
}