package shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated.nusmvLexer;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.antlrgenerated.nusmvParser;
import shakeanapple.backtracker.parser.fblockdiagram.fromscratch.nusmvparsing.exceptions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/**
 * Created by buzhinsky on 11/11/19.
 */
public class ParseBlockMain {
    public static NuSMVModule processBlock(String dirName, String filename) throws IOException {
        final byte[] bytes = Files.readAllBytes(Paths.get(dirName, filename));
        final List<String> errors = new ArrayList<>();
        final List<String> warnings = new ArrayList<>();
        NuSMVModule result;
        try (InputStream in = new ByteArrayInputStream(bytes)) {
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
                public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet,
                                            ATNConfigSet atnConfigSet) {
                    errors.add("reportAmbiguity");
                }

                @Override
                public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet,
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
            return errors.isEmpty() ? result : null;
        } catch (NullPointerException | RecognitionException e) {
            System.err.println("Parse error with " + filename);
            return null;
        } catch (TypeInferenceException | UndeclaredVariableException | DuplicateAssignmentException
                | TooDeepNextException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (!errors.isEmpty()) {
                System.err.println("Parse error(s) with " + filename + ": " + errors);
            }
            if (!warnings.isEmpty()) {
                System.err.println("Parse warning(s) with " + filename + ": " + warnings);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        final String dirName = args.length >= 1 ? args[0] : "nusmv_block_tests";
        System.out.println(">> Using directory with basic blocks: " + dirName);
        final String[] filenames = new File(dirName).list();
        if (filenames == null) {
            System.err.println("No such directory or IO error while accessing it!");
            return;
        }
        Arrays.sort(filenames);
        for (String filename : filenames) {
            final String filenameLower = filename.toLowerCase();
            if (!filenameLower.endsWith(".smv") && !filenameLower.endsWith(".txt")) {
                continue;
            }
            System.out.println(">> Processing: " + filename);
            final NuSMVModule result = processBlock(dirName, filename);
            if (result == null) {
                continue;
            }
            System.out.println(result);
            try {
                System.out.println(result.toFunctionBlockNetwork());
            } catch (UnresolvedTypeException | UndeclaredVariableException | MissingAssignmentException e) {
                e.printStackTrace();
            }
            try (PrintWriter pw = new PrintWriter(Paths.get(dirName, filename + ".preprocessed").toFile())) {
                pw.println(result);
            }
        }
    }
}
