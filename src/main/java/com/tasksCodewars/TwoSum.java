package com.tasksCodewars;

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(twoSum(new int[]{4,2,6}, 10)));



    }

    public static int[] twoSum(int[] numbers, int target) {

        int[] ints = new int[2];
        for ( int i = 0; i < numbers.length; i++ ) {
            for ( int j = 0; j < numbers.length; j++ ) {
                if(i!=j) {
                    if ((Double.parseDouble(String.valueOf(numbers[i])) / target) + (Double.parseDouble(String.valueOf(numbers[j])) / target) == 1.0) {
                        ints[0] = i;
                        ints[1] = j;
                        break;
                    }

                }
            }
        }

        return ints;
    }
}
