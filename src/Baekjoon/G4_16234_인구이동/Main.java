package Baekjoon.G4_16234_인구이동;

import java.util.*;
import java.io.*;

public class Main {

    private static int answer = 0;
    private static int N, L, R;
    private static int[][] populations;
    private static boolean[][] v;

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        populations = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) populations[i][j] = Integer.parseInt(st.nextToken());
        }

        // solution
        solution();

        // output
        System.out.println(answer);
        br.close();
    }

    public static void solution() {
        while (true) {
            boolean anyMove = false;
            v = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j]) anyMove = bfs(i, j) || anyMove;
                }
            }

            if (!anyMove) return;
            answer++;
        }
    }

    public static boolean bfs(int i, int j) {
        final int[] di = {-1, 0, 1, 0};
        final int[] dj = {0, 1, 0, -1};
        final List<int[]> list = new ArrayList<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();

        q.offerLast(new int[]{i, j});
        v[i][j] = true;
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            list.add(new int[]{cur[0], cur[1]});
            for (int d = 0; d < 4; d++) {
                int ni = cur[0] + di[d];
                int nj = cur[1] + dj[d];
                if (0 <= ni && ni < N && 0 <= nj && nj < N && !v[ni][nj] &&
                        L <= Math.abs(populations[cur[0]][cur[1]] - populations[ni][nj]) &&
                        Math.abs(populations[cur[0]][cur[1]] - populations[ni][nj]) <= R) {
                    v[ni][nj] = true;
                    q.offerLast(new int[]{ni, nj});
                }
            }
        }

        if (list.size() == 1) return false;

        int sum = list.stream().mapToInt(p -> populations[p[0]][p[1]]).sum();
        int nPopulation = sum / list.size();
        for (int[] p : list) populations[p[0]][p[1]] = nPopulation;
        return true;
    }

}
