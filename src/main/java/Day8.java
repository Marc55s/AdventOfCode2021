import java.util.*;

public class Day8 extends AOCHandler {

    public Day8() {
        super("test");
    }

    void solve(List<String> input) {

        String[] fourDigits = null;
        int c = 0;
        int c2 = 0;
        String concat = "";
        for (String s : input) {
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
			     6        ->           6, 9 rest
			 */
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
            for (String rest : copy) {
                if (rest.length() == 5) {
                    if (rest.contains(String.valueOf(reversed.get(1).charAt(0))) && rest.contains(String.valueOf(reversed.get(1).charAt(1)))) {  //contains both chars from 1
                        digit.put(rest, 3);
                    } else {
                        //if rest contains any letter of a 6
                        var temp = sortString(reversed.get(1));
                        if (rest.contains(String.valueOf(temp.charAt(0)))) {
                            digit.put(rest, 2);
                        } else {
                            digit.put(rest, 5);
                        }
                    }
                } else if (rest.length() == 6) {
                    if (rest.contains(String.valueOf(reversed.get(1).charAt(0))) && rest.contains(String.valueOf(reversed.get(1).charAt(1)))) {
                        digit.put(rest, 9);
                    } else {
                        digit.put(rest, 6);
                    }
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
            System.out.println(concat);
            c2 += Integer.parseInt(concat);
            concat = "";
        }
        System.out.println("Part 1: " + c);
        System.out.println("Part 2: " + c2);
    }

    public static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }
}
