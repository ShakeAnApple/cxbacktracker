package shakeanapple.backtracker.parser.fblockdiagramold;

public class Parser {
//    private final String path;
//
//    private final String ROOT_NAME = "main";
//
//    public Parser(String path) {
//        this.path = path;
//    }
//
//
//    public Diagram parse() throws IOException {
//        List<String> contents = this.readLines();
//
//        ModuleBuilder mb = new ModuleBuilder(contents);
//        List<String> rootContents = this.extractRoot(mb);
//
//        ParsingDiagram d = mb.parseRoot(rootContents);
//        return d.translate();
//    }
//
//    private List<String> extractRoot(ModuleBuilder mb) {
//        return mb.extractModule(ROOT_NAME);
//    }
//
//    private List<String> readLines() throws IOException {
//        return Files.readAllLines(Path.of(path)).stream()
//                .map(String::trim)
//                .filter(str -> !this.isCommentOrEmpty(str))
//                .collect(Collectors.toList());
//    }
//
//    private boolean isCommentOrEmpty(String str) {
//        return str.startsWith("--") || str.isEmpty();
//    }
}
