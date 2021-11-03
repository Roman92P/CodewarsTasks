package com.tasksCodewars;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

public class Hamming {
    private static final Logger log = LogManager.getLogger(Hamming.class);

    public static long hamming(int n) {
        long[] h = new long[n];
        h[0] = 1;
        long x2 = 2, x3 = 3, x5 = 5;
        int i = 0, j = 0, k = 0;

        for (int index = 1; index < n; index++) {
            h[index] = Math.min(x2, Math.min(x3, x5));
            if (h[index] == x2) x2 = 2 * h[++i];
            if (h[index] == x3) x3 = 3 * h[++j];
            if (h[index] == x5) x5 = 5 * h[++k];
        }

        return h[n - 1];
    }

    public static double multiply235(int n) {
        List<Double> list = new ArrayList<>();
        double number = 1;
        list.add((double) number);
        for ( int i = 0; i < n; i++ ) {
            for ( int j = 0; j < n; j++ ) {
                for ( int k = 0; k < n; k++ ) {
                    number = Math.pow((long)2, i) * Math.pow((long)3, j) * Math.pow((long)5, k);
                    list.add((double) number);
                }
            }
           list =  list.stream().sorted().collect(Collectors.toList());
        }
        System.out.println(list);
        return new ArrayList<>(list).stream().distinct().collect(Collectors.toList()).get(n-1);
    }
// another try
    public static long hammingTwo(int n){
        TreeSet<Long> ts = new TreeSet<>(Arrays.asList(2L, 3L, 5L));
        long smallest = 1;
        for (int i = 1; i < n; i++) {
            smallest = ts.pollFirst();
            ts.add(smallest * 2);
            ts.add(smallest * 3);
            ts.add(smallest * 5);
            System.out.println(ts);
        }
        return smallest;
    }
}
