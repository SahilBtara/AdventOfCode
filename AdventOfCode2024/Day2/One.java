package Day2;

import java.io.BufferedReader;
import java.io.FileReader;

public class One {
    public static void main(String[] args) {
        String path = "AdventOfCode2024/Day2/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int ans = 0;
            while ((line = reader.readLine()) != null) {
                String[] report = line.split(" ");
                boolean increasing = true, decreasing = true;
                String prevLevel = null;
                for(String level : report) {
                    if(prevLevel == null) {
                        prevLevel = level;
                        continue;
                    }
                    int curr = Integer.parseInt(level);
                    int prev = Integer.parseInt(prevLevel);
                    int diff = Math.abs(curr - prev);
                    prevLevel = level;
                    if(diff < 1 || diff > 3) {
                        decreasing = false;
                        increasing = false;
                        break;
                    }
                    if(curr > prev) {
                        decreasing = false;
                    } else if(prev > curr) {
                        increasing = false;
                    }
                }
                if(increasing || decreasing) {
                    ans++;
                }
            }
            System.out.println(ans);
        } catch(Exception e) {
            System.out.println("File not found");
        }
    }
}