import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 extends AOCHandler {

    public Day13() {
        super("13");
    }

    void solve(List<String> input) {
        List<Point> points = new ArrayList<>();
        List<String> folds = new ArrayList<>();
        boolean foldings = false;
        int maximumX = 0;
        int maximumY = 0;
        for (String s : input) {
            if (foldings) folds.add(s);
            if (!s.isEmpty() && !foldings) {
                String[] f = s.split(",");
                int a = Integer.parseInt(f[1]);
                int b = Integer.parseInt(f[0]);
                maximumX = Math.max(maximumX, a);
                maximumY = Math.max(maximumY, b);
                points.add(new Point(a, b));

            } else {
                foldings = true;
            }
        }
        String[][] map = new String[maximumX + 1][maximumY + 1];
        String[][] mapForPartOne = new String[maximumX + 1][maximumY + 1];
        for (int i = 0; i < points.size(); i++) {
            Point now = points.get(i);
            map[now.x][now.y] = "#";
        }
        for (int o = 0; o < folds.size(); o++) {
            String firstFold = folds.get(o);
            var a = firstFold.split(" ");
            var c = a[2].split("=");
            int foldCoord = Integer.parseInt(c[1]);
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == null) map[i][j] = ".";
                    if (c[0].equals("y")) {
                        if (i > foldCoord && map[i][j].equals("#")) {
                            map[i][j] = ".";
                            map[2 * foldCoord - i][j] = "#";
                        }
                    } else {
                        if (j > foldCoord && map[i][j].equals("#")) {
                            map[i][j] = ".";
                            map[i][2 * foldCoord - j] = "#";
                        }
                    }
                }
            }
            //Copy first state of folding
            if (o == 0) {
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        mapForPartOne[i][j] = map[i][j];
                    }
                }
            }
        }
        int ans = 0;
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (mapForPartOne[y][x].equals("#")) ans++;
            }
        }
        System.out.println("Part 1: " + ans);
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < map[y].length / 10; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println(y);
        }
    }
}