package shakeanapple.backtracker.parser.fblockdiagram;

import shakeanapple.backtracker.common.variable.Variable;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlock;
import shakeanapple.backtracker.core.fblockmapping.model.FunctionBlockDiagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser {
    private final String path;

    private final List<String> contents;

    public Parser(String path) throws IOException {
        this.path = path;

        this.contents = Files.readAllLines(Path.of(path) );
    }

    public FunctionBlockDiagram parse(){
        List<TextVariable> vars = new ArrayList<>();
        Map<String, TextModule> modules = new HashMap<>();
        List<Integer> moduleStarts = this.contents.stream()
                .filter(line -> line.contains("MODULE"))
                .map(this.contents::indexOf)
                .sorted()
                .collect(Collectors.toList());

        int from = moduleStarts.get(0);
        for(int i = 1; i < moduleStarts.size(); i++){
            int to = i;
            ModuleBuilder mb = new ModuleBuilder(this.contents.subList(from, to));
            TextModule m = mb.build();
            modules.put(m.getName(), m);
            from = to;
        }


        return null;
    }


}
