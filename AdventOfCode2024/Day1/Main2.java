import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main2 {
    public static void main(String[] args) {
        String path = "Day1/input1.txt";
        ArrayList<Integer> list1 = new ArrayList<>();
        HashMap<Integer, Integer> list2 = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] inputs = line.split("   ");
                Integer input1 = Integer.valueOf(inputs[0]);
                Integer input2 = Integer.valueOf(inputs[1]);
                list1.add(input1);
                list2.put(input2, list2.getOrDefault(input2, 0)+1);
            }

            int ans = 0;
            for(Integer number : list1) {
                ans += number * list2.getOrDefault(number, 0);
            }

            System.out.println(ans);
        } catch(Exception e) {
            System.out.println("File not found");
        }
    }
}
