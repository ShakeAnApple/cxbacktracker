package shakeanapple.backtracker.parser.ltlformula.recent;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import shakeanapple.backtracker.core.ltl.formula.model.LtlFormula;
import shakeanapple.backtracker.core.ltl.formula.model.tree.FormulaNode;
import shakeanapple.backtracker.parser.ltlformula.recent.antlr.ltlLexer;
import shakeanapple.backtracker.parser.ltlformula.recent.antlr.ltlParser;
import shakeanapple.backtracker.parser.ltlformula.recent.tree.Node;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Parser {
    public LtlFormula parse(String formula){
        final List<String> errors = new ArrayList<>();
        final List<String> warnings = new ArrayList<>();
        Node result;
        try (InputStream in = new ByteArrayInputStream(String.join("\n", formula).getBytes())) {
            final ltlLexer lexer = new ltlLexer(CharStreams.fromStream(in));
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final ltlParser parser = new ltlParser(tokens);
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
                public void reportContextSensitivity(org.antlr.v4.runtime.Parser parser, DFA dfa, int i, int i1, int i2,
                                                     ATNConfigSet atnConfigSet) {
                    warnings.add("reportContextSensitivity");
                }
            });
            result = parser.formula().f;
            if (errors.isEmpty()){
                FormulaNode formulaRoot = result.translate();
                return new LtlFormula(formulaRoot);
            }
            return null;
        } catch (NullPointerException | RecognitionException | IOException e) {
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
}
