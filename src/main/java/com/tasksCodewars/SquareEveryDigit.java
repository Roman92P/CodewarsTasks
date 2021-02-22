package com.tasksCodewars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SquareEveryDigit {
    public int squareDigits(int n) {
        String str = String.valueOf(n);
        char[]chars = str.toCharArray();
        List<Integer> intList = new ArrayList<>();
        for ( Character c:chars ){
            intList.add(Integer.parseInt(String.valueOf(c)));
        }
        List<Integer> collect = intList.stream().map(integer -> integer * integer).collect(Collectors.toList());
        String str1 = collect.stream().map(i->i.toString()).collect(Collectors.joining(""));
        int result =Integer.parseInt(str1);
        return result;
    }
}
