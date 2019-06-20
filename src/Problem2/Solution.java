package Problem2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

/** Forgone Solution */

public class Solution {

    public Scanner scanner;

    public Solution() {
        scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    }

    public BigInteger N;
    public char K = '4';

    public boolean isGood(String s) {
        return s.indexOf(K) == -1;
    }

    public String increaseCheckValue(String s) {
        return changeCheckValue(s, true);
    }

    public String decreaseCheckValue(String s) {
        return changeCheckValue(s, false);
    }

    public String changeCheckValue(String s, boolean increase) {
        char[] arr = s.toCharArray();
        int i = s.indexOf(K);
        arr[i] = increase ? '5' : '3';

        while (i++ != arr.length - 1) {
            arr[i] = increase ? '0' : '9';
        }
        return new String(arr);
    }

    public void getSolution() {
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {
            N = new BigInteger(scanner.next());

            BigInteger N1 = new BigInteger("1"), N2 = N.subtract(N1);

            while (!isGood(N1.toString()) || !isGood(N2.toString())) {
                while (!isGood(N1.toString())) {
                    N1 = new BigInteger(increaseCheckValue(N1.toString()));
                    N2 = N.subtract(N1);
                }
                while (!isGood(N2.toString())) {
                    N2 = new BigInteger(decreaseCheckValue(N2.toString()));
                    N1 = N.subtract(N2);
                }
            }

            System.out.println("Case #" + (i + 1) + ": " + N1 + " " + N2);
        }
    }

    public static void main(String[] args) {
        new Solution().getSolution();
    }
}
