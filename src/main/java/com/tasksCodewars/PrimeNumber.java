package com.tasksCodewars;

public class PrimeNumber {
    public static void main(String[] args) {
        System.out.println(ifPrimeNumber(83));
    }

    private static boolean ifPrimeNumber(int i) {
        int count = 0;
        for ( int j = i; j > 0; j-- ) {
            if (i % j == 0) {
                count++;
            }
        }
        return count == 2;
    }

    private static boolean checkForPrime(int inputNumber) {
        boolean isItPrime = true;
        if (inputNumber <= 1) {
            isItPrime = false;
            return isItPrime;
        } else {
            for ( int i = 2; i <= inputNumber / 2; i++ ) {
                if ((inputNumber % i) == 0) {
                    isItPrime = false;
                    break;
                }
            }
        }
        return isItPrime;
    }
}
