package io.calculator.backend;

import org.junit.jupiter.api.Test;

public class NumTowerTest {

    private NumTowerTest() {
    }

    @Test
    public void testSimpleForm () {
        NumTowerIfc tower = new NumTower("");
        CalcResult result = tower.calculate();
    }
}
