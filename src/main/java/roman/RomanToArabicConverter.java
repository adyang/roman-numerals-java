package roman;

import java.util.AbstractMap.SimpleImmutableEntry;

public class RomanToArabicConverter {
    private RomanChunker romanChunker;

    public RomanToArabicConverter(RomanChunker romanChunker) {
        this.romanChunker = romanChunker;
    }

    public int convert(String roman) {
        return this.romanChunker.split(roman)
                .mapToInt(this::chunkToArabicValue)
                .sum();
    }

    private int chunkToArabicValue(String romanChunk) {
        Group group = Group.from(romanChunk);
        return toArabicDigit(romanChunk, group) * group.magnitude;
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
}
