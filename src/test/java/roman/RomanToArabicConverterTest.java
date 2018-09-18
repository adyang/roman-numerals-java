package roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanToArabicConverterTest {
    private RomanToArabicConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new RomanToArabicConverter();
    }

    @Test
    void convertLowerHalfAdditionNumerals() {
        assertEquals(1, converter.convert("I"));
        assertEquals(2, converter.convert("II"));
        assertEquals(3, converter.convert("III"));
    }

    @Test
    void convertBaseNumerals() {
        assertEquals(5, converter.convert("V"));
    }

    @Test
    void convertUpperHalfAdditionNumerals() {
        assertEquals(6, converter.convert("VI"));
        assertEquals(7, converter.convert("VII"));
        assertEquals(8, converter.convert("VIII"));
    }

    @Test
    void convertSubtractionNumerals() {
        assertEquals(4, converter.convert("IV"));
    }
}
