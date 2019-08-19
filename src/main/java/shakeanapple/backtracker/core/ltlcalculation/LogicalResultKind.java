package shakeanapple.backtracker.core.ltlcalculation;

public enum LogicalResultKind {
    // if it's variable or const
    NONE,

    // if on the current step result is unknown (works for U operator, for example)
    UNKNOWN,

    // TODO maybe not needed
    UNDEFINED,

    TRUE,
    FALSE
}
