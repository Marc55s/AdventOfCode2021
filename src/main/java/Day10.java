import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

public class Day10 extends AOCHandler {

    public Day10() {
        super("10");
    }

    void solve(List<String> input) {
        Predicate<Character> characterPredicate = Day10::containsParentheses;
        List<Long> scores = new ArrayList<>();
        int ans = 0;
        char[][] line = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            line[i] = new char[input.get(i).length()];
            for (int j = 0; j < input.get(i).length(); j++) {
                line[i][j] = input.get(i).charAt(j);

            }
        }
        List<Character> illegals = new ArrayList<>();
        for (char[] chars : line) {
            Stack<Character> row = new Stack<>();
            boolean corrupted = false;
            for (char c : chars) {
                if (characterPredicate.test(c)) {
                    row.add(c);
                } else {
                    if (c == ')') {
                        if (c == (int) (row.peek() + 1)) {
                            row.pop();
                        } else {
                            corrupted = true;
                            illegals.add(c);
                            break;
                        }
                    } else if ((int) c == (int) (row.peek() + 2)) {
                        row.pop();
                    } else {
                        illegals.add(c);
                        corrupted = true;
                        break;
                    }
                }
            }

            if (!corrupted) {
                List<Character> completion = new ArrayList<>(row);
                for (int j = 0; j < completion.size(); j++) {
                    if (characterPredicate.test(completion.get(j))) {
                        if (completion.get(j) != '(') {
                            completion.set(j, (char) ((int) (completion.get(j) + 2)));
                        } else {
                            completion.set(j, (char) ((int) (completion.get(j) + 1)));
                        }
                    }
                }
                Collections.reverse(completion);

                long sum = 0;
                for (char c : completion) {
                    sum *= 5;
                    switch (c) {
                        case ')' -> sum += 1;
                        case ']' -> sum += 2;
                        case '}' -> sum += 3;
                        case '>' -> sum += 4;
                    }
                }
                scores.add(sum);
            }
        }

        for (char a : illegals) {
            switch (a) {
                case ')' -> ans += 3;
                case ']' -> ans += 57;
                case '}' -> ans += 1197;
                case '>' -> ans += 25137;
            }
        }
        System.out.println("Part 1:" + ans);
        Collections.sort(scores);
        System.out.println("Part 2:" + scores.get((scores.size() / 2)));
    }

    private static boolean containsParentheses(char c) {
        return c == '{' || c == '<' || c == '(' || c == '[';
    }

}