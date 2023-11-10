package ser515.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleSquareRootTest_akrana1 {
    @Test
    void testMAkeSqrt(){
        assertEquals(Math.sqrt(0.0), SimpleSquareRootFunction.makeSqrt(1));
    }
    @Test
    void testMAkeSqrt2(){
        assertEquals(Math.sqrt(0.0), SimpleSquareRootFunction.makeSqrt(-2));
    }

}
