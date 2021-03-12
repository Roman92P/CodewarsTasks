package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Dubstep {
    public static void main(String[] args) {
        String song ="ABC";
        song =song.toUpperCase();
        song =song.trim();
        song = song.replace("  ", " ");
        String[] s = song.split(" ");
        List<Object> collect = Arrays.stream(s).collect(Collectors.toList());
        List<String>result = new ArrayList<>();
        for ( int i = 0; i < collect.size(); i++ ) {
            if (i % 2 ==0){
                String str = "WUBWUB"+collect.get(i) +"WUB";
                result.add(str);
            }else {
                String str = (String) collect.get(i);
                result.add(str);
            }
        }
        String collect1 = result.stream().collect(Collectors.joining());
        System.out.println(collect1);
    }
}
