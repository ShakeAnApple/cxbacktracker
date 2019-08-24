package shakeanapple.backtracker.common.graph;

import java.util.*;
import java.util.stream.Collectors;

public class Graph {
    private Map<UUID, Node> nodesByIds;
    private Map<UUID, List<Transition>> transByFrom;

    public Graph(List<Node> nodes, List<Transition> trans) {
        this.nodesByIds = nodes.stream().collect(Collectors.toMap(Node::getId, n -> n));

        this.transByFrom = new HashMap<>();
        for (Transition t : trans) {
            if (!this.transByFrom.containsKey(t.getFrom().getId())){
                this.transByFrom.put(t.getFrom().getId(), new ArrayList<>());
            }
            this.transByFrom.get(t.getFrom().getId()).add(t);
        }
    }


}
