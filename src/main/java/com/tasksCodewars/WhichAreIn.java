package com.tasksCodewars;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WhichAreIn {
    public static void main(String[] args) {
        String[] array1 = {"tarp", "mice", "bull"};
        String[] array2 = {"lively", "alive", "harp", "sharp", "armstrong"};
        String join = String.join("", array2);
        List<String> ls = new ArrayList<>();
        for ( int i = 0; i < array1.length; i++ ) {
            if (join.contains(array1[i])) {
                ls.add(array1[i]);
            }
        }
        String[] array = ls.toArray(new String[0]);
        Arrays.sort(array);
        
    }
}
