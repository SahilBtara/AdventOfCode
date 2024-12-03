package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class One {
    public static void main(String[] args) {
        String path = "AdventOfCode2024/Day3/input.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
            int ans = 0;
            while((line = reader.readLine()) != null) {
                ans += getMulSum(line, pattern);
            }
            System.out.println(ans);
        } catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public static int getMulSum(String line, Pattern pattern) {
        int mulSum = 0;
        Matcher matcher = pattern.matcher(line);
        while(matcher.find()) {
            String match = matcher.group();
            Pattern numbersPattern = Pattern.compile("\\d{1,3}");
            Matcher numberMatch = numbersPattern.matcher(match);
            int mult = 1;
            while(numberMatch.find()) {
                int number = Integer.parseInt(numberMatch.group());
                mult *= number;
            }
            mulSum += mult;
        }
        return mulSum;
    }
}