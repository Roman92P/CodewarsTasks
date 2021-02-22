package com.tasksCodewars;

import java.util.Arrays;

public class SupermarketQueue {
    public static void main(String[] args) {
//        test data
        int[] customers = new int[]{2, 2, 3, 3, 4, 4 };
        int n = 2;
//        end of test data
        if(n>customers.length){
            n = customers.length;
        }
        int allTimeSum = Arrays.stream(customers).sum();
        int[] firstOnes = Arrays.copyOfRange(customers, 0, n);
        customers = removeFirstNElement(n, customers);
        int[] sumOnEachKass = Arrays.copyOf(firstOnes,firstOnes.length);
        for ( int i = 0 ; i < allTimeSum; i++ ) {
            System.out.println("iteracja");
            for ( int j = 0; j <n; j++ ) {
                int k = firstOnes[j]-1;
                firstOnes[j] = firstOnes[j]-1;
                if(k==0){
                    if(customers.length==0){
                        break;
                    }
                    sumOnEachKass[j] = sumOnEachKass[j]+customers[0];
                    firstOnes[j] = customers[0];
                    customers = removeFirstNElement(1, customers);

                }
            }
        }
        int result = Arrays.stream(sumOnEachKass).max().orElse(0);
        System.out.println(result);
    }
    private static int[] removeFirstNElement(int n, int[] customers) {
        return Arrays.copyOfRange(customers, n, customers.length);
    }


}
