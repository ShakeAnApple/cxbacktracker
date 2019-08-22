package shakeanapple.backtracker.infrastructure.visfx.graph;

import com.google.gson.Gson;
import shakeanapple.backtracker.infrastructure.visfx.jsonutils.VisEdgeAdapter;

import javax.management.relation.Relation;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class VisGraph {

    private Map<Long,VisNode> nodes;
    private List<VisEdge> edges;

    public VisGraph(){
        nodes = new HashMap<>();
        edges = new ArrayList<>();
    }

    public VisGraph(List<VisNode> nodes, List<VisEdge> edges){
        this.edges = edges;
        this.nodes = nodes.stream().collect(Collectors.toMap(VisNode::getId, n -> n));
    }

    public void addNodes(List<VisNode> nodes){
        for(VisNode node : nodes)
            this.nodes.put(node.getId(),node);
    }

    public void addEdges(List<VisEdge> edges){
        this.edges.addAll(edges);
    }

    public ArrayList<VisNode> nodesAsList(){
        return new ArrayList<>(nodes.values());
    }

    public String getNodesJson(){
        Gson gson = new Gson();
        return gson.toJson(nodesAsList());
    }

    public String getEdgesJson(){
        return VisEdgeAdapter.getAsJsonArray(edges).toString();
    }

    public boolean containsNode(long offset) {
        return this.nodes.containsKey(offset);
    }

    public VisNode getNode(long id){
        return this.nodes.get(id);
    }

    public void saveAsSif(String destFile){
        BufferedWriter fos = null;
        try {
            fos = new BufferedWriter(new FileWriter(new File(destFile)));
            for(VisEdge visEdge : edges){
                fos.write(visEdge.getFrom().getLabel() + " " + visEdge.getLabel() + " " + visEdge.getTo().getLabel() + "\n");
                fos.flush();
            }
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
