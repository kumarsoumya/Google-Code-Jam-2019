package Problem1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/** Cryptoanagrams */

public class Solution {

    public Scanner scanner;

    public Solution() {
        scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
    }

    public BigInteger GCD(BigInteger a, BigInteger b) {
        return a.gcd(b);
    }

    public int GCD(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == b) {
            return b;
        }
        if (a > b) {
            return GCD(a - b, b);
        }
        return GCD(a, b - a);
    }

    public BigInteger[] factorCodes(BigInteger[] codes) {
        int L = codes.length;

        BigInteger[] cracked = new BigInteger[L + 1];

        int mi = 0;
        for (int i = 1; i < L; i++) {
            if (codes[i - 1].compareTo(codes[i]) != 0) {
                mi = i;
                break;
            }
        }

        BigInteger prime = GCD(codes[mi - 1], codes[mi]);
        cracked[mi] = prime;
        for (int i = mi - 1; i >= 0; i--) {
            cracked[i] = codes[i].divide(cracked[i + 1]);
        }

        for (int i = mi + 1; i < L + 1; i++) {
            cracked[i] = codes[i - 1].divide(cracked[i - 1]);
        }
        return cracked;
    }

    public int[] factorCodes(int[] codes) {
        int L = codes.length;

        int[] cracked = new int[L + 1];

        int mi = 0;
        for (int i = 1; i < L; i++) {
            if (codes[i - 1] != codes[i]) {
                mi = i;
                break;
            }
        }

        int prime = GCD(codes[mi - 1], codes[mi]);
        cracked[mi] = prime;
        for (int i = mi - 1; i >= 0; i--) {
            cracked[i] = codes[i] / cracked[i + 1];
        }

        for (int i = mi + 1; i < L + 1; i++) {
            cracked[i] = codes[i - 1] / cracked[i - 1];
        }
        return cracked;
    }

    public void getSolution() {
        int T = scanner.nextInt();

        for (int i = 1; i <= T; i++) {

            String N = scanner.next();
            boolean isBigInteger = false;
            try {
                Integer.parseInt(N);
            } catch (NumberFormatException e) {
                isBigInteger = true;
            }
            int L = scanner.nextInt();

            if (isBigInteger) {
                BigInteger[] codes = new BigInteger[L];
                for (int j = 0; j < L; j++) {
                    codes[j] = new BigInteger(scanner.next());
                }
                BigInteger[] cracked = factorCodes(codes);
                int M = cracked.length;
                BigInteger[] crackedCopy = Arrays.copyOf(cracked, M);
                Arrays.sort(crackedCopy);
                HashMap<BigInteger, Integer> map = new HashMap<BigInteger, Integer>(26);
                int order = 0;
                for (int j = 1; j < M; j++) {
                    if (crackedCopy[j - 1].compareTo(crackedCopy[j]) != 0) {
                        map.put(crackedCopy[j - 1], order++);
                    }
                }
                map.put(crackedCopy[M - 1], order);
                System.out.print("Case #" + i + ": ");
                for (int j = 0; j < cracked.length; j++) {
                    if (map.containsKey(cracked[j])) {
                        System.out.print((char)('A' + map.get(cracked[j])));
                    }
                }
                System.out.println();
            } else {
                int[] codes = new int[L];
                for (int j = 0; j < L; j++) {
                    codes[j] = scanner.nextInt();
                }
                int[] cracked = factorCodes(codes);
                int M = cracked.length;
                int[] crackedCopy = Arrays.copyOf(cracked, M);
                Arrays.sort(crackedCopy);
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(26);
                int order = 0;
                for (int j = 1; j < M; j++) {
                    if (crackedCopy[j - 1] != crackedCopy[j]) {
                        map.put(crackedCopy[j - 1], order++);
                    }
                }
                map.put(crackedCopy[M - 1], order);

                System.out.print("Case #" + i + ": ");
                for (int j = 0; j < cracked.length; j++) {
                    if (map.containsKey(cracked[j])) {
                        System.out.print((char)('A' + map.get(cracked[j])));
                    }
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        new Solution().getSolution();
    }

}
