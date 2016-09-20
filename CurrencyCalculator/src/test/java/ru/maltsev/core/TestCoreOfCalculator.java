package ru.maltsev.core;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestCoreOfCalculator {

    @Test
    public void testRubTOUsd(){

        assertEquals(CoreOfCalculator.transferRubTOUsd(70), 1.08, 0.01);

        assertEquals(CoreOfCalculator.transferRubTOUsd(15), 0.23, 0.01);

        assertEquals(CoreOfCalculator.transferRubTOUsd(123), 1.90, 0.01);
    }

    @Test
    public void testUsdToRub(){

        assertEquals(CoreOfCalculator.transferUsdToRub(90), 5813.10, 0.01);

        assertEquals(CoreOfCalculator.transferUsdToRub(1), 64.59, 0.01);

        assertEquals(CoreOfCalculator.transferUsdToRub(3), 193.77, 0.01);
    }

}
