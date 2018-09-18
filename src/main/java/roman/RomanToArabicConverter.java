package roman;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;
import java.util.HashMap;

public class RomanToArabicConverter {
    public int convert(String roman) {
        if (isOnesGroup(roman)) {
            return convertToArabic(roman, Group.ONES);
        } else {
            return convertToArabic(roman, Group.TENS);
        }
    }

    private boolean isOnesGroup(String roman) {
        return Arrays.asList('I', 'V').contains(roman.charAt(0));
    }

    private int convertToArabic(String roman, Group group) {
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
        TENS('X', 'L', 'C', 10);

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

        private int digit(int ch) {
            return this.digitMap.get((char) ch);
        }

        private boolean isSubtraction(char lessSignificantChar) {
            return lessSignificantChar == half || lessSignificantChar == full;
        }
    }
}
