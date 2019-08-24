package shakeanapple.backtracker.core.fblockmapping.model;

import shakeanapple.backtracker.common.variable.*;
import shakeanapple.backtracker.parser.fblockdiagram.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionBlockDiagram {
    private final List<FunctionBlock> fblocks;

    public FunctionBlockDiagram(List<FunctionBlock> fblocks) {
        this.fblocks = fblocks;
    }

    public static FunctionBlockDiagram load(String path){
        try {
            Parser p = new Parser(path);
            return p.parse();
        }
        catch (Exception e){
            throw new RuntimeException("Can't load diagram");
        }
    }
}
