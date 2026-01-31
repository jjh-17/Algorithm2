package Programmes.LV2_충돌위험찾기;

import java.util.*;

class Solution {
    class Node {
        Map<Integer, Integer> visited = new HashMap<>(); // 시간대 별 방문 로봇 수
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 그래프 초기화
        final Node[][] G = new Node[101][101];
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                G[i][j] = new Node();
            }
        }

        // 각 로봇의 루트 순회
        for (int[] route : routes) {
            int time = 0;
            int r = points[route[0] - 1][0];
            int c = points[route[0] - 1][1];
            G[r][c].visited.put(time, G[r][c].visited.getOrDefault(time++, 0) + 1);

            // 루트에 따라 이동
            for (int i = 1; i < route.length; i++) {
                int nr = points[route[i] - 1][0];
                int nc = points[route[i] - 1][1];

                int dr = Integer.compare(nr, r);
                for (int j = 1; j <= Math.abs(r - nr); j++) {
                    G[r + dr * j][c].visited.put(time, G[r + dr * j][c].visited.getOrDefault(time++, 0) + 1);
                }

                int dc = Integer.compare(nc, c);
                for (int j = 1; j <= Math.abs(c - nc); j++) {
                    G[nr][c + dc * j].visited.put(time, G[nr][c + dc * j].visited.getOrDefault(time++, 0) + 1);
                }

                r = nr;
                c = nc;
            }
        }

        // 각 노드를 순회하면서 시간대 별 방문한 로봇의 수가 2이상이면 위험 횟수 증가
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                for (int v : G[i][j].visited.values()) {
                    if (v >= 2) {
                        ++answer;
                    }
                }
            }
        }

        return answer;
    }
}
