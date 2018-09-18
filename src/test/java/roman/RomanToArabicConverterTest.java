package roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanToArabicConverterTest {
    private RomanToArabicConverter converter;

    @BeforeEach
    void setUp() {
        this.converter = new RomanToArabicConverter(new RomanChunker());
    }

    @Nested
    class onesPlaceOnlyNumeralConversion {
        @Test
        void convertLowerHalfAdditionNumerals() {
            assertEquals(1, converter.convert("I"));
            assertEquals(2, converter.convert("II"));
            assertEquals(3, converter.convert("III"));
        }

        @Test
        void convertHalfNumeral() {
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
            assertEquals(9, converter.convert("IX"));
        }
    }

    @Nested
    class tensPlaceOnlyNumeralConversion {
        @Test
        void convertLowerHalfAdditionNumerals() {
            assertEquals(10, converter.convert("X"));
            assertEquals(20, converter.convert("XX"));
            assertEquals(30, converter.convert("XXX"));
        }

        @Test
        void convertHalfNumeral() {
            assertEquals(50, converter.convert("L"));
        }

        @Test
        void convertUpperHalfAdditionNumerals() {
            assertEquals(60, converter.convert("LX"));
            assertEquals(80, converter.convert("LXXX"));
        }

        @Test
        void convertSubtractionNumerals() {
            assertEquals(40, converter.convert("XL"));
            assertEquals(90, converter.convert("XC"));
        }
    }

    @Nested
    class hundredsPlaceOnlyNumeralConversion {
        @Test
        void convertLowerHalfAdditionNumerals() {
            assertEquals(300, converter.convert("CCC"));
        }

        @Test
        void convertHalfNumeral() {
            assertEquals(500, converter.convert("D"));
        }

        @Test
        void convertUpperHalfAdditionNumerals() {
            assertEquals(800, converter.convert("DCCC"));
        }

        @Test
        void convertSubtractionNumerals() {
            assertEquals(400, converter.convert("CD"));
            assertEquals(900, converter.convert("CM"));
        }
    }

    @Nested
    class thousandsPlaceOnlyNumeralConversion {
        @Test
        void convertLowerHalfAdditionNumerals() {
            assertEquals(3000, converter.convert("MMM"));
        }
    }

    @Nested
    class mixedPlaceNumeralConversion {
        @Test
        void convertLowerHalfAdditionNumerals() {
            assertEquals(11, converter.convert("XI"));
        }
    }
}
