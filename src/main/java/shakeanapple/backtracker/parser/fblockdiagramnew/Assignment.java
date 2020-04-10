package shakeanapple.backtracker.parser.fblockdiagramnew;

public class Assignment {
    private String notNegatedContent;
    private boolean isFromOuterInterface;
    private String blockFromName;
    private String varFromName;
    private boolean isNegated;
    private boolean isConstInteger;
    private boolean isConstBoolean;

    public Assignment(String notNegatedContent, boolean isFromOuterInterface, String blockFromName, String varFromName, boolean isNegated, boolean isConstInteger, boolean isConstBoolean) {
        this.notNegatedContent = notNegatedContent;
        this.isFromOuterInterface = isFromOuterInterface;
        this.blockFromName = blockFromName;
        this.varFromName = varFromName;
        this.isNegated = isNegated;
        this.isConstInteger = isConstInteger;
        this.isConstBoolean = isConstBoolean;
    }

    private Assignment(){}

    public static Assignment of(String inputStr){
        Assignment input = new Assignment();
        inputStr = inputStr.trim();
        if (inputStr.startsWith("!")){
            input.isNegated = true;
            inputStr = inputStr.replace("!", "");
        }
        if (!inputStr.contains(".")){
            if (isConstBoolean(inputStr)){
                input.isConstBoolean = true;
            } else if (isConstInteger(inputStr)){
                input.isConstInteger = true;
            } else{
                input.isFromOuterInterface = true;
            }
        } else {
            String[] parts = inputStr.split("\\.");
            input.blockFromName = parts[0].replace("!", "");
            input.varFromName = parts[1];
        }
        input.notNegatedContent = inputStr;
        return input;
    }

    private static boolean isConstBoolean(String str){
        return str.toLowerCase().equals("true") || str.toLowerCase().equals("false");
    }

    private static boolean isConstInteger(String str){
        try {
            Integer.parseInt(str.substring(0,1));
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public String getNotNegatedContent() {
        return this.notNegatedContent;
    }

    public boolean isFromOuterInterface() {
        return this.isFromOuterInterface;
    }

    public String getBlockFromName() {
        return this.blockFromName;
    }

    public String getVarFromName() {
        return this.varFromName;
    }

    public boolean isNegated() {
        return this.isNegated;
    }

    public boolean isConstInteger() {
        return this.isConstInteger;
    }

    public boolean isConstBoolean() {
        return this.isConstBoolean;
    }
}
