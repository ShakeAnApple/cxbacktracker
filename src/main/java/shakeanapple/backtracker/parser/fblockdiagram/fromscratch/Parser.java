package shakeanapple.backtracker.parser.fblockdiagram.fromscratch;

import shakeanapple.backtracker.common.XmlSerializer;
import shakeanapple.backtracker.core.diagramexplanation.model.FunctionBlockComplex;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BlockDefinition;
import shakeanapple.backtracker.parser.basiccomponents.xmlmodel.Block;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        ModuleBuilder mb = new ModuleBuilder(contents);
        List<String> rootContents = this.extractRoot(mb);

        FunctionBlockComplex d = mb.parseRoot(rootContents);
        return d;
    }

    private List<String> extractRoot(ModuleBuilder mb) {
        return mb.extractModule(ROOT_NAME);
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
