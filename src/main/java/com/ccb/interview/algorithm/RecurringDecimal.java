package com.ccb.interview.algorithm;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * 给出一个分数如，如果是循环小数则用括号括其循环部分，否则，给出小数形式
 * 输入两个数
 * 1 3
 * 1 4
 * 1 6
 * 1 7
 * 输出结果
 * 0.(3)
 * 0.25
 * 0.1(6)
 * 0.(142857)
 */
public class RecurringDecimal {

    public static void main(String[] args) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt(), m = scanner.nextInt();
            StringBuilder res = new StringBuilder("0.");
            int cur = -1;
            while (cur != 0) {
                n *= 10;
                if (n < m) {
                    res.append(0);
                    continue;
                }
                cur = n % m;
                n /= m;
                if (!set.add(n)) {
                    Iterator<Integer> iterator = set.iterator();
                    while (iterator.hasNext()) {
                        Integer next = iterator.next();
                        if (next.equals(n)) {
                            break;
                        } else {
                            iterator.remove();
                        }
                    }
                    res=new StringBuilder(res.toString().substring(0, res.length() - set.size()));
                    res.append("(");
                    set.forEach(res::append);
                    res.append(")");
                    break;
                }
                res.append(n);
                n = cur;
            }
            System.out.println(res);
            set.clear();
        }
        scanner.close();
    }
}
