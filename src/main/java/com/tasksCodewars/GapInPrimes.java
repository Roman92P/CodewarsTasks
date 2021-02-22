package com.tasksCodewars;

import java.util.ArrayList;
import java.util.List;

public class GapInPrimes {
    public static void main(String[] args) {

        System.out.println(gap(2,100,110));

    }
    //wrong
    public static List<Long> gap(int g, long m, long n) {
        List<Long> arr = new ArrayList<>();
        for ( long i = m; i <m+1 ; i++ ) {
            int count = 0;
            for ( int j = 1; j <i ; j++ ) {
                if(i%j==0){
                    count++;
                }
                if(count==0){
                    arr.add(i);
                }
            }
        }
        return arr;
    }
}
