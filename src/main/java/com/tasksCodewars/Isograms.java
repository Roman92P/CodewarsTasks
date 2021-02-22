package com.tasksCodewars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Isograms {
    public static boolean  isIsogram(String str) {
        boolean result = false;
        str = str.toUpperCase();
        String[] split = str.split("");
        List<String>test = new ArrayList<>();
        for ( String s: split ){
            test.add(s);
        }
        List<String> collect = test.stream().distinct().collect(Collectors.toList());
        if(collect.size()==test.size()){
            result = true;
        }
        return result;
    }
}
