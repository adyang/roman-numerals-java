package roman;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

public class RomanToArabicConverter {
    public int convert(String roman) {
        Group group = Group.from(roman);
        return toArabicDigit(roman, group) * group.magnitude;
    }

    private int toArabicDigit(String roman, Group group) {
        return reverse(roman)
                .chars()
                .mapToObj(ch -> pair(group.digit(ch), (char) ch))
                .reduce(pair(0, group.unit), (lessSig, moreSig) -> computeRomanNumerals(lessSig, moreSig, group))
                .getKey();
    }

    private StringBuilder reverse(String roman) {
        return new StringBuilder(roman).reverse();
    }

    private SimpleImmutableEntry<Integer, Character> pair(int sumSoFar, char romanChar) {
        return new SimpleImmutableEntry<>(sumSoFar, romanChar);
    }

    private SimpleImmutableEntry<Integer, Character> computeRomanNumerals(SimpleImmutableEntry<Integer, Character> lessSig, SimpleImmutableEntry<Integer, Character> moreSig, Group group) {
        int sumSoFar = group.isSubtraction(lessSig.getValue()) ? lessSig.getKey() - moreSig.getKey() : lessSig.getKey() + moreSig.getKey();
        return pair(sumSoFar, moreSig.getValue());
    }

    enum Group {
        ONES('I', 'V', 'X', 1),
        TENS('X', 'L', 'C', 10),
        HUNDREDS('C', 'D', 'M', 100),
        THOUSANDS('M', '\0', '\0', 1000);

        private final char unit;
        private final char half;
        private final char full;
        public final int magnitude;
        private final HashMap<Character, Integer> digitMap = new HashMap<>();

        Group(char unit, char half, char full, int magnitude) {
            this.unit = unit;
            this.half = half;
            this.full = full;
            this.magnitude = magnitude;
            this.digitMap.put(unit, 1);
            this.digitMap.put(half, 5);
            this.digitMap.put(full, 10);
        }

        private static Group from(String romanChunk) {
            return Arrays.stream(Group.values())
                    .filter(g -> matchesGroup(romanChunk, g))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Unable to derive Group, invalid roman chunk: " + romanChunk));
        }

        private static boolean matchesGroup(String romanChunk, Group g) {
            return Stream.of(g.unit, g.half)
                    .filter(ch -> ch != '\0')
                    .anyMatch(ch -> ch == romanChunk.charAt(0));
        }

        private int digit(int ch) {
            return this.digitMap.get((char) ch);
        }

        private boolean isSubtraction(char lessSignificantChar) {
            return lessSignificantChar == half || lessSignificantChar == full;
        }
    }
}
