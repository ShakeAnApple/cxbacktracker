package shakeanapple.backtracker.parser.fblockdiagramold.model;

public class ParsingDiagram {
//    private ParsingModule root;
//    private Map<String, ParsingModule> modules;
//
//    public ParsingDiagram(ParsingModule root, Map<String, ParsingModule> modules) {
//        this.root = root;
//        this.modules = modules;
//    }
//
//    public Diagram translate() {
//        Map<String, FunctionBlock> blocks = new HashMap<>();
//
//        FunctionBlock root = this.root.translate();
//
//        for (ParsingModule module : modules.values()) {
//            blocks.put(module.getName(), module.translate());
//        }
//
//        blocks.put("main", root);
//
//        for (ParsingModule module : modules.values()) {
//            for (ParsingOutput output: module.getOutputsMap().values()) {
//                for (ParsingConnection connection: output.getOutgoingConnections()){
//
//                    FunctionBlock from = blocks.get(connection.from().getName());
//                    OutputVariable fromVar = from.getOutputs().get(connection.fromVar().getInfo().getName());
//                    FunctionBlock to = blocks.get(connection.to().getName());
//                    InputVariable toVar = to.getInputs().get(connection.toVar().getInfo().getName());
//
//                    fromVar.connect(toVar, from, to, connection.isInverted());
//                    toVar.connect(fromVar, from, to, connection.isInverted());
//                }
//            }
//        }
//
//        return new Diagram(new ArrayList<>(blocks.values()), root);
        //TODO do I really need to keep this parser?..
//        throw new RuntimeException("Not implemented");
//    }
}
