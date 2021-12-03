import java.util.List;
import java.util.stream.IntStream;

public class Day1 extends AOCHandler {

    public Day1() {
        super("1");
    }

    void solve(List<String> input) {
        List<Long> depths = convertToLongs(input);
        //Part 1
        int ans = 0;
        for (int i = 0; i < depths.size() - 1; i++) {
            if (depths.get(i) < depths.get(i + 1)) ans++;
        }
        System.out.println("Part 1: " + ans);
        //Oneliner
        int a  = (int) IntStream.range(0,depths.size()-1).filter(i -> depths.get(i) < depths.get(i+1)).count();
        System.out.println("Oneliner: "+ (a == ans));

        //Part 2
        int secans = 0;
        for (int i = 0; i < depths.size() - 3; i++) {
            if (depths.get(i) < depths.get(i + 3)) secans++;
        }
        System.out.println("\nPart 2: " + secans);
        //Oneliner
        int b  = (int) IntStream.range(0,depths.size()-3).filter(i -> depths.get(i) < depths.get(i+3)).count();
        System.out.println("Oneliner: "+ (b == secans));
    }
}