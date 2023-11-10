package ser515.unittest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SimpleSquareRootTest2_akrana1 {
    @Test
    void testMAkeSqrtZeroAll() {
        assertAll("Combining all zereo tests",
                () -> assertEquals(0.0, SimpleSquareRootFunction.makeSqrt(1), "Test for x=1"),
                () -> assertEquals(0.0, SimpleSquareRootFunction.makeSqrt(-2), "Test for x= -2")
        );
    }
    @ParameterizedTest
    @ValueSource(ints = {1,-2})
    void testMakeSqrtZeroParam(int x){
        assertEquals(0.0, SimpleSquareRootFunction.makeSqrt(x));
}
}

