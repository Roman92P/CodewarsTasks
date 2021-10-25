package com.tasksCodewars;

public class StripComments {
    public static void main(String[] args) {
        System.out.println(stripComments("apples, pears # and bananas\ngrapes\nbananas !apples", new String[] { "#", "!" }));
    }
    public static String stripComments(String text, String[] commentSymbols) {

        String[] split = text.split("\n");
        



        return split.toString();
    }
}


