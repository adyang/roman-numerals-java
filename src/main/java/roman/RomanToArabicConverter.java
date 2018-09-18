package roman;

import java.util.AbstractMap.SimpleImmutableEntry;

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
        int sumSoFar = lessSigChar == 'V' ? lessSig.getKey() - moreSig.getKey() : lessSig.getKey() + moreSig.getKey();
        return pair(sumSoFar, moreSig.getValue());
    }

    private int unitValue(int ch) {
        return ch == 'V' ? 5 : 1;
    }
}
