import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.List;

public class Day6 extends AOCHandler{

	public Day6() {
		super("6");
	}

	void solve(List<String> input) {
		List<Integer> lanternFish = new ArrayList<>();
		String[] split = input.get(0).split(",");
		for(String s : split){
			lanternFish.add(Integer.parseInt(s));
		}

		for (int i = 0; i < 80; i++) {
			for (int j = 0; j < lanternFish.size(); j++) {
				if(lanternFish.get(j) == 0){
					lanternFish.set(j,6);
					lanternFish.add(9);
				}else {
					lanternFish.set(j, lanternFish.get(j) - 1);
				}
			}
		}
		System.out.println("Part 1:"+lanternFish.size());


	}
}