package Day6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Two {
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
            // findThePath(map, r, c, 0);
            int ans = 0;
            for(int i=0; i<map.length; i++) {
                for(int j=0; j<map[0].length; j++) {
                    // no block already and not starting position
                    if(map[i][j] != '#' && !(i==r && j==c)) {
                        char block = map[i][j];
                        map[i][j] = '#';
                        boolean isCycle = cycle(map, r, c, 0, new int[map.length][map[0].length]);
                        if(isCycle) {
                            ans++;
                        }
                        map[i][j] = block;
                    }
                }
            }
            System.out.println(ans);
            // printArr(map);
        } catch(Exception e) {
            System.out.println("Exception!!!!!!!!" + e);
        }
    }

    public static boolean cycle(char[][] map, int r, int c, int direction, int[][] positionCount) {
        if(r<0 || r>=map.length || c<0 || c>=map[0].length) {
            return false;
        }
        if(positionCount[r][c] > 4) {
            return true;
        }
        positionCount[r][c]++;
        switch (direction) {
            // up
            case 0 -> {
                if(r-1>=0 && map[r-1][c]=='#') {
                    direction = (direction+1)%4;
                    return cycle(map, r, c, direction, positionCount);
                } else {
                    return cycle(map, r-1, c, direction, positionCount);
                }
            }
            // right
            case 1 -> {
                if(c+1<map[0].length && map[r][c+1]=='#') {
                    direction = (direction+1)%4;
                    return cycle(map, r, c, direction, positionCount);
                } else {
                    return cycle(map, r, c+1, direction, positionCount);
                }
            }
            // down
            case 2 -> {
                if(r+1<map.length && map[r+1][c]=='#') {
                    direction = (direction+1)%4;
                    return cycle(map, r, c, direction, positionCount);
                } else {
                    return cycle(map, r+1, c, direction, positionCount);
                }
            }
            // left
            case 3 -> {
                if(c-1>=0 && map[r][c-1]=='#') {
                    direction = (direction+1)%4;
                    return cycle(map, r, c, direction, positionCount);
                } else {
                    return cycle(map, r, c-1, direction, positionCount);
                }
            }
        }
        return false;
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