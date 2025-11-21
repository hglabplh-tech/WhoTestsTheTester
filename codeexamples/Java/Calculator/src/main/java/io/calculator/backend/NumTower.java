package io.calculator.backend;

import java.util.Stack;

public class NumTower<T> implements NumTowerIfc<T> {

    private final String formString;

    private final Stack<String> numStack = new Stack<>();


    public NumTower(String formString) {
        this.formString = formString;
    }

    public Float calculate(Float op1, Float op2, String in) {
        try {
            String temp = in.trim();
            if (temp.equals("+")) {
                op1 = op1 + calculate(op2, 0.0f , "+");
            } else {
                throw new IllegalStateException("Unexpected value: " + temp);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return op1;
    }


}
