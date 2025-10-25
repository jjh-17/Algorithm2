package Baekjoon.G5_12919_A와B2;

import java.io.*;

public class Main {

    private static String S, T;
    private static int answer = 0;

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // input
        S = br.readLine();
        T = br.readLine();

        // solution
        solution(new StringBuilder(T));

        // output
        br.close();
        System.out.println(answer);
    }

    public static void solution(StringBuilder cur) {
        if (answer == 1) return;
        if (cur.length() == S.length()) {
            answer = cur.toString().equals(S) ? 1 : 0;
            return;
        }

        // T -> S
        if (cur.toString().charAt(0) == 'B') {
            solution(new StringBuilder(cur).deleteCharAt(0).reverse());
        }
        if (cur.toString().charAt(cur.length()-1) == 'A') {
            solution(new StringBuilder(cur).deleteCharAt(cur.length()-1));
        }
    }
}
