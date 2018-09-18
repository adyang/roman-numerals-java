package roman;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RomanChunkerTest {

    private RomanChunker romanChunker;

    @BeforeEach
    void setUp() {
        romanChunker = new RomanChunker();
    }

    @Test
    void singlePlaceNumeral() {
        assertStreamEquals(Stream.of("X"), romanChunker.split("X"));
        assertStreamEquals(Stream.of("CD"), romanChunker.split("CD"));
        assertStreamEquals(Stream.of("MMM"), romanChunker.split("MMM"));
    }

    @Test
    void twoPlaceNumeral() {
        assertStreamEquals(Stream.of("X", "I"), romanChunker.split("XI"));
        assertStreamEquals(Stream.of("XC", "V"), romanChunker.split("XCV"));
        assertStreamEquals(Stream.of("CM", "XL"), romanChunker.split("CMXL"));
    }

    @Test
    void threePlaceNumeral() {
        assertStreamEquals(Stream.of("CCC", "X", "I"), romanChunker.split("CCCXI"));
        assertStreamEquals(Stream.of("DCC", "XL", "IV"), romanChunker.split("DCCXLIV"));
        assertStreamEquals(Stream.of("MM", "CD", "IX"), romanChunker.split("MMCDIX"));
        assertStreamEquals(Stream.of("M", "LXXX", "VII"), romanChunker.split("MLXXXVII"));
    }

    @Test
    void fourPlaceNumeral() {
        assertStreamEquals(Stream.of("MMM", "D", "XC", "IX"), romanChunker.split("MMMDXCIX"));
    }

    private void assertStreamEquals(Stream<String> expected, Stream<String> actual) {
        assertEquals(expected.collect(Collectors.toList()), actual.collect(Collectors.toList()));
    }
}
