package io.calculator.backend;

public class NumTower<T> implements NumTowerIfc<T> {

    public final String formString;

    public NumTower(String formString) {
        this.formString = formString;
    }

    public CalcResult<T> calculate() {
        return null;
    }
}
