import java.awt.Point;
import java.util.*;

public class Day9 extends AOCHandler {

    public Day9() {
        super("9");
    }

    void solve(List<String> input) {

        int[][] map = new int[input.size()][input.get(0).length()];
        List<Point> lowPointCoords = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                map[i][j] = Integer.parseInt(String.valueOf(input.get(i).charAt(j)));
            }
        }


        int riskLevel = 0;
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(0).length(); j++) {
                //9 can't be a low point
                if (map[i][j] == 9)
                    continue;
                if (isLowPoint(map, i, j)) {
                    riskLevel += 1 + map[i][j];
                    lowPointCoords.add(new Point(i, j));
                }
            }
        }
        System.out.println("Part 1: " + riskLevel);

        BFS b = new BFS();
        int length = map.length;
        int depth = map[0].length;
        for (Point lowPointCoord : lowPointCoords) {
            int x = lowPointCoord.x;
            int y = lowPointCoord.y;
            b.bfs(length, depth, map, x, y);
        }


        Collections.sort(b.basins);
        int threeLargestBasins = 1;
        for (int i = 1; i < 4; i++) {
            threeLargestBasins *= b.basins.get(b.basins.size() - i);
        }
        System.out.println("Part 2: " + threeLargestBasins);
    }

    public static class BFS {
        List<Integer> basins = new ArrayList<>();

        public boolean isInsideMap(int x, int y, int n, int m) {
            return x >= 0 && y >= 0 && x < n && y < m;
        }

        public void bfs(int n, int m, int[][] data, int x, int y) {
            int[][] visited = new int[101][101];
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited.length; j++) {
                    visited[i][j] = 0;
                }
            }

            Queue<Point> obj = new LinkedList<>();
            Point pq = new Point(x, y);
            obj.add(pq);

            visited[x][y] =1;
            int basinsPerLowpoint = 0;
            while (!obj.isEmpty()) {
                Point coord = obj.peek();
                int x1 = coord.x;
                int y1 = coord.y;
                int boundary = 9;
                data[x1][y1] = 0;
                basinsPerLowpoint++;
                obj.remove();
                if ((isInsideMap(x1 + 1, y1, n, m) && visited[x1 + 1][y1] == 0 && data[x1 + 1][y1] != boundary)) {
                    Point p = new Point(x1 + 1, y1);
                    obj.add(p);
                    visited[x1 + 1][y1] = 1;
                }
                if ((isInsideMap(x1 - 1, y1, n, m)) && visited[x1 - 1][y1] == 0 && data[x1 - 1][y1] != boundary) {
                    Point p = new Point(x1 - 1, y1);
                    obj.add(p);
                    visited[x1 - 1][y1] = 1;
                }
                if ((isInsideMap(x1, y1 + 1, n, m) && visited[x1][y1 + 1] == 0 && data[x1][y1 + 1] != boundary)) {
                    Point p = new Point(x1, y1 + 1);
                    obj.add(p);
                    visited[x1][y1 + 1] = 1;
                }
                if ((isInsideMap(x1, y1 - 1, n, m) && visited[x1][y1 - 1] == 0 && data[x1][y1 - 1] != boundary)) {
                    Point p = new Point(x1, y1 - 1);
                    obj.add(p);
                    visited[x1][y1 - 1] = 1;
                }
            }
            basins.add(basinsPerLowpoint);
        }
    }


    boolean isLowPoint(int[][] map, int i, int j) {
        int currentHeight = map[i][j];
        if (i - 1 > -1) {
            if (currentHeight > map[i - 1][j]) {
                return false;
            }
        }
        if (i + 1 < map.length) {
            if (currentHeight > map[i + 1][j]) {
                return false;
            }
        }
        if (j + 1 < map[0].length) {
            if (currentHeight > map[i][j + 1]) {
                return false;
            }
        }
        if (j - 1 > -1) {
            if (currentHeight > map[i][j - 1]) {
                return false;
            }
        }
        return true;
    }

}