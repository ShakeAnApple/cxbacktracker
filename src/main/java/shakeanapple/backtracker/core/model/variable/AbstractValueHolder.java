package shakeanapple.backtracker.core.model.variable;

public abstract class AbstractValueHolder<VType> {
        public abstract VType getValue();

        @Override
        public abstract String toString();
}
