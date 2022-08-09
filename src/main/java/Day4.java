import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 extends AOCHandler {

    public Day4() {
        super("4");
    }

    List<Integer> called;
    BingoField lastWin = null;
    void solve(List<String> input) {
        String[] drawn = input.get(0).split(",");
        input.remove(0);
        called = new ArrayList<>();
        for (String s : drawn) {
            called.add(Integer.parseInt(s.trim()));
        }
        List<String> field = new ArrayList<>();
        List<BingoField> bingoFields = new ArrayList<>();
        int[][] c = new int[5][5];
        int d = 0;

        for (String s : input) {
            if (!s.isEmpty()) {
                field.add(s.trim().replace("  ", " "));
                if (field.size() == 5) {
                    for (String sa : field) {
                        String[] f = sa.split(" ");
                        c[d] = Arrays.stream(f).mapToInt(e -> Integer.parseInt(e)).toArray();
                        d++;
                    }
                    d = 0;
                    bingoFields.add(new BingoField(c));
                    c = new int[5][5];
                }
            } else {
                field.clear();
            }
        }
        int u = 0;
        List<BingoField> copy = new ArrayList<>();
        bingoFields.remove(0);
        copy = bingoFields;
        boolean flip = true;
        while (u < called.size()) {
            int element = called.get(u);
            bingoFields = bingoFields.stream().map(e -> e.draw(element)).collect(Collectors.toList());

            List<BingoField> winner = bingoFields.stream().filter(BingoField::rowOrColComplete).collect(Collectors.toList());

            copy.removeAll(winner);

            if (winner.size() == 1 && flip) {
                System.out.println("Part 1: " + winner.get(0).sumOfBoard() * element);
                flip = false;
            }

            if(copy.size() == 1) {
                lastWin = copy.get(0);
            }

            if (copy.size() == 0) {
                lastWin.draw(called.get(u-1));
                System.out.println("Part 2: " + lastWin.sumOfBoard() * element);
                break;
            }
            u++;
        }
    }


    class BingoField {
        int[][] field;
        boolean[][] drawn = new boolean[5][5];

        public BingoField(int[][] field) {
            this.field = field;
        }

        BingoField draw(int num) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (field[i][j] == num) {
                        drawn[i][j] = true;
                    }
                }
            }
            return this;
        }

        boolean rowOrColComplete() {
            boolean complete = false;
            int rowCounter = 0;
            int colCounter = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (drawn[i][j]) rowCounter++;
                    if (drawn[j][i]) colCounter++;

                    if (rowCounter == 5) complete = true;
                    if (colCounter == 5) complete = true;
                }
                rowCounter = 0;
                colCounter = 0;
            }
            return complete;
        }

        int sumOfBoard() {
            int sum = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (!drawn[i][j])
                        sum += field[i][j];
                }
            }
            return sum;
        }

        void printBoard() {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (drawn[i][j])
                        System.out.print("X" + field[i][j] + " ");
                    else
                        System.out.print("." + field[i][j] + " ");

                }
                System.out.println();
            }
        }

    }
}