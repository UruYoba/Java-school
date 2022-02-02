package HW13;

import java.util.Arrays;

public enum Operation {
    PLUS(new String[]{"+", "плюс", "прибавить"}),
    MINUS(new String[]{"-", "минус", "отнять"}),
    DIVIDE(new String[]{"/", "разделить", "делить"}),
    MULTIPLY(new String[]{"*", "умножить"});

    public String[] values;

    Operation(String[] values) {
        this.values = values;
    }

    public boolean validateOperation(String op){
        return Arrays.asList(this.values).contains(op);
    }
}
