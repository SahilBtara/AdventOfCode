package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class One {
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
        String XMAS = "XMAS", SAMX = "SAMX";

        int m = input.size(), n = input.get(0).length();
        char[][] inputArr = new char[m][n];
        buildInpurArr(input, inputArr);
        printArr(inputArr);
        // calculate ans
        int ans = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                String str = "";
                // horizontal
                int c = j;
                for(int count = 0; count<4 && c<n; count++) {
                    str += inputArr[i][c++];
                }
                if(XMAS.equals(str) || SAMX.equals(str)) {
                    ans++;
                }

                // vertical
                str = "";
                int r = i;
                for(int count = 0; count<4 && r<m; count++) {
                    str += inputArr[r++][j];
                }
                if(XMAS.equals(str) || SAMX.equals(str)) {
                    ans++;
                }

                // right diagonal
                str = "";
                r=i; c=j;
                for(int count = 0; count<4 && r<m && c<n; count++) {
                    str += inputArr[r++][c++];
                }
                if(XMAS.equals(str) || SAMX.equals(str)) {
                    ans++;
                }

                // left diagonal
                str = "";
                r=i; c=j;
                for(int count = 0; count<4 && r<m && c>=0; count++) {
                    str += inputArr[r++][c--];
                }
                if(XMAS.equals(str) || SAMX.equals(str)) {
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
