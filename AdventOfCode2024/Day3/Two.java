package Day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Two {
    static Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");
    static String DO = "do()";
    static String DONT = "don't()";
    static boolean enabled = true;
    
    public static void main(String[] args) {
        String path = "AdventOfCode2024/Day3/input.txt";
        int ans = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                ans += getMul(line);
            }
            System.out.println(ans);
        } catch(Exception e) {
            System.out.println("Exception " + e);
        }
    }

    public static int getMul(String line) {
        int startIdx = 0;
        int ans = 0;
        for(int i=0; i<line.length(); i++) {
            if(i+7 < line.length() && DONT.equals(line.substring(i, i+7))) {
                if(enabled) {
                    ans += getMulSum(line.substring(startIdx, i));
                }
                enabled = false;
            } 
            if(i+4 < line.length() && DO.equals(line.substring(i, i+4))) {
                if(!enabled) startIdx = i;
                enabled = true;
            } 
        }
        if(enabled) {
            ans += getMulSum(line.substring(startIdx, line.length()));
        }
        return ans;
    }

    public static int getMulSum(String line) {
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