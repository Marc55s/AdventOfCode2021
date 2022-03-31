import java.util.List;

public class Day5 extends AOCHandler {

    public Day5() {
        super("5");
    }

    void solve(List<String> input) {
        int[][] grid = new int[1000][1000];
        int ans = 0;
        for (String str : input) {
            String[] range = str.split(",");
            String[] rangeTwo = range[1].split(" ");

            int x1 = Integer.parseInt(range[0]);
            int y1 = Integer.parseInt(rangeTwo[0]);
            int x2 = Integer.parseInt(rangeTwo[2]);
            int y2 = Integer.parseInt(range[2]);
            int specialY = y1;
            if (x1 == x2) {
                if (y1 <= y2) {
                    for (int y = y1; y <= y2; y++) {
                        grid[x1][y]++;
                    }
                } else {
                    for (int y = y2; y <= y1; y++) {
                        grid[x1][y]++;
                    }
                }
            } else if (y1 == y2) {
                if (x1 <= x2) {
                    for (int x = x1; x <= x2; x++) {
                        grid[x][y1]++;
                    }
                } else {
                    for (int x = x2; x <= x1; x++) {
                        grid[x][y1]++;
                    }
                }
            } else {
                //Diagonals
                if (y1 < y2) {
                    if (x1 < x2) {
                        for (int x = x1; x <= x2; x++) {
                            grid[x][specialY]++;
                            specialY++;
                        }
                    } else {
                        specialY = y2;
                        for (int x = x2; x <= x1; x++) {
                            grid[x][specialY]++;
                            specialY--;
                        }
                    }
                } else {
                    if (x1 < x2) {
                        for (int x = x1; x <= x2; x++) {
                            grid[x][specialY]++;
                            specialY--;
                        }
                    } else {
                        specialY = y2;
                        for (int x = x2; x <= x1; x++) {
                            grid[x][specialY]++;
                            specialY++;
                        }
                    }
                }
            }
        }
        for (int[] ints : grid) {
            for (int i : ints) {
                if (i > 1) ans++;
            }
        }
        System.out.println(ans);
    }
}