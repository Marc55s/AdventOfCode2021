import java.util.*;

public class Day14 extends AOCHandler {

    public Day14() {
        super("14");
    }

    void solve(List<String> input) {
        String start = input.get(0);
        String copy = input.get(0);
        input.remove(0);
        input.remove(0);
        Map<String, String> formulas = new HashMap<>();
        for (String s : input) {
            String[] cut = s.split(" -> ");
            formulas.put(cut[0], cut[1]);
        }

        StringBuilder builder = null;
        for (int step = 0; step < 10; step++) {
            builder = new StringBuilder();
            for (int i = 0; i < start.length() - 1; i++) {
                String pair = start.substring(i, i + 2);
                String newElement = formulas.get(pair);
                String inserted = pair.charAt(0) + newElement + pair.charAt(1);
                //System.out.println(String.format("Pair %s with element(%s) = %s", pair, newElement, inserted));
                if (i > 0)
                    builder.append(inserted.substring(1));
                else
                    builder.append(inserted);

            }
            start = builder.toString();
        }
        Map<Character, Integer> oc = new HashMap<>();
        for (int i = 0; i < builder.toString().length(); i++) {
            if (oc.get(builder.toString().charAt(i)) == null)
                oc.put(builder.toString().charAt(i), 1);
            else
                oc.put(builder.toString().charAt(i), oc.get(builder.toString().charAt(i)) + 1);
        }
        List<Integer> sorted = new ArrayList<>(oc.values().stream().toList());
        Collections.sort(sorted);
        System.out.println("Part 1: " + (sorted.get(sorted.size() - 1) - sorted.get(0)));

        //Part 2 memory optimized
        Map<String, Long> pairs = new HashMap<>();
        for (int i = 0; i < copy.length() - 1; i++) {
            pairs.putIfAbsent(copy.substring(i, i + 2), 1L);
            pairs.computeIfPresent(copy.substring(i,i+2),(s, aLong) -> aLong+1);
        }
        Map<String, Long> newPairs = null;
        Map<Character, Long> counter = null;
        for (int i = 0; i < 40; i++) {
            newPairs = new HashMap<>();
            counter = new HashMap<>();
            for (Map.Entry<String, Long> entry : pairs.entrySet()) {
                String firstPairLetter = String.valueOf(entry.getKey().charAt(0));
                String secPairLetter = String.valueOf(entry.getKey().charAt(1));
                String element = formulas.get(entry.getKey());
                long val = entry.getValue();

                newPairs.computeIfPresent(firstPairLetter+element,(s, aLong) -> aLong+val);
                newPairs.putIfAbsent(firstPairLetter+element,val);

                newPairs.computeIfPresent(element+secPairLetter,(s, aLong) -> aLong+val);
                newPairs.putIfAbsent(element+secPairLetter,val);

                counter.computeIfPresent(firstPairLetter.charAt(0),(character, aLong) -> aLong+val);
                counter.putIfAbsent(firstPairLetter.charAt(0),val);

                counter.computeIfPresent(element.charAt(0),(character, integer) -> integer+val);
                counter.putIfAbsent(element.charAt(0),val);
            }
            pairs = new HashMap<>(newPairs);
        }
        newPairs.replaceAll((k,v) -> v/2);
        counter.replaceAll((k,v) -> v/2);
        counter.computeIfPresent(copy.charAt(copy.length()-1),(character, integer) -> integer+1);
        List<Long> countedChars= new ArrayList<>(counter.values().stream().toList());
        Collections.sort(countedChars);
        System.out.println("Part 2: "+(countedChars.get(countedChars.size()-1)-countedChars.get(0)));
    }
}