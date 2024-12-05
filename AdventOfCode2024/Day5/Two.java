package Day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

class Two {
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
        boolean invalid = false;
        for(int num : numbers) {
            boolean canPrint = canPrint(num, rules, numbersPrintedTillNow, set);
            if(!canPrint) {
                invalid = true;
                break;
            }
            numbersPrintedTillNow.add(num);
        }
        if(invalid) {
            return reorder(numbers, rules, set);
        }
        return -1;
    }

    public static int reorder(int[] numbers, HashMap<Integer, HashSet<Integer>> rules, HashSet<Integer> set) {
        List<Integer> list = new ArrayList<>();
        for(int num : numbers) {
            addNext(num, rules, list, new HashSet<>(), set);
        }
        return list.get(list.size()/2);
    }

    public static void addNext(int num, HashMap<Integer, HashSet<Integer>> rules, List<Integer> list, HashSet<Integer> inProcessing, HashSet<Integer> set) {
        if(list.contains(num) || inProcessing.contains(num) || !set.contains(num)) {
            return;
        }
        // if there are no rules for this num just add this
        if(!rules.containsKey(num)) {
            list.add(num);
            return;
        }

        inProcessing.add(num);
        for(Integer rule : rules.get(num)) {
            addNext(rule, rules, list, inProcessing, set);
        }
        list.add(num);
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