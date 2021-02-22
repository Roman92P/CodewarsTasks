package com.tasksCodewars;

import java.util.ArrayList;
import java.util.List;

public class SumOfDigits {
    public static int digital_root(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();

        List<Integer> listInt = new ArrayList<>();
        for ( Character c : chars ) {
            listInt.add(Integer.parseInt(String.valueOf(c)));
        }
        Integer collect = listInt.stream().mapToInt(Integer::intValue).sum();
        if(String.valueOf(collect).toCharArray().length>1){
            collect = digital_root(collect);
        }

        return collect;

    }
}
