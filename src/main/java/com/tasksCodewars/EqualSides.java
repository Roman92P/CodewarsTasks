package com.tasksCodewars;

public class EqualSides {
    public static void main(String[] args) {
//        assertEquals(3,Kata.findEvenIndex(new int[] {1,2,3,4,3,2,1}));
//        assertEquals(1,Kata.findEvenIndex(new int[] {1,100,50,-51,1,1}));
//        assertEquals(-1,Kata.findEvenIndex(new int[] {1,2,3,4,5,6}));
//        assertEquals(3,Kata.findEvenIndex(new int[] {20,10,30,10,10,15,35}));
//        assertEquals(-1,Kata.findEvenIndex(new int[] {-8505, -5130, 1926, -9026}));
//        assertEquals(1,Kata.findEvenIndex(new int[] {2824, 1774, -1490, -9084, -9696, 23094}));
//        assertEquals(6,Kata.findEvenIndex(new int[] {4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4}));
//    }
        int [] arr = {1,100,50,-51,1,1};
        int index = -1;
        int suma = 0;
        for ( int i = 0; i < arr.length; i++ ) {
            suma = suma + arr[i];
            int suma1 = 0;
            for ( int j = i;j < arr.length; j++ ) {
               suma1 = suma1 + arr[j];
            }
            if(suma==suma1){
                index = i;
                break;
            }
        }
        System.out.println(index);
    }
}
