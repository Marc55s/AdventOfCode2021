import com.sun.jdi.IncompatibleThreadStateException;

import javax.swing.text.MaskFormatter;
import java.util.*;
import java.util.stream.IntStream;

public class Day6 extends AOCHandler {

    public Day6() {
        super("6");
    }



    void solve(List<String> input) {
        List<Integer> lanternFish = new ArrayList<>();
        String[] split = input.get(0).split(",");
        for (String s : split) {
            lanternFish.add(Integer.parseInt(s));
        }
        long[] fish = new long[9];
        lanternFish.forEach(i -> fish[i]++);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < lanternFish.size(); j++) {
                if (lanternFish.get(j) == 0) {
                    lanternFish.set(j, 6);
                    lanternFish.add(9);
                } else {
                    lanternFish.set(j, lanternFish.get(j) - 1);
                }
            }
        }
        System.out.println("Part 1: " + lanternFish.size());
        int days = 256;
        for (int i = 0; i < days; i++) {
            long newFish = fish[0];
            System.arraycopy(fish, 1, fish, 0, fish.length - 1);
            fish[6] += newFish;
            fish[8] = newFish;
        }
        System.out.println("Part 2: "+Arrays.stream(fish).sum());
    }

}