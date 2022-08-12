import java.util.*;
import java.util.stream.Collectors;

public class Day8 extends AOCHandler {

    public Day8() {
        super("8");
    }

    void solve(List<String> input) {

        String[] fourDigits = null;
        int c = 0;
        int c2 = 0;
        String concat = "";

        for (String s : input) {
            //Formatting input
            Map<String, Integer> digit = new HashMap<>();
            String[] all = s.split(" \\| ");
            String[] signalPattern = all[0].split(" ");
            fourDigits = all[1].split(" ");

            int[] l = new int[]{2, 4, 3, 7};
            for (int i = 0; i < fourDigits.length; i++) {
                for (int j = 0; j < l.length; j++) {
                    if (fourDigits[i].length() == l[j]) {
                        c++;
                    }
                }
            }

			/*
			len: 2,3,4,7  -> digit: 1, 4, 7, 8
			     5        ->           2, 3, 5,
			     6        ->           6, 9, 0 rest
			 */

            //Assigning digits with single length
            List<String> pattern = Arrays.asList(signalPattern);
            List<String> copy = new ArrayList<>(List.copyOf(pattern));
            for (int i = 0; i < pattern.size(); i++) {
                switch (pattern.get(i).length()) {
                    case 2 -> {
                        digit.put(pattern.get(i), 1);
                        copy.remove(pattern.get(i));
                    }
                    case 3 -> {
                        digit.put(pattern.get(i), 7);
                        copy.remove(pattern.get(i));
                    }
                    case 4 -> {
                        digit.put(pattern.get(i), 4);
                        copy.remove(pattern.get(i));
                    }
                    case 7 -> {
                        digit.put(pattern.get(i), 8);
                        copy.remove(pattern.get(i));
                    }
                }
            }

            Map<Integer, String> reversed = new HashMap<>();
            for (Map.Entry<String, Integer> entry : digit.entrySet()) {
                reversed.put(entry.getValue(), entry.getKey());
            }

            //Sorting to use longer signalpatterns to define 0,6,9
            copy = copy.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
            Collections.reverse(copy);

            for (String rest : copy) {
                if (rest.length() == 5) {
                    if (rest.contains(String.valueOf(reversed.get(1).charAt(0))) && rest.contains(String.valueOf(reversed.get(1).charAt(1)))) {  //contains both chars from 1
                        digit.put(rest, 3);
                    } else {
                        //if rest contains any letter of a 6
                        boolean b = allThreeInCommon(reversed.get(1), reversed.get(6), rest);
                        if (b) {
                            digit.put(rest, 5);
                        } else {
                            digit.put(rest, 2);
                        }
                    }
                } else if (rest.length() == 6) {
                    //diff between 0 and 9
                    if (rest.contains(String.valueOf(reversed.get(1).charAt(0))) && rest.contains(String.valueOf(reversed.get(1).charAt(1)))) {
                        //contains a whole 4? -> 9
                        if (rest.contains(String.valueOf(reversed.get(4).charAt(0))) && rest.contains(String.valueOf(reversed.get(4).charAt(1))) && rest.contains(String.valueOf(reversed.get(4).charAt(2))) && rest.contains(String.valueOf(reversed.get(4).charAt(3)))) {
                            digit.put(rest, 9);
                        } else {
                            digit.put(rest, 0);
                        }
                    } else if (rest.contains(String.valueOf(reversed.get(1).charAt(0))) ^ rest.contains(String.valueOf(reversed.get(1).charAt(1)))) {
                        digit.put(rest, 6);
                    }
                }
                for (Map.Entry<String, Integer> entry : digit.entrySet()) {
                    reversed.put(entry.getValue(), entry.getKey());
                }
            }

            //decode
            for (String dec : fourDigits) {
                String sorted = sortString(dec);
                for (Map.Entry<String, Integer> entry : digit.entrySet()) {
                    String key = sortString(entry.getKey());

                    if (sorted.equals(key)) {
                        concat += entry.getValue();
                    }
                }
            }

            c2 += Integer.parseInt(concat);
            concat = "";

        }

        System.out.println("Part 1: " + c);
        System.out.println("Part 2: " + c2);
    }

    private boolean allThreeInCommon(String s, String s1, String rest) {
        boolean three = false;
        if ((s1.contains(String.valueOf(s.charAt(0))) && rest.contains(String.valueOf(s.charAt(0)))) || (s1.contains(String.valueOf(s.charAt(1))) && rest.contains(String.valueOf(s.charAt(1))))) {
            three = true;
        }
        return three;
    }

    public static String sortString(String inputString) {
        char[] tempArray = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}
