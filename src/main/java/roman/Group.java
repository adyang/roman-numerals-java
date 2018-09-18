package roman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Stream;

enum Group {
    ONES('I', 'V', 'X', 1),
    TENS('X', 'L', 'C', 10),
    HUNDREDS('C', 'D', 'M', 100),
    THOUSANDS('M', '\0', '\0', 1000);

    final char unit;
    final char half;
    final char full;
    final int magnitude;
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

    static Group from(String romanChunk) {
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

    int digit(int ch) {
        if (this.doesNotContain((char) ch))
            throw new IllegalArgumentException("Char '" + (char) ch + "' does not belong to Group " + this);
        return this.digitMap.get((char) ch);
    }

    boolean isSubtraction(char lessSignificantChar) {
        return lessSignificantChar == half || lessSignificantChar == full;
    }

    public boolean doesNotContain(char ch) {
        return !this.digitMap.containsKey(ch);
    }
}
