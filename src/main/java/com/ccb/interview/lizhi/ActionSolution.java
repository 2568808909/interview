package com.ccb.interview.lizhi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 在荔枝解决action问题时想的小算法
 */
public class ActionSolution {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("杰伦");
        words.add("杰伦");
        words.add("青花瓷");
        Map<String, List<Integer>> map = new HashMap<>();
        String brief = "杰伦，我好喜欢你的青花瓷啊，杰伦";
        int index;
        for (String word : words) {
            List<Integer> count = map.computeIfAbsent(word, k -> new ArrayList<>());
            if (count.size() == 0) {
                index = brief.indexOf(word);
            } else {
                Integer lastIndex = count.get(count.size() - 1);
                index = brief.indexOf(word, lastIndex + 1);
            }
            if (index != -1) {
                count.add(index);
            }
        }
        System.out.println(map);
    }
}
