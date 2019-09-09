package shakeanapple.backtracker.common;

public class Tuple<T1, T2, T3> {
    private T1 obj1;
    private T2 obj2;
    private T3 obj3;

    public Tuple(T1 obj1, T2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    public Tuple(T1 obj1, T2 obj2, T3 obj3) {
        this.obj1 = obj1;
        this.obj2 = obj2;
        this.obj3 = obj3;
    }

    public T1 getObj1() {
        return obj1;
    }

    public T2 getObj2() {
        return obj2;
    }

    public T3 getObj3() {
        return obj3;
    }
}
