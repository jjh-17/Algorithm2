package Programmes.LV3_봉인된주문;

import java.util.*;
import java.lang.*;

public class Solution {
    final long RADIX = 'z' - 'a' + 1;

    public String solution(long n, String[] bans) {
        // 금지 마법을 길이 > 사전 순으로 정렬
        Arrays.sort(bans, (a, b) -> {
            if (a.length() != b.length())
                return Integer.compare(a.length(), b.length());
            return a.compareTo(b);
        });

        // 순회 하면서 n 증가
        for (String ban : bans) {
            long banNum = convertWord2Num(ban);
            if (banNum <= n) ++n;
        }

        return convertNum2Word(n);
    }

    // a-z로 나타낸 26진수
    private long convertWord2Num(String word) {
        long num = 0;

        for (int i = 0; i < word.length(); i++) {
            num += (long) (Math.pow(RADIX, word.length() - i - 1) * (word.charAt(i) - 'a' + 1));
        }

        return num;
    }

    // 26진수를 a-z로 나타냄
    private String convertNum2Word(long num) {
        String word = "";

        while (num > 0) {
            long rest = num % RADIX;
            rest = rest == 0 ? RADIX : rest;
            word = (char) ('a' + rest - 1) + word;
            num = num / RADIX - (rest == RADIX ? 1 : 0);
        }

        return word;
    }
}
