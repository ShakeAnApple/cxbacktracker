package shakeanapple.backtracker.parser.ltlformula;

import shakeanapple.backtracker.parser.ltlformula.tree.Node;

public class State {
    private final String str;
    private int curPos;
    private Node root;

    private String lastChecked;

    public State(String str) {
        this.str = str;
        this.curPos = 0;
    }

    public Node getRoot(){
        return this.root;
    }

    public String getLastChecked(){
        return this.lastChecked;
    }

    public String pickNext(int count) {
        String res = "";
        if (this.curPos + count > this.str.length()){
            res = this.str.substring(
                    this.curPos
            );
        } else {
            res = this.str.substring(
                    this.curPos,
                    this.curPos + count
            );
        }
        this.lastChecked = res;
        return res;
    }

    public String readNext(int count) {
        String res = this.pickNext(count);
        this.curPos += count;
        return res;
    }

    public void replaceRoot(Node node){
        this.root = node;
    }

    public boolean isEol(){
        return  curPos == str.length();
    }

    public String getStr(){
        return this.str.substring(this.curPos);
    }

    public void skip() {
        while(this.pickNext(1).equals(" ")){
            this.readNext(1);
        }
    }
}
