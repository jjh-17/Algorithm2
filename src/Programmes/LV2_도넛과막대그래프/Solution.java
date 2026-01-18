package Programmes.LV2_도넛과막대그래프;

import java.util.*;

public class Solution {
    class Node {
        List<Integer> outs = new ArrayList<>(); // 나가는 간선의 목적지
        List<Integer> ins = new ArrayList<>();  // 들어온 간선의 출발지
    }

    public int[] solution(int[][] edges) {
        int createdNode = -1;   // 생성된 노드
        int numDonut = 0;       // 도넛 그래프 개수
        int numStick = 0;       // 막대 그래프 개수
        int num8 = 0;           // 8자 그래프 개수
        boolean[] v = new boolean[1_000_001];

        // 초기화
        Node[] G = new Node[1_000_001];
        for (int i = 1; i <= 1_000_000; i++) G[i] = new Node();
        for (int[] edge : edges) {
            G[edge[0]].outs.add(edge[1]);
            G[edge[1]].ins.add(edge[0]);
        }

        // 생성된 정점 찾기
        for (int i = 1; i <= 1_000_000; i++) {
            if (G[i].ins.size() == 0 && G[i].outs.size() >= 2) {
                createdNode = i;
                break;
            }
        }

        for (int nodeNum : G[createdNode].outs) {
            int curNode = nodeNum;

            // 한 번 들른 곳에 도달할 때까지 이동
            while (!v[curNode]) {
                v[curNode] = true;
                switch (G[curNode].outs.size()) {
                    case 0:
                        ++numStick;
                        break;
                    case 1:
                        curNode = G[curNode].outs.get(0);
                        break;
                    default:
                        ++num8;
                        break;
                }
            }
            if (G[curNode].outs.size() == 1) ++numDonut;
        }

        return new int[]{createdNode, numDonut, numStick, num8};
    }
}
