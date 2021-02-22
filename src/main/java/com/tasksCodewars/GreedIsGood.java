package com.tasksCodewars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GreedIsGood {
// Three 1's => 1000 points
// Three 6's =>  600 points
// Three 5's =>  500 points
// Three 4's =>  400 points
// Three 3's =>  300 points
// Three 2's =>  200 points
// One   1   =>  100 points
// One   5   =>   50 point
    public static void main(String[] args) {

        int[] ints = {2,4,4,5,4};

        List<Integer> integers = sortedDistingIntList(ints);
//        integers.forEach(System.out::print);
        System.out.println("\n");

        int bingoNum = findBingoNum(ints);
        System.out.println(bingoNum);


        int greed = greedy(ints);
        System.out.println(greed);


    }

    /**
     * Calculate final score
     * @param ints
     * @return int
     */
    public static int greedy(int[] ints) {
        List<Integer> integers = sortedDistingIntList(ints);
        int result = 0;

        int bingoNum = findBingoNum(ints);
        if(bingoNum==1){
            result += 1000;
        }
        else {
            result += bingoNum*100;
        }

        for ( int i = 0; i < integers.size(); i++ ) {
            if(integers.get(i) == 1){
                result += 100;
            }
            else if(integers.get(i)==5){
                result += 50;
            }
        }

        if(bingoNum==1){
            result = result-3*(bingoNum*100);
        }
        if(bingoNum==5){
            result = result-3*(bingoNum*10);
        }
        return result;
    }

    /**
     * Sort array and save only distinct elements
     * @param dice
     * @return List
     */
    public static List<Integer> sortedDistingIntList(int[] dice){
        return Arrays.stream(dice).sorted().boxed().collect(Collectors.toList());
    }
    /**
     * Find element that apears 3 times in array
     * @param dice
     * @return int
     */
    public static int findBingoNum(int[] dice){
        Arrays.sort(dice);
        int checker = dice[0];
        int count = 0;
        int bingoNum = 0;
        for ( int i = 1; i < dice.length; i++ ) {
            if(checker==dice[i]){
                count++;
                if(count==2){
                    bingoNum = dice[i];
                    count=0;
                }
            }
            else if(checker!=dice[i]){
                count=0;
                checker = dice[i];
            }
        }
        return bingoNum;
    }
}
