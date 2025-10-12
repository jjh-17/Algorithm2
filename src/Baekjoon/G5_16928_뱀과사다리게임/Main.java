package Baekjoon.G5_16928_뱀과사다리게임;

import java.util.*;
import java.io.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();
    private static final int L = 100;
    private static final int D = 6;
    private static final Map<Integer, Integer> map = new HashMap<>();
    private static final int[] count = new int[L + 1];
    private static int N, M;

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // algorithm
        algorithm();

        // output
        System.out.println(sb.toString().trim());
        br.close();
    }

    private static void algorithm() {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(1);

        int n = 0;
        while (!dq.isEmpty()) {
            int l = dq.size();
            for (int i = 0; i < l; i++) {
                int cp = dq.pollFirst();
                if (cp == L) {
                    sb.append(count[L]);
                    return;
                }

                for (int d = 1; d < D + 1; d++) {
                    int np = cp + d;
                    if (np > L) break;
                    if (map.containsKey(np)) np = map.get(np);
                    if (count[np] == 0 || count[np] > n + 1) {
                        count[np] = n + 1;
                        dq.offerLast(np);
                    }
                }
            }
            n++;
        }
    }

}
