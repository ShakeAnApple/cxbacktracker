package shakeanapple.backtracker.parser.ltlformula.recent;

public class IntGenerator {
    private int val;
    private static IntGenerator generator;

    private IntGenerator(){
        this.val = 0;
    }

    public static IntGenerator instance(){
        if (generator == null){
            generator = new IntGenerator();
        }
        return generator;
    }

    public int next(){
        return ++this.val;
    }
}
