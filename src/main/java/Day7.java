import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Day7 extends AOCHandler {

    public Day7() {
        super("7");
    }

    void solve(List<String> input) {
        List<Long> in = new ArrayList<>();
        String[] split = input.get(0).split(",");
        for (String s : split) {
            in.add(Long.parseLong(s));
        }
        long minFuel = 1000000000L;
        long minFuelExpensive = 1000000000L;
        long fuel;
        long expFuelPerHorizontal;
        long expFuel;

        for (int j = 1; j < 1000; j++) {
            fuel = 0;
            expFuel = 0;
            for (Long pos : in) {
                fuel += Math.abs(pos - j);
                expFuelPerHorizontal = IntStream.rangeClosed(1, (int) Math.abs(pos - j)).sum();
                expFuel += expFuelPerHorizontal;
            }
            minFuel = Math.min(minFuel, fuel);
            minFuelExpensive = Math.min(minFuelExpensive, expFuel);
        }
        System.out.println("Part 1: " + minFuelExpensive);
        System.out.println("Part 2: " + minFuel);
    }
}