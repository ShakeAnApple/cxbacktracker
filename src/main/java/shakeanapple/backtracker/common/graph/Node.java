package shakeanapple.backtracker.common.graph;

import java.util.UUID;

public class Node<VType> {
    private final UUID id;
    private final VType value;

    public Node(VType value) {
        this.value = value;
        this.id = UUID.randomUUID();
    }

    public VType getValue(){
        return this.value;
    }

    public UUID getId() {
        return this.id;
    }
}
