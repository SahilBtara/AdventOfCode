package Day2;

import java.io.BufferedReader;
import java.io.FileReader;

public class Two {
    public static void main(String[] args) {
        String path = "Day2/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            int ans = 0;
            while ((line = reader.readLine()) != null) {
                String[] report = line.split(" ");
                if(isValidReport(report, 0, Integer.MIN_VALUE, false, true) || 
                    isValidReport(report, 0, Integer.MIN_VALUE, false, false)) {
                    ans++;
                }
            }
            System.out.println("Answer = " + ans);
        } catch(Exception e) {
            System.out.println("File not found");
        }
    }

    public static boolean isValidReport(String[] report, int idx, int prevValue, boolean removed, boolean increasing) {
        if(idx == report.length) {
            return true;
        }

        if(!removed) {
            if(isValidReport(report, idx+1, prevValue, true, increasing)) {
                return true;
            }
        }

        int currValue = Integer.parseInt(report[idx]);
        int diff = Math.abs(currValue - prevValue);
        
        // Keep the element and check validity
        if(prevValue != Integer.MIN_VALUE) {
            if(diff<1 || diff>3) {
                return false;
            }
            if(increasing && currValue < prevValue) {
                return false;
            }
            if(!increasing && currValue > prevValue) {
                return false;
            }
        }

        return isValidReport(report, idx+1, currValue, removed, increasing);

    }

}