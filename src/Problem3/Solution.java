package Problem3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/** You Can Go Your Own Way */

public class Solution {

    public Scanner scanner;

    public Solution() {
        scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    }

    public void getSolution() {
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {
            int N = scanner.nextInt();
            System.out.print("Case #" + i + ": ");
            for (char move : scanner.next().toCharArray()) {
                char newMove =  (move == 'S') ? 'E' : 'S';
                System.out.print(newMove);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        new Solution().getSolution();
    }

}
