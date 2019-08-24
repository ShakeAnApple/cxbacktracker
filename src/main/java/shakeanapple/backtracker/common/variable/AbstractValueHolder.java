package shakeanapple.backtracker.common.variable;

public abstract class AbstractValueHolder<VType> {
        public abstract VType getValue();

        @Override
        public abstract String toString();
}
