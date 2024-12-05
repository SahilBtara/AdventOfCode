package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;

class One {
    public static void main(String[] args) {
       String path = "AdventOfCode2024/Day5/input.txt";
       HashMap<Integer, HashSet<Integer>> rules = new HashMap<>();
       int ans = 0;
       try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                if(line.contains("|")) {
                    addRule(line, rules);
                } else if(!line.isBlank()) {
                    int middleNum = isValid(line, rules);
                    if(middleNum != -1){
                        ans+=middleNum;
                    }
                }
            }
            System.out.println(ans);
       } catch(Exception e) {
            System.out.println("Exception" + e);
       }
    }

    public static int isValid(String line, HashMap<Integer, HashSet<Integer>> rules) {
        String[] numbersStr = line.split(",");
        int[] numbers = new int[numbersStr.length];
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<numbersStr.length; i++) {
            numbers[i] = Integer.parseInt(numbersStr[i].trim());
            set.add(numbers[i]);
        } 

        HashSet<Integer> numbersPrintedTillNow = new HashSet<>();
        for(int num : numbers) {
            boolean canPrint = canPrint(num, rules, numbersPrintedTillNow, set);
            if(!canPrint) {
                return -1;
            }
            numbersPrintedTillNow.add(num);
        }
        return numbers[numbers.length/2];
    }

    public static boolean canPrint(int num,  HashMap<Integer, HashSet<Integer>> rules, HashSet<Integer> numbersPrintedTillNow, HashSet<Integer> set) {
        if(!rules.containsKey(num)) {
            return true;
        }
        for(Integer rule : rules.get(num)) {
            if(set.contains(rule) && !numbersPrintedTillNow.contains(rule)) {
                return false;
            }
        }
        return true;
    }

    public static void addRule(String line, HashMap<Integer, HashSet<Integer>> rules) {
        String[] numbers = line.split("\\|");
        int num1 = Integer.parseInt(numbers[0]), num2 = Integer.parseInt(numbers[1]);
        rules.computeIfAbsent(num2, k -> new HashSet<>()).add(num1);
    }
}