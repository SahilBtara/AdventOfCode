package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class One {
    public static void main(String[] args) {
        String path = "AdventOfCode2024/Day6/input.txt";
        List<List<Character>> mapList = new ArrayList<>();
        mapList = new ArrayList<>();
        int r=0, c=0;
        try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                List<Character> row = new ArrayList<>();
                int idx = 0;
                for(Character ch : line.toCharArray()) {
                    if(ch == '^') {
                        r = mapList.size();
                        c = idx;
                    }
                    row.add(ch);
                    idx++;
                }               
                mapList.add(row); 
            }
            char[][] map = convertToArr(mapList);
            int ans = findThePath(map, r, c, 0);
            System.out.println(ans);
            // printArr(map);
        } catch(Exception e) {
            System.out.println("Exception!!!!!!!!" + e);
        }
    }

    public static int findThePath(char[][] map, int r, int c, int direction) {
        int ans = 0;
        if(r<0 || r>=map.length || c<0 || c>=map[0].length) {
            return 1;
        }
        if(map[r][c] == '.') {
            map[r][c] = 'X';
            ans++;
        }
        switch(direction) {
            case 0 -> {
                // switch direction
                if(r-1>=0 && map[r-1][c]=='#') {
                    direction = (direction+1)%4;
                    ans += findThePath(map, r, c, direction);
                } else {
                    ans += findThePath(map, r-1, c, direction);
                }
            }
            case 1 -> {
                // switch direction
                if(c+1<map[0].length && map[r][c+1]=='#') {
                    direction = (direction+1)%4;
                    ans += findThePath(map, r, c, direction);
                } else {
                    ans += findThePath(map, r, c+1, direction);
                }
            }
            case 2 -> {
                // switch direction
                if(r+1<map.length && map[r+1][c]=='#') {
                    direction = (direction+1)%4;
                    ans += findThePath(map, r, c, direction);
                } else {
                    ans += findThePath(map, r+1, c, direction);
                }
            }
            case 3 -> {
                // switch direction
                if(c-1>=0 && map[r][c-1]=='#') {
                    direction = (direction+1)%4;
                    ans += findThePath(map, r, c, direction);
                } else {
                    ans += findThePath(map, r, c-1, direction);
                }
            }
        }
        return ans;
    }

    public static char[][] convertToArr(List<List<Character>> mapList) {
        int m = mapList.size(), n = mapList.get(0).size();
        char[][] map = new char[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                map[i][j] = mapList.get(i).get(j);
            }
        }
        return map;
    }

    public static void printArr(char[][] map) {
        for (char[] map1 : map) {
            for (int j = 0; j<map1.length; j++) {
                System.out.print(map1[j] + " ");
            }
            System.out.println();
        }
    }
}