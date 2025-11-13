package io.calculator.backend;

public class CalcResult<T> {

    private final T value;
    public CalcResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
