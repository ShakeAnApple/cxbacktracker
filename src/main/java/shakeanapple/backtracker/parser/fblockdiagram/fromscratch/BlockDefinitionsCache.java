package shakeanapple.backtracker.parser.fblockdiagram.fromscratch;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated.nusmvLexer;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated.nusmvParser;
import shakeanapple.backtracker.core.diagramexplanation.model.complexblockdefinition.BlockDefinition;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.NuSMVModule;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class BlockDefinitionsCache {
    private Map<String, BlockDefinition> definitions = new HashMap<>();
    private long currentId = 0;

    public BlockDefinition parseAndCache(List<String> moduleContents) {
        final List<String> errors = new ArrayList<>();
        final List<String> warnings = new ArrayList<>();
        NuSMVModule result;
        try (InputStream in = new ByteArrayInputStream(String.join("\n", moduleContents).getBytes())) {
            final nusmvLexer lexer = new nusmvLexer(CharStreams.fromStream(in));
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final nusmvParser parser = new nusmvParser(tokens);
            parser.addErrorListener(new ANTLRErrorListener() {
                @Override
                public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s,
                                        RecognitionException e) {
                    errors.add("syntaxError");
                }

                @Override
                public void reportAmbiguity(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet,
                                            ATNConfigSet atnConfigSet) {
                    errors.add("reportAmbiguity");
                }

                @Override
                public void reportAttemptingFullContext(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, BitSet bitSet,
                                                        ATNConfigSet atnConfigSet) {
                    warnings.add("reportAttemptingFullContext");
                }

                @Override
                public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2,
                                                     ATNConfigSet atnConfigSet) {
                    warnings.add("reportContextSensitivity");
                }
            });
            result = parser.module().m;
            result.clarifyTypes();
            if (errors.isEmpty()){
                NuSMVModule.TransformOutput res = result.toFunctionBlockNetwork(this.currentId);
                BlockDefinition def = res.block.translate();
                this.currentId = res.nextId + 1;
                this.definitions.put(def.getTypeName(), def);
                return def;
            }
            return null;
        } catch (NullPointerException | RecognitionException | TypeInferenceException | UndeclaredVariableException
                | DuplicateAssignmentException | TooDeepNextException | IOException | MissingAssignmentException | UnresolvedTypeException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (!errors.isEmpty()) {
                System.err.println("Parse error(s): " + errors);
            }
            if (!warnings.isEmpty()) {
                System.err.println("Parse warning(s): " + warnings);
            }
        }
    }

    public boolean definitionExists(String moduleType) {
        return this.definitions.containsKey(moduleType);
    }

    public BlockDefinition get(String moduleType) {
        return this.definitions.get(moduleType);
    }
}
