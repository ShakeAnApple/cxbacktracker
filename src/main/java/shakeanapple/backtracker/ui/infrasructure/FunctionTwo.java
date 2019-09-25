package shakeanapple.backtracker.ui.infrasructure;

@FunctionalInterface
public interface FunctionTwo<T1, T2, R> {
    R apply(T1 var1, T2 var2);
}