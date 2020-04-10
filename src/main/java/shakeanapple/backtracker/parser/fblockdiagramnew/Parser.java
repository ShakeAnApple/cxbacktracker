package shakeanapple.backtracker.parser.fblockdiagramnew;

import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {
    private final String path;

    private final String ROOT_NAME = "main";

    public Parser(String path) throws IOException {
        this.path = path;
    }

    public FunctionBlockComplex parse() throws IOException {
        List<String> contents = this.readLines();

        SystemLexer lexer = new SystemLexer(contents, ROOT_NAME);
        List<ParsingModule> modules = lexer.extractModulesAndDependencies();
        final SystemBuilder systemBuilder = new SystemBuilder(ROOT_NAME, modules);

        return systemBuilder.buildRoot(modules.stream().filter(ParsingModule::isRoot).findFirst().get());
    }

    private List<String> readLines() throws IOException {
        return Files.readAllLines(Path.of(path)).stream()
                .map(String::trim)
                .filter(str -> !this.isCommentOrEmpty(str))
                .collect(Collectors.toList());
    }

    private boolean isCommentOrEmpty(String str) {
        return str.startsWith("--") || str.isEmpty();
    }
}
