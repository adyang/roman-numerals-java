package roman;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Stream;

public class RomanChunker {
    public Stream<String> split(String romanNumeral) {
        LinkedHashMap<Group, List<String>> groupings = new LinkedHashMap<>();
        Group group = Group.THOUSANDS;
        for (String romanChar : romanNumeral.split("")) {
            if (group.doesNotContain(romanChar.charAt(0)))
                group = Group.from(romanChar);
            groupings.computeIfAbsent(group, g -> new ArrayList<>()).add(romanChar);
        }
        return combineCharacter(groupings);
    }

    private Stream<String> combineCharacter(LinkedHashMap<Group, List<String>> groupings) {
        return groupings.entrySet().stream()
                .map(entry -> String.join("", entry.getValue()));
    }
}
