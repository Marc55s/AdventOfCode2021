import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AOCHandler {
    public AOCHandler(String day) {
        File file = new File("D:\\Development\\Java Projects\\AdventofCode2021\\src\\main\\resources\\puzzle" + day + ".txt");
        List<String> inputLines = new ArrayList<>();
        BufferedReader reader;
        if (!file.exists()) {
            solve(new ArrayList<>());
            return;
        }

        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("File not found!!");
            solve(new ArrayList<>());
            return;
        }

        try {
            String line;
            while ((line = reader.readLine()) != null)
                inputLines.add(line);

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        solve(inputLines);
    }

    abstract void solve(List<String> input);

    public List<Long> convertToLongs(List<String> input) {
        List<Long> ints = new ArrayList<>();
        for (String s : input)
            ints.add(Long.parseLong(s));
        return ints;
    }
}
