import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day3 extends AOCHandler {

    public Day3() {
        super("3");
    }

    void solve(List<String> input) {
        int one = 0;
        int zero = 0;
        String gamma = "";
        String epsilon = "";
        for (int j = 0; j < input.get(0).length(); j++) {

            for (String s : input) {
                if (s.charAt(j) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
            if (zero > one) {
                gamma = gamma.concat("0");
                epsilon = epsilon.concat("1");
            } else {
                gamma = gamma.concat("1");
                epsilon = epsilon.concat("0");
            }
            zero = 0;
            one = 0;
        }
        System.out.println("Part 1: " + Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2));

        //Part 2
        List<String> oxygen = new ArrayList<>(input);
        List<Integer> oxIndex = new ArrayList<>();
        List<Integer> oxOneIndex = new ArrayList<>();
        List<String> co2 = new ArrayList<>(input);
        List<Integer> coIndex = new ArrayList<>();
        List<Integer> coOneIndex = new ArrayList<>();

        int oxygenRate;
        int co2Rate;
        int ones = 0;
        int zeros = 0;
        int zer = 0;
        int on = 0;

        for (int i = 0; i < input.get(0).length(); i++) {
            for (int j = 0; j < oxygen.size(); j++) {
                if (oxygen.get(j).charAt(i) == '0') {
                    zeros++;
                    oxIndex.add(j);
                } else {
                    ones++;
                    oxOneIndex.add(j);
                }
            }
            for (int j = 0; j < co2.size(); j++) {
                if (co2.get(j).charAt(i) == '0') {
                    zer++;
                    coIndex.add(j);
                } else {
                    on++;
                    coOneIndex.add(j);
                }
            }
            Collections.reverse(coIndex);
            Collections.reverse(coOneIndex);
            Collections.reverse(oxIndex);
            Collections.reverse(oxOneIndex);

            if (co2.size() > Math.max(coOneIndex.size(), coIndex.size())) {
                if (zer <= on) {
                    for (int g : coOneIndex) co2.remove(g);
                } else {
                    for (int g : coIndex) co2.remove(g);
                }
            }
            if (zeros <= ones) {
                for (int g : oxIndex) oxygen.remove(g);
            } else {
                for (int g : oxOneIndex) oxygen.remove(g);
            }
            oxIndex.clear();
            oxOneIndex.clear();
            coIndex.clear();
            coOneIndex.clear();
            zeros = 0;
            ones = 0;
            zer = 0;
            on = 0;
        }
        oxygenRate = Integer.parseInt(oxygen.get(0), 2);
        co2Rate = Integer.parseInt(co2.get(0), 2);
        System.out.println("Part 2:" + co2Rate * oxygenRate);
    }
}