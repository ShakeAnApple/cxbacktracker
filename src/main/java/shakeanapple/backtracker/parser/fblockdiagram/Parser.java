package shakeanapple.backtracker.parser.fblockdiagram;

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
    private List<BlockDefinition> blockDefinitions;

    private final String ROOT_NAME = "main";

    public Parser(String path, String blockDefsDirPath) throws IOException {
        this.path = path;

        this.loadDefinitions(blockDefsDirPath);
    }

    private void loadDefinitions(String blockDefsDirPath) throws IOException {
        this.blockDefinitions = new ArrayList<>();
        Files.list(Paths.get(blockDefsDirPath)).forEach(path -> {
            try {
                Block b = XmlSerializer.deserializeFromXML(path.toString());
                this.blockDefinitions.add(b.translate());
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        });
    }


    public FunctionBlockComplex parse() throws IOException {
        List<String> contents = this.readLines();

        ModuleBuilder mb = new ModuleBuilder(contents);
        List<String> rootContents = this.extractRoot(mb);

        FunctionBlockComplex d = mb.parseRoot(rootContents, this.blockDefinitions.stream().collect(Collectors.toMap(BlockDefinition::getTypeName, b -> b)));
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
