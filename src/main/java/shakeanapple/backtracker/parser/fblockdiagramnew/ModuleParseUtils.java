package shakeanapple.backtracker.parser.fblockdiagramnew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleParseUtils {
    public static List<String> readInputs(String moduleSignature) {
        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher m = p.matcher(moduleSignature);
        String inputsString = "";

        if (m.find()) {
            inputsString = m.group(1);
        }
        if (inputsString == null || inputsString.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.asList(inputsString.split(", "));
    }
}
