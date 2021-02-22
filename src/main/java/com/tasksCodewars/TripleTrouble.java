package com.tasksCodewars;

public class TripleTrouble {
    public static void main(String[] args) {

    }

    public static int TripleDouble(long num1, long num2) {
        String str1 = String.valueOf(num1);
        String str2 = String.valueOf(num2);

        int triple = findTriple(str1);
        boolean aDouble = findDouble(str2, triple);

        if (triple != 0 && aDouble) {
            return 1;
        }
        return 0;
    }

    private static boolean findDouble(String s, int number) {
        boolean result = false;
        char c = (char) number;
        int count = 0;
        for ( int i = 1; i < s.length(); i++ ) {
            if (c == s.charAt(i)) {
                count++;
                if (count == 2) {
                    result = true;
                }
            } else {
                count = 0;
            }
        }
        return result;
    }

    private static int findTriple(String s) {
        int result = 0;
        char c = s.charAt(0);
        int count = 0;
        for ( int i = 1; i < s.length(); i++ ) {
            if (c == s.charAt(i)) {
                count++;
                if (count == 2) {
                    result = c;
                }
            } else {
                count = 0;
                c = s.charAt(i);
            }
        }
        return result;
    }
}
