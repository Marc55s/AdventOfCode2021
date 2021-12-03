import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day2 extends AOCHandler {

    public Day2() {
        super("2");
    }

    void solve(List<String> input) {
        int depth = 0;
        int x = 0;
        int n = 0;
        int d = 0;
        int hz = 0;
        for (String s : input) {
            String[] f = s.split(" ");
            switch (f[0]) {
                case "forward" -> {
                    x += Integer.parseInt(f[1]);
                    hz += Integer.parseInt(f[1]);
                    d += n * Integer.parseInt(f[1]);
                }
                case "down" -> {
                    depth += Integer.parseInt(f[1]);
                    n += Integer.parseInt(f[1]);
                }
                case "up" -> {
                    depth -= Integer.parseInt(f[1]);
                    n -= Integer.parseInt(f[1]);
                }
            }
        }
        System.out.println("Part 1: " + depth * x);
        var g = IntStream.range(0, input.size()).filter(i -> input.get(i).split(" ")[0].equals("forward")).map(a -> Integer.parseInt(input.get(a).split(" ")[1])).sum() * (IntStream.range(0, input.size()).filter(i -> input.get(i).split(" ")[0].equals("down")).map(a -> Integer.parseInt(input.get(a).split(" ")[1])).sum() - IntStream.range(0, input.size()).filter(i -> input.get(i).split(" ")[0].equals("up")).map(a -> Integer.parseInt(input.get(a).split(" ")[1])).sum());
        System.out.println("Oneliner: " + (g == (depth * x)));
        System.out.println("Part 2: " + hz * d);
    }
}