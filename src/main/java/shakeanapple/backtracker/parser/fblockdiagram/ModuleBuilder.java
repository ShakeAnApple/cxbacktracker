package shakeanapple.backtracker.parser.fblockdiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ModuleBuilder {
    private final List<String> contents;

    public ModuleBuilder(List<String> contents) {
        this.contents = contents;
    }

    public TextModule build() {
        String name = readName();
        List<TextVariable> inputs = readInputs();
        List<TextVariable> internals = readInternals();

        List<TextVariable> outputs = readOutputs();
        List<TextVariable> outPutsTypeByVar = outputs.stream().filter(o -> o.getTypeFromVarName() != null).collect(Collectors.toList());
        for (TextVariable var: outPutsTypeByVar) {
            boolean isTypeDefined = false;
            boolean typeDefinesByInput = false;
            TextVariable curCandidate = var;
            while (!isTypeDefined && !typeDefinesByInput){
                String inferFrom = curCandidate.getTypeFromVarName();
                curCandidate = internals.stream().filter(v -> v.getName().equals(inferFrom)).findFirst().orElse(null);
                if (curCandidate == null){
                    curCandidate = inputs.stream().filter(v -> v.getName().equals(inferFrom)).findFirst().orElse(null);
                    typeDefinesByInput = true;
                }
                if (curCandidate.getType() != null){
                    isTypeDefined = true;
                }
            }
            var.defineType(curCandidate.getType());
        }

        return null;
    }

    private List<TextVariable> readOutputs() {
        List<TextVariable> res = this.contents.subList(this.contents.indexOf("DEFINE"), this.contents.indexOf("ASSIGN"))
                .stream()
                .filter(str -> !this.isCommentOrEmpty(str) && str.contains(":="))
                .map(str -> {
                    String[] parts = str.trim().split(" := ");
                    String type = this.inferType(parts);
                    if (type.equals("boolean") || type.equals("integer"))
                        return new TextVariable(parts[0], type);
                    return new TextVariable(parts[0], type, true);
                }).collect(Collectors.toList());
        return res;
    }

    private boolean isCommentOrEmpty(String str) {
        return str.startsWith("--") || str.isEmpty();
    }

    private List<TextVariable> readInternals() {
        List<TextVariable> res = this.contents.subList(this.contents.indexOf("VAR"), this.contents.indexOf("DEFINE"))
                .stream()
                .filter(str -> !this.isCommentOrEmpty(str))
                .map(str -> {
                    String[] parts = str.trim().replace(";", "").split(": ");
                    return new TextVariable(parts[0], parts[1]);
                })
                .collect(Collectors.toList());

        return res;
    }

    private String inferType(String[] parts) {
        if (parts.length < 2) {
            return this.inferFromCase(parts[0]);
        }
        if (this.isTypeBoolean(parts[1])) {
            return "boolean";
        }
        if (this.isTypeInteger(parts[1])) {
            return "integer";
        }
        return parts[1];
    }

    private boolean isTypeInteger(String clue) {
        try {
            Integer.parseInt(clue.substring(0, 1));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private boolean isTypeBoolean(String clue) {
        if (clue.toLowerCase().equals("false") || clue.toLowerCase().equals("true")) {
            return true;
        }
        if (clue.startsWith("!")) {
            return true;
        }
        return false;
    }

    private String inferFromCase(String varName) {
        int idxOfVar = this.contents.indexOf(
                this.contents.stream().filter(str -> str.contains(varName) && str.contains(":=")).findFirst()
        );

        List<String> caseBlock = this.contents.subList(idxOfVar + 1,
                this.contents.indexOf(
                        this.contents.stream().filter(str -> str.contains("esac")).findFirst()
                )
        );

        List<String> typeClues = caseBlock.stream()
                .filter(str -> !this.isCommentOrEmpty(str))
                .map(str -> str.trim().replace(";", "").split(" : ")[1].toLowerCase())
                .collect(Collectors.toList());

        if (typeClues.stream().anyMatch(this::isTypeBoolean)) {
            return "boolean";
        }
        if (typeClues.stream().anyMatch(this::isTypeInteger)) {
            return "integer";
        }
        return typeClues.get(1);
    }

    private List<TextVariable> readInputs() {
        Pattern p = Pattern.compile("\\(([()])\\)");
        Matcher m = p.matcher(this.contents.get(0));
        String inputsString = "";
        if (m.find()) {
            inputsString = m.group(1);
        }
        if (inputsString == null || inputsString.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(inputsString.split(", "))
                .map(TextVariable::new)
                .collect(Collectors.toList());
    }

    // TODO ok, I'm just to lazy to use regex now...
    private String readName() {
        String nameLine = this.contents.get(0).trim().replace("MODULE", "");
        char next = ' ';
        int nextIdx = 0;
        StringBuilder name = new StringBuilder();
        while (next != '(') {
            name.append(next);
            next = nameLine.charAt(nextIdx);
            nextIdx++;
        }
        return name.toString();
    }
}
