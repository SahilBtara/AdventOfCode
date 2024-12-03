import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String file = "AdventOfCode2024/Day1/input1.txt";
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){  
            String line;
            while((line = reader.readLine()) != null) {
                String[] inputs = line.split("   ");
                list1.add(Integer.valueOf(inputs[0]));
                list2.add(Integer.valueOf(inputs[1]));
            } 

            Collections.sort(list1);
            Collections.sort(list2);
            int ans = 0;
            for(int i=0; i<list1.size(); i++) {
                ans += Math.abs(list1.get(i) - list2.get(i));
            }

            System.out.println(ans);

            reader.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }
}