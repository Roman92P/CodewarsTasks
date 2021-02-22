package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mumbling {
    //    accum("abcd") -> "A-Bb-Ccc-Dddd"
//accum("RqaEzty") -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
//accum("cwAt") -> "C-Ww-Aaa-Tttt"
    public static void main(String[] args) {
//        String s = "NyffsGeyylB";
//        String s = "abcd";
//        String s = "ZpglnRxqenU";
//        String s = "NyffsGeyylB";
//        s = s.toLowerCase();
//
//        char[] chars = s.toCharArray();
//        char controlChar = 0;
//
//        int counter=0;
//        List<String> result = new ArrayList<>();
//        for ( int i = 0; i < chars.length; i++ ) {
//           char c = chars[i];
//            System.out.println("Wartość controlChar : "+ controlChar + "\n");
//            System.out.println("Wartość c z pętli: "+ c + "\n");
//            System.out.println("Counter"+counter+"\n");
//
//            if(c!=controlChar){
//                String schar = "-"+ Character.toString(c).toUpperCase();
//                if(i ==0){
//                    schar = Character.toString(c).toUpperCase();
//                }
//                result.add(schar);
//            }
////            int index = s.indexOf(chars[i]);
//            char[] arr = new char[counter];
//            Arrays.fill(arr, c);
//            StringBuilder builder = new StringBuilder();
//            for ( char value : arr) {
//                builder.append(value);
//            }
//            String text = builder.toString();
//           result.add(text);
//            controlChar = chars[i];
//            counter = counter+1;
//
//        }
//
//        result.forEach(System.out::print);
//        System.out.println("\n");
//    }


        String s = "NyffsGeyylB";
        s = s.toLowerCase();
        char[] chars = s.toCharArray();
        int counter=0;
        List<String> result = new ArrayList<>();
        for ( int i = 0; i < chars.length; i++ ) {
            char c = chars[i];
                String schar = "-"+ Character.toString(c).toUpperCase();
                if(i ==0){
                    schar = Character.toString(c).toUpperCase();
                }
                result.add(schar);
            char[] arr = new char[counter];
            Arrays.fill(arr, c);
            StringBuilder builder = new StringBuilder();
            for ( char value : arr) {
                builder.append(value);
            }
            String text = builder.toString();
            result.add(text);
            counter = counter+1;

        }

        StringBuilder builder = new StringBuilder();
        for(String x:result){
            builder.append(x);
        }
//        return builder.toString();
    }


}
