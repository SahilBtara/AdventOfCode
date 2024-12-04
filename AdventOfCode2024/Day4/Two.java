package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Two {
    public static void main(String[] args) {
        String path = "AdventOfCode2024/Day4/input.txt";
        List<String> input = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                input.add(line);
            }
            int ans = countXmas(input);
            System.out.println(ans);
        } catch(Exception e) {
            System.out.println("Exception ");
        }
    }

    public static int countXmas(List<String> input) {
        // Strings to match
        int m = input.size(), n = input.get(0).length();
        char[][] inputArr = new char[m][n];
        buildInpurArr(input, inputArr);
        // printArr(inputArr);
        // calculate ans
        int ans = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(inputArr[i][j] != 'A') {
                    continue;
                }
                if(i-1<0 || j-1<0 || i+1>=m || j+1>=n) {
                    continue;
                }
                String str1 = "" + inputArr[i-1][j-1] + inputArr[i][j] + inputArr[i+1][j+1];
                String str2 = "" + inputArr[i-1][j+1] + inputArr[i][j] + inputArr[i+1][j-1];
                if(("MAS".equals(str1) || "SAM".equals(str1)) && ("MAS".equals(str2) || "SAM".equals(str2))) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void buildInpurArr(List<String> input, char[][] inputArr) {
        for(int i=0; i<input.size(); i++) {
            int j = 0;
            for(char ch : input.get(i).toCharArray()) {
                inputArr[i][j++] = ch;
            }
        }
    }

    public static void printArr(char[][] input) {
        System.out.println("Number of lines of input " + input.length);
        for(char[] input1 : input) {
            for(int j = 0; j<input1.length; j++) {
                System.out.print(input1[j] + " ");
            }
            System.out.println();
        }
    }
}
