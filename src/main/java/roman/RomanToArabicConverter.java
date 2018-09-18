package roman;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Arrays;

public class RomanToArabicConverter {
    public int convert(String roman) {
        return reverse(roman)
                .chars()
                .mapToObj(ch -> pair(unitValue(ch), (char) ch))
                .reduce(pair(0, 'I'), this::computeRomanNumerals)
                .getKey();
    }

    private StringBuilder reverse(String roman) {
        return new StringBuilder(roman).reverse();
    }

    private SimpleImmutableEntry<Integer, Character> pair(int sumSoFar, char romanChar) {
        return new SimpleImmutableEntry<>(sumSoFar, romanChar);
    }

    private SimpleImmutableEntry<Integer, Character> computeRomanNumerals(SimpleImmutableEntry<Integer, Character> lessSig, SimpleImmutableEntry<Integer, Character> moreSig) {
        char lessSigChar = lessSig.getValue();
        int sumSoFar = isBaseChar(lessSigChar) ? lessSig.getKey() - moreSig.getKey() : lessSig.getKey() + moreSig.getKey();
        return pair(sumSoFar, moreSig.getValue());
    }

    private boolean isBaseChar(char ch) {
        return Arrays.asList('V', 'X').contains(ch);
    }

    private int unitValue(int ch) {
        switch (ch) {
            case 'X': return 10;
            case 'V': return 5;
            case 'I': return 1;
            default:
                throw new IllegalArgumentException("Unknown Roman Character: " + ch);
        }
    }
}
