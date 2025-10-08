package Baekjoon.S2_1260_DFS와BFS;

import java.io.*;
import java.util.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();
    static int N, M, V;
    static boolean[] v;
    static PriorityQueue<Integer>[] dfsPQ;
    static PriorityQueue<Integer>[] bfsPQ;

    public static void main(String[] args) throws Exception {
        System.setIn(Main.class.getResourceAsStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, M, V
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        v = new boolean[N];

        // 정점 번호가 작은 것을 먼저 방문하기 위함
        dfsPQ = new PriorityQueue[N+1];
        bfsPQ = new PriorityQueue[N+1];
        for(int i=0;i<=N;i++) {
            dfsPQ[i] = new PriorityQueue<>();
            bfsPQ[i] = new PriorityQueue<>();
        }
        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            dfsPQ[start].add(end); dfsPQ[end].add(start);
            bfsPQ[start].add(end); bfsPQ[end].add(start);
        }

        //dfs
        v = new boolean[N+1];
        dfs(V);
        sb.append("\n");

        //bfs
        v = new boolean[N+1];
        bfs(V);
        br.close();
        System.out.println(sb.toString());
    }

    static void dfs(int s) {
        v[s] = true;

        sb.append(s).append(" ");
        while(!dfsPQ[s].isEmpty()) {
            int next = dfsPQ[s].poll();
            if(!v[next]) dfs(next);
        }
    }

    static void bfs(int s) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        queue.offerLast(s);
        v[s] = true;
        while(!queue.isEmpty()) {
            int start = queue.pollFirst();
            sb.append(start).append(" ");
            while(!bfsPQ[start].isEmpty()) {
                int end = bfsPQ[start].poll();
                if(!v[end]) {
                    v[end] = true;
                    queue.offerLast(end);
                }
            }
        }
    }

}
